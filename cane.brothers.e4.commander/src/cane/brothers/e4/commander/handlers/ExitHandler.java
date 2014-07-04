/*******************************************************************************
 * File: ExitHandler.java
 * 
 * Date: Jul 5, 2014
 * Author: Mikhail Niedre
 * 
 * Copyright (c) 2014 Original authors and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * <a href="http://www.eclipse.org/legal/epl-v10.html">epl-v1.0</a>
 * 
 * Contributors:
 * Original authors and others - initial API and implementation
 ******************************************************************************/
package cane.brothers.e4.commander.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class ExitHandler {
	// @Inject
	// MApplication application;

	@Execute
	public void execute(IWorkbench workbench,
	        @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		if (MessageDialog.openConfirm(shell, "Confirmation",
		        "Do you want to exit?")) {
			workbench.close();
		}
	}

	// @Execute
	// public void execute(IWorkbench workbench,
	// @Named(IServiceConstants.ACTIVE_SHELL) Shell shell){
	// // if (MessageDialog.openConfirm(shell, "Confirmation",
	// // "Do you want to exit?")) {
	// // workbench.close();
	// // }
	// List<MWindow> existingWindows = application.getChildren();
	// for(MWindow win: existingWindows) {
	// System.out.println(win.getElementId());
	// System.out.println(win.getLabel());
	// for(MWindowElement elem :win.getChildren()) {
	// System.out.println(elem.getElementId());
	// }
	// }
	// //existingWindow.setX(200);
	// MTrimmedWindow newWindow = MBasicFactory.INSTANCE.createTrimmedWindow();
	// newWindow.setWidth(200);
	// newWindow.setHeight(400);
	// application.getChildren().add(newWindow);
	// }
}