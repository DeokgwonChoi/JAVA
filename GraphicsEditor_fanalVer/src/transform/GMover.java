package transform;

import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GGroup;
import shapes.GShapeTool;

public class GMover extends GTransformer{
	
	public GMover(GShapeTool shape) {
		super(shape);
		if(shape instanceof GGroup) {
			shapelist = new Vector<GShapeTool>();
			for(GShapeTool subshape : ((GGroup) shape).getSubShapes()) {
				shapelist.add(subshape);
			}
		}
		af = new AffineTransform();
	}

	@Override
	public void init(int x, int y) {
		oldX = x;
		oldY = y;	
	}

	@Override
	public void Transform(int x, int y) {
		af.setToTranslation(x - oldX, y - oldY);
		if(shape instanceof GGroup) {
			GShapeTool temp;
			for(int i=0; i < shapelist.size(); i++) {
				temp = shapelist.get(i);
				temp.setShape(af.createTransformedShape(temp.getShape()));
			}
		}
		shape.setShape(af.createTransformedShape(shape.getShape()));
		shape.getAnchor().move(x - oldX, y - oldY);
		
		oldX = x;
		oldY = y;
	}
}