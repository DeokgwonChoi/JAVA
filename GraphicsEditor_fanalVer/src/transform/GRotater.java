package transform;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import shapes.GGroup;
import shapes.GShapeTool;

public class GRotater extends GTransformer {
	protected Point2D.Double ROrigin;
	protected double theta;

	public GRotater(GShapeTool shape) {
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
		// ȸ���� ������ �߽��� ���մϴ�
		ROrigin = new Point2D.Double(shape.getShape().getBounds().getCenterX(), shape.getShape().getBounds().getCenterY());
		//  ���콺 �����Ϳ� �߽����� �̷�� ���� ���մϴ�
		theta = Math.atan2(ROrigin.y - y, x - ROrigin.x);
	}

	@Override
	public void Transform(int x, int y) {
		// ó�� ���� ������ �̵��� ��(���콺 �����ͷ� ������ ����)�� ���մϴ�
		double theta2 = theta - Math.atan2(ROrigin.y - y, x - ROrigin.x);
		
		// AffineTrasform���� ȸ����ȯ��Ŵ
		af.setToRotation(theta2, ROrigin.x, ROrigin.y);
		//shape.getAnchor().rotate(theta2, ROrigin);
		shape.getAnchor().resize(shape.getShape().getBounds());
		
		if(shape instanceof GGroup) { // �׷��̸� ���� ���Һ���
			GShapeTool temp;
			for(int i=0; i < shapelist.size(); i++) {
				temp = shapelist.get(i);
				temp.setShape(af.createTransformedShape(temp.getShape()));
				shape.setShape(shape.getShape().getBounds().createUnion(temp.getShape().getBounds()));
			}
		} else {
			shape.setShape(af.createTransformedShape(shape.getShape())); // �ڽ� ����
		}
		
		//shape.setShape(af.createTransformedShape(shape.getShape())); // �ڽ� ����
		theta = Math.atan2(ROrigin.y - y, x - ROrigin.x); // �̵��� �� ����
	}

}
