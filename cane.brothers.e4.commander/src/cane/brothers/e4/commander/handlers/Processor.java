 
package cane.brothers.e4.commander.handlers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.basic.MWindowElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;

public class Processor {
	@Inject
	MApplication application;
	
	public Processor(){
		System.out.println("Processor");
	}
	
	@Execute
	public void execute(IWorkbench workbench,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		List<MWindow> existingWindows = application.getChildren();
		for(MWindow win: existingWindows) {
			System.out.println(win.getElementId());
			System.out.println(win.getLabel());
			for(MWindowElement elem :win.getChildren()) {
				System.out.println(elem.getElementId());
			}
		}
		//existingWindow.setX(200);
		MTrimmedWindow newWindow = MBasicFactory.INSTANCE.createTrimmedWindow();
		newWindow.setWidth(200);
		newWindow.setHeight(400);
		application.getChildren().add(newWindow);
	}
		
}