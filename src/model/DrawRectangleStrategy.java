package model;

import model.interfaces.DrawStrategy;

import java.awt.*;
import java.util.EnumMap;

public class DrawRectangleStrategy implements DrawStrategy {
    public static void drawStrategy(Shape shape){
        int width = shape.getWidth();
        int height = shape.getHeight();
        int xMin = shape.getXMin();
        int yMin = shape.getYMin();

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


        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("filled_in"))
            graphics.fillRect(xMin, yMin, width, height);

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("outline"))
            graphics.drawRect(xMin, yMin, width, height);

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("outline_and_filled_in")) {
            graphics.setColor(primary);
            graphics.fillRect(xMin, yMin, width, height);
            graphics.setColor(secondary);
            graphics.drawRect(xMin, yMin, width, height);
        }

    }
}

/*public class drawRectangleStrategy {
    public static void drawStrategy(Shape shape) {


        int width = shape.getWidth();
        int height = shape.getHeight();
        int xMin = shape.getXMin();
        int yMin = shape.getYMin();

        Graphics2D graphics = shape.getPaintCanvas().getGraphics2D();
        EnumMap<ShapeColor,Color> colorMap = new EnumMap<>(ShapeColor.class);

        generateMap generate = new generateMap(colorMap);
        colorMap=generate.getEnumMap();
        Color primary = colorMap.get(shape.getPrimaryColor());
        graphics.setColor(primary);

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("filled_in"))
            graphics.fillRect(xMin, yMin, width, height);

        if(shape.getApplicationState().getActiveShapeShadingType().toString().equalsIgnoreCase("outline"))
            graphics.drawRect(xMin, yMin, width, height);



    }

} */
