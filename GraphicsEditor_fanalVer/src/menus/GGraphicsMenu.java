package menus;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.GConstants;
import main.GConstants.EColorMenuItems;
import frames.GPanel;


public class GGraphicsMenu extends JMenu {
	
	private Vector<JMenuItem> menuItems;
	private ActionHandler handler;
	
	private GPanel panel;
	
	public GGraphicsMenu(String string) {
		super(string);
		
		menuItems = new Vector<JMenuItem>();
		handler = new ActionHandler();
		
		for(EColorMenuItems eMenuItem: EColorMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItem.setActionCommand(eMenuItem.name());
			menuItem.setAccelerator(KeyStroke.getKeyStroke (eMenuItem.getkey(),InputEvent.CTRL_MASK)); 
			menuItem.setToolTipText(eMenuItem.getHelp());
			menuItem.addActionListener(handler);
			menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	
	public void initialize(GPanel panel) {
		this.panel = panel;		
	}
	
	public void setPanel(GPanel panel) {
		this.panel = panel;
	}
	
	public void changeLineColor() {
		Color color = JColorChooser.showDialog(null, EColorMenuItems.lineColor.name(), null);
		if(color != null) {
			panel.setLineColor(color);
		}
	}
	
	public void changeFillColor() {
		Color color = JColorChooser.showDialog(null, GConstants.EColorMenuItems.fillColor.name(), null);
		if(color != null) {
			panel.setFillColor(color);
		}
	}
	
	private void clearLineColor() {
		panel.setLineColor(getForeground());
	}
	

	private void clearFillColor() {
		panel.setFillColor(getBackground());
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(GConstants.EColorMenuItems.lineColor.name())) {
				changeLineColor();
			} else if(e.getActionCommand().equals(GConstants.EColorMenuItems.fillColor.name())) {
				changeFillColor();
			} else if(e.getActionCommand().equals(GConstants.EColorMenuItems.clearLineColor.name())) {
				clearLineColor();
			} else if(e.getActionCommand().equals(GConstants.EColorMenuItems.clearFillColor.name())) {
				clearFillColor();
			}
		}
	}
}
