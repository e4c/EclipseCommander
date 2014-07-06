/*******************************************************************************
 * File: PreferenceDialog.java
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
package cane.brothers.e4.commander.test;

import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.BackingStoreException;

/**
 * The eclipse commander preference dialog
 * 
 */
public class ECPreferenceDialog extends TitleAreaDialog {

	@Inject
	@Preference
	IEclipsePreferences preferences;

	// @Inject
	// Logger log;

	private Button tabStayActiveCheckBox;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public ECPreferenceDialog(Shell parentShell) {
		super(parentShell);
		setTitle("Set Preferences	");
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tabStayActiveCheckBox = new Button(container, SWT.CHECK);

		if (preferences != null) {
			tabStayActiveCheckBox.setSelection(preferences.getBoolean(
			        "stay_active_tab", true));
		}
		// tabStayActiveCheckBox.
		// .setText("jdbc:derby:memory:test;create=true");
		// tabStayActiveCheckBox.setLayoutData(new GridData(SWT.FILL,
		// SWT.CENTER, true, false, 1,
		// 1));

		setMessage("Set preferences");
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
		        true);
		createButton(parent, IDialogConstants.CANCEL_ID,
		        IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void okPressed() {

		try {
			preferences.putBoolean("stay_active_tab",
			        tabStayActiveCheckBox.getSelection());
			preferences.flush();
		}
		catch (BackingStoreException e) {
			// log.log(Level.WARNING, e.getMessage());
			e.printStackTrace();
		}
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}
