/*******************************************************************************
 * File: SetFocusToOppositePartHandler.java
 * 
 * Date: 2014/08/19
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
 * Mikhail Niedre - initial API and implementation
 *******************************************************************************/
package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
 * set focus to opposite part by Tab key.
 *
 */
public class SetFocusToOppositePartHandler {

    @Inject
    EModelService modelService;

    @Inject
    MApplication application;

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    MPart activePart;

    @Execute
    public void execute() {

	if (activePart != null) {

	    // // clear selection on current tab
	    // if (activePart.getObject() instanceof DynamicTab) {
	    // DynamicTab currentTab = (DynamicTab) activePart.getObject();
	    // currentTab.clearSelection();
	    // }
	    //
	    // // set focus to opposite active tab
	    // List<MPart> oppositeTabs = PartUtils.findVisibleOppositeTab(
	    // application, modelService, activePart);
	    //
	    // // pass throw all opposite active tab and remove selections
	    // for (MPart elem : oppositeTabs) {
	    // System.out.println(elem);
	    // if (elem.getObject() instanceof DynamicTab) {
	    // System.out.println("set focus to opposite tab");
	    //
	    // DynamicTab oppositeTab = (DynamicTab) elem.getObject();
	    //
	    // if (oppositeTab.getTable() != null) {
	    // oppositeTab.getTable().setFocus();
	    //
	    // // TODO select first
	    // }
	    // }
	    // }
	}
    }
}