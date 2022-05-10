package controller;

import model.GroupedShapeList;
import model.MasterShapeList;
import model.SelectedShapeList;
import model.Shape;
import model.interfaces.IApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class JPaintController implements IJPaintController {
    public final IUiModule uiModule;
    private final IApplicationState applicationState;
    private MasterShapeList masterShapeList;
    private SelectedShapeList shapeList;
    private PaintCanvasBase paintCanvas;
    private ArrayList<Shape> selectedShapes;
    public ArrayList<Shape> copiedShapes = new ArrayList<>();
    public GroupedShapeList groupedShapeList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, SelectedShapeList shapeList, MasterShapeList masterShapeList, PaintCanvasBase paintCanvas, GroupedShapeList groupedShapeList, ArrayList<Shape> selectedShapes) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.masterShapeList=masterShapeList;
        this.shapeList=shapeList;
        this.paintCanvas=paintCanvas;
        this.selectedShapes=selectedShapes;
        this.groupedShapeList=groupedShapeList;



    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        //Here I use the command patters for copying, pasting, and deleting respectively
        ICommand command = new copyCommand(paintCanvas, shapeList.selectedShapeList, applicationState, copiedShapes);
        ICommand pCommand = new pasteCommand(copiedShapes, paintCanvas, applicationState, masterShapeList, selectedShapes);
        ICommand dCommand = new deleteCommand(shapeList.selectedShapeList, paintCanvas, applicationState, masterShapeList);
        ICommand gCommand = new GroupCommand(paintCanvas, masterShapeList, shapeList.selectedShapeList, applicationState, groupedShapeList.groupedShapeList);
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> command.run());
        uiModule.addEvent(EventName.PASTE, () -> pCommand.run());
        uiModule.addEvent(EventName.DELETE, () -> dCommand.run());
        uiModule.addEvent(EventName.UNDO, () -> CommandHistory.undo());
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
        uiModule.addEvent(EventName.GROUP, () -> gCommand.run() );

    }
}
