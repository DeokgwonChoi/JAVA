package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;


public class GRectangle extends GShapeTool {
	
	private Rectangle rectangle;
	
	public GRectangle() {
		super();
		rectangle = new Rectangle();
		this.shape = rectangle;
	}

	@Override
	public Point getOrigin() {
		return new Point(rectangle.x, rectangle.y);
	}

	@Override
	public void setOrigin(int x, int y) {
		rectangle.x = x;
		rectangle.y = y;
	}

	@Override
	public void addPoint(int x, int y) {
		rectangle.width = x - rectangle.x;
		rectangle.height = y - rectangle.y;
	}

	@Override
	public void setLastPoint(int x, int y) {
		rectangle.width = x - rectangle.x;
		rectangle.height = y - rectangle.y;
	}

	@Override
	public GRectangle clone() {
		return new GRectangle();
	}
	
	@Override
	public GShapeTool deepcopy() {
		AffineTransform af = new AffineTransform();
		Shape copyshape = af.createTransformedShape(this.shape);
		GRectangle newshape = new GRectangle();
		newshape.setShape(copyshape);
		newshape.setGraphicAttribute(this);
		return newshape;
	}
}
