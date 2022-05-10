package controller;

import model.*;
import model.Shape;
import model.interfaces.DrawStrategy;
import model.interfaces.IApplicationState;
import model.interfaces.OutlineSelectedStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class pasteCommand implements ICommand, IUndoable{
    private MasterShapeList masterShapeList;
    private ArrayList<Shape> selectedShapes = new ArrayList<>();
    private PaintCanvasBase paintCanvas;

    public ArrayList<Shape> copiedShapes = new ArrayList<>();
    private IApplicationState applicationState;
    public DrawStrategy drawStrategy;
    public OutlineSelectedStrategy strategy;

    public pasteCommand(ArrayList<Shape> copiedShapes, PaintCanvasBase paintCanvas, IApplicationState applicationState, MasterShapeList list, ArrayList<Shape> selectedShapes) {
        this.masterShapeList=list;
        this.paintCanvas=paintCanvas;
        this.applicationState=applicationState;
        this.copiedShapes=copiedShapes;
        this.selectedShapes=selectedShapes;
    }



    @Override
    public void run() {
        int x = 300;




        /*for(Shape shape : masterShapeList.shapeList )
            shape.pattern(); */

        for(Shape shape : copiedShapes) {
            //shape.setShapeType(shape.getShapeType());
            shape.pattern();
        }
        CommandHistory.add(this);

    }

    @Override
    public void undo() {

            Graphics2D g = paintCanvas.getGraphics2D();
            g.setColor(Color.WHITE);
            g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());


            for(Shape shape: selectedShapes) {

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
