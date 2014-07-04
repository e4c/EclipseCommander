package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.PartUtils;
import cane.brothers.e4.commander.parts.ITabNameId;

/**
 * Open new tab directly after active part.
 * @see ITabNameId 
 *  
 * @author cane
 *
 */
public class CopyPartHandler {
	@Inject
	EPartService partService;

	@Execute
	//public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
	public void execute() {
		System.out.println((this.getClass().getSimpleName() + " called"));
		MPart part = partService.getActivePart();

		MPart newPart = copyPart(part);
		partService.showPart(newPart, PartState.VISIBLE);
		partService.showPart(part, PartState.ACTIVATE);
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		return partService.isPartVisible(part);
	}

	private MPart copyPart(MPart part) {
		MPart newPart = MBasicFactory.INSTANCE.createPart();

		newPart.setLabel(PartUtils.createPartLabel(part));
		newPart.setElementId(PartUtils.createElementId(part));
		newPart.setContributionURI(part.getContributionURI());
		newPart.setCloseable(part.isCloseable());
		// NB должен быть задан parent, чтобы закладку добавить на туже сторону
		newPart.setParent(part.getParent());

		return newPart;
	}
}