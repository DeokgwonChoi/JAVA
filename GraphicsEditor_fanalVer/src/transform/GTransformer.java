package transform;

import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShapeTool;

public abstract class GTransformer {

	protected GShapeTool shape;
	protected Vector<GShapeTool> shapelist;
	protected int oldX;
	protected int oldY;
	protected AffineTransform af;
	
	public GTransformer(GShapeTool shape) {
		this.shape = shape;
	}
	public abstract void init(int x, int y);
	public abstract void Transform(int x, int y);

}