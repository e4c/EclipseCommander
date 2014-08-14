/*******************************************************************************
 * File: SelectFirstPathClickAction.java
 * 
 * Date: 14 авг. 2014 г.
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
import org.eclipse.nebula.widgets.nattable.ui.action.IMouseClickAction;
import org.eclipse.nebula.widgets.nattable.viewport.action.ViewportSelectRowAction;
import org.eclipse.nebula.widgets.nattable.viewport.command.ViewportSelectRowCommand;
import org.eclipse.swt.events.MouseEvent;

/**
 * It simply select first path.
 * 
 * @see ViewportSelectRowAction
 */
public class ViewportSelectFirstPathAction implements IMouseClickAction {

    private final boolean withShiftMask;
    private final boolean withControlMask;

    /**
     * Constructor
     *
     */
    public ViewportSelectFirstPathAction() {
	this(false, false);
    }

    /**
     * Constructor
     *
     * @param withShiftMask
     * @param withControlMask
     */
    public ViewportSelectFirstPathAction(boolean withShiftMask,
	    boolean withControlMask) {
	this.withShiftMask = withShiftMask;
	this.withControlMask = withControlMask;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.nebula.widgets.nattable.ui.action.IMouseAction#run(org.eclipse
     * .nebula.widgets.nattable.NatTable, org.eclipse.swt.events.MouseEvent)
     */
    @Override
    public void run(NatTable natTable, MouseEvent event) {
	// first row
	int rowPosition = 1;

	// only perform the selection if the cursor is null
	if (natTable.getCursor() == null) {
	    natTable.doCommand(new ViewportSelectRowCommand(natTable,
		    rowPosition, withShiftMask, withControlMask));
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.nebula.widgets.nattable.ui.action.IMouseClickAction#isExclusive
     * ()
     */
    @Override
    public boolean isExclusive() {
	return true;
    }

}
