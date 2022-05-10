//Wasif Ahmad
//SE 450 Sprint 1
package main;

import controller.*;
import model.*;
import model.Shape;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){


        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ArrayList<Shape> selectedShapes = new ArrayList<>();
        GroupedShapeList groupedShapeList = new GroupedShapeList();
        MasterShapeList masterShapeList = new MasterShapeList(selectedShapes, uiModule, groupedShapeList);
        SelectedShapeList selectedShapeList = new SelectedShapeList();


        IJPaintController controller = new JPaintController(uiModule, appState, selectedShapeList, masterShapeList, paintCanvas, groupedShapeList, selectedShapes);
        controller.setup();
        paintCanvas.addMouseListener(new MyMouseListener(paintCanvas, appState, masterShapeList, selectedShapeList, controller,groupedShapeList));

        //The paintCanvas will add the mouse listener which takes in MyMouseListener
        //class as a constructor. In this class the command pattern is executed.


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410); */
        //Filled in rectangle
        //Graphics2D graphics2d = paintCanvas.getGraphics2D();

        /*graphics2d.setColor(Color.GREEN);
        //graphics2d.fillOval(12, 13, 400, 400);

        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(12, 13, 400, 400);
*/
        // Outlined rectangle
       /* graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(12, 13, 200, 400);

        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);
*/

        // Clears the Canvas
        //paintCanvas.repaint();
    }
}
