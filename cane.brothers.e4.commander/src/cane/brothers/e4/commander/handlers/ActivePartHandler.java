package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

public class ActivePartHandler {
	
	// Tracks the active part
	@Inject
	@Optional
	public void receiveActivePart(
			@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
		if (activePart != null) {
			System.out.println("Active part changed " + activePart.getLabel());
		}
	}
}
