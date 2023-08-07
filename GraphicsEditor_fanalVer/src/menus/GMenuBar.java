package menus;

import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import frames.GPanel;
import main.GConstants;




public class GMenuBar extends JMenuBar implements Runnable {

	private static final long serialVersionUID = 1L;
	private GFileMenu fileMenu;
	private GEditMenu editMenu;
	private GGraphicsMenu colorMenu;
	private Thread t;
	JLabel timeLabel;
	private GPanel panel;
	
	public GMenuBar() {
		super();
		
		fileMenu = new GFileMenu(GConstants.EMenus.File.getName());
		this.fileMenu.setToolTipText("���ϼ����� �մϴ�.");
		this.add(fileMenu);
		editMenu = new GEditMenu(GConstants.EMenus.Edit.getName());
		this.editMenu.setToolTipText("���ϼ����� �մϴ�.");
		this.add(editMenu);
		colorMenu = new GGraphicsMenu(GConstants.EMenus.Color.getName());
		this.colorMenu.setToolTipText("���ϼ����� �մϴ�.");
		this.add(colorMenu);
		this.timeLabel = new JLabel();
		this.add(timeLabel);
		this.t = new Thread(this);
		t.start();
	}
	public void run() {
		// TODO �ڵ� ������ �޼ҵ� ����
		while (true) {
			show();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}
		}
	}

	public void show() {

		Calendar calendar = Calendar.getInstance();
		int h = calendar.get(Calendar.HOUR);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		String time =  + h + ":" + m + ":" + s;
		timeLabel.setText(time);
	

	}
	public void setPanel(GPanel panel) {
		this.panel = panel;
		colorMenu.setPanel(this.panel);
	}

	public void initialize(GPanel panel) {
		fileMenu.initialize(panel);
		editMenu.initialize(panel);
		colorMenu.initialize(panel);
	}
}
