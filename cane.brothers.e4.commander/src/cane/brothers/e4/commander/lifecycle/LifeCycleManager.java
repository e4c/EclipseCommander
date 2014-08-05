/**
 * 
 */
package cane.brothers.e4.commander.lifecycle;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import cane.brothers.e4.commander.IdStorage;

/**
 * @author cane
 *
 */
public class LifeCycleManager {
	
	@Inject
	MApplication application;
	
	@Inject
	EModelService modelService;
	
	@Inject
	EPartService partService;

	/**
	 * 
	 */
	public LifeCycleManager() {
		System.out.println("life cycle manager");

	}
	
	@SuppressWarnings("restriction")
	@ProcessAdditions
	void
	addModelHook() {
		
		
		List<MWindow> existingWindows = application.getChildren();
		for(MWindow win: existingWindows) {
			System.out.println(win.getElementId());
			System.out.println(win.getLabel());
//			for(MWindowElement elem :win.getChildren()) {
//				System.out.println(elem.getElementId());
//			}
		}
		
	// setAppWindowSize();
	}

	private void setAppWindowSize() {
		MWindow window = (MWindow) modelService.find(IdStorage.WINDOW_ID,
				application);
		window.setWidth(1000);
	}

}
