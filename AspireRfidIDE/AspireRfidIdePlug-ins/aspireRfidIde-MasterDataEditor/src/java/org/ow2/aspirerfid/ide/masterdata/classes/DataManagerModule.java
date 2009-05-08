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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.ow2.aspirerfid.ide.masterdata.tools.MasterDataCaptureClient;
import org.ow2.aspirerfid.ide.masterdata.tools.QueryClientGuiHelper;
/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class DataManagerModule {
	
	private String queryUrl;
	private String captureUrl;
	private epcisQueryModule queryModule;
	private String vocabularyId;
	private QueryClientGuiHelper client;
	private MasterDataCaptureClient mdClient;
	private TreeMap<Key_Map,String> info;
	
	
	TreeMap<Key_Map,String> biz_disposition_info; 
	TreeMap<Key_Map,String> biz_step_info;
	TreeMap<Key_Map,String>  biz_readpoints_info;
	private TreeMap<Key_Map,String> biz_location_info;
	private TreeMap<Key_Map,String> biz_transactionType_info;
	
	private List<String>origList;
	private List<String>newList;
	
	private EpcisEvent event;
	private String oldTransactionId;
	private TreeItem oldNode;
	List<String> children;
	String LocDisplayedId;
	public DataManagerModule()
	{
		
	}
	public DataManagerModule(String captureUrl,String queryUrl)
	{
		this.captureUrl = captureUrl;
		this.queryUrl = queryUrl;
		this.client = new QueryClientGuiHelper(queryUrl);
		this.mdClient = new MasterDataCaptureClient(captureUrl);
		queryModule = new epcisQueryModule();
		event = null;
		origList = new ArrayList<String>();
		newList = new ArrayList<String>();
		
		
	}
	public void setQueryUrl(String queryUrl)
	{
		this.queryUrl = queryUrl;
	}
	public String getQueryUrl()
	{
		return this.queryUrl;
	}
	public void setCaptureUrl(String captureUrl)
	{
		this.captureUrl = captureUrl;
	}
	public String getCaptureUrl()
	{
		return this.captureUrl;
	}
	public void setVocabularyId(String id)
	{
		this.vocabularyId = id;
		if(vocabularyId.equals(EpcisConstants.DISPOSITION_ID))
			this.info = biz_disposition_info;
		else if(vocabularyId.equals(EpcisConstants.BUSINESS_STEP_ID))
			this.info = biz_step_info;
		else if(vocabularyId.equals(EpcisConstants.READ_POINT_ID))
			this.info = biz_readpoints_info;
		else if(vocabularyId.equals(EpcisConstants.BUSINESS_LOCATION_ID))
			this.info = biz_location_info;
	}
	public String getVocabularyId()
	{
		return this.vocabularyId;
	}
	
	public List<String> getOrigList()
	{
		return origList;
		
	}
	
	public List<String> getnewList()
	{
		return newList;
		
	}
	private void storeInfo()
	{
		if(this.vocabularyId.equals(EpcisConstants.DISPOSITION_ID))
			this.biz_disposition_info = info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_STEP_ID))
			this.biz_step_info = info;
		else if(this.vocabularyId.equals(EpcisConstants.READ_POINT_ID))
			this.biz_readpoints_info = info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_LOCATION_ID))
			this.biz_location_info = info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID))
			this.biz_transactionType_info = info;
	}
	
	public void clearInternal()
	{
		event = null;
	}
	public List<String>getElementUris(List<String> attribute,List<String>val)
	{
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		return queryModule.getElementIDs(attribute,val);
	}
	
	
	public List<String> adjust_locations(List<String> locations)
	{
		List<String> result = new ArrayList<String>();
		StringTokenizer element;
		for(int i = 0; i < locations.size(); i++)
		{
			StringBuffer e = new StringBuffer();
			element = new StringTokenizer(locations.get(i),",");
			int total = element.countTokens();
			for(int j = 0; j < total; j++)
			{
				String id = element.nextToken();
				if(locations.contains(id))
				   e.append(id);
				if(j < total -1)
					e.append(",");
				

			}
			result.add(e.toString());
			
			
		}
		return result;
	}
	public List<String>getLocations(String deprecated)
	{
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		java.util.List<String>attr = new ArrayList<String>();
		attr.add("urn:epcglobal:epcis:mda:Name");
		List<String> ids = queryModule.getElementIDs(attr);
		if(ids == null)
			return new ArrayList<String>();//To avoid NPE
		List<String> result = new ArrayList<String>();
		attr.clear();
		for(int i = 0; i < ids.size(); i++)
		{
			attr.add("urn:epcglobal:epcis:mda:deprecated");
			
			String dep = getSpecificAttributeValue(ids.get(i), attr);
			if(dep == null || dep.equals(""))
			{
				if(deprecated.equals("no"))
					result.add(ids.get(i));
			}
			else if(dep.equals(deprecated))
				result.add(ids.get(i));
				
			
		}
		//if(deprecated.equals("yes"))
			//return adjust_locations(result);
		return result;
	}
	
	
	public List<String>getElementUris(List<String> attribute)
	{
		
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		return queryModule.getElementIDs(attribute);
		
	}
	
	private String getEventsOfTransaction(String transactionId)
	{
		queryModule.setVocabulary(this.vocabularyId);
		List<String> attribute = new ArrayList<String>();
		attribute.add("urn:epcglobal:epcis:mda:Children");
		return this.getSpecificAttributeValue(transactionId, attribute);
		
	}
	
	public boolean changeTransaction(String newTransaction)
	{
		if(event == null)
			return false;
		String children = this.getEventsOfTransaction(this.oldTransactionId);
		StringTokenizer events = new StringTokenizer(children,",");
		
		if(children == null || children.equals(""))
			return false;
		
		List<String>newChildren = new ArrayList<String>();
		int total = events.countTokens();
		for(int i = 0; i < total; i++)
		{
			String tempChild = events.nextToken();
			if(!tempChild.equals(event.getUri()))
					newChildren.add(tempChild);
				
		}
		if(newChildren.size() == 0)
		{
			masterDataAttributeEdit(oldTransactionId, "Children", "RandomValue", "3");
			
		}
		else
		{
			StringBuffer newEvents = new StringBuffer();
			for(int i = 0; i < newChildren.size(); i++)
				newEvents.append(newChildren.get(i));
			masterDataAttributeEdit(oldTransactionId, "Children",newEvents.toString(), "2");
			
		}
			
		children = this.getEventsOfTransaction(newTransaction);
		StringBuffer newEvents = new StringBuffer();
		if(children!=null && !children.equals(""))
			newEvents.append(children+",");
		newEvents.append(event.getUri());
		return masterDataAttributeEdit(newTransaction, "Children",newEvents.toString(), "2");
		
		
		
	}
	
	
	public void storeTransactionSwapInfo(String oldTransactionId, String eventId,TreeItem node)
	{
		event = new EpcisEvent(eventId);
		this.oldTransactionId = oldTransactionId;
		oldNode = node;
		
	}
	
	public boolean verifySelection(Combo cb, String selection)
	{
		int total = cb.getItemCount();
		if(total == 0)
			return true;//No change other. The user did not press combo to see options, so combo has no items
		for(int i = 0; i < total; i++)
			if(cb.getItem(i).equals(selection))
				return true;
		return false;
		
	}
	public void setLocationDisplayedId(String id)
	{
		this.LocDisplayedId = id;
	}
	
	public String getLocationDisplayedId()
	{
		return this.LocDisplayedId;
	}
	
	public TreeItem getOldNode()
	{
		return oldNode;
	}
	public boolean deleteEvent(String eventId,String transactionId)
	{
		boolean result = false;
		String events = getEventsOfTransaction(transactionId);
		
		if(events == null || events.equals(""))
			return false;
		
		StringTokenizer children = new StringTokenizer(events,",");
		int total = children.countTokens();
		List<String> newChildren = new ArrayList<String>();
		
		for(int i = 0; i < total; i++)
		{
			String tempChild = children.nextToken();
			if(!tempChild.equals(eventId))
				newChildren.add(tempChild);
			
		}
		
		if(newChildren.size() == 0)
		{
			result = this.masterDataAttributeEdit(transactionId, "Children", "RandomValue", "3");
		}
		else
		{
			StringBuffer newEvents = new StringBuffer();
			for(int i = 0; i < newChildren.size(); i++)
				newEvents.append(newChildren.get(i));
			result = this.masterDataAttributeEdit(transactionId, "Children", newEvents.toString(), "2");
				
			
		}
		
		if(!result)
			return false;
		
		return this.masterDataElementEdit(eventId, "4");
			
		
		
	}
	
	public boolean deleteTransaction(String transactionId)
	{
		boolean result = false;
		
		String events = getEventsOfTransaction(transactionId);
		if(events != null && !events.equals(""))
		{
			StringTokenizer children = new StringTokenizer(events,",");
			int total = children.countTokens();
			for(int i = 0; i < total; i++)
			{
				result = this.masterDataElementEdit(children.nextToken(), "4");
				if(!result)
					return result;
			}
		}
		result = this.masterDataElementEdit(transactionId, "4");
		return result;
		
		
	}
	
	public String getBusinessLocationFullUri(String id)
	{
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		List<String> attr = new ArrayList<String>();
		attr.add("urn:epcglobal:epcis:mda:Name");
		List<String> uris = queryModule.getElementIDs(attr);
		int total = uris.size();
		if(total == 0)
			return "";
		for(int i = 0; i < total; i++)
		{
			if(uris.get(i).endsWith(id))
				return uris.get(i);
		}
		return "";
	}
	
	public String getSpecificAttributeValue(String elem,List<String> Attribute)
	{
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		return queryModule.getSpecificAttribute(elem,Attribute);
		
	}
	
	public List<String> getElementNames(String elem,String extraAttr)
	{
		List<String> result = new ArrayList<String>();
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		info = queryModule.queryTagsAndNamesInfo(elem,extraAttr);
		if(info == null)
			return result;
		storeInfo();
		Set<Entry<Key_Map,String>> se = info.entrySet();
		for(Iterator<Entry<Key_Map,String>> i = se.iterator();i.hasNext();)
		{
			Entry<Key_Map,String> temp = i.next();
			Key_Map k = temp.getKey();
			String v = temp.getValue();
			String name = k.getName();
			result.add(name);		
		}
		return result;
		
		
	}
	
	public void addNewInternalInfo(String uri,String name,int index)
	{
		this.info.put(new Key_Map(name,index), uri);
		
	}
	
	
	
	public String getUri(int index,String name)
	{
		Key_Map k = new Key_Map();
		k.setIdx(index);
		k.setName(name);
		if(this.vocabularyId.equals(EpcisConstants.DISPOSITION_ID))
			info = biz_disposition_info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_STEP_ID))
			info = biz_step_info;
		else if(this.vocabularyId.equals(EpcisConstants.READ_POINT_ID))
			info = biz_readpoints_info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_LOCATION_ID))
			info = biz_location_info;
		else if(this.vocabularyId.equals(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID))
			info = biz_transactionType_info;
		
		if(info == null)
			return null;
		return info.get(k);
		
	}
	
	public void excludeAttributes(List<Attribute> origList, String attr)
	{
		int total = origList.size();
		for(int i = 0; i <total; i++)
		{
			if(origList.get(i).getAttribute().endsWith(attr))
			{
				origList.remove(i);
				return;
			}
		}
		return;
		
	}
	public List<Attribute>getAttributes(String uri)
	{
		List<Attribute>attributes = new ArrayList<Attribute>();
		attributes = this.queryModule.getElemsAttributes(uri);
		//List<String>result = new ArrayList<String>();
		//int total = result.size();
		//for(int i = 0 ; i < total; i++)
			//result.add(((Attribute)attributes.get(i)).getValue());
		return attributes;	
		
		
	}
	public List<Attribute>getAttributes(String uri,List<String>exclude)
	{
		List<Attribute>attributes = new ArrayList<Attribute>();
		this.queryModule.setVocabulary(this.vocabularyId);
		attributes = this.queryModule.getElemsAttributes(uri);
		for(int i = 0; i < exclude.size();i++)
		{
			//if(attributes.contains(exclude.get(i)))
				//attributes.remove(exclude.get(i));
			for(int j = 0; j < attributes.size(); j++)
			{
				if(attributes.get(j).equals(exclude.get(i)))
				{
					attributes.remove(j);
					break;
				}
			}
			
		}
	
		
		
		
		return attributes;	
		
		
	}

	private void saveChildren(TreeItem children[] , List<String>ids)
	{
		
		if(children.length == 0)
			return;
		for(int i = 0; i < children.length; i++)
		{
			ids.add((String)children[i].getData());
			saveChildren(children[i].getItems(),ids);
			
		}
			
	}
	
	public String parseUri(String uri,String separator,int position)
	{
		return queryModule.parseNameSpace(uri, separator, position);
	}
	
	public boolean changeLocationParent(String newParentId)
	{
		queryModule.setVocabulary(this.vocabularyId);
		String parentId = queryModule.parseNameSpace((String)oldNode.getData(), ",", -1);
		String newId = queryModule.removeOldParents((String)oldNode.getData(), parentId);
		List<Attribute>attributes = queryModule.getAttributes((String)oldNode.getData());
		this.masterDataElementEdit((String)oldNode.getData(), "3");
		this.masterDataElementEdit(newParentId+","+newId, "1");
		for(int i = 0; i < attributes.size(); i++)
		{
			this.masterDataAttributeEdit(newParentId+","+newId, queryModule.parseNameSpace(attributes.get(i).getAttribute(),":",-1), attributes.get(i).getValue(), "2");
		}
		
		for(int i = 0; i < children.size();i++)
		{
			attributes.clear();
			attributes = queryModule.getAttributes(children.get(i));
			String id = queryModule.removeOldParents(children.get(i), parentId);
			//id = queryModule.parseNameSpace(id, ",", -1);
			this.masterDataElementEdit(children.get(i), "3");
			StringBuffer newParent = new StringBuffer();
			newParent.append(newParentId+","+id);
			this.masterDataElementEdit(newParent.toString(), "1");
			for(int j = 0; j < attributes.size(); j++)
			{
				this.masterDataAttributeEdit(newParent.toString(), queryModule.parseNameSpace(attributes.get(j).getAttribute(),":" +
						"",-1), attributes.get(j).getValue(), "2");
			}
		}
		
		return true;
	}
	
	
	public void saveLocationHierarchy(TreeItem node)
	{
		List<String>ids = new ArrayList<String>();
		ids.add((String)node.getData());
		for(int i = 0; i < node.getItemCount(); i++)
		{
			saveChildren(node.getItems(),ids);
			
			
		}
		oldNode = node;
		children = ids;
		children.remove(0);
	}
	
	
	public String createFQBizLocationId(String uri, Tree theTree)
	{
		if(theTree.getSelectionCount() == 0)
			return uri;
		TreeItem parent_node = theTree.getSelection()[0].getParentItem();
		StringBuffer fqId = new StringBuffer();
		if(parent_node != null && parent_node.getData() != null)
		{
			fqId.append(parent_node.getData());
			fqId.append(",");
			
		}
		
		fqId.append(uri);
		return fqId.toString();
		
	}
	public void deprecateChildren(TreeItem[] children,String deprecate)
	{
		if(children == null)
			return;
		for(int i = 0; i < children.length; i++)
			deprecate(children[i],deprecate);
	}
	public boolean deprecate(TreeItem node,String deprecate)
	{
		String uri = (String)node.getData();
		if(uri == null || uri.equals(""))
			return false;
		boolean result = masterDataAttributeEdit(uri, "deprecated", deprecate, "2");
		if(node.getItemCount() > 0)
		{
			for(int i = 0; i < node.getItemCount(); i++)
				deprecateChildren(node.getItems(),deprecate);
				//deprecate(node.getItem(i));
			
		}
		return result;
		
	}
	
	public boolean masterDataAttributeEdit(String uri,String attribute,String value,String mode)
	{
		return this.mdClient.simpleMasterDataAndAttributeEdit(this.vocabularyId, uri, attribute, value, mode);
	}
	
	public boolean masterDataElementEdit(String uri,String mode)
	{
		return this.mdClient.simpleMasterDataEdit(this.vocabularyId, uri, mode);
	}
	
	public boolean modifyEvent(String eventUri, String name, String type,Combo step, Combo location, Combo disposition, String spec, Combo readpoint, String action, Combo transactionType, String reports)
	{
		int total = event.getAttributes().size();
		if(total == 0)
			return false;
		for(int i = 0; i < total; i++)
		{
			if(event.getAttributes().get(i).getAttribute().endsWith("event_name"))
			{
				if(!name.equals(event.getAttributes().get(i).getValue()))
				{
					
					this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), name, "2");
					
				}
				
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("event_type"))
			{
				if(!type.equals(event.getAttributes().get(i).getValue()))
				{
					
					this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), type, "2");
					
					
				}
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("business_step"))
			{
				this.vocabularyId = EpcisConstants.BUSINESS_STEP_ID;
				String bizStepUri = getUri(step.getSelectionIndex(),step.getText());
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
				if(bizStepUri != null)
				{	
					if(!bizStepUri.equals(event.getAttributes().get(i).getValue()))
					{
						
						this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), bizStepUri, "2");
					}
				}
			}
			
			else if(event.getAttributes().get(i).getAttribute().endsWith("business_location"))
			{
				this.vocabularyId = EpcisConstants.BUSINESS_LOCATION_ID;
				String bizLocationUri= getUri(location.getSelectionIndex(),location.getText());
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
				if(bizLocationUri != null)
				{
					if(!bizLocationUri.equals(event.getAttributes().get(i).getValue()))
					{
						
						this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1),queryModule.parseNameSpace(bizLocationUri,",",-1), "2");
					}
				}
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("disposition"))
			{
				this.vocabularyId = EpcisConstants.DISPOSITION_ID;
				String bizDispositionUri = getUri(disposition.getSelectionIndex(),disposition.getText());
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
				if(bizDispositionUri != null)
				{
					if(!bizDispositionUri.equals(event.getAttributes().get(i).getValue()))
					{
						
						this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), bizDispositionUri, "2");
					}
				}
				
			}

			else if(event.getAttributes().get(i).getAttribute().endsWith("ecspec_name"))
			{
				if(!spec.equals(event.getAttributes().get(i).getValue()))
				{
					
					this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), spec, "2");
					
					
				}
				
			}
				
			else if(event.getAttributes().get(i).getAttribute().endsWith("read_point"))
			{
				this.vocabularyId = EpcisConstants.READ_POINT_ID;
				String bizReadpointUri = getUri(readpoint.getSelectionIndex(),readpoint.getText());
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
				if(bizReadpointUri != null)
				{
					if(!bizReadpointUri.equals(event.getAttributes().get(i).getValue()))
					{
						
						this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), bizReadpointUri, "2");
					}
				}
				
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("transaction_type"))
			{
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID;
				String TransTypeUri = getUri(transactionType.getSelectionIndex(),transactionType.getText());
				this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
				if(TransTypeUri != null)
				{
					if(!TransTypeUri.equals(event.getAttributes().get(i).getValue()))
					{
						
						this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), TransTypeUri, "2");
					}
				}
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("action"))
			{
				if(!action.equals(event.getAttributes().get(i).getValue()))
				{
					
					
					this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), action, "2");
					
					
				}
			}
			else if(event.getAttributes().get(i).getAttribute().endsWith("ecreport_names"))
			{
				if(!reports.equals(event.getAttributes().get(i).getValue()))
				{
					
					this.masterDataAttributeEdit(eventUri, queryModule.parseNameSpace(event.getAttributes().get(i).getAttribute(), ":", -1), reports, "2");
					
					
				}
			}
			
			
		}
		this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
			return true;
	}
		
		
		
		
	
	
	
	public boolean insertEvent(String transactionUri,String eventUri, String name, String type,Combo step, Combo location, Combo disposition, String spec, Combo readpoint, String action, Combo transactionType, String reports)
	{
		if(event != null)	//Modification
			return modifyEvent(eventUri,name,type,step,location,disposition,spec,readpoint,action,transactionType,reports);
			
			
			
		
		boolean result = false;
		this.vocabularyId = EpcisConstants.BUSINESS_STEP_ID;
		String bizStepUri = getUri(step.getSelectionIndex(),step.getText());
		this.vocabularyId = EpcisConstants.BUSINESS_LOCATION_ID;
		String bizLocationUri= getUri(location.getSelectionIndex(),location.getText());
		this.vocabularyId = EpcisConstants.DISPOSITION_ID;
		String bizDispositionUri = getUri(disposition.getSelectionIndex(),disposition.getText());
		this.vocabularyId = EpcisConstants.READ_POINT_ID;
		String bizReadpointUri = getUri(readpoint.getSelectionIndex(),readpoint.getText());
		this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID;
		String TransTypeUri = getUri(transactionType.getSelectionIndex(),transactionType.getText());
		
		//restore
		this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
		List<String> attr = new ArrayList<String>();
		attr.add("urn:epcglobal:epcis:mda:Children");
		String children = this.getSpecificAttributeValue(transactionUri,attr);
		StringBuffer newChildren = new StringBuffer();
		if(children!=null)
			newChildren.append(children+",");
		newChildren.append(eventUri);
		result = this.masterDataAttributeEdit(transactionUri, "Children", newChildren.toString(), "2");
		if(!result)
			return result;
		
		result = this.masterDataElementEdit(eventUri, "1");
		if(!result)
			return result;
		result = this.masterDataAttributeEdit(eventUri, "event_name", name, "2");
		result = this.masterDataAttributeEdit(eventUri, "event_type", type, "2");
		result = this.masterDataAttributeEdit(eventUri, "business_step", bizStepUri, "2");
		result = this.masterDataAttributeEdit(eventUri, "business_location", queryModule.parseNameSpace(bizLocationUri, ",", -1), "2");
		result = this.masterDataAttributeEdit(eventUri, "disposition", bizDispositionUri, "2");
		result = this.masterDataAttributeEdit(eventUri, "ecspec_name", spec, "2");
		result = this.masterDataAttributeEdit(eventUri, "read_point", bizReadpointUri, "2");
		result = this.masterDataAttributeEdit(eventUri, "transaction_type", TransTypeUri, "2");
		result = this.masterDataAttributeEdit(eventUri, "action", action, "2");	
		result = this.masterDataAttributeEdit(eventUri, "ecreport_names", reports, "2");	
		
		
		
		
		return result;
		
	}
	
	
	public boolean getEventInfo(String uri,Text name, Combo type,Combo step, Combo location, Combo disposition, Text spec, Combo readpoint, Combo action, Combo transactionType, org.eclipse.swt.widgets.List reports)
	{
		boolean result = false;
		event = new EpcisEvent(uri);
		queryModule.setVocabulary(this.vocabularyId);
		queryModule.setClient(client);
		List<Attribute> attributes = queryModule.getAttributes(uri);
		if(attributes == null || attributes.size() == 0)
			return false;
		event.getAttributes().addAll(attributes);
		int size = attributes.size();
		for(int i = 0; i < size; i ++)
		{
			if(attributes.get(i).getAttribute().endsWith("event_name"))
				name.setText(attributes.get(i).getValue());
			else if(attributes.get(i).getAttribute().endsWith("event_type"))
				type.setText(attributes.get(i).getValue());
			else if(attributes.get(i).getAttribute().endsWith("business_step"))
			{
				this.setVocabularyId(EpcisConstants.BUSINESS_STEP_ID);
				List<String> attr = new ArrayList<String>();
				attr.add("urn:epcglobal:epcis:mda:Name");
				String bstep = this.getSpecificAttributeValue(attributes.get(i).getValue(), attr);
				if(bstep == null || bstep.equals(""))
					step.setText("Info not available");
				else
					step.setText(bstep);
			}
			
			else if(attributes.get(i).getAttribute().endsWith("business_location"))
			{
				this.setVocabularyId(EpcisConstants.BUSINESS_LOCATION_ID);
				List<String> attr = new ArrayList<String>();
				attr.add("urn:epcglobal:epcis:mda:Name");
				String loc = this.getSpecificAttributeValue(this.getBusinessLocationFullUri(attributes.get(i).getValue()), attr);
				if(loc == null || loc.equals(""))
					location.setText("Info not available");
				else
					location.setText(loc);
				
			}
			else if(attributes.get(i).getAttribute().endsWith("disposition"))
			{
				this.setVocabularyId(EpcisConstants.DISPOSITION_ID);
				List<String> attr = new ArrayList<String>();
				attr.add("urn:epcglobal:epcis:mda:Name");
				String disp = this.getSpecificAttributeValue(attributes.get(i).getValue(), attr);
				if(disp == null || disp.equals(""))
					disposition.setText("Info not available");
				else
					disposition.setText(disp);
			}

			else if(attributes.get(i).getAttribute().endsWith("ecspec_name"))
				spec.setText(attributes.get(i).getValue());
			else if(attributes.get(i).getAttribute().endsWith("read_point"))
			{
				this.setVocabularyId(EpcisConstants.READ_POINT_ID);
				List<String> attr = new ArrayList<String>();
				attr.add("urn:epcglobal:epcis:mda:Name");
				String rp = this.getSpecificAttributeValue(attributes.get(i).getValue(), attr);
				if(rp == null || rp.equals(""))
					readpoint.setText("Info not available");
				else
					readpoint.setText(rp);
				
			}
			else if(attributes.get(i).getAttribute().endsWith("transaction_type"))
			{
				this.setVocabularyId(EpcisConstants.BUSINESS_TRANSACTION_TYPE_ID);
				List<String> attr = new ArrayList<String>();
				attr.add("urn:epcglobal:epcis:mda:Name");
				String tt = this.getSpecificAttributeValue(attributes.get(i).getValue(), attr);
				if(tt == null || tt.equals(""))
					transactionType.setText("Info not available");
				else
					transactionType.setText(tt);
				
			}
			else if(attributes.get(i).getAttribute().endsWith("action"))
				action.setText(attributes.get(i).getValue());
			else if(attributes.get(i).getAttribute().endsWith("ecreport_names"))
			{
				StringTokenizer report_names = new StringTokenizer(attributes.get(i).getValue(),",");
				int total = report_names.countTokens();
				if(total == 0)
					reports.add("No reports available!");
				else
					for(int j = 0; j < total; j++)
						reports.add(report_names.nextToken());
			}
			
			
		}
		this.vocabularyId = EpcisConstants.BUSINESS_TRANSACTION_ID;
		return true;
		
		
		
		
		
	}
	public String removeToken(String uri,String separator,int position)
	{
		return this.queryModule.removeToken(uri, separator, position);
	}
	
	public boolean editReadPoints(String location_id)
	{
		boolean result = false;
		StringBuffer readPoints = new StringBuffer();
		int total = newList.size();
		for(int i = 0; i < total; i++)
		{
			readPoints.append(newList.get(i));
			if(i < total -1)
				readPoints.append(",");
		}
		result = masterDataAttributeEdit(location_id, "ReadPoints", readPoints.toString(), "2");
		newList.clear();
		return result;
	}
	

}