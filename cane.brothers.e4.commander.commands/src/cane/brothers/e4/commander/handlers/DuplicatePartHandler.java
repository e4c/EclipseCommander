/*******************************************************************************
 * File: DuplicatePartHandler.java
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

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.PartCopyType;
import cane.brothers.e4.commander.service.api.IPartService;

/**
 * Open new tab directly after active part using part service.
 * 
 * TODO open after active tab or in the tail
 * 
 * @see IPartService
 * 
 */
public class DuplicatePartHandler {

    private static final Logger log = LoggerFactory.getLogger(DuplicatePartHandler.class);

    @Inject
    IPartService partService;

    @Inject
    private IEclipseContext context;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        // storePathInContext(activePart);

        if (partService.copyPart(activePart, PartCopyType.DUPLICATE)) {
            if (log.isDebugEnabled()) {
                log.debug("part was duplicated sucessfully!"); //$NON-NLS-1$
            }
        }
        else {
            log.error("there are some problems on duplicating part"); //$NON-NLS-1$
        }
    }

    private void storePathInContext(MPart activePart) {
        // store current path into context
        // Object rootPathObject = context.get();
        if (activePart != null) {
            Map<String, String> state = activePart.getPersistedState();
            String rootPath = state.get(IdStorage.STATE_ROOT_PATH);
            context.set(IdStorage.STATE_ROOT_PATH, rootPath);
        }
    }
}
