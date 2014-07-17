package cane.brothers.e4.efs.test2.parts;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class NIO2Part {

	private Text txtInput;
	private TableViewer tableViewer;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter path to see efs properties");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {

				if (e.getSource() == txtInput) {
					Text txt = (Text) e.getSource();
					Path path = null;
					try {
						path = Paths.get(txt.getText());
					} catch (InvalidPathException ex) {
						System.out.println(ex.getMessage());
						path = Paths.get("/");
					}

					tableViewer.getTable().removeAll();

					for (String str : createInput(path)) {
						tableViewer.add(str);
					}
					tableViewer.getControl().update();
				}
			}

			private List<String> createInput(Path path) {
				List<String> list = new ArrayList<>();

				if (path != null) {
					list.add("Path.fileName : " + path.getFileName());
					list.add("Path.fullName : " + path.toString());
					list.add("Path.fileSystem.separator : "
							+ path.getFileSystem().getSeparator());
					list.add("Path.root : " + path.getRoot());
					list.add("is Dir: " + Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
					list.add("is Absolute: " + path.isAbsolute());

					if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
						list.add("names:");
						DirectoryStream<Path> dir = null;
						try {
							dir = Files.newDirectoryStream(path);
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}

						if (dir != null) {
							for (Path filename : dir) {
								list.add("  " + filename);
								System.out.println(filename);
							}
						}
					}
				}
				return list;
			}

		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Focus
	public void onFocus() {
		tableViewer.getTable().setFocus();
	}

}