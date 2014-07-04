package cane.brothers.e4.commander.test;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import cane.brothers.e4.commander.IdStorage;

public class OpenPerspectiveHandler {

	@Execute
	public void execute(MApplication app, EPartService partService,
			EModelService modelService) {

		MPerspective element = (MPerspective) modelService.find(
				IdStorage.MAIN_PERSPECTIVE_ID, app);

		partService.switchPerspective(element);

	}

}
