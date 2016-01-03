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
package cane.brothers.e4.commander.handlers.internal;

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Update tab label when You set new path.
 *
 */
public class UpdateLabelPartHandler {

    private static final Logger log = LoggerFactory.getLogger(UpdateLabelPartHandler.class);

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    @Optional
    @Execute
    public void execute(@UIEventTopic(PartEvents.TOPIC_PART_PATH_OPEN) Path newPath) {

        // update label of current tab
        if (activePart != null && newPath != null) {
            String fileName = PathUtils.getFileName(newPath);
            activePart.setLabel(fileName);

            if (log.isDebugEnabled()) {
                log.debug("active part has new tab label: " + fileName); //$NON-NLS-1$
            }
        }
    }
}
