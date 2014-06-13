 
package cane.brothers.e4.commander.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class ExitHandler {
	//@Inject
	//MApplication application;
	
	@Execute
	public void execute(IWorkbench workbench,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell){
		if (MessageDialog.openConfirm(shell, "Confirmation",
				"Do you want to exit?")) {
			workbench.close();
		}
	}
	
//	@Execute
//	public void execute(IWorkbench workbench,
//			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell){
////		if (MessageDialog.openConfirm(shell, "Confirmation",
////				"Do you want to exit?")) {
////			workbench.close();
////		}
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
//	}
}