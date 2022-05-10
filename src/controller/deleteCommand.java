package controller;

import javafx.scene.web.HTMLEditorSkin;
import model.MasterShapeList;
import model.SelectedShapeList;
import model.Shape;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class deleteCommand implements ICommand, IUndoable {
    private MasterShapeList masterShapeList;
    private SelectedShapeList shapeList;
    private PaintCanvasBase paintCanvas;
    private ArrayList<Shape> selectedShapeList;
    public ArrayList<Shape> copiedShapes = new ArrayList<>();
    private IApplicationState applicationState;


    public deleteCommand(ArrayList<Shape> selectedShapeList, PaintCanvasBase paintCanvas, IApplicationState applicationState, MasterShapeList masterShapeList) {
        this.paintCanvas=paintCanvas;
        this.selectedShapeList=selectedShapeList;
        this.applicationState=applicationState;
        this.masterShapeList=masterShapeList;
    }

    @Override
    public void run() {
        for (Shape shape: this.selectedShapeList) {
            if (masterShapeList.shapeList.contains(shape))
                masterShapeList.shapeList.remove(shape);
        }

        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for(Shape shape: masterShapeList.shapeList)
            shape.pattern();

        CommandHistory.add(this);
        
    }

    @Override
    public void undo() {
        Graphics2D g = paintCanvas.getGraphics2D();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,paintCanvas.getWidth(),paintCanvas.getHeight());

        for(Shape shape: this.selectedShapeList) {
            shape.pattern();
            masterShapeList.add(shape);
        }

        for(Shape shape: masterShapeList.shapeList)
            shape.pattern();




    }

    @Override
    public void redo() {
        run();

    }
}
