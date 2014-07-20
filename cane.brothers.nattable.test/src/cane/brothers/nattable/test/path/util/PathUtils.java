package cane.brothers.nattable.test.path.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;

public class PathUtils {

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

}
