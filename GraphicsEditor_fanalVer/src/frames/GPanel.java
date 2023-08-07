package frames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EPointerState;
import main.GConstants.EShapeType;
import shapes.GGroup;
import shapes.GSelect;
import shapes.GShapeTool;
import transform.GDrawer;
import transform.GMover;
import transform.GResizer;
import transform.GRotater;
import transform.GTransformer;


public class GPanel extends JPanel {
	
	// Attribute
	private enum EDrawingState {idle, TPDrawing, NPDrawing, moving, rotating, resizing};
	private EDrawingState drawingState;
	private Color lineColor, fillColor;
	
	// Components
	private MouseHandler handler;
	private Vector<GShapeTool> shapes;
	private GClipBoard clipboard;
	private GStack stack;
	private GGroup group;
	
	// Working Variables
	private GShapeTool drawingTool, currentShape, selectedShape;
	private GTransformer trans;
	private Stroke dashedStroke;
	private EPointerState pointerState;
	
	public GPanel() {
		super();
		// Attribute
		drawingState = EDrawingState.idle;
		// Components
		handler = new MouseHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
		shapes = new Vector<GShapeTool>();
		clipboard = new GClipBoard();
		stack = new GStack();
		group = new GGroup();
		// Working Variables
		float dashs[] = {4};
		dashedStroke = new BasicStroke(
				1, 
				BasicStroke.CAP_ROUND, 
				BasicStroke.JOIN_ROUND, 
				10, 
				dashs, 
				0);
		lineColor = this.getForeground();
		fillColor = this.getBackground();
		//lineColor = Color.BLACK;
		//fillColor = Color.WHITE;
	}
	
	public void initialize() {	}
	
