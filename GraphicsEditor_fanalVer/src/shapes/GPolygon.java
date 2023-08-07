package shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import main.GConstants;

public class GPolygon extends GShapeTool {

	private Polygon polygon;
	
	public GPolygon() {
		super();
		shapeType = GConstants.EShapeType.NP;
		polygon = new Polygon();
		this.shape = polygon;
	}
	
	@Override
	public Point getOrigin() {
		return null;
	}

	@Override
	public void setOrigin(int x, int y) {
		polygon.addPoint(x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		polygon.addPoint(x, y);
	}

	@Override
	public void setLastPoint(int x, int y) {
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}

	@Override
	public GPolygon clone() {
		return new GPolygon();
	}

	@Override
	public GShapeTool deepcopy() {
		AffineTransform af = new AffineTransform();
		Shape copyshape = af.createTransformedShape(this.shape);
		GPolygon newshape = new GPolygon();
		newshape.setShape(copyshape);
		newshape.setGraphicAttribute(this);
		return newshape;
	}

}
