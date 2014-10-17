/*******************************************************************************
 * File: LogManager.java
 * 
 * Date: 12 окт. 2014 г.
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
package cane.brothers.e4.commander.logging;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;

/**
 * TODO
 */
public class LoggerManager {

    private static final LoggerManager INSTANCE = new LoggerManager();
    private Logger logger;

    public static LoggerManager getLogger() {
	return INSTANCE;
    }

    /**
     * Constructor
     *
     */
    private LoggerManager() {
    }

    public void init() {
	if (this.logger == null) {
	    try {
		Bundle bundle = FrameworkUtil.getBundle(LoggerManager.class);
		bundle.start();

		BundleContext bundleContext = bundle.getBundleContext();

		IEclipseContext eclipseContext = EclipseContextFactory
			.getServiceContext(bundleContext);
		this.logger = ContextInjectionFactory.make(
			WorkbenchLogger.class, eclipseContext);
	    }
	    catch (BundleException e) {
		e.printStackTrace();
	    }
	    catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /*
     * ERROR
     */
    public void error(String message) {
	if (logger != null) {
	    logger.error(message);
	}
    }

    public void error(Throwable t, String message) {
	if (logger != null) {
	    logger.error(t, message);
	}
    }

    public void error(Throwable t) {
	if (logger != null) {
	    logger.error(t, null);
	}
    }

    /*
     * WARNING
     */
    public void warn(String message) {
	if (logger != null// && logger.isWarnEnabled()
	) {
	    logger.warn(message);
	}
    }

    public void warn(Throwable t, String message) {
	if (logger != null// && logger.isWarnEnabled()
	) {
	    logger.warn(t, message);
	}
    }

    public void warn(Throwable t) {
	if (logger != null// && logger.isWarnEnabled()
	) {
	    logger.warn(t, null);
	}
    }

    /*
     * DEBUG
     */
    public void debug(String message) {
	if (logger != null// && logger.isDebugEnabled()
	) {
	    logger.debug(message);
	}
    }

    public void debug(Throwable t, String message) {
	if (logger != null// && logger.isDebugEnabled()
	) {
	    logger.debug(t, message);
	}
    }

    public void debug(Throwable t) {
	if (logger != null// && logger.isDebugEnabled()
	) {
	    logger.debug(t, null);
	}
    }

    /*
     * INFO
     */
    public static void info(Logger logger, String message) {
	if (logger != null// && logger.isInfoEnabled()
	) {
	    logger.info(message);
	}
    }

    public static void info(Logger logger, Throwable t, String message) {
	if (logger != null// && logger.isInfoEnabled()
	) {
	    logger.info(t, message);
	}
    }

    public static void info(Logger logger, Throwable t) {
	if (logger != null// && logger.isInfoEnabled()
	) {
	    logger.info(t, null);
	}
    }

}
