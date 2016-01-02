/*******************************************************************************
 * File: OpenPathAction.java
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
package cane.brothers.e4.commander.pathTable.action;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.nebula.widgets.nattable.viewport.command.ViewportSelectRowCommand;
import org.eclipse.swt.events.KeyEvent;

import cane.brothers.e4.commander.pathTable.command.OpenPathCommand;

/**
 * Open new path by keys press action.
 *
 */
public class OpenPathAction implements IKeyAction {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction#run(org.eclipse
     * .nebula.widgets.nattable.NatTable, org.eclipse.swt.events.KeyEvent)
     */
    @Override
    public void run(NatTable natTable, KeyEvent event) {
        // open new path
        natTable.doCommand(new OpenPathCommand());

        // select first
        if (natTable.getCursor() == null) {
            natTable.doCommand(new ViewportSelectRowCommand(natTable, 1, false, false));
        }
    }

}
