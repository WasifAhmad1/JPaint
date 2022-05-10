package model;

import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import model.interfaces.OutlineSelectedStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class SelectedShapeList {

    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    public ArrayList<Shape> selectedShapeList = new ArrayList<>();
    ArrayList<Shape> temp = new ArrayList<>();
    public OutlineSelectedStrategy strategy;
    public DrawStrategy drawStrategy;
    private int deltaX;
    private int x;
    private int y;
    private int deltaY;


    public SelectedShapeList() {
        this.selectedShapeList = selectedShapeList;


    }


    public void add(Shape shape) {
        selectedShapeList.add(shape);

    }

    public ArrayList getSelectedList() {
        return selectedShapeList;

    }
    public void setDeltaY(int deltaY) {this.deltaY=deltaY;}
    public void setDeltaX(int deltaX) {this.deltaX=deltaX;}
    public int getDeltaX() {return deltaX;}
    public int getDeltaY() {return deltaY;}

    public void moveShapes(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, MasterShapeList masterShapeList, IApplicationState applicationState) {
       //This code works but looks sloppy. I will refactor it to look cleaner.
        deltaX = endPoint.getX() - startPoint.getX();
        this.setDeltaX(deltaX);
        //System.out.println(this.getDeltaX());
        x = startPoint.getX();
        y = startPoint.getY();
        deltaY = endPoint.getY() - startPoint.getY();
        this.setDeltaY(deltaY);

        ArrayList<Shape> shapesToMove = new ArrayList<>();

        for (Shape shape : selectedShapeList) {
            //if (x >= shape.getX() && x < shape.getX() + shape.getWidth() && y > shape.getY() && y < shape.getY() + shape.getHeight()) {
            Point startPoint2 = new Point(shape.getX() + deltaX, shape.getY() + deltaY);
            Point endPoint2 = new Point(shape.getEndPoint().getX() + deltaX, shape.getEndPoint().getY() + deltaY);
                //Shape shape2 = new Shape(paintCanvas, startTemp, endTemp, applicationState);
                //shapesToMove.add(shape2);
            int xMin = Math.min(startPoint2.getX(), endPoint2.getX());
            int xMax = Math.max(startPoint2.getX(), endPoint2.getX());

            int yMin = Math.min(startPoint2.getY(), endPoint2. getY());
            int yMax = Math.max(startPoint2. getY(), endPoint2.getY());
            int triangleMidPoint = (xMin) + ((xMax-xMin)/2);

            int width = (endPoint2.getX() - startPoint2.getX());
            int height = (endPoint2.getY() - startPoint2.getY());
            shape.setStartPoint(startPoint2);
            shape.setEndPoint(endPoint2);
            shape.setxMin(xMin);
            shape.setxMax(xMax);
            shape.setyMin(yMin);
            shape.setyMax(yMax);
            shape.setHeight(height);
            shape.setWidth(width);
            shape.setTriangleMidPoint(triangleMidPoint);

            //shapesToMove.add(shape);


               /* if(masterShapeList.shapeList.contains(shape)) {
                    masterShapeList.shapeList.remove(shape);

                } */

                //System.out.println(shape.getX() + " " + startTemp.getX());
           // }
        }


        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

//Utilize the strategy pattern to drawn outline around selected shapes that are being moved around

        for(Shape shape: selectedShapeList) {

            //shape.pattern();
            if(shape.getShapeType().toString().equals("ELLIPSE")) {
                drawStrategy=new DrawEllipseStrategy();
                DrawEllipseStrategy.drawStrategy(shape);
                strategy = new SelectEllipse();
                SelectEllipse.outlineSelectStrategy(shape);
            }
            if(shape.getShapeType().toString().equals("RECTANGLE")) {
                drawStrategy=new DrawRectangleStrategy();
                DrawRectangleStrategy.drawStrategy(shape);
                strategy = new SelectRectangle();
                SelectRectangle.outlineSelectStrategy(shape);
            }
            if(shape.getShapeType().toString().equals("TRIANGLE")) {
                drawStrategy=new DrawTriangleStrategy();
                DrawTriangleStrategy.drawStrategy(shape);

                strategy= new SelectTriangle();
                SelectTriangle.outlineSelectStrategy(shape);
            }
        }
         for(Shape shape : masterShapeList.shapeList) {
             if(shape.getShapeType().toString().equals("ELLIPSE")) {
                 drawStrategy=new DrawEllipseStrategy();
                 DrawEllipseStrategy.drawStrategy(shape);
             }
             if(shape.getShapeType().toString().equals("TRIANGLE")) {

                 drawStrategy=new DrawTriangleStrategy();
                 DrawTriangleStrategy.drawStrategy(shape);
             }
             if(shape.getShapeType().toString().equals("RECTANGLE")){

                 drawStrategy=new DrawRectangleStrategy();
                 DrawRectangleStrategy.drawStrategy(shape);
             }
        }


        //System.out.println(masterShapeList.shapeList.size());



    }
}
