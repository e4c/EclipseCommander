/*******************************************************************************
 * File: IRootPath.java
 * 
 * Date: 2014/08/11
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
package cane.brothers.e4.commander.pathTable;

import java.nio.file.Path;

/**
 * interface for current using path
 *
 */
public interface IRootPath {

    /**
     * 
     * @return current used path
     */
    public Path getRootPath();

    /**
     * @param path
     *            the new root path to set
     */
    public void setRootPath(Path newPath);
}
