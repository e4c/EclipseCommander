/*******************************************************************************
 * File: TabsPreferencePage.java
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
package cane.brothers.e4.commander.preferences.pages;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import cane.brothers.e4.commander.preferences.PreferenceConstants;

/**
 * Class contained tabs related preferences.
 * 
 */
public class TabsPreferencePage extends FieldEditorPreferencePage {

    /**
     * Constructor
     * 
     */
    public TabsPreferencePage() {
        super(GRID);
        // setTitle("Tabs Settings");
        setDescription("There are preferences present on this page to show tab's properies."); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors
     * ()
     */
    @Override
    protected void createFieldEditors() {
        addField(new BooleanFieldEditor(PreferenceConstants.PB_STAY_ACTIVE_TAB,
                "&Stay tab active", getFieldEditorParent())); //$NON-NLS-1$
    }

}
