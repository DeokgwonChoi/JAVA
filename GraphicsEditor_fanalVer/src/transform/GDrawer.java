package transform;

import shapes.GShapeTool;


public class GDrawer extends GTransformer {

	public GDrawer(GShapeTool shape) {
		super(shape);
	}

	@Override
	public void init(int x, int y) {
		shape.setOrigin(x, y);
	}
	
	@Override
	public void Transform(int x, int y) {
		shape.setLastPoint(x, y);
	}
	
}
