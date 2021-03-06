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

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.IResolveSelection;
import cane.brothers.e4.commander.api.PartCopyType;
import cane.brothers.e4.commander.preferences.PreferenceConstants;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;

/**
 * Open new tab directly after active part using part service.
 * 
 * TODO open after active tab or in the tail
 * 
 * @see IPartService
 * 
 */
public class DuplicatePartHandler implements IResolveSelection {

    private static final Logger log = LoggerFactory.getLogger(DuplicatePartHandler.class);

    @Inject
    IPartService partService;

    @Inject
    private ITabService tabService;

    @Inject
    @Preference(value = PreferenceConstants.PB_STAY_ACTIVE_TAB, nodePath = IdStorage.PREF_PLUGIN_ID)
    private boolean stayActiveTab;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        if (partService.copyPart(activePart, PartCopyType.DUPLICATE)) {
            if (log.isDebugEnabled()) {
                log.debug("part was duplicated sucessfully!"); //$NON-NLS-1$
            }

            resolveSelections(activePart);
        }
        else {
            log.error("there are some problems on duplicating part"); //$NON-NLS-1$
        }
    }

    @Override
    public void resolveSelections(MPart activePart) {
        tabService.clearSelection(stayActiveTab ? null : activePart);
        tabService.setSelection(stayActiveTab ? null : partService.getLastPart());
    }
}
