/**
 * 
 */
package cane.brothers.e4.efs.test2.parts;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Mikhail Niedre
 *
 */
public class PathTableLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String text = null;
		Path path = (Path) element;
		
		if (path != null) {
			switch (columnIndex) {
			// type
			case 0:
				if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
					text = "d";
				} else {
					text = "f";
				}
				break;
			// name
			case 1:
				text = path.getFileName().toString();
				break;
			default:
				break;
			}
		}
		return text;
	}


}
