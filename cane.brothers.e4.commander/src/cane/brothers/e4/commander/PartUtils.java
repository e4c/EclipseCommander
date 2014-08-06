/*******************************************************************************
 * File: PartUtils.java
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
package cane.brothers.e4.commander;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

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
}
