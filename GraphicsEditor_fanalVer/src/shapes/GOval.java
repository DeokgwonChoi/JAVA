package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class GOval extends GShapeTool {
	
	private Ellipse2D.Double ellipse;
	
	public GOval() {
		super();
		ellipse = new Ellipse2D.Double();
		this.shape = ellipse;
	}

	@Override
	public Point getOrigin() {
		return new Point((int)ellipse.x, (int)ellipse.y);
	}

	@Override
	public void setOrigin(int x, int y) {
		ellipse.x = x;
		ellipse.y = y;
	}

	@Override
	public void addPoint(int x, int y) {
		ellipse.width = x - ellipse.x;
		ellipse.height = y - ellipse.y;
	}

	@Override
	public void setLastPoint(int x, int y) {
		ellipse.width = x - ellipse.x;
		ellipse.height = y - ellipse.y;
	}

	@Override
	public GOval clone() {
		return new GOval();
	}
	
	@Override
	public GShapeTool deepcopy() {
		AffineTransform af = new AffineTransform();
		Shape copyshape = af.createTransformedShape(this.shape);
		GOval newshape = new GOval();
		newshape.setShape(copyshape);
		newshape.setGraphicAttribute(this);
		return newshape;
	}
}
