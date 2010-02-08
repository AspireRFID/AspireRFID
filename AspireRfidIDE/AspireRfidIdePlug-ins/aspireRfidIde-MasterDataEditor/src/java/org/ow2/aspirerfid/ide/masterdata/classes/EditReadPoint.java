/*
 * Copyright © 2008-2010, Aspire
 * 
 * Aspire is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 2.1 as published by
 * the Free Software Foundation (the "LGPL").
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library in the file COPYING-LGPL-2.1; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY
 * KIND, either express or implied. See the GNU Lesser General Public License
 * for the specific language governing rights and limitations.
 */


package org.ow2.aspirerfid.ide.masterdata.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.ow2.aspirerfid.ide.masterdata.swtdesigner.SWTResourceManager;
import org.ow2.aspirerfid.ide.masterdata.views.MasterDataEditorView;;
/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class EditReadPoint extends Dialog {

	private Text newRP_name;
	private Text newRP_uri;
	private List AssignedRP_list;
	private List availRP_list;
	protected Object result;
	protected Shell shell;
	private MasterDataEditorView mdi;
	private boolean update = false;
	
	private String location_id;
	private DataManagerModule dm;
	
	private Label pickFromTheLabel;
	java.util.List<String> readpoints_additions = null;
	TreeMap<Key_Map,String> readers = new TreeMap<Key_Map,String>();
	java.util.List<String> readpoint_removals = null;
	java.util.List<ModifiedUnit> units = new java.util.ArrayList<ModifiedUnit>();

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	
	
	public EditReadPoint(Shell parent,  String bizLocId, DataManagerModule dm) {
		super(parent, 1);
		this.dm = dm;
		location_id = bizLocId;
		System.out.println("IN WINDOW URI = "+bizLocId);
	}

	
	public EditReadPoint(Shell parent,  MasterDataEditorView mdi) {
		super(parent, 1);
		this.mdi = mdi;
	}

	
	public EditReadPoint(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public EditReadPoint(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	
	public void showAllReadpoints()
	{
	
		java.util.List<String> ids = new ArrayList<String>();
		availRP_list.removeAll();
		AssignedRP_list.removeAll();
		
		dm.setVocabularyId(EpcisConstants.READ_POINT_ID);
		dm.getOrigList().clear();
		dm.getOrigList().addAll(dm.getnewList());
		java.util.List<String> names = dm.getElementNames(null,null);
		if(names.size() == 0)
		{
			pickFromTheLabel.setText("No information available!");
			pickFromTheLabel.setVisible(true);
			
		}
		else
		{
			java.util.List<String>attr = new ArrayList<String>();
			for(int i = 0; i < names.size(); i++)
				availRP_list.add(names.get(i));
			if(location_id!=null && !location_id.equals(""))
			{
				java.util.List<String> rp_list = new ArrayList<String>();
				if(dm.getnewList().size() == 0)
				{
				
					
					attr.add("urn:epcglobal:epcis:mda:ReadPoints");
					dm.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
					String read_points = dm.getSpecificAttributeValue(location_id, attr);
					if(read_points != null && !read_points.equals(""))
					{
						StringTokenizer readers = new StringTokenizer(read_points,",");
						int total = readers.countTokens();
						for(int i = 0; i < total; i++)
							rp_list.add(readers.nextToken());
						
					}
				}
				else
					rp_list.addAll(dm.getnewList());
				int total;
				if((total = rp_list.size()) > 0)
				{
					for(int i = 0; i < total; i++)
					{
						String reader_uri = rp_list.get(i);
						attr.clear();
						attr.add("urn:epcglobal:epcis:mda:Name");
						dm.setVocabularyId(EpcisConstants.READ_POINT_ID);
						String name = dm.getSpecificAttributeValue(reader_uri, attr);
						if(name != null && !name.equals(""))
						{
							AssignedRP_list.add(name);
							ids.add(reader_uri);
						}
					}
					
					
				}
					
			}
			
				
			
		}
				
		AssignedRP_list.setData(ids);//Always set to avoid NPE
		dm.getOrigList().addAll(ids);
		
	}
	/*public void showAllReadpoints()
	{
		
		availRP_list.removeAll();
		AssignedRP_list.removeAll();
		//Populate the list with all the available read points
		org.ow2.aspirerfid.ide.masterdata.classes.ReadPointsReader reader_RP = new org.ow2.aspirerfid.ide.masterdata.classes.ReadPointsReader(mdi.getQueryClient());
		mdi.info = reader_RP.getTags_Names();
		java.util.Set<java.util.Map.Entry<Key_Map,String>> se = mdi.info.entrySet();
				
		for(Iterator<java.util.Map.Entry<Key_Map,String>> i = se.iterator();i.hasNext();)
		{
			java.util.Map.Entry<Key_Map,String> temp = i.next();
			Key_Map k = temp.getKey();
			String v = temp.getValue();
			System.out.println("Key = <"+k.getIdx()+","+k.getName()+"> - Value = <"+v+">");
			
			availRP_list.add(k.getName());
			
						
		}
		
	//	System.out.println("Read Points:"+this.mdi.underEdit.getReadpoints().toString());
		
		//if(mdi.underEdit.getReadPoints()  == null || mdi.underEdit.getReadPoints().size() == 0)
	//	{
		//	//AssignedRP_list.add("No readers assigned");
		//	return;
		//}
			
		java.util.List<String> rps = mdi.underEdit.getReadPoints();
		Iterator<String>its = rps.iterator();
		AssignedRP_list.removeAll();
		AssignedRP_list.setData(readpoints_additions);
		
		while(its.hasNext())
		{

			String val = its.next();
			System.out.println("RPS   "+val);
			org.ow2.aspirerfid.ide.masterdata.classes.epcisQueryModule q = new org.ow2.aspirerfid.ide.masterdata.classes.epcisQueryModule();
			q.setClient(this.mdi.getQueryClient());
			java.util.List<String> name = q.getValues("urn:epcglobal:epcis:vtype:ReadPoint", val, "Name");
			//AssignedRP_list.add(val);
			AssignedRP_list.add(name.get(0));
			readpoints_additions.add(val);
			
		}
		
		
	}
	*/
	@SuppressWarnings("unchecked")
	public void addReadPoint()
	{
		if(availRP_list.getSelectionCount() == 0)
			return;
		String selection = availRP_list.getSelection()[0];
		String [] assigned  = AssignedRP_list.getItems();
		for(int i = 0; i < assigned.length; i++)
		{
			if(selection.equalsIgnoreCase(assigned[i]))
				return;
			
		}
		dm.setVocabularyId(EpcisConstants.READ_POINT_ID);
		String id = dm.getUri(availRP_list.getSelectionIndex(), availRP_list.getSelection()[0]);
		//dm.getnewList().add(id);
		AssignedRP_list.add(availRP_list.getSelection()[0]);
		java.util.List<String>ids = (ArrayList<String>)AssignedRP_list.getData();
		ids.add(id);

		
		
	}
	
	/*public void addReadPoint()
	{
		
		String selection = availRP_list.getSelection()[0];
		String [] assigned  = AssignedRP_list.getItems();
		for(int i = 0; i < assigned.length; i++)
		{
			if(selection.equalsIgnoreCase(assigned[i]))
				return;
			
		}
		AssignedRP_list.add(selection);
		String uri = this.mdi.info.get(new Key_Map(availRP_list.getSelection()[0],availRP_list.getSelectionIndex()));
		
	
		readpoints_additions.add(uri);
		update = true;
	}*/
	

	
	@SuppressWarnings("unchecked")
	public void removeReadpoint()
	{
		if(AssignedRP_list.getSelection()[0] == null)
			return;
		
		if(AssignedRP_list.getItemCount() == 1)
		{
			pickFromTheLabel.setText("Can not have a location with no read-points");
			pickFromTheLabel.setVisible(true);
			return;			
		}
		String id = ((ArrayList<String>)AssignedRP_list.getData()).get(AssignedRP_list.getSelectionCount() - 1);
		((ArrayList<String>)AssignedRP_list.getData()).remove(AssignedRP_list.getSelectionCount() - 1);
		//dm.getnewList().remove(id);
		AssignedRP_list.remove(AssignedRP_list.getSelection()[0]);
		
		
		
		
		
	}
	
	/*public void removeReadpoint()
	{
		if(AssignedRP_list.getSelection()[0] == null)
			return;
		if(AssignedRP_list.getItemCount() == 0)
			return;
		
		readpoints_additions.remove(AssignedRP_list.getSelectionCount() - 1);
		AssignedRP_list.remove(AssignedRP_list.getSelection()[0]);
		
		
		update = true;
		
	}*/
	
	@SuppressWarnings("unchecked")
	public void doUpdate()
	{
		//if(!dm.getOrigList().containsAll((ArrayList<String>)AssignedRP_list.getData())){
		
		dm.getnewList().clear();	
		dm.getnewList().addAll((ArrayList<String>)AssignedRP_list.getData());
		//}
		
		dm.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
		this.getParent().close();
		result = new Boolean(true);
		return;
	}
	
	/*public void doUpdate()
	{
		
		
		boolean remove = false;
		if(AssignedRP_list.getItemCount() == 0)
		{
			if(!update)
			{
				this.getParent().close();//Nothing to add
				return;
			}
			else
				remove = true;//Must have removed all
			
			
		}
		if(!update)
		{
			this.getParent().close();//Nothing to add
			return;
		}
		
	
		//Add changes
		StringBuffer readPoints = new StringBuffer();
	
		int size = AssignedRP_list.getItemCount();
		for(int i = 0; i < size ; i++)
		{
			readPoints.append(readpoints_additions.get(i));
			if(i < size - 1)
				readPoints.append("_");
		}
		
		ModifiedUnit unit = new ModifiedUnit();
		unit.setOldEPC(this.mdi.underEdit.getFQTag());
		unit.setOldAttribute("Read Point");
		mdi.underEdit.clearReadPoints();
		
		mdi.underEdit.setReadPoint(readPoints.toString());
		unit.setOldValue(readPoints.toString());
		
		
		if(remove)
		{
			readPoints.append("read point");//If null data will not be captured. Value is not significant. Put anything as long as not empty
			//this.mdi.underEdit.clearReadPoints();//remove all read points
		}
		
		unit.setNewValue(readPoints.toString());
		unit.setRemove(remove);
		
		this.mdi.modifications_pool.addUnit(unit);	
		AssignedRP_list.removeAll();
		System.out.println("Now read points="+mdi.underEdit.getReadpoints().toString());
		
		this.getParent().close();
		return;
		
		
		
		
		
	
	}
	*/
	
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(500, 502);
		shell.setText("Add or remove read points");

		availRP_list = new List(shell, SWT.BORDER);
		availRP_list.setBounds(30, 155, 152, 262);
		
		AssignedRP_list = new List(shell, SWT.BORDER);
		AssignedRP_list.setBounds(310, 155, 152, 262);

		final Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				addReadPoint();
			}
		});
		button.setText(">>");
		button.setBounds(225, 255, 48, 25);

		final Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				removeReadpoint();
			}
		});
		button_1.setText("<<");
		button_1.setBounds(225, 290, 48, 25);

		final Label availableLabel = new Label(shell, SWT.NONE);
		availableLabel.setText("Available");
		availableLabel.setBounds(30, 125, 152, 15);

		final Label assignedLabel = new Label(shell, SWT.NONE);
		assignedLabel.setText("Assigned");
		assignedLabel.setBounds(310, 125, 152, 15);

		final Button okButton = new Button(shell, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				doUpdate();
			}
		});
		okButton.setText("OK");
		okButton.setBounds(410, 435, 48, 25);

		pickFromTheLabel = new Label(shell, SWT.NONE);
		pickFromTheLabel.setVisible(false);
		pickFromTheLabel.setForeground(SWTResourceManager.getColor(255, 0, 0));
		pickFromTheLabel.setText("Pick from the list of availables");
		pickFromTheLabel.setBounds(30, 440, 243, 15);

		final Group newAdditionGroup = new Group(shell, SWT.NONE);
		newAdditionGroup.setText("New Addition");
		newAdditionGroup.setBounds(10, 15, 452, 104);

		newRP_uri = new Text(newAdditionGroup, SWT.BORDER);
		newRP_uri.setBounds(10, 38, 154, 25);

		newRP_name = new Text(newAdditionGroup, SWT.BORDER);
		newRP_name.setBounds(263, 38, 154, 25);

		final Label uriLabel = new Label(newAdditionGroup, SWT.NONE);
		uriLabel.setText("Uri:");
		uriLabel.setBounds(10, 15, 99, 15);

		final Label nameLabel = new Label(newAdditionGroup, SWT.NONE);
		nameLabel.setText("Name:");
		nameLabel.setBounds(263, 15, 70, 15);

		final Button addButton = new Button(newAdditionGroup, SWT.NONE);
		addButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if(newRP_uri.getText().equals("") || newRP_name.getText().equals(""))
					return;
				String voc = dm.getVocabularyId();
				dm.setVocabularyId(EpcisConstants.READ_POINT_ID);
				boolean result = false;
				result = dm.masterDataElementEdit(newRP_uri.getText(), "1");
				if(!result)
				{
					
				}
				result = dm.masterDataAttributeEdit(newRP_uri.getText(), "Name", newRP_name.getText(), "1");
				if(!result)
				{
					
				}
				availRP_list.add(newRP_name.getText());
				dm.addNewInternalInfo(newRP_uri.getText(), newRP_name.getText(), availRP_list.getItemCount()-1);
				newRP_uri.setText("");
				newRP_name.setText("");

				
				
				
			}
		});
		addButton.setText("Add");
		addButton.setBounds(397, 69, 34, 25);
		readpoints_additions = new java.util.ArrayList<String>();
		readpoint_removals = new java.util.ArrayList<String>();
		
		showAllReadpoints();
		//
	}

}
