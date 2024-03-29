package frames;

import java.util.Vector;

import shapes.GShapeTool;

public class GStack {

	private Vector<Vector<GShapeTool>> data;
	private int num;
	
	public GStack() {
		data = new Vector<Vector<GShapeTool>>();
		num = 0;
	}
	
	public void push(Vector<GShapeTool> shapes) {
		//System.out.println("         현도형수 : " + shapes.size() + " / 스택수 : " + num + " / 스택갯수 :  " + data.size());
		Vector<GShapeTool> temp = new Vector<GShapeTool>();
		
		if(num < data.size()){
			//System.out.println(num +"번부터 " + data.size() + "번 까지 초기화");
			for(int i=data.size()-1; num <= i; i--) {
				//System.out.println(i + "번초기화");
				data.remove(i);
			}
		}
		
		for(int i=0; i<shapes.size(); i++) {
			temp.add(shapes.get(i).deepcopy());
		}
		data.add(temp);
		num++;
		//System.out.println("넣기]현도형수 : " + shapes.size() + " / 스택수 : " + num + " / 스택갯수 :  " + data.size());
	}
	
	/**
	 * 
	 * @param param = +1 : redo
	 * 		  param = -1 : undo
	 * @return
	 */
	public Vector<GShapeTool> pop(int param) {
		if(num <= data.size()) {
			num = num + param;
		}
		//System.out.print((param > 0 ? "리두" : "언두") + (num) +"번째] ");
		//System.out.println("스택수 : " + num + " / 스택갯수 : " + data.size());
		Vector<GShapeTool> temp = null;
		
		if(num > 0 && num <= data.size()) {
			temp = data.get(num-1); // 
		} else if(num == data.size()) {
			temp = data.lastElement();
		}
				
		//System.out.println("결과  스택수 : " + num + " / 스택갯수 : " + data.size());
		return temp;
	}
	
	public int getStacksize() {
		return num;
	}
	
	public void clear() {
		data.clear();
	}
}
