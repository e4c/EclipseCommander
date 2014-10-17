/*******************************************************************************
 * File: EclipseAppender.java
 * 
 * Date: 14 окт. 2014 г.
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
package cane.brother.e4.commander.logback.appender;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * "This sourcecode is released under EPL."
 * 
 * @author David Virdefors, www.oixx.se, Copyright 2012 Appends logback logging
 *         events to Eclipse RCP logging events. This makes the logs visible in
 *         the Eclipse GUI in the <strong>Error Log</strong> view as well as in
 *         the log files in your workspace.
 */
public class EclipseAppender extends AppenderBase<ILoggingEvent> {

    private static final int DEBUG = 10000;
    private static final int INFO = 20000;
    private static final int WARN = 30000;
    private static final int ERROR = 40000;

    @Override
    protected void append(ILoggingEvent event) {
	Bundle bundle = Platform
		.getBundle("cane.brother.e4.commander.logback.config");
	if (bundle == null) {
	    System.out
		    .println("No bundle found when trying to log to eclipse.");
	}
	else {
	    ILog log = Platform.getLog(bundle);
	    log.log(new Status(getStatus(event), event.getLoggerName(), event
		    .getMessage()));
	}
    }

    /**
     * Converts logback status to Eclipse IStatus. Debug will become info.
     * 
     * @param event
     *            including the level/status
     * @return the IStatus value as an int.
     */
    private int getStatus(ILoggingEvent event) {
	final int level = event.getLevel().levelInt;
	if (level == ERROR) {
	    return IStatus.ERROR;
	}
	else if (level == INFO) {
	    return IStatus.INFO;
	}
	else if (level == DEBUG) {
	    return IStatus.INFO;
	}
	else if (level == WARN) {
	    return IStatus.WARNING;
	}
	return IStatus.INFO;
    }
}
