package model.dialogs;

import model.CopyType;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseCopyDialog implements IDialogChoice<CopyType> {

    private final IApplicationState applicationState;

    public ChooseCopyDialog(IApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Copy";
    }

    @Override
    public String getDialogText() {
        return "Select copy to copy shapes drawn on the canvas";
    }

    @Override
    public CopyType[] getDialogOptions() {
        return CopyType.values();
    }

    @Override
    public CopyType getCurrentSelection() {
        return applicationState.getCopyMode();
    }
}
