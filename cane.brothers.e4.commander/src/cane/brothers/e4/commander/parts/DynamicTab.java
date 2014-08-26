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
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import cane.brothers.e4.commander.MyEventConstants;
import cane.brothers.e4.commander.pathTable.PathNatTable;
import cane.brothers.e4.commander.pathTable.data.PathFixture;
import cane.brothers.e4.commander.utils.PartUtils;

/**
 * Dynamic Tab. GUI class of part descriptor implementation.
 * 
 */
public class DynamicTab {

    @Inject
    private IEventBroker eventBroker;

    @Inject
    private EModelService modelService;

    @Inject
    private MApplication application;

    @Inject
    private IEclipseContext context;

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    private ESelectionService selectionService;

    @Inject
    private EPartService partService;

    /**
     * GUI stuff
     */
    private PathNatTable table;

    /**
     * @return the table
     */
    public PathNatTable getTable() {
	return table;
    }

    private Path rootPath;

    private final Color bgColor = Display.getCurrent().getSystemColor(
	    SWT.COLOR_WHITE);

    @Inject
    public DynamicTab() {
    }

    @PostConstruct
    public void createPartControl(Composite parent) {

	Composite panel = new Composite(parent, SWT.NONE);
	// panel.setLayout(new GridLayout(2, true));

	panel.setBackground(bgColor);

	GridLayout gridLayout = new GridLayout();
	gridLayout.marginWidth = 0;
	panel.setLayout(gridLayout);

	// TODO switch context
	rootPath = Paths.get(context.get("rootPath").toString());
	// rootPath = Paths.get(activePart.getPersistedState().get("rootPath"));

	// create path table
	table = new PathNatTable(panel, rootPath, eventBroker);
	table.setBackground(bgColor);

	// attach a selection listener to our table
	table.getSelectionProvider().addSelectionChangedListener(
		new ISelectionChangedListener() {
		    @Override
		    public void selectionChanged(SelectionChangedEvent event) {
			System.out.println("Selection changed:");

			IStructuredSelection selection = (IStructuredSelection) event
				.getSelection();

			// set the selection to the service
			selectionService.setSelection(selection.size() == 1 ? selection
				.getFirstElement() : selection.toArray());

			for (Object sel : selection.toArray()) {
			    if (sel instanceof PathFixture) {
				System.out.println("   " + sel);
			    }
			}
		    }
		});

	// layout
	GridDataFactory.fillDefaults().grab(true, true).applyTo(table);

	// add a region for buttons
	Composite buttonArea = new Composite(panel, SWT.NONE);
	buttonArea.setLayout(new RowLayout());
	GridDataFactory.fillDefaults().grab(true, false).span(2, 1)
		.applyTo(buttonArea);

	// create a button to enable selection provider change
	Button button = new Button(buttonArea, SWT.PUSH);
	button.setText("focus");
	button.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {

		// clear current selection
		PartUtils.clearSelection(activePart);
		System.out.println("remove selection from current tab");

		MPart oppositePart = PartUtils.getVisibleOppositePart(
			application, modelService, partService, activePart);

		// TODO will be not necessary after #31
		// clear opposite part selection
		PartUtils.clearSelection(oppositePart);
		System.out.println("remove selection from opposite tab");

		// set opposite tab focus and selection
		PartUtils.setSelection(oppositePart);
		System.out
			.println("set focus and default selection on opposite tab");
	    }
	});
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

    /**
     * set default table selection
     */
    public void setSelection() {
	if (table != null) {
	    table.setDefaultSelection();
	    System.out.println("set default table selection");
	}
    }

    @Focus
    public void setFocus() {
	if (table != null) {
	    if (table.setFocus()) {
		System.out.println("set focus for table");
	    }
	}
    }

    @Override
    public String toString() {
	return super.toString() + " "
		+ (rootPath != null ? rootPath.toString() : rootPath);
    }

}
