/*******************************************************************************
 * File: DynamicTab.java
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
package cane.brothers.e4.commander.parts;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import cane.brothers.e4.commander.MyEventConstants;
import cane.brothers.e4.commander.PartUtils;
import cane.brothers.e4.commander.pathTable.PathNatTable;

/**
 * Dynamic Tab. GUI class of part descriptor implementation.
 * 
 */
public class DynamicTab {

    @Inject
    private IEventBroker eventBroker;

    @Inject
    EModelService modelService;

    @Inject
    MApplication application;

    /**
     * GUI stuff
     */
    private PathNatTable table;

    private Path rootPath;

    private final Color bgColor = Display.getCurrent().getSystemColor(
	    SWT.COLOR_WHITE);

    @Inject
    IEclipseContext context;

    @Inject
    public DynamicTab() {
    }

    @PostConstruct
    public void createPartControl(Composite parent) {

	parent.setBackground(bgColor);

	GridLayout gridLayout = new GridLayout();
	gridLayout.marginWidth = 0;
	parent.setLayout(gridLayout);

	// TODO switch context
	rootPath = Paths.get(context.get("rootPath").toString());
	// rootPath = Paths.get(activePart.getPersistedState().get("rootPath"));

	// create path table
	table = new PathNatTable(parent, rootPath, eventBroker);
	table.setBackground(bgColor);

	// layout
	GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
    }

    @Inject
    @Optional
    private void setRootPart(
	    @Named(IServiceConstants.ACTIVE_PART) MPart activePart,
	    @UIEventTopic(MyEventConstants.TAB_PATH_OPEN) Path newPath) {

	// update root path of current tab
	if (activePart != null && activePart.getObject() == this) {

	    rootPath = newPath;
	    if (table != null) {
		table.setRootPath(rootPath);
		table.refresh();
	    }
	}
    }

    /**
     * clear table selection
     */
    public void clearSelection() {
	if (table != null) {
	    table.clearSelection();
	}
    }

    @Focus
    private void setFocus(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
	if (table != null) {
	    table.setFocus();
	}

	// clear other selections
	if (activePart != null) {
	    List<MPart> oppositeTabs = PartUtils.findActiveOppositeTabs(
		    application, modelService, activePart);

	    // pass throw all opposite active tab and remove selections
	    for (MPart elem : oppositeTabs) {
		System.out.println(elem);
		if (elem.getObject() instanceof DynamicTab) {
		    System.out.println("remove selection from opposite tab");

		    DynamicTab oppositeTab = (DynamicTab) elem.getObject();
		    oppositeTab.clearSelection();
		}
	    }
	}
    }
}
