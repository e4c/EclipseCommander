/*******************************************************************************
 * File: TabServiceContextFunction.java
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

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;

import cane.brothers.e4.commander.service.api.ITabService;

/**
 * TODO
 */
public class TabServiceContextFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {

	ITabService partService = ContextInjectionFactory.make(
		TabServiceImpl.class, context);

	MApplication app = context.get(MApplication.class);
	if (app != null) {
	    IEclipseContext appCtx = app.getContext();
	    if (appCtx != null) {
		appCtx.set(ITabService.class, partService);
	    }
	}

	return partService;
    }

}
