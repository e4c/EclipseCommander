/*******************************************************************************
 * File: PartServiceContextFunction.java
 * 
 * Date: 07 сент. 2014 г.
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

import cane.brothers.e4.commander.service.api.IPartService;

/**
 * Part service context function
 */
public class PartServiceContextFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {

        // delegate to ContextInjectionFactory
        // instead of creating a new PartServiceImpl with the new operator.
        // because PartServiceImpl itself uses injection
        IPartService partService = ContextInjectionFactory.make(PartServiceImpl.class, context);

        // cache the newly created part Service in the application context for
        // the next time somebody requests it (this prevents this
        // ContextFunction
        // from being called again).

        // get application ctx (to make it available to all parts)
        // and register partService obj with appCtx
        MApplication app = context.get(MApplication.class);
        if (app != null) {
            IEclipseContext appCtx = app.getContext();
            if (appCtx != null) {
                appCtx.set(IPartService.class, partService);
            }
        }

        // return model for current invocation
        // next invocation uses object from application context
        return partService;
    }

}
