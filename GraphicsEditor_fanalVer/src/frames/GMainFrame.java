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
		// close 버튼 누르면 프로그램 끝내기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 초기에 보이기
		
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
