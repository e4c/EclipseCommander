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

public class PathUtils {

	public static String getAttributesString(Path path) {
		Set<String> views = FileSystems.getDefault().supportedFileAttributeViews();
		
		if (views.contains("posix")) {
			return getPosixAttributesString(path);
		} else {
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
		} catch (IOException e) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attrs.toString();
	}
}
