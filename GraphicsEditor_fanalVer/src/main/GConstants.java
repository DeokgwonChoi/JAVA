package main;
import shapes.*;


public class GConstants {

	public static enum EMenus {
		File("파일"), Edit("편집"), Color("색변경");
		private String name;
		private EMenus(String s) {this.name = s;}
		public String getName() {return this.name;}
	}
	
	public static enum EFileMenuItems {
		nnew("새로만들기",'N',"새로운 도화지를 생성합니다."),
		open("열기",'O',"저장된 파일을 불러옵니다."), 
		save("저장",'S',"내용을 저장합니다."), 
		exit("나가기",'E',"그림판을 종료합니다.");
		private String name,help;
		char key;
		private EFileMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EEditMenuItems {
		redo("다시실행",'F',"취소한 동작을 재실행합니다."),
		undo("되돌리기",'Z',"최근에 한 동작을 취소합니다."),
		copy("복사",'C',"해당 항목을 복사합니다."),
		cut("잘라내기",'X',"해당 항목을 복사한 후 잘라냅니다.."),
		paste("붙여넣기",'V',"복사된 항목을 붙여놓습니다."),
		delete("삭제",'Q',"해당 항목을 삭제합니다."),
		group("그룹",'G',"해당 항목들을 하나로 묶습니다."),
		ungroup("그룹해제",'H',"묶어진 항목들을 풀어냅니다.");
		private String name,help;
		char key;
		private EEditMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EColorMenuItems {
		lineColor("선색",'I',"도형의 선색을 변경합니다."),
		fillColor("면색",'U',"도형의 해당 색으로 채웁니다."), 
		clearLineColor("선색 초기화",'L',"선색을 검정색으로 변경합니다."),
		clearFillColor("면색 초기화",'P',"도형의 색을 하얀색으로 변경합니다.");
		private String name,help;
		char key;
		private EColorMenuItems(String n, char ck,String h) {this.name = n; this.key =ck; this.help = h;}
		public String getName() {return this.name;}
		public char getkey() {return this.key;}
		public String getHelp() {return this.help;}
	}
	
	public static enum EShapeTool {
		group("그룹", GSelect.class.getName(),"./image/pen.gif", "./image/penSLT.gif"),
		rectangle("사각형", GRectangle.class.getName(),"./image/Rect.png", "./image/PRect.png"),
		ellipse("원", GOval.class.getName(),"./image/Oval.png", "./image/POval.png"),
		line("선", GLine.class.getName(),"./image/Line.png", "./image/PLine.png"),
		polygon("다각형", GPolygon.class.getName(),"./image/Poly.png", "./image/PPoly.png");
		
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
