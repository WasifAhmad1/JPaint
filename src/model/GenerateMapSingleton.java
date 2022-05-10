package model;

import java.awt.*;
import java.util.EnumMap;

public class GenerateMapSingleton {

    private static GenerateMapSingleton instance;
    public static EnumMap<ShapeColor, Color> colorMap;
    public ShapeColor shapeColor;

    private GenerateMapSingleton(EnumMap<ShapeColor, Color> colorMap) {

        this.colorMap=colorMap;
        colorMap.put(ShapeColor.BLACK, Color.BLACK);
        colorMap.put(ShapeColor.BLUE, Color.BLUE);
        colorMap.put(ShapeColor.CYAN, Color.CYAN);
        colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorMap.put(ShapeColor.GRAY, Color.GRAY);
        colorMap.put(ShapeColor.GREEN, Color.GREEN);
        colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
        colorMap.put(ShapeColor.PINK, Color.PINK);
        colorMap.put(ShapeColor.RED, Color.RED);
        colorMap.put(ShapeColor.WHITE, Color.WHITE);
        colorMap.put(ShapeColor.YELLOW, Color.YELLOW);

    }


    public EnumMap getEnumMap () { return colorMap;}

    public static GenerateMapSingleton getInstance(EnumMap<ShapeColor, Color> colorMap) {
        instance= new GenerateMapSingleton(colorMap);
        return instance;
    }
}
