package cane.brothers.e4.commander.api;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

/**
 * Defines interface for classes who should manage tab selections.
 */
public interface IResolveSelection {

    /**
     * manage tab selections
     *
     * @param activePart
     */
    public void resolveSelections(MPart activePart);

}
