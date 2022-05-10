package model;

import model.interfaces.DrawStrategy;

public class ShapeFactory {
    public DrawStrategy drawStrategy;


    public static void getStrategy(Shape shape) {
        if (shape.getShapeType().toString().equals("ELLIPSE")) {
            DrawEllipseStrategy.drawStrategy(shape);
        }
        if (shape.getShapeType().toString().equals("TRIANGLE")) {
            DrawTriangleStrategy.drawStrategy(shape);
        }
        if (shape.getShapeType().toString().equals("RECTANGLE")) {
            DrawRectangleStrategy.drawStrategy(shape);
        }



    }
}
