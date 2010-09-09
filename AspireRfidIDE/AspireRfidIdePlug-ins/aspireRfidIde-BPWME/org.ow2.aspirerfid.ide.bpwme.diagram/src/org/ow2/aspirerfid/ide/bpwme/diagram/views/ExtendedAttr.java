package org.ow2.aspirerfid.ide.bpwme.diagram.views;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;
import org.ow2.aspirerfid.ide.bpwme.diagram.preferences.PreferenceConstants;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ExtendedAttr extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.ow2.aspirerfid.ide.bpwme.diagram.views.ExtendedAttr";

	private TableViewer viewer;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	
	class ExtendedAttribute{
		private String key;
		private String value;
		public ExtendedAttribute(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getName() {
			return key;
		}
		
		public String getValue() {
			return null;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	class ExtendedAttrContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			HashMap<String,String> oldMap = PreferenceConstants.P_EXTEND;
			ArrayList<ExtendedAttribute> attributes = new ArrayList<ExtendedAttribute>();
			IPreferenceStore store = BpwmeDiagramEditorPlugin.getInstance().getPreferenceStore();
			Iterator<String> iter = oldMap.keySet().iterator();
			while(iter.hasNext()) {
				String key = iter.next();
				String value = store.getString(key);
				attributes.add(new ExtendedAttribute(key, value));
			}
			return attributes.toArray();
		}
	}
	class ExtendedAttrLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			ExtendedAttribute ea = (ExtendedAttribute)obj;
			switch(index) {
			case 0:
				return ea.getName();
			case 1:
				return ea.getValue();
			default:
				return null;
			}
			
		}
		public Image getColumnImage(Object obj, int index) {
			return null;
		}
	}
	
	class ExtendedCellModifier implements ICellModifier {

		@Override
		public boolean canModify(Object element, String property) {
			if (property.equals("value")) {
				return true;
			}else {
				return false;
			}
		}

		@Override
		public Object getValue(Object element, String property) {
			ExtendedAttribute ea = (ExtendedAttribute)element;
			if(property.equals("value")) {
				return ea.getValue();
			}else if(property.equals("name")) {
				return ea.getName();
			}
			return null;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			ExtendedAttribute ea = (ExtendedAttribute)element;
			if(property.equals("value")) {
				ea.setValue((String)value);
			}
		}
	}

	/**
	 * The constructor.
	 */
	public ExtendedAttr() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		Table table = viewer.getTable();
		//column headers and row limits visible
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		//each column TableViewerColumn produce for
		TableViewerColumn viewerNameColumn = new TableViewerColumn(viewer, SWT.NONE);
		viewerNameColumn.getColumn().setText("Name");
		viewerNameColumn.getColumn().setWidth(200);

		TableViewerColumn viewerValueColumn = new TableViewerColumn(viewer, SWT.NONE);
		viewerValueColumn.getColumn().setText("Value");
		viewerValueColumn.getColumn().setWidth(200);
		
		viewer.setContentProvider(new ExtendedAttrContentProvider());
		viewer.setLabelProvider(new ExtendedAttrLabelProvider());
		viewer.setInput(getViewSite());
		viewer.setColumnProperties(new String[] {"name", "value"});
		viewer.setCellModifier(new ExtendedCellModifier());
		
		CellEditor[] editors = new CellEditor[2];
		editors[1] = new ComboBoxCellEditor();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}