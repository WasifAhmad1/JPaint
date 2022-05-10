package model;

import model.interfaces.OutlineSelectedStrategy;

import java.awt.*;

public class SelectRectangle implements OutlineSelectedStrategy {
    public static void outlineSelectStrategy (Shape shape) {
        Graphics2D graphics2d = shape.getPaintCanvas().getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(shape.getXMin() , shape.getYMin() , shape.getWidth() , shape.getHeight() );
    }
}
