package info.bladt.busfest.component.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

import java.io.Serializable;

/**
 * Created by leif on 01.03.14.
 */
public class WizardStep implements Serializable {
    private Component inputForm;
    private Panel confirmationPanel;
    private WizardStepListener stepListener;

    public WizardStep(Component inputForm) {
        this.inputForm = inputForm;
    }

     public WizardStep(Component inputForm, Panel confirmationPanel) {
        this.inputForm = inputForm;
        this.confirmationPanel = confirmationPanel;
    }

    public Component getInputForm() {
        return inputForm;
    }

    public Panel getConfirmationPanel() {
        return confirmationPanel;
    }

    public WizardStepListener getStepListener() {
        return stepListener;
    }

    public void setStepListener(WizardStepListener stepListener) {
        this.stepListener = stepListener;
    }
}
