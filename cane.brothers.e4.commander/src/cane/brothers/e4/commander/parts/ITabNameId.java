/*******************************************************************************
 * File: ITabNameId.java
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
package cane.brothers.e4.commander.parts;

/**
 * Defines tab name convention interface.
 * 
 */
public interface ITabNameId {

	public static final String ELEMENT_ID = "cane.brothers.e4.commander.part"
	        .intern();

	/**
	 * @return element id
	 */
	public String getElementId();

	/**
	 * tab name with new id specified for left or right side
	 * 
	 * @return tab name with new id
	 */
	public String getTabName();
}
