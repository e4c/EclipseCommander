package cane.brothers.e4.commander.pathTable.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cane.brothers.e4.commander.pathTable.utils.PathUtils;

/**
 * @author icane
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
		
		if(path != null) {
			if(path.getFileName() != null) {
				this.name = path.getFileName().toString();
			} else {
				System.out.println("error file name");
			}
			try {
				this.size = Files.size(path);
			} catch (IOException ex) {
				this.size = 0;
				// TODO ex
			}
		} else {
			System.out.println("error path");
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
