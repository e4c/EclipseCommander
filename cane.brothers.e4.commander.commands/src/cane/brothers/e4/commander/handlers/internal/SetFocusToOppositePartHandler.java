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
package cane.brothers.e4.commander.handlers.internal;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.api.IResolveSelection;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;

/**
 * set focus to opposite part by Tab key.
 *
 */
public class SetFocusToOppositePartHandler implements IResolveSelection {

    private static final Logger log = LoggerFactory.getLogger(SetFocusToOppositePartHandler.class);

    @Inject
    private IPartService partService;

    @Inject
    private ITabService tabService;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        resolveSelections(activePart);

        if (log.isDebugEnabled()) {
            log.debug("set focus and default selection on opposite tab");
        }
    }

    @Override
    public void resolveSelections(MPart activePart) {
        tabService.clearSelection(activePart);
        tabService.setSelection(partService.getOppositePart(activePart));

    }
}