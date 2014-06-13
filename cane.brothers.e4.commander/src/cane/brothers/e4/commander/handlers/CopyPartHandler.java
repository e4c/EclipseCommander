package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.parts.ITabNameId;

/**
 * TODO
 *  - открывать закладку сразу за активной
 *  
 * @author cane
 *
 */
public class CopyPartHandler {
	@Inject
	EPartService partService;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		System.out.println((this.getClass().getSimpleName() + " called"));

		MPart newPart = copyPart(part);
		// NB должен быть задан parent, чтобы закладку добавить на туже сторону
		partService.showPart(newPart, PartState.ACTIVATE);
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		return partService.isPartVisible(part);
	}

	private MPart copyPart(MPart part) {
		MPart newPart = MBasicFactory.INSTANCE.createPart();

		newPart.setLabel(createPartLabel(part));
		newPart.setElementId(createElementId(part));
		newPart.setContributionURI(part.getContributionURI());
		newPart.setCloseable(part.isCloseable());
		newPart.setParent(part.getParent());

		return newPart;
	}

	private String createPartLabel(MPart part) {
		String label = null;
		if (part != null && part.getObject() != null) {
			if (part.getObject() instanceof ITabNameId) {
				ITabNameId tab = (ITabNameId) part.getObject();
				label = tab.getTabName();
			}
		}
		return label;
	}

	private String createElementId(MPart part) {
		String elemId = null;
		if (part != null && part.getObject() != null) {
			if (part.getObject() instanceof ITabNameId) {
				ITabNameId tab = (ITabNameId) part.getObject();
				elemId += tab.getElementId();
			}
		}
		return elemId;
	}

}