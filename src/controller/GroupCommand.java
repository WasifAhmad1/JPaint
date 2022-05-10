package controller;

import model.*;
import model.Shape;
import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import model.interfaces.OutlineSelectedStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GroupCommand implements ICommand {
    private MasterShapeList masterShapeList;
    private SelectedShapeList shapeList;
    private PaintCanvasBase paintCanvas;
    private ArrayList<Shape> selectedShapeList;
    public ArrayList<Shape> groupedShapes;
    private IApplicationState applicationState;
    public OutlineSelectedStrategy strategy;
    public DrawStrategy drawStrategy;


    public GroupCommand(PaintCanvasBase paintCanvas, MasterShapeList masterShapeList, ArrayList<Shape> selectedShapeList, IApplicationState applicationState, ArrayList<Shape> groupedShapes) {
        this.paintCanvas=paintCanvas;
        this.masterShapeList=masterShapeList;
        this.selectedShapeList=selectedShapeList;
        this.applicationState=applicationState;
        this.groupedShapes=groupedShapes;
    }

    @Override
    public void run() {
        Iterator<Shape> iter = masterShapeList.shapeList.iterator();

        for(Shape shape: selectedShapeList) {
            groupedShapes.add(shape);

        }

        while(iter.hasNext()) {
            Shape shape = iter.next();

            if(groupedShapes.contains(shape)) {
                iter.remove();
            }
        }
        selectedShapeList.clear();

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for(Shape shape: masterShapeList.shapeList) {
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

       int xMin = groupedShapes.get(0).getXMin();
        int yMin = groupedShapes.get(0).getYMin();
       int xMax = groupedShapes.get(0).getXMax();
       int yMax = groupedShapes.get(0).getYMax();
        for(Shape shape: groupedShapes) {


            if(shape.getXMin()<xMin) {
                xMin=shape.getXMin();
            }
            if(shape.getYMin()<yMin) {
                yMin=shape.getYMin();
            }
            if(shape.getXMax()>xMax) {
                xMax=shape.getXMax();
            }
            if(shape.getYMax()>yMax) {
                yMax=shape.getYMax();
            }

            {
                if (shape.getShapeType().toString().equals("ELLIPSE")) {
                    drawStrategy = new DrawEllipseStrategy();
                    DrawEllipseStrategy.drawStrategy(shape);
                }
                if (shape.getShapeType().toString().equals("TRIANGLE")) {

                    drawStrategy = new DrawTriangleStrategy();
                    DrawTriangleStrategy.drawStrategy(shape);
                }
                if (shape.getShapeType().toString().equals("RECTANGLE")) {

                    drawStrategy = new DrawRectangleStrategy();
                    DrawRectangleStrategy.drawStrategy(shape);
                }
            }

        }
        g.setColor(Color.BLUE);
        g.drawRect(xMin, yMin, (xMax-xMin), (yMax-yMin));









        

    }
}
