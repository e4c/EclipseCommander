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
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

import cane.brothers.e4.commander.service.api.IPartService;

/**
 * Open new tab directly after active part using PartDescriptor.
 * 
 * TODO open after active tab or in the tail
 * 
 * @see PartDescriptor
 * 
 */
public class DuplicatePartHandler {

    @Inject
    IPartService partService;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {

	// TODO
	if (partService.copyPart(activePart)) {
	    System.out
		    .println("part was copied to opposite panel sucessfully!");
	}
	else {
	    System.out
		    .println("there are some problems on copying part to another panel");
	}
	//
	// if (activePart != null && partService != null) {
	//
	// // �������� ������� ������������ ������ ��� ����,
	// // ����� ���������� id � ��� ��� ����� �������.
	//
	// // TODO use part service
	//
	// // create a new Part based on a PartDescriptor
	// // in the application model
	// // assume the ID is used for the PartDescriptor
	// MPart newPart = partService
	// .createPart(IdStorage.DYNAMIC_PART_DESCRIPTOR_ID);
	// newPart = PartUtils.copyPart(newPart, activePart);
	//
	// // ��������� ����� ������� �� ���� �������
	// // 1.
	// // newPart.setParent(part.getParent());
	//
	// // 2.
	// MElementContainer<MUIElement> container = activePart.getParent();
	//
	// // 3.
	// if (container != null && container.getChildren() != null) {
	// container.getChildren().add(newPart);
	// }
	//
	// // 4. EPartService
	// // MPartStack stack = (MPartStack)modelService.find(stack_id,
	// // application);
	// // stack.getChildren().add(part);
	//
	// // If multiple parts of this type are now allowed
	// // in the application model,
	// // then the provided part will be shown
	// // and returned
	// newPart = partService.showPart(newPart, PartState.VISIBLE);
	//
	// // The current tab will stay active
	// partService.showPart(stayActiveTab ? activePart : newPart,
	// PartState.ACTIVATE);
	// }
    }
}
