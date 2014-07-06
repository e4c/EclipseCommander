/*******************************************************************************
 * File: GeneralPreferencePage.java
 * 
 * Date: Jul 6, 2014
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
package cane.brothers.e4.commander.preferences.pages;

import org.eclipse.jface.preference.FieldEditorPreferencePage;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class GeneralPreferencePage extends FieldEditorPreferencePage {

	public GeneralPreferencePage() {
		super(GRID);
		// setTitle("General Settings");
		// setDescription("A demonstration of a preference page implementation");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {

		// addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH,
		// "&Directory preference:", getFieldEditorParent()));
		// addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
		// "&An example of a boolean preference", getFieldEditorParent()));
		//
		// addField(new RadioGroupFieldEditor(PreferenceConstants.P_CHOICE,
		// "An example of a multiple-choice preference", 1,
		// new String[][] { { "&Choice 1", "choice1" },
		// { "C&hoice 2", "choice2" } }, getFieldEditorParent()));
		// addField(new StringFieldEditor(PreferenceConstants.P_STRING,
		// "A &text preference:", getFieldEditorParent()));
	}
}