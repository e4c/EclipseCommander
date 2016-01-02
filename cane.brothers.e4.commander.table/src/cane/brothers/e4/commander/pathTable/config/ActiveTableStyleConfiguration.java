/*******************************************************************************
 * File: ActiveTableStyleConfiguration.java
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
package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;

/**
 * TODO
 *
 */
public class ActiveTableStyleConfiguration extends AbstractRegistryConfiguration {

    public static final String ACTIVE_LABEL = "ACTIVE";

    @Override
    public void configureRegistry(IConfigRegistry configRegistry) {
        IStyle style = new Style();
        style.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_BLUE);
        style.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, GUIHelper.COLOR_WHITE);

        configRegistry
                .registerConfigAttribute(CellConfigAttributes.CELL_STYLE, style, DisplayMode.NORMAL, ACTIVE_LABEL);

        configRegistry
                .registerConfigAttribute(CellConfigAttributes.CELL_STYLE, style, DisplayMode.SELECT, ACTIVE_LABEL);
    }

}
