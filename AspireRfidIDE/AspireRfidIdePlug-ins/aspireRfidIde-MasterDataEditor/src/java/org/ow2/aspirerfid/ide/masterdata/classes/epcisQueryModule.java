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

import org.fosstrak.epcis.model.ArrayOfString;
import org.fosstrak.epcis.model.Poll;
import org.fosstrak.epcis.model.QueryParam;
import org.fosstrak.epcis.model.QueryParams;
import org.fosstrak.epcis.model.QueryResults;
import org.fosstrak.epcis.model.VocabularyElementType;
import org.fosstrak.epcis.model.VocabularyType;
import org.ow2.aspirerfid.ide.masterdata.classes.*;

import org.ow2.aspirerfid.ide.masterdata.tools.QueryClientGuiHelper;

import java.util.*;

import javax.xml.bind.JAXBElement;


/**
 * @author Eystathios Mertikas e-mail: efme@ait.edu.gr
 * 
 */
public class epcisQueryModule {
	
	private QueryClientGuiHelper client;
	private String vocabulary;
	
	
	public epcisQueryModule()
	{
		client = null;
	}
	public epcisQueryModule(QueryClientGuiHelper client)
	{
		this.client = client;
	}
	public epcisQueryModule(QueryClientGuiHelper client, String vocabulary)
	{
		this.client = client;
		this.vocabulary = vocabulary;
	}
	
	
	public void setClient(QueryClientGuiHelper client)
	{
		this.client = client;
	}
	
	public void setVocabulary(String vocabulary)
	{
		this.vocabulary = vocabulary;
	}
	public String getVocabulary()
	{
		return this.vocabulary;
	}
	
	/*public List<Object> getEPCTags2(String epc)
	{
		Object[][] data = {};
		List<Object> result = new ArrayList<Object>();
		org.fosstrak.epcis.model.ArrayOfString ls = new org.fosstrak.epcis.model.ArrayOfString();
		org.fosstrak.epcis.model.ArrayOfString ls2 = new org.fosstrak.epcis.model.ArrayOfString();
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(false);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(false);
			client.addParameter(param3);	
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			ls.getString().add("urn:epcglobal:epcis:vtype:Disposition");
			voc.setValue(ls);
			client.addParameter(voc);
			
			QueryParam uri = new QueryParam();
			uri.setName("WD_name");
			
			ls2.getString().add(epc);
			
			uri.setValue(ls2);
			client.addParameter(uri);
			
			data = client.runMasterDataQuery();
			for (int j = 0; j < data.length; j++) 
			{
			                                            
				if (!data.equals(null) && data[j][0].equals(this.vocabulary))
				{
					for (int i = 1; i < data[j].length; i++) 
					{
						if (data[j][i] == null)
							break;
						result.add(data[j][i]);
						
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}
	*/
	
