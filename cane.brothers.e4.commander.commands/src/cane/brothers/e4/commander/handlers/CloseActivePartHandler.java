/*******************************************************************************
 * File: CloseActivePartHandler.java
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

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

/**
 * Close active tab handler.
 * 
 */
public class CloseActivePartHandler {
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part, EPartService partService) {
        // close active part
        partService.hidePart(part);
    }

    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part, EPartService partService) {
        return partService.isPartVisible(part);
    }

}