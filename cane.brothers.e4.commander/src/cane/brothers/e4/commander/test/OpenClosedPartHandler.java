package cane.brothers.e4.commander.test;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class OpenClosedPartHandler {

	@Execute
	public void execute(EPartService partService) {

		MPart part = partService.findPart("e4rcp.part.0");
		part.setVisible(true);
		partService.showPart(part, PartState.VISIBLE);

	}
}
