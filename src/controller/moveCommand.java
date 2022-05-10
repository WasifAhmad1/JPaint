package controller;

import model.*;
import model.Point;
import model.Shape;
import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import model.interfaces.OutlineSelectedStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class moveCommand implements ICommand, IUndoable{
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    private MasterShapeList masterShapeList;
    private SelectedShapeList selectedShapeList;
    public OutlineSelectedStrategy strategy;
    public DrawStrategy drawStrategy;

    public moveCommand(ShapeType shapeType, PaintCanvasBase paintCanvas, Point start, Point end, IApplicationState applicationState, MasterShapeList masterShapeList, SelectedShapeList selectedShapeList)
        {
            this.paintCanvas = paintCanvas;
            this.startPoint = start;
            this.endPoint = end;
            this.shapeType = shapeType;
            this.applicationState = applicationState;
            this.masterShapeList = masterShapeList;
            this.selectedShapeList = selectedShapeList;
        }

        public void run () {
            selectedShapeList.moveShapes(startPoint, endPoint, paintCanvas, masterShapeList, applicationState);
            CommandHistory.add(this);



        }

    @Override
    public void undo() {

        int getLast= selectedShapeList.selectedShapeList.size()-1;
        if(selectedShapeList.selectedShapeList.size()>=1) {
            for (Shape shape : selectedShapeList.selectedShapeList) {
                Point startPoint2 = new Point(shape.getX() - selectedShapeList.getDeltaX(), shape.getY() - selectedShapeList.getDeltaY());
                Point endPoint2 = new Point(shape.getEndPoint().getX() - selectedShapeList.getDeltaX(), shape.getEndPoint().getY() - selectedShapeList.getDeltaY());

                int xMin = Math.min(startPoint2.getX(), endPoint2.getX());
                int xMax = Math.max(startPoint2.getX(), endPoint2.getX());

                int yMin = Math.min(startPoint2.getY(), endPoint2.getY());
                int yMax = Math.max(startPoint2.getY(), endPoint2.getY());
                int triangleMidPoint = (xMin) + ((xMax - xMin) / 2);

                int width = (endPoint2.getX() - startPoint2.getX());
                int height = (endPoint2.getY() - startPoint2.getY());

                shape.setxMin(xMin);
                shape.setxMax(xMax);
                shape.setyMin(yMin);
                shape.setyMax(yMax);
                shape.setHeight(height);
                shape.setWidth(width);
                shape.setTriangleMidPoint(triangleMidPoint);

                shape.setStartPoint(startPoint2);
                shape.setEndPoint(endPoint2);
            }
        }

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for(Shape shape: selectedShapeList.selectedShapeList) {

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


    }

    @Override
    public void redo() {
        run();

    }
}



