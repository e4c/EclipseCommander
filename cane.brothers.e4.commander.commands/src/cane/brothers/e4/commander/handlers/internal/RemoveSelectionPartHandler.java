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
package cane.brothers.e4.commander.handlers.internal;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.service.api.ITabService;

/**
 * remove selection on non active part by event.
 */
public class RemoveSelectionPartHandler {

    private static final Logger log = LoggerFactory.getLogger(RemoveSelectionPartHandler.class);

    @Inject
    private EPartService partService;

    @Inject
    private ITabService tabService;

    // @Inject
    // public void clearOtherSelection(
    // @Optional @Named(IServiceConstants.ACTIVE_SELECTION) PathFixture
    // selectedFixture) {
    // if (selectedFixture != null) {
    // System.out.println("selection");
    // }
    // }

    //
    @Inject
    @Execute
    public void removeSelection(@Optional @UIEventTopic(PartEvents.TOPIC_PART_REMOVE_SELECTION) final MPart activePart) {
        if (log.isDebugEnabled()) {
            log.debug(this.getClass().getSimpleName() + " called"); //$NON-NLS-1$
        }

        if (activePart != null && partService != null) {

            // pass throw all parts
            for (MPart p : partService.getParts()) {
                if (p != null && !p.equals(activePart)) {

                    tabService.clearSelection(p);
                    if (log.isDebugEnabled()) {
                        log.debug("remove selection from non active tab");
                    }
                }
            }
        }
    }

}
