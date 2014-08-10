/*******************************************************************************
 * File: PathUtils.java
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
package cane.brothers.e4.commander.pathTable.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * TODO use apache common some-how to check OS
 */
public class PathUtils {

    public static String getAttributesString(Path path) {
	Set<String> views = FileSystems.getDefault()
		.supportedFileAttributeViews();

	if (views.contains("posix")) {
	    return getPosixAttributesString(path);
	}
	else {
	    return getDosAttributesString(path);
	}
    }

    public static String getDosAttributesString(Path path) {
	DosFileAttributeView basicView = Files.getFileAttributeView(path,
		DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
	StringBuilder attrs = new StringBuilder();

	try {
	    // + all basic attributes
	    DosFileAttributes dosAttrs = basicView.readAttributes();

	    attrs.append(dosAttrs.isReadOnly() ? "r" : "-");
	    attrs.append(dosAttrs.isHidden() ? "h" : "-");
	    attrs.append(dosAttrs.isArchive() ? "a" : "-");
	    attrs.append(dosAttrs.isSystem() ? "s" : "-");
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	return attrs.toString();
    }

    public static String getPosixAttributesString(Path path) {
	PosixFileAttributeView basicView = Files.getFileAttributeView(path,
		PosixFileAttributeView.class);
	StringBuilder attrs = new StringBuilder();

	try {
	    // + all basic attributes
	    PosixFileAttributes posixAttrs = basicView.readAttributes();

	    attrs.append(PosixFilePermissions.toString(posixAttrs.permissions()));
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	return attrs.toString();
    }
}
