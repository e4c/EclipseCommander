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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * TODO
 */
public class PartServiceImpl implements IPartService {

    private static int tabsId = 1;

    @Inject
    EModelService modelService;

    @Inject
    MApplication application;

    @Inject
    private IEventBroker broker;

    private Set<MPart> openedParts = new LinkedHashSet<MPart>();

    /**
     * Constructor
     *
     */
    public PartServiceImpl() {
	// createInitialModel();
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
	    // broker.post(MyEventConstants.TOPIC_TODO_NEW, updateTodo);

	    result = true;
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

	part.setContributorURI(descriptor.getContributorURI());
	part.setCloseable(descriptor.isCloseable());
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
