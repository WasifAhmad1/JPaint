package controller;

import model.*;
import model.Point;
import model.Shape;
import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class drawCommand implements ICommand, IUndoable {
    //credit goes to Jose Gomez from the discussion board for this idea.

    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    private MasterShapeList masterShapeList;
    public DrawStrategy drawStrategy;
    public ArrayList<Shape> groupedShapes = new ArrayList<>();
    public ArrayList<Shape> temp = new ArrayList<>();

    public drawCommand(ShapeType shapeType, PaintCanvasBase paintCanvas, Point start, Point end, IApplicationState applicationState, MasterShapeList masterShapeList, ArrayList<Shape> groupedShapes) {
        this.paintCanvas =  paintCanvas;
        this.startPoint = start;
        this.endPoint = end;
        this.shapeType = shapeType;
        this.applicationState = applicationState;
        this.masterShapeList = masterShapeList;
        this.groupedShapes=groupedShapes;
    }

    @Override
    public void run () {
        //MouseEvent e = null;

       //A new shape object will be created and added to the master shape list.
            Shape shape = new Shape(paintCanvas, startPoint, endPoint, applicationState);
            masterShapeList.add(shape);
            CommandHistory.add(this);
            shape.pattern();


    }

    @Override
    public void undo() {
        int getLast= masterShapeList.shapeList.size()-1;
        if(masterShapeList.shapeList.size()>=1) {
            Shape shape2 = masterShapeList.shapeList.get(getLast);
            masterShapeList.shapeList.remove(shape2);
            temp.add(shape2);

            Graphics2D g = paintCanvas.getGraphics2D();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
            for (Shape shape : masterShapeList.shapeList) {
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

    }

    @Override
    public void redo() {
        int getFirst=0;
        if(temp.size()>=1) {
            Shape shape2 = temp.get(getFirst);
            masterShapeList.shapeList.add(shape2);
            temp.remove(shape2);

            Graphics2D g = paintCanvas.getGraphics2D();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
            for (Shape shape : masterShapeList.shapeList) {
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
        }else {
            System.out.println("No more shapes left to redo");
        }


    }
}

