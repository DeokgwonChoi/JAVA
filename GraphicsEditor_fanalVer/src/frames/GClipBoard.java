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
		clipBaord.clear(); // Ŭ������ ����ֱ�
		GShapeTool shape;
		for(int i=shapes.size(); i>0; i--) {
			shape = shapes.get(i-1);
			if(shape.isSelected()) {
				clipBaord.add(shape.deepcopy());
			}
		}
	}
	
	public void cut(Vector<GShapeTool> shapes) {
		clipBaord.clear(); // Ŭ������ ����ֱ�
		GShapeTool shape;
		for(int i=shapes.size(); i>0; i--) {
			shape = shapes.get(i-1);
			if(shape.isSelected()) {
				clipBaord.add(shape.deepcopy());
				shapes.remove(shape); // for-each�� ����ϸ� java.util.ConcurrentModificationException �߻��ؼ� �Ϲ� for������ ����
			}
		}
	}
}
