/*******************************************************************************
 * File: PathSelectionLayerConfiguration.java
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

import org.eclipse.nebula.widgets.nattable.selection.config.DefaultSelectionLayerConfiguration;

/**
 * TODO
 *
 */
public class PathSelectionLayerConfiguration extends
	DefaultSelectionLayerConfiguration {

    @Override
    protected void addSelectionUIBindings() {
	addConfiguration(new PathSelectionUiBinding());
    }

}
