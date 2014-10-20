/*******************************************************************************
 * File: PartServiceImpl.java
 * 
 * Date: 07 сент. 2014 г.
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
package cane.brothers.e4.commander.service.internal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MStackElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.IDynamicTab;
import cane.brothers.e4.commander.api.PartCopyType;
import cane.brothers.e4.commander.event.TabEvents;
import cane.brothers.e4.commander.preferences.PreferenceConstants;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Part service concrete realization
 */
public class PartServiceImpl implements IPartService {

    private static final Logger log = LoggerFactory
	    .getLogger(PartServiceImpl.class);

    // counter
    private static int tabsId = 1;

    @Inject
    EModelService modelService;

    @Inject
    MApplication application;

    @Inject
    private IEventBroker broker;

    @Inject
    EPartService partService;

    @Inject
    ITabService tabService;

    @SuppressWarnings("restriction")
    @Inject
    @Preference(PreferenceConstants.PB_STAY_ACTIVE_TAB)
    boolean stayActiveTab;

    private Set<MPart> openedParts = new LinkedHashSet<MPart>();

    /**
     * Constructor
     *
     */
    public PartServiceImpl() {
    }

    /**
     * Create id for the new tab.
     * 
     * @return element id for the new tab.
     */
    public static String createElementId() {
	return IdStorage.DYNAMIC_PART_ELEMENT_PREFIX_ID + tabsId++;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cane.brothers.e4.commander.service.api.IPartService#getParts()
     */
    @Override
    public Set<MPart> getParts() {
	return openedParts;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.IPartService#createPart(java.nio
     * .file.Path, java.lang.String)
     */
    @Override
    public boolean createPart(Path rootPath, String panelId) {
	boolean result = false;

	if (rootPath != null) {
	    String rootPathString = null;

	    rootPathString = PathUtils.getFileName(rootPath);

	    // create dynamic part
	    MPart part = createDynamicPart(modelService);

	    if (part != null) {
		// specify part
		part.setLabel(rootPathString);
		part.setElementId(createElementId());

		// TODO save state if part not private
		Map<String, String> state = part.getPersistedState();
		state.put(IdStorage.STATE_ROOT_PATH, rootPath.toString());

		// add tab to the part stack (panel)
		MPartStack panel = (MPartStack) modelService.find(panelId,
			application);
		panel.getChildren().add(part);

		//
		openedParts.add(part);

		// TODO Send out events
		broker.post(TabEvents.TOPIC_TAB_OPEN, part);

		result = true;
	    }
	    else {
		log.error("Unable to create dynamic part.");
	    }
	}

	return result;
    }

    /**
     * Allow to avoid using of EPartService, because it is not working in life
     * cycle manager.
     * 
     * @param modelService
     *            using for creating part descriptor and new empty part.
     * @return MPart created on part descriptor. There are no menus, handlers
     *         and toolbar.
     */
    @Inject
    private MPart createDynamicPart(EModelService modelService) {
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
	if (log.isDebugEnabled()) {
	    log.debug("ContributorURI: " + descriptor.getContributorURI()); //$NON-NLS-1$
	}
	part.setContributorURI(descriptor.getContributorURI());
	part.setCloseable(descriptor.isCloseable());
	if (log.isDebugEnabled()) {
	    log.debug("ContributionURI: " + descriptor.getContributionURI());
	}
	part.setContributionURI(descriptor.getContributionURI());
	part.setLabel(descriptor.getLabel());
	part.setIconURI(descriptor.getIconURI());
	part.setTooltip(descriptor.getTooltip());

	// part.getHandlers().addAll(EcoreUtil.copyAll(descriptor.getHandlers()));

	part.getTags().addAll(descriptor.getTags());

	// TODO set if property
	part.getPersistedState().putAll(descriptor.getPersistedState());
	part.getBindingContexts().addAll(descriptor.getBindingContexts());
	return part;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.IPartService#copyPart(org.eclipse
     * .e4.ui.model.application.ui.basic.MPart,
     * cane.brothers.e4.commander.api.PartCopyType)
     */
    @Override
    public boolean copyPart(MPart activePart, PartCopyType copyType) {

	// 1. copy part
	MPart newPart = copyPart(partService, activePart);

	// 2. get panel
	MPartStack panel = getPanel(modelService, application, activePart,
		copyType);

	// 3. add part into panel
	if (panel != null && panel.getChildren() != null) {
	    panel.getChildren().add(newPart);
	}

	// 4. add created part to opened parts set
	openedParts.add(newPart);

	// TODO Send out events
	// broker.post(MyEventConstants.TOPIC_TODO_NEW, updateTodo);

	// 6. show part
	showPart(partService, newPart, activePart);

	return true;
    }

    /**
     * Copy all available parameters from initial part to build part
     * 
     * @param partService
     *            EPartService
     * @param activePart
     *            initial part
     * @return MPart finite part
     */
    private MPart copyPart(EPartService partService, MPart activePart) {

	MPart newPart = partService
		.createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
	newPart = internalCopyPart(newPart, activePart);

	return newPart;
    }

    /**
     * Copy all necessary data from given part to new one
     * 
     * @param newPart
     * @param part
     * @return copy of part
     */
    /**
     * @param newPart
     * @param part
     * @return
     */
    private MPart internalCopyPart(MPart newPart, MPart part) {
	if (part != null) {

	    // TODO copy persist state
	    Map<String, String> state = part.getPersistedState();
	    if (state != null) {
		Path rootPath = null;
		String strRootPath = state.get("rootPath"); //$NON-NLS-1$

		if (strRootPath == null) {
		    IDynamicTab tab = tabService.getTab(part);
		    rootPath = tab.getRootPath();
		}
		else {
		    rootPath = Paths.get(strRootPath);
		}

		if (rootPath != null) {
		    newPart.setLabel(PathUtils.getFileName(rootPath));
		    newPart.setElementId(createElementId());

		    // NB! copy also "active" tag
		    newPart.getTags().addAll(part.getTags());
		}
	    }
	    else {
		log.warn("there are no root path in persisted states");
	    }
	}

	return newPart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.IPartService#getOppositePart(org
     * .eclipse.e4.ui.model.application.ui.basic.MPart)
     */
    @Override
    public MPart getOppositePart(MPart activePart) {
	MPart visiblePart = null;

	if (activePart != null) {
	    // find opposite panel
	    String oppositePanelId = getPanelId(activePart, PartCopyType.COPY);
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
     * @param opposite
     * @return
     */
    private String getPanelId(MPart part, PartCopyType copyType) {
	// panel id's
	String panelId = null;

	if (part != null && part.getParent() != null) {
	    panelId = part.getParent().getElementId();
	    if (PartCopyType.COPY == copyType) {
		panelId = getOppositePanelId(panelId);
	    }
	    if (log.isDebugEnabled()) {
		log.debug("panel id: " + panelId + //$NON-NLS-1$
			"; copy type: " + copyType); //$NON-NLS-1$
	    }
	}

	return panelId;
    }

    /**
     * @param panelId
     * @return
     */
    private String getOppositePanelId(String panelId) {
	if (panelId != null) {
	    return (panelId.equals(IdStorage.LEFT_PANEL_ID) ? IdStorage.RIGHT_PANEL_ID
		    : IdStorage.LEFT_PANEL_ID);
	}
	return panelId;
    }

    /**
     * @param partService
     * @param newPart
     * @param activePart
     */
    private void showPart(EPartService partService, MPart newPart,
	    MPart activePart) {

	partService.showPart(newPart, PartState.VISIBLE);

	// The current tab will stay active
	partService.showPart(stayActiveTab ? activePart : newPart,
		PartState.ACTIVATE);
    }

    /**
     * Returns opposite panel for given active part
     * 
     * @param modelService
     *            EModelService
     * @param app
     *            MApplication
     * @param activePart
     *            active MPart
     * @param copyType
     *            TODO
     * @return MPartStack panel
     */
    private MPartStack getPanel(EModelService modelService, MApplication app,
	    MPart activePart, PartCopyType copyType) {
	MPartStack panel = null;

	String oppositePanelId = getPanelId(activePart, copyType);
	MUIElement oppositeElement = modelService.find(oppositePanelId,
		application);

	if (oppositeElement instanceof MPartStack) {
	    panel = (MPartStack) oppositeElement;
	}

	return panel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.IPartService#getPart(java.lang
     * .String)
     */
    @Override
    public MPart getPart(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.service.api.IPartService#closePart(java.lang
     * .String)
     */
    @Override
    public boolean closePart(String id) {
	// TODO Auto-generated method stub
	return false;
    }

}
