/*******************************************************************************
 * File: CopyPartHandler.java
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
package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.PartUtils;
import cane.brothers.e4.commander.preferences.PreferenceConstants;

/**
 * Copy tab to other panel using PartDescriptor.
 * 
 * @see PartDescriptor
 */
public class CopyPartHandler {

	@Inject
	EModelService modelService;

	@Inject
	MApplication application;

	@Inject
	EPartService partService;

	@Inject
	@Preference(PreferenceConstants.PB_STAY_ACTIVE_TAB)
	boolean stayActiveTab;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
		System.out.println((this.getClass().getSimpleName() + " called")); //$NON-NLS-1$

		MPart newPart = partService
		        .createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
		newPart = copyPart(newPart, activePart);

		// ��������� ����� ������� �� ������ �������

		String oppositePanelId = PartUtils.getPanelId(activePart, true);
		MUIElement oppositePanel = modelService.find(oppositePanelId,
		        application);

		if (oppositePanel instanceof MPartStack) {
			MPartStack stack = (MPartStack) oppositePanel;
			stack.getChildren().add(newPart);
		}

		partService.showPart(newPart, PartState.VISIBLE);

		// The current tab will stay active
		partService.showPart(stayActiveTab ? activePart : newPart,
		        PartState.ACTIVATE);
	}

	private MPart copyPart(MPart newPart, MPart part) {
		if (part != null) {

			newPart.setLabel(PartUtils.createPartLabel(part));
			newPart.setElementId(PartUtils.createElementId(part));

			// NB! copy also "active" tag
			newPart.getTags().addAll(part.getTags());
		}

		return newPart;
	}

}