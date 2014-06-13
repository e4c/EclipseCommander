 
package cane.brothers.e4.commander.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class CloseActivePartHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part, 
			EPartService partService) {
		// close active part
		partService.hidePart(part);
	}
	
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part,
			EPartService partService) {
		return partService.isPartVisible(part);
	}
		
}