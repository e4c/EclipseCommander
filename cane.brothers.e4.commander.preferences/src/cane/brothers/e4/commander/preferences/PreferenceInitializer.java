/*******************************************************************************
 * File: PreferenceInitializer.java
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
package cane.brothers.e4.commander.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;

import com.opcoach.e4.preferences.ScopedPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    private static final Logger log = LoggerFactory.getLogger(PreferenceInitializer.class);

    /**
     * default constructor
     */
    public PreferenceInitializer() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
     * initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        if (log.isDebugEnabled()) {
            log.debug("Enter in default Preference Initializer"); //$NON-NLS-1$
        }

        IPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, IdStorage.PREF_PLUGIN_ID);

        store.setDefault(PreferenceConstants.PB_STAY_ACTIVE_TAB, true);
    }
}
