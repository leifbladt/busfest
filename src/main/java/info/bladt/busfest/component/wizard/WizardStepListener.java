package info.bladt.busfest.component.wizard;

import java.io.Serializable;

/**
 * Created by leif on 01.03.14.
 */
public interface WizardStepListener extends Serializable {
    public void onPrevious();

    public void onNext();
}
