package cane.brothers.e4.efs.test2.parts;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;

public class NIO2TablePart {

	private TableViewer sourceTableViewer;
	private TableViewer contentTableViewer;

	@Inject
	public NIO2TablePart() {
		// TODO Your code here
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		sourceTableViewer = new TableViewer(parent);
		sourceTableViewer.getTable().setLayoutData(
				new GridData(SWT.FILL, SWT.TOP, true, false));
		sourceTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						ISelection selection = event.getSelection();
						if (selection instanceof StructuredSelection) {
							StructuredSelection sel = (StructuredSelection) selection;
							if (sel.getFirstElement() instanceof String) {
								Path p = Paths.get((String) sel
										.getFirstElement());

								if (contentTableViewer != null) {
									contentTableViewer
											.setInput(createContentInput(p));
								}
							}
						}
						System.out.println(event.getSelection());
					}
				});
		for (String p : createSourceInput()) {
			sourceTableViewer.add(p);
		}

		contentTableViewer = new TableViewer(parent, SWT.BORDER
				| SWT.FULL_SELECTION);
		contentTableViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		contentTableViewer.setLabelProvider(new PathTableLabelProvider());

		// Set up the table
		Table table = contentTableViewer.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		// Add the first column
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setResizable(true);
		tc.setWidth(20);
		tc.setText("Type");

		// Add the second column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setResizable(true);
		tc.setWidth(200);
		tc.setText("Name");

//		// Pack the columns
//		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
//			table.getColumn(i).pack();
//		}

		// Turn on the header and the lines
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		// contentTableViewer.setUseHashlookup(true);
	}

	@Focus
	public void onFocus() {
		sourceTableViewer.getTable().setFocus();
	}

	private List<String> createSourceInput() {
		List<String> list = new ArrayList<>();
		list.add("C:\\");
		list.add("C:\\Cane");
		return list;
	}

	private List<Path> createContentInput(Path dir) {
		List<Path> result = new ArrayList<>();
		if (dir != null && Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
				for (Path entry : stream) {
					result.add(entry);
				}
			} catch (DirectoryIteratorException | IOException ex) {
				System.out.println(ex.getMessage());
			}

		}
		return result;
	}

}