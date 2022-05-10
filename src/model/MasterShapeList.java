package model;

import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import model.interfaces.OutlineSelectedStrategy;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MasterShapeList {
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    public ArrayList<Shape> shapeList = new ArrayList<>();
    public ArrayList<Shape> copiedShapes = new ArrayList<>();
    public OutlineSelectedStrategy strategy;
    public IUiModule uiModule;
    public DrawStrategy drawStrategy;
    public GroupedShapeList groupedShapeList;
    public ShapeFactory shapeFactory;

    public MasterShapeList(ArrayList<Shape> copiedShapes, IUiModule uiModule, GroupedShapeList groupedShapeList) {
        this.shapeList = shapeList;
        this.copiedShapes=copiedShapes;
        this.uiModule=uiModule;
        this.groupedShapeList=groupedShapeList;

    }

    public void add(Shape shape) {
        shapeList.add(shape);
    }

    public void selectShapes(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, SelectedShapeList selectedShapeList, IApplicationState applicationState, GroupedShapeList groupedShapeList) {
        //I struggled a lot with this portion of the code and while I am able to select shapes I have not implemented the code yet to de-select shaped. Therefore this code is incomplete and requires additional work.
        ArrayList<Shape> copiedShapeList = new ArrayList<>(shapeList);
        int xMin = Math.min(startPoint.getX(), endPoint.getX());
        int xMax = Math.max(startPoint.getX(), endPoint.getX());
        int yMin = Math.min(startPoint.getY(), endPoint. getY());
        int yMax = Math.max(startPoint. getY(), endPoint.getY());
        int width = (endPoint.getX() - startPoint.getX());
        int length = (endPoint.getY() - startPoint.getY());




        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());


        for(Shape shape: this.shapeList) {
            shapeFactory.getStrategy(shape);

        }

        for(Shape shape: groupedShapeList.groupedShapeList) {
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





        selectedShapeList.selectedShapeList.clear();

        for (Shape shape : shapeList) {
            Graphics2D graphics2d = paintCanvas.getGraphics2D();
            //Graphics2D graphics = shape.getPaintCanvas().getGraphics2D();
            Rectangle r1 = new Rectangle(xMin, yMin, width, length);
            Rectangle r2 = new Rectangle(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
            //shape.setSelected(chosen = false);
            //System.out.println(r2.getHeight());
            if (r2.intersects(r1)) {
                selectedShapeList.add(shape);
//Utilize the strategy pattern to draw an outline around selected shapes
                if(shape.getShapeType().toString().equals("ELLIPSE")) {
                    strategy = new SelectEllipse();
                    SelectEllipse.outlineSelectStrategy(shape);
                }
                if(shape.getShapeType().toString().equals("RECTANGLE")) {
                    strategy = new SelectRectangle();
                    SelectRectangle.outlineSelectStrategy(shape);
                }
                if(shape.getShapeType().toString().equals("TRIANGLE")) {
                    strategy= new SelectTriangle();
                    SelectTriangle.outlineSelectStrategy(shape);
                }

            }

        }


        //copyShapes(selectedShapeList.selectedShapeList, paintCanvas, applicationState);






    }

}






        /* if ((x >= shape.getX() && x < shape.getX() + shape.getWidth() && y > shape.getY() && y < shape.getY() + shape.getHeight() ) && shape.getSelected()==false) {
        selectedShapeList.add(shape);
                /*for (Shape shape2 : shapeList) {
                    if (x >= shape2.getX() && x < shape2.getX() + shape2.getWidth() && y > shape2.getY() && y < shape2.getY() + shape2.getHeight() && shape2.getSelected() == false) {
                        selectedShapeList.add(shape2);
                        shape2.setSelected(selected = true);
                    }

                }*/




            //if (r1.intersects(r2)) {
                //if (x >= shape.getX() && x < shape.getX() + shape.getWidth() && y > shape.getY() && y < shape.getY() + shape.getHeight()) {
                //graphics.setStroke(new BasicStroke(5));
                //graphics.drawRect(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
                //System.out.println("Collission detected");
                //selectedShapeList.add(shape);
                //shapeList.remove(shape);




        //System.out.println(selectedShapeList.selectedShapeList.size());




//x >= shape.getX() && x < shape.getX() + shape.getWidth() && y > shape.getY() && y < shape.getY() + shape.getHeight()