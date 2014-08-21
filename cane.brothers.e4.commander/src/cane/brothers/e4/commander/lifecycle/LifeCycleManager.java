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

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.PartUtils;

/**
 * TODO
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

    /**
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

	// set default path
	Path rootPath = setDefaultPath();
	String rootPathString = null;
	
	if(rootPath != null) {
		
		if(rootPath.getFileName() != null) {
			rootPathString = rootPath.getFileName().toString();
		} else {
			rootPathString = rootPath.toString();
		}
		
		// FIXME set general context value
		context.set("rootPath", rootPath.toString());
		
		// persist default path
		// prefs.put(PreferenceConstants.PS_DEFAULT_TAB_PATH, rootPathString);
		
		//
		// create first parts, one left and one right
		//
		
		// left part
		MPart leftPart = PartUtils.createPart(modelService);
		
		leftPart.setLabel(rootPathString);
		leftPart.setElementId(PartUtils.createElementId());
		
		Map<String, String> state = leftPart.getPersistedState();
		state.put("rootPath", rootPath.toString());
		
		// add tab to left stack
		MPartStack leftPartStack = (MPartStack) modelService.find(
				IdStorage.LEFT_PANEL_ID, application);
		
		leftPartStack.getChildren().add(leftPart);
		
		// right part
		MPart rightPart = PartUtils.createPart(modelService);
		
		rightPart.setLabel(rootPathString);
		rightPart.setElementId(PartUtils.createElementId());
		
		state = rightPart.getPersistedState();
		state.put("rootPath", rootPath.toString());
		
		// add tab to right stack
		MPartStack rightPartStack = (MPartStack) modelService.find(
				IdStorage.RIGHT_PANEL_ID, application);
		rightPartStack.getChildren().add(rightPart);
	}


	// setAppWindowSize();
    }

    /**
     * Default path can be different for various OS
     * 
     * @return default path
     */
	private Path setDefaultPath() {
		Path defaultPath = Paths.get("");
		
		if(SystemUtils.IS_OS_WINDOWS) {
			defaultPath = Paths.get("C:\\"); //$NON-NLS-1$
		} 
		else if(SystemUtils.IS_OS_MAC_OSX) {
			defaultPath = Paths.get(System.getenv().get("HOME")); //$NON-NLS-1$
		}
		return defaultPath;
	}

    // private void setAppWindowSize() {
    // MWindow window = (MWindow) modelService.find(IdStorage.WINDOW_ID,
    // application);
    // window.setWidth(1000);
    // }

}
