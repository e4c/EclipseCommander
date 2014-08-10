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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.PartUtils;
import cane.brothers.e4.commander.preferences.PreferenceConstants;

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
	System.out.println((this.getClass().getSimpleName() + " called"));

	// �������� ������� ������������ ������ ��� ����,
	// ����� ���������� id � ��� ��� ����� �������.

	// create a new Part based on a PartDescriptor
	// in the application model
	// assume the ID is used for the PartDescriptor
	MPart newPart = partService
		.createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
	newPart = copyPart(newPart, activePart);

	// If multiple parts of this type are now allowed
	// in the application model,
	// then the provided part will be shown
	// and returned
	newPart = partService.showPart(newPart, PartState.VISIBLE);

	// The current tab will stay active
	partService.showPart(stayActiveTab ? activePart : newPart,
		PartState.ACTIVATE);
    }

    private MPart copyPart(MPart newPart, MPart part) {
	if (part != null) {

	    // load root path
	    Map<String, String> state = part.getPersistedState();
	    Path rootPath = Paths.get(state.get("rootPath"));
	    String rootPathString = rootPath.getFileName().toString();
	    newPart.setLabel(rootPathString);
	    // newPart.setLabel(PartUtils.createPartLabel(part));

	    newPart.setElementId(PartUtils.createElementId());
	    // newPart.setElementId(PartUtils.createElementId(part));

	    // NB! copy also "active" tag
	    newPart.getTags().addAll(part.getTags());

	    /**
	     * ������� ContributionURI ��, ��� �����, ���������� id view part,
	     * ������� ����� �������������� ��� �������� ����� �������.
	     */
	    // ������ ContributionURI ����� �����, ����� ������� ����� ��������
	    // ������ ������� - ���������� ��� PartDescriptor id - ���� �����
	    // newPart.setContributionURI(part.getContributionURI());

	    if (part.getParent() != null) {
		System.out.println("Parent id: "
			+ part.getParent().getElementId());
	    }

	    // newPart.setCloseable(part.isCloseable());
	    // ��������� ����� ������� �� ���� �������
	    // 1.
	    newPart.setParent(part.getParent());

	    // 2.
	    // MElementContainer<MUIElement> container = part.getParent();
	    // container.getChildren().add(newPart);

	    // 3. EPartService
	    // MPartStack stack = (MPartStack)modelService.find(stack_id,
	    // application);
	    // stack.getChildren().add(part);
	}

	return newPart;
    }
}