	// sets and gets
	public GShapeTool getDrawingTool() {return drawingTool;}
	public void setDrawingTool(GShapeTool drawingTool) {this.drawingTool = drawingTool;}
	public void setLineColor(Color lineColor) {this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) {this.fillColor = fillColor;}
	public Object getShapes() {	return this.shapes; }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(GShapeTool shape: shapes) {
			shape.draw((Graphics2D) g);
		}
	}
	
	public void init(int x, int y) {
		if(drawingState != EDrawingState.idle) {
			switch (drawingState) {
				case TPDrawing:
				case NPDrawing:
					try {
						currentShape = drawingTool.getClass().newInstance();
						currentShape.setLineColor(this.lineColor);
						currentShape.setFillColor(this.fillColor);
						
						trans = new GDrawer(currentShape);
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					break;
				case  moving:
					trans = new GMover(selectedShape);
					break;
				case resizing:
					trans = new GResizer(selectedShape);
					break;
				case rotating:
					trans = new GRotater(selectedShape);
					break;
			}
			
			trans.init(x, y);
			if(drawingState == EDrawingState.NPDrawing) {
				nPAddPoint(x, y); // n-1 점추가
			} else {
				trans.Transform(x, y);
			}
			Graphics g = this.getGraphics();
			currentShape.drawXOR((Graphics2D) g, this.getBackground());
		}
	}
	
	public void keep(int x, int y) {
		Graphics2D g = (Graphics2D)this.getGraphics();	
		
		if(drawingState == EDrawingState.TPDrawing || drawingState == EDrawingState.NPDrawing) {
			Stroke s = g.getStroke();
			g.setStroke(dashedStroke);
			currentShape.drawXOR(g, this.getBackground());
			trans.Transform(x, y);
			currentShape.drawXOR(g, this.getBackground());
			g.setStroke(s);
		} else {
			selectedShape.drawXOR(g, this.getBackground());
			if(drawingState == EDrawingState.resizing) {
				((GResizer)trans).Transform(x, y, pointerState);
			} else {
				trans.Transform(x, y);
			}	
			selectedShape.drawXOR(g, this.getBackground());
		}
	}
	
	public void finish(int x, int y) {
		if(!(currentShape instanceof GSelect)) {
			Graphics2D g = (Graphics2D)this.getGraphics();	
			
			if(drawingState == EDrawingState.NPDrawing || drawingState == EDrawingState.TPDrawing) {
				Stroke s = g.getStroke();
				g.setStroke(dashedStroke);
				currentShape.drawXOR(g, this.getBackground());
				g.setStroke(s);
				currentShape.draw(g);
				shapes.add(currentShape);
			}
		} else {	
			//System.out.println("그룹영역 : " + currentShape.getShape().getBounds());
			int i=0;
			for(GShapeTool shape : shapes) {
				if(shape.getShape().intersects(currentShape.getShape().getBounds())) {
					shape.setSelected(true); 
					i++;
				}
			}
		}
		stack.push(shapes); // redo, undo support
		trans = null; // for garbage collector
		repaint();
	}
	
	public void nPAddPoint(int x, int y) {
		currentShape.addPoint(x, y);
	}
	
	public EPointerState includes(int x, int y) {
		EPointerState pointerState = null;
		for(GShapeTool shape: shapes) {
			pointerState = shape.includes(x, y);
			if(pointerState != null) {
				selectedShape = shape;
				return pointerState;
			}
		}
		return pointerState;
	}
	
	public void changePointer(EPointerState pointerState) {
		if(pointerState != null) {
			switch (pointerState) {
				case NW: setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR)); break;
				case WW: setCursor(new Cursor(Cursor.W_RESIZE_CURSOR)); break;
				case SW: setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR)); break;
				case NN: setCursor(new Cursor(Cursor.N_RESIZE_CURSOR)); break;
				case SS: setCursor(new Cursor(Cursor.S_RESIZE_CURSOR)); break;
				case NE: setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR)); break;
				case EE: setCursor(new Cursor(Cursor.E_RESIZE_CURSOR)); break;
				case SE: setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR)); break;
				case RR: setCursor(new Cursor(Cursor.HAND_CURSOR)); break;
				case MM: setCursor(new Cursor(Cursor.MOVE_CURSOR)); break;
			}
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void clearSelection() {
		for(GShapeTool shape: shapes) {
			shape.setSelected(false);
		}
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				button1Pressed(e);
			}
		}
		
		public void button1Pressed(MouseEvent e) {
			if(drawingState == EDrawingState.idle) {
				pointerState = includes(e.getX(), e.getY());
				clearSelection(); // 도형이 아니면 셀레트를 날린다
				
				// if mouse is on a shape
				if(pointerState != null) {
					selectedShape.setSelected(true);
					// Transform 선택
					if(pointerState ==  EPointerState.MM) {
						drawingState = EDrawingState.moving;
					} else if(pointerState ==  EPointerState.RR) {
						drawingState = EDrawingState.rotating;
					} else {
						drawingState = EDrawingState.resizing;			
					}
					init(e.getX(), e.getY());
				} else { // if mouse is not on a shape
					if(drawingTool.getShapeType() == EShapeType.TP) {
						drawingState = EDrawingState.TPDrawing;
						init(e.getX(), e.getY());
					}
				}
				repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(drawingState != EDrawingState.idle && drawingState != EDrawingState.NPDrawing) {
				keep(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(drawingState != EDrawingState.idle && drawingState != EDrawingState.NPDrawing) {
				finish(e.getX(), e.getY());
				drawingState = EDrawingState.idle;
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getClickCount() == 1) {
					button1Clicked(e);
				} else if(e.getClickCount() == 2) {
					button1DblClicked(e);
				}
			}
		}
		
		public void button1Clicked(MouseEvent e) {
			if(drawingState == EDrawingState.idle) {
				pointerState = includes(e.getX(), e.getY());
				if(pointerState == null) {
					if(drawingTool.getShapeType() == EShapeType.NP) {
						drawingState = EDrawingState.NPDrawing;
						init(e.getX(), e.getY());
					}
				} else {
					selectedShape.setSelected(true);
				}
			} else if(drawingState == EDrawingState.NPDrawing) {
				nPAddPoint(e.getX(), e.getY());
			}
		}
		
		public void button1DblClicked(MouseEvent e) {
			if(drawingState == EDrawingState.NPDrawing) {
				finish(e.getX(), e.getY());
				drawingState = EDrawingState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if(drawingState == EDrawingState.idle) {
				pointerState = includes(e.getX(), e.getY());
				changePointer(pointerState);
			} else if(drawingState == EDrawingState.NPDrawing) {
				keep(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
	}

	public void setsetShapeList(Vector<GShapeTool> obj) {
		try {
			currentShape = drawingTool.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.shapes = obj;
		repaint();		
	}
	
	/* edit function */
	public void redo() {
		shapes.clear();
		Vector<GShapeTool> temp = stack.pop(1);
		if(temp != null) {
			for(int i=0; i<temp.size(); i++) {
				shapes.add(temp.get(i).deepcopy());
			}
		}
		repaint();
	}
	
	public void undo() {
		shapes.clear();
		Vector<GShapeTool> temp = stack.pop(-1);
		if(temp != null) {
			for(int i=0; i<temp.size(); i++) {
				shapes.add(temp.get(i).deepcopy());
			}
		}
		repaint();
	}
	
	public void cut() {
		clipboard.cut(shapes);
		stack.push(shapes);
		repaint();
	}
	
	public void copy() {
		clipboard.copy(shapes);
		stack.push(shapes);
	}
	
	public void paste() {

		for(GShapeTool shape : clipboard.paste()) {
			shapes.add(shape.deepcopy());
		}
		stack.push(shapes);
		repaint();
	}
	
	public void delete() {
		for(int i=shapes.size(); i>0; i--) {
			GShapeTool temp = shapes.get(i-1);
			if(temp.isSelected()) {
				temp.setSelected(false);
				shapes.remove(temp);
			}
		}
		stack.push(shapes);
		repaint();
	}
	
	public void group() {
		for(int i=shapes.size(); i>0; i--) {
			GShapeTool temp = shapes.get(i-1);
			if(temp.isSelected()) {
				temp.setSelected(false);
				group.addshape(temp.deepcopy());
				shapes.remove(temp);
			}
		}
		shapes.add(group);
		group.setSelected(true);
		stack.push(shapes);
		repaint();
	}
	
	public void ungroup() {
		Vector<GShapeTool> templist = new Vector<GShapeTool>();
		for(int i=shapes.size(); i>0; i--) {
			GShapeTool temp = shapes.get(i-1);
			if(temp instanceof GGroup && temp.isSelected()) {
				for(GShapeTool subshape : ((GGroup)temp).getSubShapes()) {
					subshape.setSelected(true);
					templist.add(subshape);
				}
				shapes.remove(temp);
			}
		}
		shapes.addAll(templist);
		stack.push(shapes);
		repaint();
	}
	
	public void nnew() {
		shapes = new Vector<GShapeTool>();
		stack = new GStack();
		repaint();
	}
	 
}
