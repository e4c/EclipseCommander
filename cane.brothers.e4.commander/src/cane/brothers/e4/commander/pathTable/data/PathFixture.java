package cane.brothers.e4.commander.pathTable.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cane.brothers.e4.commander.pathTable.utils.PathUtils;

public class PathFixture {
	
	private String name;
	private long size;
	private String attributes;
	
	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public PathFixture(Path path) {
		this.name = path.getFileName().toString();
		try {
			this.size = Files.size(path);
		} catch (IOException ex) {
			this.size = 0;
			// TODO ex
		}
		
		this.attributes = PathUtils.getAttributesString(path);
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
