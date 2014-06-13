 
package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.parts.LeftTab;


public class TestHandler {
	@Inject
	MApplication application;
	@Inject
	EModelService modelService;
	@Inject
	EPartService partService;
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		System.out.println((this.getClass().getSimpleName() + " called"));
		
		
		
//		MElementContainer<MUIElement> parent = part.getParent();
//		int position = parent.getChildren().indexOf(part);
//		if(newPart != null) {
//			parent.getChildren().add(position + 1, newPart);			
//		}
		
		
		
		
		//partService.
//		if(part != null) {
//			System.out.println("ElementId:" + part.getElementId());
//			System.out.println("label:" + part.getLabel());
//			Object partObject = part.getObject();
//			if(partObject instanceof LeftTab) {
//				LeftTab tab = (LeftTab) partObject;
//				//tab
//			}
//		}
		
		//partService.createPart(part.getElementId() + ".new");
		//partService.hidePart(part);
		
		
//		List<MWindow> existingWindows = application.getChildren();
//		for(MWindow win: existingWindows) {
//			System.out.println(win.getElementId());
//			System.out.println(win.getLabel());
//			for(MWindowElement elem :win.getChildren()) {
//				System.out.println(elem.getElementId());
//			}
//		}
//		//existingWindow.setX(200);
//		MTrimmedWindow newWindow = MBasicFactory.INSTANCE.createTrimmedWindow();
//		newWindow.setWidth(200);
//		newWindow.setHeight(400);
//		application.getChildren().add(newWindow);			
	}
		
}