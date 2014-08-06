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
package cane.brothers.e4.commander.test.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import cane.brothers.e4.commander.PartUtils;
import cane.brothers.e4.commander.parts.ITabNameId;

/**
 * Open new tab directly after active part.
 * 
 * @see ITabNameId
 * 
 */
public class CopyPartHandler {
	@Inject
	EPartService partService;

	@Execute
	// public void execute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
	public void execute() {
		System.out.println((this.getClass().getSimpleName() + " called"));
		MPart part = partService.getActivePart();

		MPart newPart = copyPart(part);
		partService.showPart(newPart, PartState.VISIBLE);
		partService.showPart(part, PartState.ACTIVATE);
	}

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) MPart part) {
		return partService.isPartVisible(part);
	}

	private MPart copyPart(MPart part) {
		MPart newPart = MBasicFactory.INSTANCE.createPart();

		newPart.setLabel("test");
		
		newPart.setElementId(PartUtils.createElementId());
		//newPart.setElementId(PartUtils.createElementId(part));
		newPart.setContributionURI(part.getContributionURI());
		newPart.setCloseable(part.isCloseable());
		// NB ������ ���� ����� parent, ����� �������� �������� �� ���� �������
		newPart.setParent(part.getParent());

		return newPart;
	}
}