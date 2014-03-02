package info.bladt.busfest.component.wizard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leif on 01.03.14.
 */
public class WizardModel implements Serializable {
    private WizardStep activeStep;

    private List<WizardStep> steps = new ArrayList<>();

    public void add(WizardStep step) {
        steps.add(step);
    }

    public WizardStep getActiveStep() {
        return activeStep;
    }

    public void setActiveStep(WizardStep activeStep) {
        this.activeStep = activeStep;
    }

    public void next() {
        if (getActiveStep().getStepListener() != null) {
            getActiveStep().getStepListener().onNext();
        }
        // TODO Make it failsafe
        setActiveStep(steps.get(steps.indexOf(activeStep) + 1));
    }

    public void previous() {
        if (getActiveStep().getStepListener() != null) {
            getActiveStep().getStepListener().onPrevious();
        }
        // TODO Make it failsafe
        setActiveStep(steps.get(steps.indexOf(activeStep) - 1));
    }

    public void finish() {}

    public boolean isFirstStep() {
        return steps.indexOf(activeStep) == 0;
    }

    public boolean isLastStep() {
        return steps.indexOf(activeStep) == (steps.size() - 1);
    }
}
