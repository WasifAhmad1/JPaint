package controller;

import model.*;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class selectCommand implements ICommand, IUndoable{
    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Point endPoint;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    private MasterShapeList masterShapeList;
    private SelectedShapeList selectedShapeList;
    private JPaintController jPaintController;
    public ArrayList<Shape> groupedShapes = new ArrayList<>();
    public GroupedShapeList groupedShapeList;

    public selectCommand(ShapeType shapeType, PaintCanvasBase paintCanvas, Point start, Point end, IApplicationState applicationState, MasterShapeList masterShapeList, SelectedShapeList selectedShapeList, GroupedShapeList groupedShapeList) {
        this.paintCanvas =  paintCanvas;
        this.startPoint = start;
        this.endPoint = end;
        this.shapeType = shapeType;
        this.applicationState = applicationState;
        this.masterShapeList = masterShapeList;
        this.selectedShapeList = selectedShapeList;
        this.groupedShapeList=groupedShapeList;

    }

    @Override
    public void run() {
        masterShapeList.selectShapes(startPoint, endPoint, paintCanvas, selectedShapeList, applicationState, groupedShapeList);
        }


    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}

