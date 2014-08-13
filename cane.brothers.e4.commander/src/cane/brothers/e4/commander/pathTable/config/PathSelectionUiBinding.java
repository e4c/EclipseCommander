/*******************************************************************************
 * File: PathSelectionUiBinding.java
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

import org.eclipse.nebula.widgets.nattable.config.AbstractUiBindingConfiguration;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.nebula.widgets.nattable.ui.action.IMouseAction;
import org.eclipse.nebula.widgets.nattable.ui.action.NoOpMouseAction;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.KeyEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.MouseEventMatcher;
import org.eclipse.swt.SWT;

import cane.brothers.e4.commander.pathTable.action.OpenPathAction;
import cane.brothers.e4.commander.pathTable.action.OpenPathMouseClickAction;

/**
 * TODO
 *
 */
public class PathSelectionUiBinding extends AbstractUiBindingConfiguration {

    @Override
    public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {

	// press Enter
	configureEnterBindings(uiBindingRegistry, new OpenPathAction());

	// double-click
	configureBodyMouseClickBindings(uiBindingRegistry,
		new OpenPathMouseClickAction());
    }

    protected void configureEnterBindings(UiBindingRegistry uiBindingRegistry,
	    IKeyAction action) {
	uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.NONE,
		SWT.CR), action);
	uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.MOD1,
		SWT.CR), action);
    }

    protected void configureBodyMouseClickBindings(
	    UiBindingRegistry uiBindingRegistry, IMouseAction action) {

	// added NoOpMouseAction on single click because of Bug 428901
	uiBindingRegistry.registerFirstSingleClickBinding(
		MouseEventMatcher.bodyLeftClick(SWT.NONE),
		new NoOpMouseAction());

	uiBindingRegistry.registerDoubleClickBinding(
		MouseEventMatcher.bodyLeftClick(SWT.NONE), action);
    }

}
