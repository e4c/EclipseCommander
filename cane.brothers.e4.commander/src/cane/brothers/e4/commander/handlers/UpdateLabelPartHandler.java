/*******************************************************************************
 * File: UpdateLabelPartHandler.java
 * 
 * Date: 2014/08/10
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

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

import cane.brothers.e4.commander.MyEventConstants;

/**
 * TODO
 *
 */
public class UpdateLabelPartHandler {

    @Inject
    @Optional
    @Execute
    public void execute(
	    @UIEventTopic(MyEventConstants.TAB_PATH_OPEN) Path newPath,
	    @Named(IServiceConstants.ACTIVE_PART) MPart activePart) {

	// update label of current tab
	if (activePart != null && newPath != null) {
	    String rootPathString = null;
	    // root path have no file name
	    if (newPath.getFileName() == null) {
		rootPathString = newPath.toString();
	    }
	    else {
		rootPathString = newPath.getFileName().toString();
	    }
	    activePart.setLabel(rootPathString);

	}
    }
}
