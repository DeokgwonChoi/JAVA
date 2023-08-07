package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants;
import main.GConstants.EShapeTool;

import shapes.GShapeTool;



public class GToolBar extends JToolBar {

	// Attribute
	
	// Components
	private ButtonGroup buttonGroup;
	private Vector<JRadioButton> buttons;
	private ActionHandler handler;
	
	// Association
	private GPanel panel;
	
	public GToolBar() {
		super();
		
		buttons = new Vector<JRadioButton>();
		buttonGroup = new ButtonGroup();
		
		handler = new ActionHandler();
		
		for(GConstants.EShapeTool eShapeTool: GConstants.EShapeTool.values()) {
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eShapeTool.getText()));
			button.setSelectedIcon(new ImageIcon(eShapeTool.getClickText()));
			button.setActionCommand(eShapeTool.getClassName());
			button.addActionListener(handler);
			buttons.add(button);
			buttonGroup.add(button);

			this.add(button);
		}	
	}
	
	public void initialize(GPanel panel) {
		this.panel = panel;
		buttons.get(EShapeTool.rectangle.ordinal()).doClick();
	}	
	
	public GPanel getPanel() {
		return panel;
	}

	public void setPanel(GPanel panel) {
		this.panel = panel;
	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				panel.setDrawingTool((GShapeTool)Class.forName(e.getActionCommand()).newInstance());
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	
}
