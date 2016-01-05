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
package cane.brothers.e4.commander.event;

/**
 * E4 UI part event constants. Occurs on the part.
 *
 */
public final class PartEvents extends BaseEvents {

    /**
     * Base name of all path events
     */
    public static final String TOPIC_BASE_PART = TOPIC_BASE + "/part/"; //$NON-NLS-1$

    public static final String TOPIC_BASE_PART_PATH = TOPIC_BASE + "/part/path/";
    /**
     * Sent when opening new path in active tab.
     */
    public static final String TOPIC_PART_PATH_OPEN = TOPIC_BASE_PART_PATH + "open"; //$NON-NLS-1$

    /**
     * Sent when need to refresh path in visible tabs.
     */
    public static final String TOPIC_PART_PATH_REFRESH = TOPIC_BASE_PART_PATH + "refresh"; //$NON-NLS-1$

}
