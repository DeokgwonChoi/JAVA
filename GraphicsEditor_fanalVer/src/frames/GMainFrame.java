package frames;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import menus.GMenuBar;


public class GMainFrame extends JFrame {

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GPanel drawingPanel;
	
	public GMainFrame() {
		super();
		
		// set x,y position
		setLocation(100, 100);
		// set width, height
		setSize(400, 600);
		// close ��ư ������ ���α׷� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// �ʱ⿡ ���̱�
		
		// new components
		menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		toolBar = new GToolBar();
		this.add(BorderLayout.NORTH, toolBar);
		drawingPanel = new GPanel();
		//drawingPanel.setBackground(Color.white);
		this.add(drawingPanel);
		
		// initialize components functions
		setVisible(true);
	}

	public void initialize() {
		// set association
		//toolBar.setPanel(drawingPanel);
		//menuBar.setPanel(drawingPanel);
		
		//initialize associated functions
		toolBar.initialize(drawingPanel);
		menuBar.initialize(drawingPanel);
		drawingPanel.initialize();
		
	}
	
	
}
