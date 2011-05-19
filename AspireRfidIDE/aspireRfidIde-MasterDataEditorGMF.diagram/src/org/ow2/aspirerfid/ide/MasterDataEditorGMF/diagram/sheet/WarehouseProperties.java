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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.sheet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.common.ui.services.properties.extended.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * @author Eleftherios Karageorgiou (elka) e-mail: elka@ait.edu.gr
 *
 */
public class WarehouseProperties extends PropertySource {
	
	/**
	 * Warehouse
	 */
	private AbstractWarehouse element = (AbstractWarehouseImpl) getElement();
	
	/**
	 * Element attr
	 */
	private String[] elementAttr = {element.getAttr1(), element.getAttr2(), element.getAttr3(), element.getAttr4(), element.getAttr5(), 
									element.getAttr6(), element.getAttr7(), element.getAttr8(), element.getAttr9(), element.getAttr10(), 
									element.getAttr11(), element.getAttr12(), element.getAttr13(), element.getAttr14(), element.getAttr15(), 
									element.getAttr16(), element.getAttr17(), element.getAttr18(), element.getAttr19(), element.getAttr20()};
	
	/**
	 * Constructor
	 */
	public WarehouseProperties(Object object) {
		super(object);
	}
	
	/**
	 * Get property descriptors
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {		
		int count = 0;
		
		for (int i = 0; i < MasterDataEditParts.getNewWarehouseAttr().length; i++) {
			if (!(MasterDataEditParts.getNewWarehouseAttr()[i].isEmpty() && MasterDataEditParts.getNewWarehouseAttr()[i] == "")) {
				count++;
			}
		}
		IPropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[4 + count];
		
		propertyDescriptors[0] = new TextPropertyDescriptor("ID", "ID");
		propertyDescriptors[1] = new TextPropertyDescriptor("Name", "Name");
		propertyDescriptors[2] = new TextPropertyDescriptor("Description", "Description");
		propertyDescriptors[3] = new TextPropertyDescriptor("Type", "Type");
		
		for (int i = 4, j = 0; i < propertyDescriptors.length; i++, j++) {
			propertyDescriptors[i] = new TextPropertyDescriptor(MasterDataEditParts.getNewWarehouseAttr()[j], 
					MasterDataEditParts.getNewWarehouseAttr()[j]);
		}

		return propertyDescriptors;
	}
	
	/**
	 * Get property value
	 */
	@Override
	public Object getPropertyValue(Object propertyId) {
		if (propertyId.equals("ID"))
			return element.getID();
		else if (propertyId.equals("Name"))
			return element.getName();
		else if (propertyId.equals("Description"))
			return element.getDescription();
		else if (propertyId.equals("Type"))
			return element.getType();
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[0]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[0]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[1]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[1]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[2]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[2]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[3]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[3]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[4]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[4]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[5]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[5]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[6]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[6]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[7]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[7]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[8]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[8]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[9]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[9]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[10]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[10]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[11]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[11]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[12]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[12]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[13]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[13]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[14]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[14]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[15]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[15]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[16]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[16]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[17]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[17]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[18]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[18]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[19]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[19]);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[20]))
			return getRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[20]);
		
		return "";
	}
	
	/**
	 * Set property value
	 */
	@Override
	public void setPropertyValue(Object propertyId, Object value) {	
		resetProperties();
		
		if (propertyId.equals("ID"))
			element.setID((String) value);
		else if (propertyId.equals("Name"))
			element.setName((String) value);
		else if (propertyId.equals("Description"))
			element.setDescription((String) value);
		else if (propertyId.equals("Type"))
			element.setType((String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[0])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[0], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[1]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[1], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[2])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[2], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[3]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[3], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[4])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[4], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[5]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[5], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[6])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[6], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[7]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[7], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[8])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[8], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[9]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[9], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[10])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[10], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[11]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[11], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[12])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[12], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[13]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[13], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[14])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[14], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[15]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[15], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[16])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[16], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[17]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[17], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[18])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[18], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[19]))
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[19], (String) value);
		else if (propertyId.equals(MasterDataEditParts.getNewWarehouseAttr()[20])) 
			setRandomPropertyValue(MasterDataEditParts.getNewWarehouseAttr()[20], (String) value);
	}
	
	/**
	 * Set property value
	 */
	public void setRandomPropertyValue(String propertyId, String value) {
		if (element.getAttr1().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr1("");
			else element.setAttr1(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr2().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr2("");
			else element.setAttr2(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr3().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr3("");
			else element.setAttr3(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr4().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr4("");
			else element.setAttr4(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr5().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr5("");
			else element.setAttr5(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr6().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr6("");
			else element.setAttr6(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr7().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr7("");
			else element.setAttr7(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr8().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr8("");
			else element.setAttr8(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr9().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr9("");
			else element.setAttr9(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr10().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr10("");
			else element.setAttr10(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr11().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr11("");
			else element.setAttr11(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr1().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr12("");
			else element.setAttr12(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr13().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr13("");
			else element.setAttr13(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr14().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr14("");
			else element.setAttr14(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr15().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr15("");
			else element.setAttr15(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr16().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr16("");
			else element.setAttr16(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr17().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr17("");
			else element.setAttr17(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr18().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr18("");
			else element.setAttr18(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr19().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr19("");
			else element.setAttr19(propertyId + "_" + value);
			return;
		}
		else if (element.getAttr20().startsWith(propertyId + "_")) {
			if (value.isEmpty() || value == "")	element.setAttr20("");
			else element.setAttr20(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr1().isEmpty() || element.getAttr1() == "")) {
			element.setAttr1(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr2().isEmpty() || element.getAttr2() == "")) {
			element.setAttr2(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr3().isEmpty() || element.getAttr3() == "")) {
			element.setAttr3(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr4().isEmpty() || element.getAttr4() == "")) {
			element.setAttr4(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr5().isEmpty() || element.getAttr5() == "")) {
			element.setAttr5(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr6().isEmpty() || element.getAttr6() == "")) {
			element.setAttr6(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr7().isEmpty() || element.getAttr7() == "")) {
			element.setAttr7(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr8().isEmpty() || element.getAttr8() == "")) {
			element.setAttr8(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr9().isEmpty() || element.getAttr9() == "")) {
			element.setAttr9(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr10().isEmpty() || element.getAttr10() == "")) {
			element.setAttr10(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr11().isEmpty() || element.getAttr11() == "")) {
			element.setAttr1(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr12().isEmpty() || element.getAttr12() == "")) {
			element.setAttr12(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr13().isEmpty() || element.getAttr13() == "")) {
			element.setAttr13(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr14().isEmpty() || element.getAttr14() == "")) {
			element.setAttr14(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr15().isEmpty() || element.getAttr15() == "")) {
			element.setAttr15(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr16().isEmpty() || element.getAttr16() == "")) {
			element.setAttr16(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr17().isEmpty() || element.getAttr17() == "")) {
			element.setAttr17(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr18().isEmpty() || element.getAttr18() == "")) {
			element.setAttr18(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr19().isEmpty() || element.getAttr19() == "")) {
			element.setAttr19(propertyId + "_" + value);
			return;
		}
		else if ((element.getAttr20().isEmpty() || element.getAttr20() == "")) {
			element.setAttr20(propertyId + "_" + value);
			return;
		}
	}
	
	/**
	 * Get random property value
	 */
	private String getRandomPropertyValue(String warehouseAttr) {
		if (element.getAttr1().startsWith(warehouseAttr + "_"))
			return element.getAttr1().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr2().startsWith(warehouseAttr + "_"))
			return element.getAttr2().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr3().startsWith(warehouseAttr + "_"))
			return element.getAttr3().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr4().startsWith(warehouseAttr + "_"))
			return element.getAttr4().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr5().startsWith(warehouseAttr + "_"))
			return element.getAttr5().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr6().startsWith(warehouseAttr + "_"))
			return element.getAttr6().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr7().startsWith(warehouseAttr + "_"))
			return element.getAttr7().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr8().startsWith(warehouseAttr + "_"))
			return element.getAttr8().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr9().startsWith(warehouseAttr + "_"))
			return element.getAttr9().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr10().startsWith(warehouseAttr + "_"))
			return element.getAttr10().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr11().startsWith(warehouseAttr + "_"))
			return element.getAttr11().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr12().startsWith(warehouseAttr + "_"))
			return element.getAttr12().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr13().startsWith(warehouseAttr + "_"))
			return element.getAttr13().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr14().startsWith(warehouseAttr + "_"))
			return element.getAttr14().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr15().startsWith(warehouseAttr + "_"))
			return element.getAttr15().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr16().startsWith(warehouseAttr + "_"))
			return element.getAttr16().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr17().startsWith(warehouseAttr + "_"))
			return element.getAttr17().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr18().startsWith(warehouseAttr + "_"))
			return element.getAttr18().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr19().startsWith(warehouseAttr + "_"))
			return element.getAttr19().replaceFirst(warehouseAttr + "_", "");
		else if (element.getAttr20().startsWith(warehouseAttr + "_"))
			return element.getAttr20().replaceFirst(warehouseAttr + "_", "");
		
		return "";
	}
	
	/**
	 * Resets the values of non existing properties
	 */
	private void resetProperties() {
		List<Integer> countAttr = new ArrayList<Integer>();
		boolean found = false;
		
		for (int i = 0; i <  elementAttr.length; i++) {
			System.out.println(elementAttr[i]);
			if (!(elementAttr[i].isEmpty() && elementAttr[i] == "")) {
				found = false;
				
				for (int j = 0; j < MasterDataEditParts.getNewWarehouseAttr().length; j++) {
					
					System.out.println(MasterDataEditParts.getNewWarehouseAttr()[j]);
					if (elementAttr[i].startsWith(MasterDataEditParts.getNewWarehouseAttr()[j] + "_")) {
						found = true;System.out.println(found);
					}
				}
				if (!found)
					countAttr.add(i);
			}
		}
		
		for (int i = 0; i < countAttr.size(); i++) {
			switch(countAttr.get(i)) {
				case 0:element.setAttr1("");break;
				case 1:element.setAttr2("");break;
				case 2:element.setAttr3("");break;
				case 3:element.setAttr4("");break;
				case 4:element.setAttr5("");break;
				case 5:element.setAttr6("");break;
				case 6:element.setAttr7("");break;
				case 7:element.setAttr8("");break;
				case 8:element.setAttr9("");break;
				case 9:element.setAttr10("");break;
				case 10:element.setAttr11("");break;
				case 11:element.setAttr12("");break;
				case 12:element.setAttr13("");break;
				case 13:element.setAttr14("");break;
				case 14:element.setAttr15("");break;
				case 15:element.setAttr16("");break;
				case 16:element.setAttr17("");break;
				case 17:element.setAttr18("");break;
				case 18:element.setAttr19("");break;
				case 19:element.setAttr20("");break;
			}
		}
	}
}