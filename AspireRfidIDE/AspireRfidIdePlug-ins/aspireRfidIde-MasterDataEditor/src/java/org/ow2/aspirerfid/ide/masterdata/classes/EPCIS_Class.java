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
import java.util.*;

import org.eclipse.jface.preference.IPreferenceStore;
import org.ow2.aspirerfid.commons.epcis.model.QueryParam;

import org.ow2.aspirerfid.ide.masterdata.preferences.PreferenceConstants;
import org.ow2.aspirerfid.ide.masterdata.Activator;
import org.ow2.aspirerfid.ide.masterdata.tools.*;

/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class EPCIS_Class {
	
	/** Returns the preference store for this UI plug-in */
	IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();

	private String EPC_Tag;
	private String Name;
	private List<Attribute> attributes;

	private MasterDataCaptureClient newClient;
	private QueryClientGuiHelper client;
	private String Vocabulary;
	private String Vocab_Attributes;
	
	public EPCIS_Class(String voc, String voc_attr)
	{
		this.EPC_Tag = this.Name = null;
		attributes = new ArrayList<Attribute>();
		this.Vocabulary = voc;
		this.Vocab_Attributes = voc_attr;
	
	}
	public EPCIS_Class()
	{
		this.EPC_Tag = this.Name = null;
		attributes = new ArrayList<Attribute>();
		
	}
	
	public EPCIS_Class(String EPC_Tag, String Name,String Voc, String Voc_Attr)
	{
		this.EPC_Tag = EPC_Tag;
		this.Name = Name;
		this.attributes = new ArrayList<Attribute>();
		this.Vocabulary = Voc;
		this.Vocab_Attributes = Voc_Attr;
		
		
			
	}
	
	
	
	public String getVocabulary()
	{
		return this.Vocabulary;
	}
	public void setVocabulary(String voc)
	{
		this.Vocabulary = voc;
	}
	
	
	public String getVoc_Attr()
	{
		return this.Vocab_Attributes;
	}
	public void setVocab_Attrs(String voc_attr)
	{
		this.Vocab_Attributes = voc_attr;
	}
	
	
	public void setEPC(String EPC_Tag)
	{
		this.EPC_Tag = EPC_Tag;
		
	}
	
	public String getEPC()
	{
		return this.EPC_Tag;
		
	}
	
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public List<Attribute> getAttributes()
	{
		return this.attributes;
	}
	
	public Attribute getAttribute(int index)
	{
		return this.attributes.get(index);
	}
	public void removeAttribute(int index)
	{
		this.attributes.remove(index);
	}
	public void addAttribute(Attribute a)
	{
		this.attributes.add(a);
	}
	public void addAttribute(Attribute a, int index)
	{
		this.attributes.add(index,a);
	}
	public void addAll(List<Attribute>list)
	{
		this.attributes.addAll(list);
	}
	public void setClient(MasterDataCaptureClient client)
	{
		this.newClient = new MasterDataCaptureClient(preferences.getString(PreferenceConstants.P_MdeEpcisRepositoryCaptureURL));
	}
	public MasterDataCaptureClient getClient2()
	{
		return this.newClient;
	}
	public void setClient(QueryClientGuiHelper client)
	{
		this.client = client;
	}
	public QueryClientGuiHelper getClient()
	{
		return this.client;
	}
	
	public void insertEPC_DB(String fqTag)
	{
		String temp = this.EPC_Tag;
		this.EPC_Tag = fqTag;
		//insertEPC_DB();
		insertEPC_DB2();
		this.EPC_Tag = temp;
	}
	
	
	public void insertEPC_DB2()
	{
		try
		{
			System.out.println("URI = "+this.getVocabulary());
			System.out.println("EPC = "+this.getEPC());
			newClient.simpleMasterDataEdit(this.getVocabulary(),this.getEPC(), "1");
			//QueryParam queryParam = new QueryParam();
			//queryParam.setName("eventType");
			//queryParam.setValue("SimpleMasterDataInsertion");
			//client.addParameter(queryParam);
			//QueryParam param = new QueryParam();
			//param.setName(this.getVocabulary());
			//param.setValue(this.getEPC());
			//client.addParameter(param);
			//client.runMasterDataInsertionQuery();
		}
		catch(Exception e){}
		
	}
	
	
	public void insertEPC_DB()
	{
		this.newClient.simpleMasterDataEdit(this.getVocabulary(), this.getEPC(), "1");
	/*	try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName(this.getVocabulary());
			param.setValue(this.getEPC());
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
		}
		catch(Exception e){}*/
		
	}
	public void insertAttributes_DB(String fqtag)
	{
		String temp = this.EPC_Tag;
		this.EPC_Tag = fqtag;
		insertAttributes_DB();
		this.EPC_Tag = temp;
	}
	
	public void insertAttributes_DB()
	{
		
		Iterator<Attribute> it = this.getAttributes().iterator();
	
		while(it.hasNext())
		{
	
			Attribute temp = it.next();
			//temp.setAttribute("urn:epcglobal:epcis:mda:"+temp.getAttribute());
			temp.setAttribute(temp.getAttribute());
			
			temp.setValue(temp.getValue());
			System.out.println("Attrribute is "+temp.getValue());
			this.insert_Attribute_Value(temp);
			
		}
		
	}
	public void insertName_DB(String fqtag)
	{
		String temp = this.EPC_Tag;
		this.EPC_Tag = fqtag;
		insertName_DB();
		this.EPC_Tag = temp;
	}
	public void insertName_DB()
	{
		if(this.newClient == null)
			System.out.println("Client null");
		System.out.println("Vocabulary is");
		System.out.println(this.getVocabulary());
		System.out.println("EPC is");
		System.out.println(this.getEPC());
		System.out.println("Name is");
		System.out.println(this.getName());
		
		
		this.newClient.simpleMasterDataAndAttributeEdit(this.getVocabulary(), this.getEPC(), "Name", this.getName(), "1");
		//this.insert_Attribute_Value(new Attribute("Name",this.getName()));
	}
	
	public void insert_Attribute_Value(Attribute at)
	{
		
		System.out.println("n insert attr val");
		System.out.println("Attr="+at.getAttribute()+" val="+at.getValue());
		this.newClient.simpleMasterDataAndAttributeEdit(this.getVocabulary(), this.getEPC(), at.getAttribute(), at.getValue(), "2");//"1");
		/*try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataInsertion");
			client.addParameter(queryParam);
			QueryParam param = new QueryParam();
			param.setName(this.getVoc_Attr());
			param.setValue(this.getEPC() + "@" + at.getAttribute() + "@"+ at.getValue());
			client.addParameter(param);
			client.runMasterDataInsertionQuery();
			System.out.println("Back from query client");
				

		
		}
		catch(Exception e){}*/
		
	}
	
	
	
	
	
	
	public String getValueByAttribute(String attribute)
	{
		Iterator<Attribute> it = this.getAttributes().iterator();
		while(it.hasNext())
		{
			Attribute temp = it.next();
			if(temp.getAttribute().endsWith(attribute))
				return temp.getValue();
		}
		return null;
		
		
	}
	
	
	
	
	
	
	
	
	

}
