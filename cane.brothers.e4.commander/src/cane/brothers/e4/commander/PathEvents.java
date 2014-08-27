/*******************************************************************************
 * File: MyEventConstants.java
 * 
 * Date: 2014/08/11
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
package cane.brothers.e4.commander;

/**
 * E4 UI path event constants.
 *
 */
public final class PathEvents {

    /**
     * Base name of all app events
     */
    public static final String TOPIC_BASE = "cane/brothers/e4/commander/ui"; //$NON-NLS-1$

    /**
     * Base name of all path events
     */
    public static final String PATH_TOPIC_BASE = TOPIC_BASE + "/tab/path/"; //$NON-NLS-1$

    /**
     * Sent when opening new path in active tab.
     */
    public static final String TAB_PATH_OPEN = PATH_TOPIC_BASE + "open"; //$NON-NLS-1$

    /**
     * Sent when necessary to remove selection from opposite tab.
     */
    public static final String TAB_REMOVE_SELECTION = PATH_TOPIC_BASE
	    + "selection/remove"; //$NON-NLS-1$

}
