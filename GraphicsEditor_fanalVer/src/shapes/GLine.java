package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class GLine extends GShapeTool {
	
	private Line2D.Double line;
	
	public GLine() {
		super();
		line = new Line2D.Double(){
			@Override
			public boolean contains(double x, double y){
				return this.intersects(x, y, 1, 1);
			}
		};
		this.shape = line;
	}

	@Override
	public Point getOrigin() { return null; }

	@Override
	public void setOrigin(int x, int y) {
		line.x1 = x;
		line.y1 = y;
	}

	@Override
	public void addPoint(int x, int y) {
		line.x2 = x;
		line.y2 = y;
	}

	@Override
	public void setLastPoint(int x, int y) {
		line.x2 = x;
		line.y2 = y;
	}

	@Override
	public GLine clone() { return new GLine(); }

	@Override
	public GShapeTool deepcopy() {
		AffineTransform af = new AffineTransform();
		Shape copyshape = af.createTransformedShape(this.shape);
		GLine newshape = new GLine();
		newshape.setShape(copyshape);
		newshape.setGraphicAttribute(this);
		return newshape;
	}

}