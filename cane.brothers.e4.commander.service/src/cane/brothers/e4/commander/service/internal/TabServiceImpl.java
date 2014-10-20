/*******************************************************************************
 * File: TabService.java
 * 
 * Date: 26 сент. 2014 г.
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
package cane.brothers.e4.commander.service.internal;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.api.IDynamicTab;
import cane.brothers.e4.commander.service.api.ITabService;

/**
 * TODO
 */
public class TabServiceImpl implements ITabService {

    private static final Logger log = LoggerFactory
	    .getLogger(TabServiceImpl.class);

    /**
     * Constructor
     *
     */
    public TabServiceImpl() {
	// TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.ITabService#getTab(org.eclipse
     * .e4.ui.model.application.ui.basic.MPart)
     */
    @Override
    public IDynamicTab getTab(MPart part) {
	IDynamicTab tab = null;
	if (part != null) {
	    if (part.getObject() instanceof IDynamicTab) {
		tab = (IDynamicTab) part.getObject();
	    }
	    else {
		log.error("Given MPart is not a kind of DynamicTab part: " + part.toString()); //$NON-NLS-1$
	    }
	}
	else {
	    log.error("Part is null");
	}
	return tab;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.ITabService#clearSelection(org
     * .eclipse.e4.ui.model.application.ui.basic.MPart)
     */
    @Override
    public void clearSelection(MPart part) {
	IDynamicTab tab = getTab(part);
	if (tab != null) {
	    tab.clearSelection();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.ITabService#setSelection(org.eclipse
     * .e4.ui.model.application.ui.basic.MPart)
     */
    @Override
    public void setSelection(MPart part) {
	IDynamicTab tab = getTab(part);
	if (tab != null) {
	    tab.setFocus();
	    tab.setSelection();
	}
    }

}
