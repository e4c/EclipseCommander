/*******************************************************************************
 * File: LifeCycleManager.java
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
package cane.brothers.e4.commander.lifecycle;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.IDynamicTab;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Life cycle manager class.
 * 
 */
public class LifeCycleManager {

    // @Inject
    // MApplication application;

    // @Inject
    // EModelService modelService;

    // @Inject
    // EPartService partService;

    // @Inject
    // @Preference(PreferenceConstants.PS_DEFAULT_TAB_PATH)
    // boolean defaultTabPath;

    // @Inject
    // @Optional
    // IPartService partService;

    // @Inject
    // private ITabService tabService;

    /**
     * Constructor
     *
     */
    public LifeCycleManager() {
	System.out.println("life cycle manager");
    }

    @PreSave
    public void preSave(MApplication application, EModelService modelService,
	    EPartService partService) {
	System.out.println("preSave()");
	// setAppWindowSize();
    }

    @ProcessRemovals
    public void processRemovals() {
	System.out.println("processRemovals()");

	// set default path
	// Path rootPath = PathUtils.getDefaultPath();
	//
	// partService = Activator.getDefault().getPartService();
	// if (partService != null) {
	// partService.createPart(rootPath, IdStorage.LEFT_PANEL_ID);
	// partService.createPart(rootPath, IdStorage.RIGHT_PANEL_ID);
	// }
    }

    @PostContextCreate
    public void postContextCreate(MApplication application,
	    EModelService modelService, EPartService partService) {
	System.out.println("postContextCreate()");

    }

    @ProcessAdditions
    public void processAdditions(MApplication application,
	    EModelService modelService, IEclipseContext context) {

	System.out.println("processAdditions()");

	createInitialModel(context);

	System.out.println(application.getDescriptors());
	// setAppWindowSize();
    }

    /**
     * create initial path model
     * 
     * @param context
     *            eclipse context
     */
    private void createInitialModel(IEclipseContext context) {
	// get default path
	Path rootPath = PathUtils.getDefaultPath();

	// FIXME set general context value
	context.set("rootPath", rootPath.toString());

	// path service already exist in eclipse context
	IPartService partService = context.get(IPartService.class);
	if (partService != null) {

	    partService.createPart(rootPath, IdStorage.LEFT_PANEL_ID);
	    partService.createPart(rootPath, IdStorage.RIGHT_PANEL_ID);
	}
    }

    // private void setAppWindowSize() {
    // MWindow window = (MWindow) modelService.find(IdStorage.WINDOW_ID,
    // application);
    // window.setWidth(1000);
    // }

    @PostConstruct
    private void setDefaultSelection(final IEventBroker eventBroker,
	    final IEclipseContext context) {
	// System.out.println("1. postConstruct");

	// subscribe once for default MPart activation
	eventBroker.subscribe(UIEvents.UILifeCycle.ACTIVATE,
		new EventHandler() {
		    @Override
		    public void handleEvent(Event event) {
			// System.out.println("2. handleEvent");

			if (event != null && event.getPropertyNames() != null) {
			    // get first MPart
			    Object obj = event.getProperty(event
				    .getPropertyNames()[0]);
			    if (obj instanceof MPart) {

				// tab service already exist in eclipse context
				ITabService tabService = context
					.get(ITabService.class);
				if (tabService != null) {
				    IDynamicTab tab = tabService
					    .getTab((MPart) obj);
				    // System.out.println("3. " + tab);

				    // set default selection
				    tab.setSelection();
				}
			    }
			}
			eventBroker.unsubscribe(this);
		    }
		});
    }

}