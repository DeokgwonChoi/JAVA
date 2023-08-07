package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import main.GConstants;

public class GGroup extends GRectangle {
	
	private Vector<GShapeTool> shapes;
	private float dashs[] = {4};
	private BasicStroke dashedStroke;
	
	public GGroup() {
		super();
		shapeType = GConstants.EShapeType.GR;
		shapes = new Vector<GShapeTool>();
		dashedStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashs, 0);
	}
	
	public Vector<GShapeTool> getSubShapes() {
		return shapes;
	}
	 
	public void draw(Graphics2D g) {
		for(GShapeTool shape: shapes) {
			shape.draw(g);
		}
		if(this.isSelected()) {
			Stroke s = g.getStroke();
			g.setStroke(dashedStroke);
			g.draw(shape);
			g.setStroke(s);
			if(selected) {
				anchor.draw(g);
			}
		}
	}
	
	
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected) {
			anchor = new GAnchor(this.shape.getBounds());
		} else {
			anchor = null;
		}
	}
	
	public void addshape(GShapeTool subshape) {
		shapes.add(subshape);
		
		if(shapes.size() == 1) {
			this.shape = subshape.getShape().getBounds();
		} else {
			this.shape = this.shape.getBounds().createUnion(subshape.getShape().getBounds());
		}
		//System.out.println(this.shape.getBounds());
	}
	
	/* 임시함수 */
	public int getsize() {
		return shapes.size();
	}
	
	@Override
	public GShapeTool deepcopy() {
		AffineTransform af = new AffineTransform();
		Shape copyshape = af.createTransformedShape(this.shape);
		GGroup newshape = new GGroup();
		newshape.setShape(copyshape);
		newshape.setGraphicAttribute(this);
		return newshape;
	}
}
