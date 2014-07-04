/*******************************************************************************
 * File: CopyPartToOppositeHandler.java
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

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.PartUtils;

/**
 * Copy tab to other panel.
 * 
 */
public class CopyPartToOppositeHandler {

	@Inject
	EModelService modelService;

	@Inject
	MApplication application;

	// EPartService partService;

	@Execute
	public void execute(EPartService partService) {
		System.out.println((this.getClass().getSimpleName() + " called")); //$NON-NLS-1$

		MPart activePart = partService.getActivePart();

		MPart newPart = partService
		        .createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
		newPart = copyPart(newPart, activePart);

		// ƒобавл€ем новую вкладку на другую сторону

		String oppositePanelId = PartUtils.getPanelId(activePart, true);
		MUIElement oppositePanel = modelService.find(oppositePanelId,
		        application);

		if (oppositePanel instanceof MPartStack) {
			MPartStack stack = (MPartStack) oppositePanel;
			stack.getChildren().add(newPart);
		}

		partService.showPart(newPart, PartState.VISIBLE);
		// partService.showPart(newPart, PartState.ACTIVATE);
		partService.showPart(activePart, PartState.ACTIVATE);
	}

	private MPart copyPart(MPart newPart, MPart part) {
		if (part != null) {

			newPart.setLabel(PartUtils.createPartLabel(part));
			newPart.setElementId(PartUtils.createElementId(part));
			// newPart.setContributionURI(part.getContributionURI());
			// newPart.setCloseable(part.isCloseable());
		}

		return newPart;
	}

}
