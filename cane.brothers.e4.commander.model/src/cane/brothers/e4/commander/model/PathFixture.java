/*******************************************************************************
 * File: PathFixture.java
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
package cane.brothers.e4.commander.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Class wrapper for given path representation in the table row. One field per
 * column except path.
 *
 */
public class PathFixture {

    private Path path;

    private String name;
    private long size;
    private String attributes;

    /**
     * Constructor
     * 
     * @param path
     */
    public PathFixture(Path path) {
	this.path = path;

	if (path != null) {

	    // root path have no file name
	    this.name = PathUtils.getFileName(path);

	    try {
		this.size = Files.size(path);
	    }
	    catch (IOException ex) {
		this.size = 0;
		// TODO ex
	    }
	}
	else {
	    System.out.println("path is null");
	}

	this.attributes = PathUtils.getAttributesString(path);
    }

    public Path getPath() {
	return path;
    }

    public String getName() {
	return name;
    }

    public long getSize() {
	return size;
    }

    public String getAttributes() {
	return attributes;
    }

    @Override
    public String toString() {
	// TODO size to Kb/Mb
	return "[" + name + ", " + size + ", " + attributes + "]";
    }

}
