package frames;

import java.util.Vector;

import shapes.GShapeTool;

public class GClipBoard {
	
	private Vector<GShapeTool> clipBaord;
	
	public GClipBoard() {
		clipBaord = new Vector<GShapeTool>();
	}
	
	public void add(GShapeTool shape) {
		
	}
	
	public Vector<GShapeTool> paste() {
		return (Vector<GShapeTool>) clipBaord.clone();
	}
	
	public void copy(Vector<GShapeTool> shapes) {
		clipBaord.clear(); // 클립보드 비워주기
		GShapeTool shape;
		for(int i=shapes.size(); i>0; i--) {
			shape = shapes.get(i-1);
			if(shape.isSelected()) {
				clipBaord.add(shape.deepcopy());
			}
		}
	}
	
	public void cut(Vector<GShapeTool> shapes) {
		clipBaord.clear(); // 클립보드 비워주기
		GShapeTool shape;
		for(int i=shapes.size(); i>0; i--) {
			shape = shapes.get(i-1);
			if(shape.isSelected()) {
				clipBaord.add(shape.deepcopy());
				shapes.remove(shape); // for-each를 사용하면 java.util.ConcurrentModificationException 발생해서 일반 for문으로 수정
			}
		}
	}
}
