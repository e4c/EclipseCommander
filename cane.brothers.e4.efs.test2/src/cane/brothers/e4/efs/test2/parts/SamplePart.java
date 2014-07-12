/*******************************************************************************
 * Copyright (c) 2010 - 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Lars Vogel <lars.Vogel@gmail.com> - Bug 419770
 *******************************************************************************/
package cane.brothers.e4.efs.test2.parts;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SamplePart {

	private Text txtInput;
	private TableViewer tableViewer;
	private URI uri;
	private IFileSystem fileSystem;
	private IFileStore fileStore;

	@PostConstruct
	public void createComposite(Composite parent) {
		fileSystem = EFS.getLocalFileSystem();

		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {

				if (e.getSource() == txtInput) {
					Text txt = (Text) e.getSource();
					try {
						uri = URI.create(txt.getText());
					}
					catch (IllegalArgumentException ex2) {
						System.out.println(ex2.getMessage());
						uri = URI.create("/");
					}

					try {
						fileStore = fileSystem.getStore(uri);
					}
					catch (Exception ex) {
						System.out.println(ex.getMessage());
					}

					tableViewer.getTable().removeAll();

					for (String str : createInput(fileStore)) {
						tableViewer.add(str);
					}
					tableViewer.getControl().update();
				}
			}

			private List<String> createInput(URI uri) {
				List<String> list = new ArrayList<>();

				if (uri != null) {
					list.add("Uri.Scheme : " + uri.getScheme());
					list.add("Uri.Path : " + uri.getPath());
					list.add("Uri.Host : " + uri.getHost());
					list.add("Uri.RawUserInfo: " + uri.getRawUserInfo());
				}

				return list;
			}

			private List<String> createInput(IFileStore fileStore) {
				List<String> list = new ArrayList<>();

				if (fileStore != null) {
					list.add("FileStore.name : " + fileStore.getName());

					if (fileStore.getParent() != null) {
						list.add("FileStore.parent: "
						        + fileStore.getParent().getName());
					}

					if (fileStore.fetchInfo() != null) {
						list.add("FileStore.LastModified : "
						        + fileStore.fetchInfo().getLastModified());
						list.add("FileStore.Length : "
						        + fileStore.fetchInfo().getLength());
						list.add("FileStore.exist: "
						        + fileStore.fetchInfo().exists());
						list.add("FileStore.isDir: "
						        + fileStore.fetchInfo().isDirectory());
					}

					list.add("names:");
					try {
						String[] names = fileStore.childNames(EFS.NONE, null);
						for (String name : names) {
							list.add(fileStore.toString() + "  " + name);
						}
					}
					catch (CoreException e) {
						System.out.println(e.getMessage());
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
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}
}