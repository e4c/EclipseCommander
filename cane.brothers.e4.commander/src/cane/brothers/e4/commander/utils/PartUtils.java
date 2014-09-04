/*******************************************************************************
 * File: PartUtils.java
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
package cane.brothers.e4.commander.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.parts.DynamicTab;

/**
 * 
 * Part's utils class.
 * 
 */
public class PartUtils {

    public static final String ELEMENT_ID = "cane.brothers.e4.commander.part.dynamictab."
	    .intern();
    private static int tabsId = 1;

    /**
     * Create id for the new tab.
     * 
     * @return element id for the new tab.
     */
    public static String createElementId() {
	return ELEMENT_ID + tabsId++;
    }

    // @Inject
    // EModelService modelService;

    /**
     * Allow to avoid using of EPartService, because it is not working in life
     * cycle manager.
     * 
     * @param modelService
     *            using for creating part descriptor and new empty part.
     * @return MPart created on part descriptor. There are no menus, handlers
     *         and toolbar.
     */
    public static MPart createPart(EModelService modelService) {
	MPartDescriptor descriptor = modelService
		.getPartDescriptor(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
	if (descriptor == null) {
	    return null;
	}
	MPart part = modelService.createModelElement(MPart.class);
	part.setElementId(descriptor.getElementId());

	// part.getMenus().addAll(EcoreUtil.copyAll(descriptor.getMenus()));
	// if (descriptor.getToolbar() != null) {
	// part.setToolbar((MToolBar) EcoreUtil.copy((EObject)
	// descriptor.getToolbar()));
	// }

	part.setContributorURI(descriptor.getContributorURI());
	part.setCloseable(descriptor.isCloseable());
	part.setContributionURI(descriptor.getContributionURI());
	part.setLabel(descriptor.getLabel());
	part.setIconURI(descriptor.getIconURI());
	part.setTooltip(descriptor.getTooltip());

	// part.getHandlers().addAll(EcoreUtil.copyAll(descriptor.getHandlers()));

	part.getTags().addAll(descriptor.getTags());
	part.getPersistedState().putAll(descriptor.getPersistedState());
	part.getBindingContexts().addAll(descriptor.getBindingContexts());
	return part;
    }

    /**
     * Copy all necessary data from given part to new one
     * 
     * @param newPart
     * @param part
     * @return copy of part
     */
    public static MPart copyPart(MPart newPart, MPart part) {
	if (part != null) {

	    Map<String, String> state = part.getPersistedState();
	    if (state != null) {
		Path rootPath = null;
		String strRootPath = state.get("rootPath");

		if (strRootPath == null) {
		    DynamicTab tab = getTab(part);
		    rootPath = tab.getRootPath();
		}
		else {
		    rootPath = Paths.get(strRootPath);
		}

		if (rootPath != null) {
		    newPart.setLabel(PathUtils.getFileName(rootPath));
		    newPart.setElementId(PartUtils.createElementId());

		    // NB! copy also "active" tag
		    newPart.getTags().addAll(part.getTags());
		}
	    }
	    else {
		System.out
			.println("there are no root path in persisted states");
	    }
	}

	return newPart;
    }

    /**
     * @param part
     * @param opposite
     * @return
     */
    public static String getPanelId(MPart part, boolean opposite) {
	// panel id's
	String panelId = null;

	if (part != null && part.getParent() != null) {
	    panelId = part.getParent().getElementId();
	    if (opposite) {
		panelId = getOppositePanelId(panelId);
	    }
	    System.out.println("panel id: " + panelId + "; current: "
		    + !opposite);
	}

	return panelId;
    }

    /**
     * @param panelId
     * @return
     */
    private static String getOppositePanelId(String panelId) {
	if (panelId != null) {
	    return (panelId.equals(IdStorage.LEFT_PANEL_ID) ? IdStorage.RIGHT_PANEL_ID
		    : IdStorage.LEFT_PANEL_ID);
	}
	return panelId;
    }

    // example for search by ID
    public static void findPartsById(MApplication application,
	    EModelService service) {
	List<MPart> findElements = service.findElements(application, "mypart",
		MPart.class, null);
	System.out.println("Found part(s) : " + findElements.size());

    }

    // example for search by type
    public static void findParts(MApplication application, EModelService service) {
	List<MPart> parts = service.findElements(application, null,
		MPart.class, null);
	System.out.println("Found parts(s) : " + parts.size());

    }

    // example for search by tag
    public static void findObjectsByTag(MApplication application,
	    EModelService service) {
	List<String> tags = new ArrayList<String>();
	tags.add("justatag");
	List<MUIElement> elementsWithTags = service.findElements(application,
		null, null, tags);
	System.out.println("Found parts(s) : " + elementsWithTags.size());
    }

    // activePart must be not null
    public static MPart getVisibleOppositePart(MApplication application,
	    EModelService modelService, EPartService partService,
	    MPart activePart) {
	MPart visiblePart = null;

	if (activePart != null) {
	    // find opposite panel
	    String oppositePanelId = getPanelId(activePart, true);
	    MUIElement oppositePanel = modelService.find(oppositePanelId,
		    application);

	    if (oppositePanel instanceof MPartStack) {
		for (MStackElement elem : ((MPartStack) oppositePanel)
			.getChildren()) {
		    if (elem instanceof MPart) {
			MPart part = (MPart) elem;

			// get opposite visible part
			if (partService.isPartVisible(part)) {
			    visiblePart = part;
			    break;
			}
		    }
		}
	    }
	}
	return visiblePart;
    }

    /**
     * @param part
     *            MPart associated with DynamicTab
     * @return DynamicTab association
     */
    public static DynamicTab getTab(MPart part) {
	DynamicTab tab = null;
	if (part != null) {
	    if (part.getObject() instanceof DynamicTab) {
		tab = (DynamicTab) part.getObject();
	    }
	    else {
		System.out
			.println("Error: it is not a kind of DynamicTab part");
	    }
	}
	else {
	    System.out.println("Error: part is null");
	}
	return tab;
    }

    /**
     * Clear table selection on the tab
     * 
     * @param part
     *            MPart associated with DynamicTab
     */
    public static void clearSelection(MPart part) {
	DynamicTab tab = getTab(part);
	if (tab != null) {
	    tab.clearSelection();
	}
    }

    /**
     * Set table focus and selection on the tab
     * 
     * @param part
     *            MPart associated with DynamicTab
     */
    public static void setSelection(MPart part) {
	DynamicTab tab = getTab(part);
	if (tab != null) {
	    tab.setFocus();
	    tab.setSelection();
	}
    }
}
