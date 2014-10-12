/*******************************************************************************
 * File: CopyPartHandler.java
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
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

import cane.brothers.e4.commander.api.PartCopyType;
import cane.brothers.e4.commander.service.api.IPartService;

/**
 * Copy tab to other panel using part service.
 * 
 * @see IPartService
 */
public class CopyPartHandler {

    @Inject
    IPartService partService;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
	System.out.println((this.getClass().getSimpleName() + " called")); //$NON-NLS-1$

	if (partService.copyPart(activePart, PartCopyType.COPY)) {
	    System.out
		    .println("part was copied to opposite panel sucessfully!");
	}
	else {
	    System.out
		    .println("there are some problems on copying part to another panel");
	}

    }

}
