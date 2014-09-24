/*******************************************************************************
 * File: DuplicatePartHandler.java
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
package cane.brothers.e4.commander.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.preferences.PreferenceConstants;
import cane.brothers.e4.commander.utils.PartUtils;

/**
 * Open new tab directly after active part using PartDescriptor.
 * 
 * @see PartDescriptor
 * 
 */
public class DuplicatePartHandler {
    @Inject
    EPartService partService;

    @Inject
    @Preference(PreferenceConstants.PB_STAY_ACTIVE_TAB)
    boolean stayActiveTab;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {

	if (activePart != null && partService != null) {

	    // �������� ������� ������������ ������ ��� ����,
	    // ����� ���������� id � ��� ��� ����� �������.

	    // TODO use part service

	    // create a new Part based on a PartDescriptor
	    // in the application model
	    // assume the ID is used for the PartDescriptor
	    MPart newPart = partService
		    .createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
	    newPart = PartUtils.copyPart(newPart, activePart);

	    // ��������� ����� ������� �� ���� �������
	    // 1.
	    // newPart.setParent(part.getParent());

	    // 2.
	    MElementContainer<MUIElement> container = activePart.getParent();

	    if (container != null && container.getChildren() != null) {
		container.getChildren().add(newPart);
	    }

	    // 3. EPartService
	    // MPartStack stack = (MPartStack)modelService.find(stack_id,
	    // application);
	    // stack.getChildren().add(part);

	    // If multiple parts of this type are now allowed
	    // in the application model,
	    // then the provided part will be shown
	    // and returned
	    newPart = partService.showPart(newPart, PartState.VISIBLE);

	    // The current tab will stay active
	    partService.showPart(stayActiveTab ? activePart : newPart,
		    PartState.ACTIVATE);
	}
    }
}
