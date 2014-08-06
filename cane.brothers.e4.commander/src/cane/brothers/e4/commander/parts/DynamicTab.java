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

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import cane.brothers.e4.commander.pathTable.PathNatTable;

/**
 * Dynamic Tab. GUI class of part descriptor implementation.
 * 
 */
public class DynamicTab {

	/**
	 * GUI stuff
	 */
	private PathNatTable table;

	private Path rootPath;

	private final Color bgColor = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

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
		table = new PathNatTable(parent, rootPath);
		table.setBackground(bgColor);

		// layout
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
	}

	// @Inject
	// //@Optional
	// public void setPartInput( @Named( "inputPath" ) Object partInput ) {
	// int i1=1;
	// }

}
