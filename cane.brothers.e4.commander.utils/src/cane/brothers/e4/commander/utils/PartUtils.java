/*******************************************************************************
 * File: PartUtils.java
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
package cane.brothers.e4.commander.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Part's util class.
 * 
 */
public class PartUtils {

    private static final Logger log = LoggerFactory.getLogger(PartUtils.class);

    // example for search by ID
    public static void findPartsById(MApplication application, EModelService service) {
        List<MPart> findElements = service.findElements(application, "mypart", //$NON-NLS-1$
                MPart.class, null);
        if (log.isInfoEnabled()) {
            log.info("Found part(s) : " + findElements.size()); //$NON-NLS-1$
        }
    }

    // example for search by type
    public static void findParts(MApplication application, EModelService service) {
        List<MPart> parts = service.findElements(application, null, MPart.class, null);
        if (log.isInfoEnabled()) {
            log.info("Found parts(s) : " + parts.size()); //$NON-NLS-1$
        }
    }

    // example for search by tag
    public static void findObjectsByTag(MApplication application, EModelService service) {
        List<String> tags = new ArrayList<String>();
        tags.add("justatag"); //$NON-NLS-1$
        List<MUIElement> elementsWithTags = service.findElements(application, null, null, tags);
        if (log.isInfoEnabled()) {
            log.info("Found parts(s) : " + elementsWithTags.size()); //$NON-NLS-1$
        }
    }
}
