package controller;
import model.*;
import model.Point;
import model.Shape;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private PaintCanvasBase paintCanvas;
    private Shape shape;
    private IApplicationState applicationState;
    private ShapeType shapeType;
    private MasterShapeList masterShapeList;
    private SelectedShapeList selectedShapeList;
    private IJPaintController jPaintController;
    public ArrayList<Shape> groupedShapes = new ArrayList<>();
    public GroupedShapeList groupedShapeList;

    public MyMouseListener(PaintCanvasBase paintCanvas, IApplicationState applicationState, MasterShapeList masterShapeList, SelectedShapeList shapeList, IJPaintController controller, GroupedShapeList groupedShapeList) {
        this.selectedShapeList = shapeList;
        this.paintCanvas = paintCanvas;
        this.applicationState=applicationState;
        this.masterShapeList = masterShapeList;
        this.jPaintController=controller;
        this.groupedShapeList=groupedShapeList;


    }
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e){
        shapeType = applicationState.getActiveShapeType();

        //This will instantiate a new command object which execute the run method
        //in the drawCommand class
        Point endPoint = new Point(e.getX(), e.getY());
//based on the application state the program will run the command pattern pertaining to moving, selecting, and drawing.

        if(applicationState.getActiveStartAndEndPointMode().toString().equals("MOVE")) {
            ICommand command = new moveCommand(shapeType, paintCanvas, startPoint, endPoint, applicationState, masterShapeList, selectedShapeList);
            command.run();

        }

        if(applicationState.getActiveStartAndEndPointMode().toString().equals("SELECT")) {
            ICommand command = new selectCommand(shapeType, paintCanvas, startPoint, endPoint, applicationState, masterShapeList, selectedShapeList, groupedShapeList);
            command.run();
        }

        if (applicationState.getActiveStartAndEndPointMode().toString().equals("DRAW")) {
            ICommand command = new drawCommand(shapeType, paintCanvas, startPoint, endPoint, applicationState, masterShapeList, groupedShapes);
            command.run();
        }





    }
}
