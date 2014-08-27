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

import cane.brothers.e4.commander.PathEvents;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Update tab label when You set new path.
 *
 */
public class UpdateLabelPartHandler {

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    @Optional
    @Execute
    public void execute(@UIEventTopic(PathEvents.TAB_PATH_OPEN) Path newPath) {

	// update label of current tab
	if (activePart != null && newPath != null) {
	    activePart.setLabel(PathUtils.getFileName(newPath));
	}
    }
}
