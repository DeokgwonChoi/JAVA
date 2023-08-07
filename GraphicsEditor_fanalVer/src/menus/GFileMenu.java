package menus;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.sun.jdi.event.Event;

import shapes.GShapeTool;

import frames.GPanel;

import main.GConstants;
import main.GConstants.EFileMenuItems;

public class GFileMenu extends JMenu {
	
	private GPanel panel;
	private Vector<JMenuItem> menuItems;
	private ActionHandler handler; 
	
	public GFileMenu(String string) {
		super(string);
		
		menuItems = new Vector<JMenuItem>();
		handler = new ActionHandler();
		
		for(GConstants.EFileMenuItems eMenuItem: GConstants.EFileMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItem.setAccelerator(KeyStroke.getKeyStroke (eMenuItem.getkey(),InputEvent.CTRL_MASK)); 
			menuItem.setToolTipText(eMenuItem.getHelp());
			menuItem.addActionListener(handler);
			menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	
	private void nnew() {
		int reply=JOptionPane.showConfirmDialog(this.panel, "저장할까요?");
		if(reply==JOptionPane.YES_OPTION) {
			save();
			panel.nnew();
		}else if(reply==JOptionPane.NO_OPTION) {
			panel.nnew();
		}else if(reply==JOptionPane.CANCEL_OPTION) {
		}	
		
	}
	
	private void load() {
		JFileChooser fileDialog = new JFileChooser(new File("*.*"));
		fileDialog.showOpenDialog(null);
		File file = fileDialog.getSelectedFile();
		ObjectInputStream in = null;
		if(file != null) {
			try {
				in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
				Object obj = in.readObject();
				panel.setsetShapeList((Vector<GShapeTool>) obj);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(in != null) {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}


	private void save() {
		JFileChooser fileDialog = new JFileChooser(new File("*.*"));
		fileDialog.showSaveDialog(null);
		File file = fileDialog.getSelectedFile();
		ObjectOutputStream out = null;
		if(file != null) {
			try {
				out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
				out.writeObject(panel.getShapes());
			} catch(FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if(out != null) {
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	private void exit() {
		int reply=JOptionPane.showConfirmDialog(this.panel, "저장할까요?");
		if(reply==JOptionPane.YES_OPTION) {
			save();
			System.exit(0);
		}else if(reply==JOptionPane.NO_OPTION) {
			this.panel.initialize();
			System.exit(0);
		}else if(reply==JOptionPane.CANCEL_OPTION) {
		}			
	}
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if(action.equals(EFileMenuItems.nnew.getName())) {
				nnew();
			} else if(action.equals(EFileMenuItems.open.getName())) {
				load();
			} else if(action.equals(EFileMenuItems.save.getName())) {
				save();
			} else if(action.equals(EFileMenuItems.exit.getName())) {
				exit();
			}
		}
	}

	public void initialize(GPanel panel) {
		this.panel = panel;		
	}
}
