package controller;

import model.MasterShapeList;
import model.Point;
import model.SelectedShapeList;
import model.Shape;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class copyCommand implements ICommand , IUndoable{
    private MasterShapeList masterShapeList;
    private SelectedShapeList shapeList;
    private PaintCanvasBase paintCanvas;
    private ArrayList<Shape> selectedShapes;
    public ArrayList<Shape> copiedShapes = new ArrayList<>();
    private IApplicationState applicationState;

    public copyCommand(PaintCanvasBase paintCanvas, ArrayList<Shape> selectedShapes, IApplicationState applicationState, ArrayList<Shape> copiedShapes) {
        //this.masterShapeList=masterShapeList;
        this.shapeList=shapeList;
        this.paintCanvas=paintCanvas;
        this.selectedShapes=selectedShapes;
        this.applicationState=applicationState;
        this.copiedShapes=copiedShapes;
    }


    @Override
    public void run() {
        int x = 300;
        for (Shape shape : selectedShapes) {
            Point startTemp = new Point(shape.getX() + x,  shape.getY());
            Point endTemp = new Point(shape.getEndPoint().getX() + x, shape.getEndPoint().getY());
            Shape shape2 = new Shape(paintCanvas, startTemp, endTemp, applicationState);
            copiedShapes.add(shape2);
        }
        //.out.println(copiedShapes.size());

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
