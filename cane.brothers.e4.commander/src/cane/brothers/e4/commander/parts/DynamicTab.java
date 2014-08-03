/*******************************************************************************
 * File: DynamicTab.java
 * 
 * Date: Jul 5, 2014
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
 * Original authors and others - initial API and implementation
 ******************************************************************************/
package cane.brothers.e4.commander.parts;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import cane.brothers.e4.commander.pathTable.PathNatTable;

/**
 * Dynamic Tab
 * 
 */
public class DynamicTab implements ITabNameId {

	public static final String DYNAMIC_TAB_ID_PREF = ".dynamictab.".intern(); //$NON-NLS-1$
	private static int tabsId = 1;
	private static final String tabName = "Dynamic tab ".intern(); //$NON-NLS-1$

	private int currentTabId = -1;

	/**
	 * GUI stuff
	 */
	//private Label label;
	
	private PathNatTable table;
	
	private final Color bgColor = Display.getCurrent().getSystemColor(
	        SWT.COLOR_WHITE);
	
	

	@Inject
	public DynamicTab() {
		currentTabId = ++tabsId;
	}

	@PostConstruct
	public void createPartControl(Composite parent) {

		parent.setBackground(bgColor);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		parent.setLayout(gridLayout);
		
		Path homePath = Paths.get(System.getenv().get("HOME")); 
		table = new PathNatTable(parent, homePath);
		table.setBackground(bgColor);

//		label = new Label(parent, SWT.NONE);
//		label.setText("Dynamic tab");
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
	}

	@Override
	public String getElementId() {
		return ELEMENT_ID + DYNAMIC_TAB_ID_PREF + currentTabId;
	}

	@Override
	public String getTabName() {
		return tabName + currentTabId;
	}

}
