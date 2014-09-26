/*******************************************************************************
 * File: ITabService.java
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
package cane.brothers.e4.commander.service.api;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import cane.brothers.e4.commander.api.IDynamicTab;

/**
 * TODO
 */
public interface ITabService {

    /**
     * @param part
     *            MPart associated with IDynamicTab
     * @return IDynamicTab association
     */
    public IDynamicTab getTab(MPart part);

    /**
     * Clear table selection on the tab
     * 
     * @param part
     *            MPart associated with IDynamicTab
     */
    public void clearSelection(MPart part);

    /**
     * Set table focus and selection on the tab
     * 
     * @param part
     *            MPart associated with IDynamicTab
     */
    public void setSelection(MPart part);
}
