package model;

import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Shape {

    public DrawStrategy strategy;
    //private Shape shape;
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private int triangleMidPoint;
    private IApplicationState applicationState;
    private int height, width;
    private ShapeColor shapeColor;
    private ShapeType shapeType;
    private boolean isSelected = false;
    private ArrayList <String> shapeStates = new ArrayList<>();


    public Shape(PaintCanvasBase paintCanvas, Point start, Point end, IApplicationState applicationState) {
        this.paintCanvas =  paintCanvas;
        this.startPoint = start;
        this.endPoint = end;
        this.applicationState = applicationState;
        width=(endPoint.getX() - startPoint.getX());
        height=(endPoint.getY() - startPoint.getY());
        xMin = Math.min(startPoint.getX(), endPoint.getX());
        xMax = Math.max(startPoint.getX(), endPoint.getX());
        yMin = Math.min(startPoint.getY(), endPoint. getY());




    }
//I ended up implementing a large amount of setters and getters pertaining to the property of the shape.
//I will consider cleaning this section up as I learn more design patterns
    public void setShapeType(ShapeType shapeType) {
        this.shapeType=applicationState.getActiveShapeType();
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setxMin(int xMin) {
        this.xMin=xMin;
    }
    public void setSelected(boolean isSelected){
        this.isSelected=isSelected;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint=startPoint;
    }
    public void setEndPoint(Point endPoint) {
        this.endPoint=endPoint;
    }
    public void setyMin(int yMin) {
        this.yMin=yMin;
    }
    public void setyMax(int yMax) {
        this.yMax=yMax;
    }
    public void setxMax(int xMax) {
        this.xMax=xMax;
    }
    public void setTriangleMidPoint(int triangleMidPoint){
        this.triangleMidPoint=triangleMidPoint;
    }

    public Point getStartPoint(){ return startPoint; }

    public Point getEndPoint() { return endPoint; }

    public int getX() {return startPoint.getX();}
    public int getY() {return startPoint.getY();}
    public int getXMax(){ return xMax;}

    public int getXMin(){return xMin;}

    public int getYMax(){ return yMax;}

    public int getYMin(){return yMin;}

    public int getHeight(){ return height; }

    public int getWidth(){ return width; }
    public boolean getSelected() {return isSelected;}
    public int getTriangleMidPoint() {return  triangleMidPoint;}


    public PaintCanvasBase getPaintCanvas() {return paintCanvas;}
    public ShapeColor getShapeColor() {return shapeColor;}

    public ShapeColor getPrimaryColor() {return applicationState.getActivePrimaryColor();}
    public ShapeColor getSecondaryColor() {return applicationState.getActiveSecondaryColor();}
    public ShapeType getShapeType() {

        return shapeType;
    }
    public IApplicationState getApplicationState () {return applicationState;}
    public ArrayList getStates () {return shapeStates;}


    public void pattern() {
        MouseEvent e = null;
        Graphics2D graphics = paintCanvas.getGraphics2D();
        graphics.setColor(Color.GREEN);

        xMin = Math.min(startPoint.getX(), endPoint.getX());
        xMax = Math.max(startPoint.getX(), endPoint.getX());

        yMin = Math.min(startPoint.getY(), endPoint. getY());
        yMax = Math.max(startPoint. getY(), endPoint.getY());
        triangleMidPoint = (xMin) + ((xMax-xMin)/2);

        this.setShapeType(applicationState.getActiveShapeType());

        int width = (endPoint.getX() - startPoint.getX());
        int height = (endPoint.getY() - startPoint.getY());

        this.setxMin(xMin);
        this.setxMax(xMax);
        this.setyMin(yMin);
        this.setyMax(yMax);
        this.setHeight(height);
        this.setWidth(width);
        this.setTriangleMidPoint(triangleMidPoint);

        this.setSelected(isSelected=true);

        //graphics.fillOval(xMin, yMin, width, height);

//I implemented the strategy pattern here. The applicaton state will pass the shape type and based on this
// this shape type the program will run the appropriate shape strategy Within each draw strategy is methods pertaining to drawing outline only, filled in only, and outline as well as filled in.

        if(this.getShapeType().toString().equals("ELLIPSE")) {

            strategy=new DrawEllipseStrategy();
            DrawEllipseStrategy.drawStrategy(this);
        }

        if(this.getShapeType().toString().equals("TRIANGLE")) {

            strategy=new DrawTriangleStrategy();
            DrawTriangleStrategy.drawStrategy(this);
        }
        if(this.getShapeType().toString().equals("RECTANGLE")){

            strategy=new DrawRectangleStrategy();
            DrawRectangleStrategy.drawStrategy(this);
        }


        /*if(this.applicationState.getActiveShapeType().toString().equals("ELLIPSE")) {
            shapeStates.add("ELLIPSE");
            strategy=new drawEllipseStrategy();
            drawEllipseStrategy.drawStrategy(this);
        } */



    }


}

