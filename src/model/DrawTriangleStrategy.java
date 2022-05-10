package model;

import model.interfaces.DrawStrategy;

import java.awt.*;
import java.util.EnumMap;

public class DrawTriangleStrategy implements DrawStrategy {
    public static void drawStrategy(Shape shape) {
        int [] xValues = new int[3];
        int [] yValues = new int[3];

        xValues[0] = shape.getXMin();
        xValues[1] = shape.getTriangleMidPoint();
        xValues[2] = shape.getXMax();

        yValues[0] = shape.getYMin();
        yValues[1]= shape.getYMax();
        yValues[2]= shape.getYMax();

        Graphics2D graphics = shape.getPaintCanvas().getGraphics2D();

        EnumMap<ShapeColor,Color> colorMap = new EnumMap<>(ShapeColor.class);
        EnumMap<ShapeColor,Color> colorMap2 = new EnumMap<>(ShapeColor.class);

        GenerateMapSingleton generate = GenerateMapSingleton.getInstance(colorMap);
        /*
        generateMap generate = new generateMap(colorMap);
        generateMap generate2 = new generateMap(colorMap2); */

        colorMap=generate.getEnumMap();
        colorMap2=generate.getEnumMap();
        Color primary = colorMap.get(shape.getPrimaryColor());
        Color secondary = colorMap2.get(shape.getSecondaryColor());
        graphics.setColor(primary);
        //System.out.println(shape.getWidth());
        //graphics.fillRect(shape.getXMin(),  shape.getYMin(), shape.getWidth(), shape.getHeight());

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("filled_in"))
            graphics.fillPolygon(xValues, yValues, 3);

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("outline"))
            graphics.drawPolygon(xValues, yValues, 3);
        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("outline_and_filled_in")) {
            graphics.setColor(primary);
            graphics.fillPolygon(xValues, yValues, 3);
            graphics.setColor(secondary);
            graphics.drawPolygon(xValues, yValues, 3);
        }
        }
    }


