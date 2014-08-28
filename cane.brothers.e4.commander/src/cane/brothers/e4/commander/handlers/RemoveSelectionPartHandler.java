/*******************************************************************************
 * File: RemoveSelectionOppositePartHandler.java
 * 
 * Date: 27 авг. 2014 г.
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

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import cane.brothers.e4.commander.PathEvents;
import cane.brothers.e4.commander.utils.PartUtils;

/**
 * remove selection on non active part by event.
 */
public class RemoveSelectionPartHandler {

    @Inject
    MApplication application;

    @Inject
    EModelService modelService;

    @Inject
    private EPartService partService;

    @Inject
    @Optional
    @Execute
    public void execute(
	    @UIEventTopic(PathEvents.TAB_REMOVE_SELECTION) MPart activePart) {

	if (activePart != null && partService != null) {

	    // pass throw all parts
	    for (MPart p : partService.getParts()) {
		if (p != null && !p.equals(activePart)) {

		    PartUtils.clearSelection(p);
		    System.out.println("remove selection from non active tab");
		}
	    }
	}
    }

}