	public List<Object> getEPCTags()
	{
		Object[][] data = {};
		List<Object> result = new ArrayList<Object>();
		
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(false);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(false);
			client.addParameter(param3);		
			data = client.runMasterDataQuery();
			for (int j = 0; j < data.length; j++) 
			{
			                                            
				if (!data.equals(null) && data[j][0].equals(this.vocabulary))
				{
					for (int i = 1; i < data[j].length; i++) 
					{
						if (data[j][i] == null)
							break;
						result.add(data[j][i]);
						
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public String removeOldParents(String uri, String parent)
	{
		StringBuffer newId = new StringBuffer();
		//newId.append(parent);
		StringTokenizer oldParents = new StringTokenizer(uri,",");
		int total = oldParents.countTokens();
		for(int i = 0; i < total; i++)
		{
			String old = oldParents.nextToken();
			if(!old.equals(parent))
				continue;
			break;
		}
		newId.append(parent);
		while(oldParents.hasMoreTokens())
		{
			newId.append(",");
			newId.append(oldParents.nextToken());
			
		}
		return newId.toString();
	}
	
	
	public String removeToken(String uri,String separator,int position)
	{
		StringBuffer result = new StringBuffer();
		StringTokenizer uriParser = new StringTokenizer(uri,separator);
		int total = uriParser.countTokens();
		if(total == 0)
			return "";
		if(position == -1)
			position = total - 1;
		for(int i = 0; i < total; i++)
		{
			if(i == position)
				continue;
			result.append(uriParser.nextToken());
		}
		return result.toString();
		
	}
	
	//Parses a uri and returns the token requested. If position is -1 then returns last token
	public String parseNameSpace(String uri,String separator,int position)
	{
		StringTokenizer uriParser = new StringTokenizer(uri,separator);
		int total = uriParser.countTokens();
		if(total == 0)
			return "";
		if(position > total)
			return "";
		if(position > 0)
			total = position;
			
		for(int i = 0; i < total -1; i++)
			uriParser.nextToken();//let it go
		return uriParser.nextToken();
			
		
	}
	
	private String getAttr_Value(final List<VocabularyType> vocabularyList)
	{
		
		for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) 
    		{
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			
			for (Object v : vocabularyElementList)
			{
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				if(vocabularyElement.getAttribute().size()>0)
				{
				 String attribute =  vocabularyElement.getAttribute().get(0).getId();
				  String value =	vocabularyElement.getAttribute().get(0).getContent().get(0).toString();
				  System.out.println("<Attribute,Value>= <"+attribute+","+value+">");
				  return value;
				}
					
				
				
					
					
			}
				
			
    	}
			
		return null;
    		
		
		
		
	}
	
	public List<String>getIds(final List<VocabularyType> vocabularyList)
	{
		List<String>result = new ArrayList<String>();
		for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) 
    		{
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList)
			{
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				result.add(vocabularyElement.getId());
				
					
					
			}
				
			
    	}
			
    		
		return result;
	}
	
	
	
		
	
	
	
	public java.util.TreeMap<Key_Map,String>getInfo_NT(final List<VocabularyType> vocabularyList)
	{
		if(vocabularyList.size() == 0)
			{
			System.out.println("Query RETURNED NOTHING");
			return null;
			}
		TreeMap<Key_Map,String> info = new java.util.TreeMap<Key_Map,String>();
		int row = 0;
		System.out.println("Create Map\n\n\n");
    	for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList){
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				Key_Map Key_Attribute = new Key_Map();
				String Value_Attribute = vocabularyElement.getId();//the uri
				
				
				System.out.println("In for--> id = "+vocabularyElement.getId()+"SIZE = "+vocabularyElement.getAttribute().size());
				
				if(vocabularyElement.getAttribute().size()>0){
					
					int s = vocabularyElement.getAttribute().size();
					for(int aa1=0; aa1<s; aa1++){
					
						String attribute = vocabularyElement.getAttribute().get(aa1).getId();
						System.out.println("========>Attr: "+attribute);//vocabularyElement.getAttribute().get(aa1).getId());
						if(attribute.endsWith("Name"))//equals("Name"))//vocabularyElement.getAttribute().get(aa1).getId().equals("Name"));//"Logical_Name"))
						{
							System.out.println(" Name  ="+vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString()+", idx ="+pos);
							Key_Attribute.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							Key_Attribute.setIdx(pos);
							
							info.put(Key_Attribute, Value_Attribute);
							pos++;
						
							//break;
						}
							
						
					}
					
					
				}
				
			}
			row++;
    		
    	}
	return info;
	
		
	}
	
	
	
	
	public String getSpecificAttribute(String uri,List<String> attribute)
	{
		ArrayOfString vocabulary = new ArrayOfString();
		vocabulary.getString().add(this.vocabulary);
		ArrayOfString attribute_list = new ArrayOfString();
		attribute_list.getString().addAll(attribute);
	
		ArrayOfString element = new ArrayOfString();
		element.getString().add(uri);
			
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			voc.setValue(vocabulary);
			client.addParameter(voc);
			
			QueryParam elem = new QueryParam();
			elem.setName("EQ_name");
			elem.setValue(element);
			client.addParameter(elem);
			
			/*QueryParam ids = new QueryParam();
			ids.setName("HASATTR");
			ids.setValue(attribute_list);
			client.addParameter(ids);*/
			
			QueryParam attrName = new QueryParam();
			attrName.setName("attributeNames");
			attrName.setValue(attribute_list);
			client.addParameter(attrName);
			
			
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getAttr_Value(results.getResultsBody().getVocabularyList().getVocabulary());			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<String>getElementIDs(List<String> attribute,List<String>value)
	{
		ArrayOfString vocabulary = new ArrayOfString();
		vocabulary.getString().add(this.vocabulary);
		ArrayOfString attribute_list;
		ArrayOfString value_list;
	
		
			
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			voc.setValue(vocabulary);
			client.addParameter(voc);
			if(attribute != null && attribute.size()>0)
			{
				attribute_list = new ArrayOfString();		
				attribute_list.getString().addAll(attribute);
				QueryParam ids = new QueryParam();
				ids.setName("HASATTR");
				ids.setValue(attribute_list);
				client.addParameter(ids);
			}
			if(value != null && value.size() > 0)
			{
				value_list = new ArrayOfString();
				value_list.getString().addAll(value);
				QueryParam vals = new QueryParam();
				vals.setName("EQATTR_urn:epcglobal:epcis:mda:deprecated");
				vals.setValue(value_list);
				client.addParameter(vals);
				
			}
				
			
			
			
			
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getIds(results.getResultsBody().getVocabularyList().getVocabulary());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	//This method returns the uris of a specific vocabulary that have one of the parameters in the parameter list with a non-null value
	public List<String>getElementIDs(List<String> attribute)
	{
		ArrayOfString vocabulary = new ArrayOfString();
		vocabulary.getString().add(this.vocabulary);
		ArrayOfString attribute_list;
	
		
			
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			voc.setValue(vocabulary);
			client.addParameter(voc);
			if(attribute != null && attribute.size()>0)
			{
				attribute_list = new ArrayOfString();		
				attribute_list.getString().addAll(attribute);
				QueryParam ids = new QueryParam();
				ids.setName("HASATTR");
				ids.setValue(attribute_list);
				client.addParameter(ids);
			}
			
			
			
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getIds(results.getResultsBody().getVocabularyList().getVocabulary());			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

	public TreeMap<Key_Map,String> queryTagsAndNamesInfo(String uri,String extrAttr)
	{
		String attribute = "urn:epcglobal:epcis:mda:Name";
		
		
		
		ArrayOfString vocabulary = new ArrayOfString();
		vocabulary.getString().add(this.vocabulary);
		
		ArrayOfString attribute_list = new ArrayOfString();		
		attribute_list.getString().add(attribute);
		if(extrAttr != null)
			attribute_list.getString().add(extrAttr);
		
		//elements.getString().add(uri);
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			voc.setValue(vocabulary);
			client.addParameter(voc);
			
			QueryParam ids = new QueryParam();
			ids.setName("HASATTR");
			ids.setValue(attribute_list);
			client.addParameter(ids);
			if(uri != null && !uri.equals(""))//This is for the regular expresion search
			{
				ArrayOfString elements = new ArrayOfString();
				elements.getString().add(uri);
				QueryParam elems = new QueryParam();
				elems.setName("WD_name");
				elems.setValue(elements);
				client.addParameter(elems);
			}
			
			QueryParam attrs = new QueryParam();
			attrs.setName("attributeNames");
			attrs.setValue(attribute_list);
			client.addParameter(attrs);
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getInfo_NT(results.getResultsBody().getVocabularyList().getVocabulary());			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

		
		
	}
	
	
	
	
	
	
	
	
	
/*	public java.util.TreeMap<Key_Map,String> getTags_Names2(String uri)//List<String> uri)
	{
		org.fosstrak.epcis.model.ArrayOfString elements = new org.fosstrak.epcis.model.ArrayOfString();
		org.fosstrak.epcis.model.ArrayOfString vocab = new org.fosstrak.epcis.model.ArrayOfString();
		org.fosstrak.epcis.model.ArrayOfString att = new org.fosstrak.epcis.model.ArrayOfString();
		vocab.getString().add(this.vocabulary);
		att.getString().add("urn:epcglobal:epcis:mda:Name");
		//for(int i = 0; i < uri.size(); i++)
	//	{
	//		System.out.println("URI:"+uri.get(i)+"\n");
		//	elements.getString().add(uri.get(i));
		//}
		elements.getString().add(uri);
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			
			QueryParam voc = new QueryParam();
			voc.setName("vocabularyName");
			voc.setValue(vocab);
			client.addParameter(voc);
			
			QueryParam ids = new QueryParam();
			ids.setName("WD_name");
			ids.setValue(elements);
			client.addParameter(ids);
			
			QueryParam attrs = new QueryParam();
			attrs.setName("attributeNames");
			attrs.setValue(att);
			client.addParameter(attrs);
			
			
			
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getInfo_NT(results.getResultsBody().getVocabularyList().getVocabulary());			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
	}
	
	*/
	public java.util.TreeMap<Key_Map,String> getTags_Names()
	{
		TreeMap<Key_Map,String> info = new java.util.TreeMap<Key_Map,String>();
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return getInfo_NT(results.getResultsBody().getVocabularyList().getVocabulary());			

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	public boolean checkParents(String uri,java.util.List<String>parents)
	{
		Iterator<String>it = parents.iterator();
		while(it.hasNext())
		{
			String temp = it.next();
			if(temp.equals(uri))
				return true;
		}
		return false;
	}
		
	public void isParent(String fqTag, java.util.List<String>parents)
	{
		StringTokenizer uri = new StringTokenizer(fqTag,",");
		
		if(uri.countTokens() == 1)
		{
			
			if(!checkParents(fqTag,parents))
			{
				parents.add(fqTag);
				System.out.println("Added = "+fqTag);
			}		
				
		}
		else
		{
			//int times = uri.countTokens()-1;
			//for(int i = 0; i < times;i++){
			String p = uri.nextToken();
			if(!checkParents(p,parents))
			{
				parents.add(p);
				System.out.println("Added = "+p);
			}//}//
		}
		
		
		
	}
	
	
	
	public java.util.List<EPCIS_Class> createList2(final List<VocabularyType> vocabularyList,HashMap<String,String>UriToName,java.util.List<String>parents)
	{
		java.util.List<EPCIS_Class> elems = new  ArrayList<EPCIS_Class>();
		int row = 0;
		
		
		for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList){
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				//BusinessLocation biz_temp = new BusinessLocation();
				EPCIS_Class temp = new EPCIS_Class();
				
				
				Key_Map Key_Attribute = new Key_Map();
				temp.setEPC(vocabularyElement.getId());//get the uri
				//temp.setfqTag(vocabularyElement.getId());
				//isParent(vocabularyElement.getId(),parents);
				//if(vocabularyElement.getChildren() != null)//has children
					//temp.setChildren(vocabularyElement.getChildren().getId());
		
				
				System.out.println("In for--> id = "+vocabularyElement.getId()+"SIZE = "+vocabularyElement.getAttribute().size());
				
				if(vocabularyElement.getAttribute().size()>0){
					
					int s = vocabularyElement.getAttribute().size();
					for(int aa1=0; aa1<s; aa1++){
					
						String attribute;// = vocabularyElement.getAttribute().get(aa1).getId();
						attribute = this.parseNameSpace(vocabularyElement.getAttribute().get(aa1).getId(),":", -1);
						System.out.println("========>Attr: "+attribute);//vocabularyElement.getAttribute().get(aa1).getId());
						org.ow2.aspirerfid.ide.masterdata.classes.Attribute attribute2 = new org.ow2.aspirerfid.ide.masterdata.classes.Attribute(attribute,vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						if(attribute.endsWith("Name"))
						{
							UriToName.put(vocabularyElement.getId(), vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							temp.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						}
						//else if (attribute.endsWith("Read Point"))//equalsIgnoreCase("Read Point"))
						//{
							//temp.setReadPoint(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						//}
						else
							temp.addAttribute(attribute2);
						
						//if(attribute.endsWith("Name"))//equals("Name"))//vocabularyElement.getAttribute().get(aa1).getId().equals("Name"));//"Logical_Name"))
						//{
							System.out.println("Attr is Name or"+vocabularyElement.getAttribute().get(aa1).getId());
							
							//Key_Attribute.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							//Key_Attribute.setIdx(pos);
							//pos++;
							//info.put(Key_Attribute, Value_Attribute);
						
							//break;
						//}
							
						
					}
					
					
				}
				elems.add(temp);
				
			}
			
			row++;
    		
    	}	
    	return elems;

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public java.util.List<BusinessLocation> createList(final List<VocabularyType> vocabularyList,HashMap<String,String>UriToName,java.util.List<String>parents,java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.BusinessLocation> deprecated)
	{
		java.util.List<BusinessLocation> business_locs = new ArrayList<BusinessLocation>();
		int row = 0;
		boolean inactive = false;
		System.out.println("Create Map\n\n\n");
    	for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList){
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				BusinessLocation biz_temp = new BusinessLocation();
				
				
				//Key_Map Key_Attribute = new Key_Map();
				biz_temp.setEPC(vocabularyElement.getId());//get the uri
				biz_temp.setfqTag(vocabularyElement.getId());
				isParent(vocabularyElement.getId(),parents);
				if(vocabularyElement.getChildren() != null)//has children
					biz_temp.setChildren2(vocabularyElement.getChildren().getId());
		
				
				System.out.println("In for--> id = "+vocabularyElement.getId()+"SIZE = "+vocabularyElement.getAttribute().size());
				
				if(vocabularyElement.getAttribute().size()>0){
					
					int s = vocabularyElement.getAttribute().size();
					for(int aa1=0; aa1<s; aa1++){
					
						String attribute = this.parseNameSpace(vocabularyElement.getAttribute().get(aa1).getId(),":",-1);
						System.out.println("========>Attr: "+attribute);//vocabularyElement.getAttribute().get(aa1).getId());
						org.ow2.aspirerfid.ide.masterdata.classes.Attribute attribute2 = new org.ow2.aspirerfid.ide.masterdata.classes.Attribute(attribute,vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						if(attribute.endsWith("Name"))
						{
							UriToName.put(vocabularyElement.getId(), vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							biz_temp.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						}
						else if (attribute.endsWith("Read Point"))//equalsIgnoreCase("Read Point"))
						{
							biz_temp.setReadPoint(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						}
						//else
							biz_temp.addAttribute(attribute2);
						if(attribute.equalsIgnoreCase("deprecated") && vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString().equalsIgnoreCase("yes"))
							inactive = true;
						
						
						//if(attribute.endsWith("Name"))//equals("Name"))//vocabularyElement.getAttribute().get(aa1).getId().equals("Name"));//"Logical_Name"))
						//{
							System.out.println("Attr is Name or"+vocabularyElement.getAttribute().get(aa1).getId());
							
							//Key_Attribute.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							//Key_Attribute.setIdx(pos);
							//pos++;
							//info.put(Key_Attribute, Value_Attribute);
						
							//break;
						//}
							
						
					}
					
					
				}
				if(inactive){
					deprecated.add(biz_temp);
					inactive = false;
				}
				else
					business_locs.add(biz_temp);
				
			}
			
			row++;
    		
    	}	
    	return business_locs;
		
		
	}
	
/*	
	public java.util.List<BusinessLocation> createList(final List<VocabularyType> vocabularyList,HashMap<String,String>UriToName,java.util.List<String>parents)
	{
		java.util.List<BusinessLocation> business_locs = new ArrayList<BusinessLocation>();
		int row = 0;
		System.out.println("Create Map\n\n\n");
    	for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList){
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				BusinessLocation biz_temp = new BusinessLocation();
				
				
				//Key_Map Key_Attribute = new Key_Map();
				biz_temp.setEPC(vocabularyElement.getId());//get the uri
				biz_temp.setfqTag(vocabularyElement.getId());
				isParent(vocabularyElement.getId(),parents);
				if(vocabularyElement.getChildren() != null)//has children
					biz_temp.setChildren2(vocabularyElement.getChildren().getId());
		
				
				System.out.println("In for--> id = "+vocabularyElement.getId()+"SIZE = "+vocabularyElement.getAttribute().size());
				
				if(vocabularyElement.getAttribute().size()>0){
					
					int s = vocabularyElement.getAttribute().size();
					for(int aa1=0; aa1<s; aa1++){
					
						String attribute = this.parseNameSpace(vocabularyElement.getAttribute().get(aa1).getId(),":",-1);
						System.out.println("========>Attr: "+attribute);//vocabularyElement.getAttribute().get(aa1).getId());
						org.ow2.aspirerfid.ide.masterdata.classes.Attribute attribute2 = new org.ow2.aspirerfid.ide.masterdata.classes.Attribute(attribute,vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						if(attribute.endsWith("Name"))
						{
							UriToName.put(vocabularyElement.getId(), vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							biz_temp.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						}
						else if (attribute.endsWith("Read Point"))//equalsIgnoreCase("Read Point"))
						{
							biz_temp.setReadPoint(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						}
						//else
							biz_temp.addAttribute(attribute2);
						
						//if(attribute.endsWith("Name"))//equals("Name"))//vocabularyElement.getAttribute().get(aa1).getId().equals("Name"));//"Logical_Name"))
						//{
							System.out.println("Attr is Name or"+vocabularyElement.getAttribute().get(aa1).getId());
							
							//Key_Attribute.setName(vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
							//Key_Attribute.setIdx(pos);
							//pos++;
							//info.put(Key_Attribute, Value_Attribute);
						
							//break;
						//}
							
						
					}
					
					
				}
				business_locs.add(biz_temp);
				
			}
			
			row++;
    		
    	}	
    	return business_locs;
		
		
	}
	*/
	
	public java.util.List<EPCIS_Class> bringEverything2(HashMap<String,String>UriToName,java.util.List<String>parents)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return createList2(results.getResultsBody().getVocabularyList().getVocabulary(),UriToName,parents);	
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}		
		

		
	}
	
	
	java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> constructAttributes(final List<VocabularyType> vocabularyList, String epcURI)
	{
	
		java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> attributes = new ArrayList<org.ow2.aspirerfid.ide.masterdata.classes.Attribute>();
		
		for (Object o : vocabularyList)
    	{
    		if (o instanceof JAXBElement<?>) {
				o = ((JAXBElement<?>) o).getValue();
			}
    		VocabularyType vocabulary = (VocabularyType) o;
			if(!vocabulary.getType().equals(this.vocabulary))
				continue;
			List<VocabularyElementType> vocabularyElementList;
			vocabularyElementList = vocabulary.getVocabularyElementList().getVocabularyElement();
		
			int pos = 0;
			for (Object v : vocabularyElementList){
				
				VocabularyElementType vocabularyElement = (VocabularyElementType) v;
				
				
				
				//Key_Map Key_Attribute = new Key_Map();
				if(!epcURI.equals(vocabularyElement.getId()))
						continue;
				
				System.out.println("In for--> id = "+vocabularyElement.getId()+"SIZE = "+vocabularyElement.getAttribute().size());
				
				if(vocabularyElement.getAttribute().size()>0){
					
					int s = vocabularyElement.getAttribute().size();
					for(int aa1=0; aa1<s; aa1++){
					
						String attribute = vocabularyElement.getAttribute().get(aa1).getId();
						System.out.println("========>Attr: "+attribute);//vocabularyElement.getAttribute().get(aa1).getId());
						org.ow2.aspirerfid.ide.masterdata.classes.Attribute attribute2 = new org.ow2.aspirerfid.ide.masterdata.classes.Attribute(attribute,vocabularyElement.getAttribute().get(aa1).getContent().get(0).toString());
						
						attributes.add(attribute2);
						
						
						
					}
					
					
				}
				
				
			}
			
	//		row++;
    		
    	}	
    	return attributes;
		
		
	}
	
	public java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> getElemsAttributes(String epc)
	{
		try
		{
			ArrayOfString element = new ArrayOfString();
			element.getString().add(epc);
			ArrayOfString vocabulary = new ArrayOfString();
			vocabulary.getString().add(this.vocabulary);
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			QueryParam elem = new QueryParam();
			elem.setName("EQ_name");
			elem.setValue(element);
			client.addParameter(elem);
			
			QueryParam vocab = new QueryParam();
			vocab.setName("vocabularyName");
			vocab.setValue(vocabulary);
			client.addParameter(vocab);
			
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return constructAttributes(results.getResultsBody().getVocabularyList().getVocabulary(),epc);	
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}		
		

		
	}
	
	
	public java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.Attribute> getAttributes(String epc)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return constructAttributes(results.getResultsBody().getVocabularyList().getVocabulary(),epc);	
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}		
		
		
	}
	
	public java.util.List<BusinessLocation> bringEverything(HashMap<String,String>UriToName,java.util.List<String>parents,java.util.List<org.ow2.aspirerfid.ide.masterdata.classes.BusinessLocation> deprecated)
	{
		try
		{
			client.clearParameters();
			QueryParam queryParam = new QueryParam();
			queryParam.setName("eventType");
			queryParam.setValue("SimpleMasterDataQuery");
			client.addParameter(queryParam);
			QueryParam param2 = new QueryParam();
			param2.setName("includeAttributes");
			param2.setValue(true);
			client.addParameter(param2);
			QueryParam param3 = new QueryParam();
			param3.setName("includeChildren");
			param3.setValue(true);
			client.addParameter(param3);
			System.out.print("running query...\n");
			QueryResults results = client.runGenericQuery();
			return createList(results.getResultsBody().getVocabularyList().getVocabulary(),UriToName,parents,deprecated);	
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}		
		
	}
	
	java.util.List<String> returnValues(final List<VocabularyType> vocabularyList,String uri,String voc,String attr)
	{
		Iterator<VocabularyType> itVoc = vocabularyList.iterator();
		java.util.List<String> result = new ArrayList<String>();
	
		while(itVoc.hasNext())
		{
			VocabularyType tempVoc = itVoc.next();
			if(!tempVoc.getType().equals(voc))
				continue;
			List<VocabularyElementType> vocabularyElementList = tempVoc.getVocabularyElementList().getVocabularyElement();
			Iterator<VocabularyElementType> itEl = vocabularyElementList.iterator();
			while(itEl.hasNext())
			{
				VocabularyElementType elem = itEl.next();
				if(!elem.getId().endsWith(uri))
					continue;
				if(elem.getAttribute().size() > 0)
				{
					int size = elem.getAttribute().size();
					for(int i = 0; i < size; i++)
					{
						String attribute = elem.getAttribute().get(i).getId();
						if(!attribute.endsWith(attr))
							continue;
						else
						{
							StringTokenizer theReports = new StringTokenizer(elem.getAttribute().get(i).getContent().get(0).toString(),",");
							int tokens = theReports.countTokens();
							for(int j = 0; j < tokens; j++)
							{
								result.add(theReports.nextToken());
							}
							break;
						}
						//String value = elem.getAttribute().get(i).getContent().get(0).toString();
						//result.add(value);
						
					}
					
					
					
					
				}
				
			}
			
			
		}
		return result;
		
	}
	
	public java.util.List<String> getValues(String voc, String uri, String attr)
	{
		client.clearParameters();
		QueryParam queryParam = new QueryParam();
		queryParam.setName("eventType");
		queryParam.setValue("SimpleMasterDataQuery");
		client.addParameter(queryParam);
		//QueryParam vocab = new QueryParam();
		//vocab.setName("vocabularyName");
		//java.util.List<String> vocs = new ArrayList<String>();
		//ArrayOfString vocs = new ArrayOfString();
		//vocs.add(voc);
		//vocab.setValue(vocs);
		///client.addParameter(vocab);
		//QueryParam attr = new QueryParam();
		//attr.setName("attributeNames");
		//attr.setValue("ecspec_name");//("ecreport_names");
		//client.addParameter(attr);
		QueryParam param2 = new QueryParam();
		param2.setName("includeAttributes");
		param2.setValue(true);
		client.addParameter(param2);
		QueryParam param3 = new QueryParam();
		param3.setName("includeChildren");
		param3.setValue(false);
		client.addParameter(param3);
		System.out.print("running query...\n");
		try
		{
			QueryResults results = client.runGenericQuery();
			return returnValues(results.getResultsBody().getVocabularyList().getVocabulary(),uri,voc,attr);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
