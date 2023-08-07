package main;
import shapes.*;


public class GConstants {

	public static enum EMenus {
		File("����"), Edit("����"), Color("������");
		private String name;
		private EMenus(String s) {this.name = s;}
		public String getName() {return this.name;}
	}
	
	public static enum EFileMenuItems {
		nnew("���θ����",'N',"���ο� ��ȭ���� �����մϴ�."),
		open("����",'O',"����� ������ �ҷ��ɴϴ�."), 
		save("����",'S',"������ �����մϴ�."), 
		exit("������",'E',"�׸����� �����մϴ�.");
		private String name,help;
		char key;
		private EFileMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EEditMenuItems {
		redo("�ٽý���",'F',"����� ������ ������մϴ�."),
		undo("�ǵ�����",'Z',"�ֱٿ� �� ������ ����մϴ�."),
		copy("����",'C',"�ش� �׸��� �����մϴ�."),
		cut("�߶󳻱�",'X',"�ش� �׸��� ������ �� �߶���ϴ�.."),
		paste("�ٿ��ֱ�",'V',"����� �׸��� �ٿ������ϴ�."),
		delete("����",'Q',"�ش� �׸��� �����մϴ�."),
		group("�׷�",'G',"�ش� �׸���� �ϳ��� �����ϴ�."),
		ungroup("�׷�����",'H',"������ �׸���� Ǯ����ϴ�.");
		private String name,help;
		char key;
		private EEditMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EColorMenuItems {
		lineColor("����",'I',"������ ������ �����մϴ�."),
		fillColor("���",'U',"������ �ش� ������ ä��ϴ�."), 
		clearLineColor("���� �ʱ�ȭ",'L',"������ ���������� �����մϴ�."),
		clearFillColor("��� �ʱ�ȭ",'P',"������ ���� �Ͼ������ �����մϴ�.");
		private String name,help;
		char key;
		private EColorMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EShapeTool {
		group("�׷�", GSelect.class.getName(),"./image/pen.gif", "./image/penSLT.gif"),
		rectangle("�簢��", GRectangle.class.getName(),"./image/Rect.png", "./image/PRect.png"),
		ellipse("��", GOval.class.getName(),"./image/Oval.png", "./image/POval.png"),
		line("��", GLine.class.getName(),"./image/Line.png", "./image/PLine.png"),
		polygon("�ٰ���", GPolygon.class.getName(),"./image/Poly.png", "./image/PPoly.png");
		
		private String label;
		private String className;
		private String text;
		private String clickText;
		private EShapeTool(String label, String className, String text, String clickText) {
			this.label = label;
			this.className = className;
			this.text = text;
			this.clickText = clickText;
		}
		public String getLabel() {return this.label;}
		public String getClassName() {return className;}
		public String getText() {
			return this.text;
		}
		public  String  getClickText() {
			return this.clickText;
		}
	}
	
	public static enum EShapeType {
		TP, NP, GR
	}
	
	public static enum EPointerState {
		NW, WW, SW, NN, SS, NE, EE, SE, RR, MM
	}
	
}
