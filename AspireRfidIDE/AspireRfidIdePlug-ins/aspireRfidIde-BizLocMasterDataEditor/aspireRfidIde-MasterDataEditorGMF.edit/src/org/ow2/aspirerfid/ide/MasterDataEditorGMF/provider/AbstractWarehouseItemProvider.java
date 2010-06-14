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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * This is the item provider adapter for a {@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractWarehouseItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractWarehouseItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIDPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			
			int i = MasterDataEditParts.getNewWarehouseAttr().length;
			
			switch (i) {
			case 1:addAttr1PropertyDescriptor(object);break;
			case 2:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);break;
			case 3:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);break;
			case 4:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);break;
			case 5:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);break;
			case 6:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);break;
			case 7:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);break;
			case 8:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);break;
			case 9:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);break;
			case 10:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);break;
			case 11:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);break;
			case 12:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);break;
			case 13:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);break;
			case 14:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);break;
			case 15:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);break;
			case 16:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);break;
			case 17:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);break;
			case 18:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);break;
			case 19:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);break;
			case 20:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);break;
			case 21:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);break;
			case 22:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);break;
			case 23:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);break;
			case 24:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);break;
			case 25:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);break;
			case 26:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);break;
			case 27:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);break;
			case 28:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);break;
			case 29:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);break;
			case 30:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);break;
			case 31:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);break;
			case 32:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);break;
			case 33:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);break;
			case 34:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);break;
			case 35:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);break;
			case 36:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);break;
			case 37:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);break;
			case 38:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);break;
			case 39:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);break;
			case 40:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);break;
			case 41:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);break;
			case 42:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);break;
			case 43:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);break;
			case 44:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);break;
			case 45:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);break;
			case 46:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);addAttr46PropertyDescriptor(object);break;
			case 47:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);addAttr46PropertyDescriptor(object);addAttr47PropertyDescriptor(object);break;
			case 48:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);addAttr46PropertyDescriptor(object);addAttr47PropertyDescriptor(object);addAttr48PropertyDescriptor(object);break;
			case 49:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);addAttr46PropertyDescriptor(object);addAttr47PropertyDescriptor(object);addAttr48PropertyDescriptor(object);addAttr49PropertyDescriptor(object);break;
			case 50:addAttr1PropertyDescriptor(object);addAttr2PropertyDescriptor(object);addAttr3PropertyDescriptor(object);addAttr4PropertyDescriptor(object);addAttr5PropertyDescriptor(object);addAttr6PropertyDescriptor(object);addAttr7PropertyDescriptor(object);addAttr8PropertyDescriptor(object);addAttr9PropertyDescriptor(object);addAttr10PropertyDescriptor(object);addAttr11PropertyDescriptor(object);addAttr12PropertyDescriptor(object);addAttr13PropertyDescriptor(object);addAttr14PropertyDescriptor(object);addAttr15PropertyDescriptor(object);addAttr16PropertyDescriptor(object);addAttr17PropertyDescriptor(object);addAttr18PropertyDescriptor(object);addAttr19PropertyDescriptor(object);addAttr20PropertyDescriptor(object);addAttr21PropertyDescriptor(object);addAttr22PropertyDescriptor(object);addAttr23PropertyDescriptor(object);addAttr24PropertyDescriptor(object);addAttr25PropertyDescriptor(object);addAttr26PropertyDescriptor(object);addAttr27PropertyDescriptor(object);addAttr28PropertyDescriptor(object);addAttr29PropertyDescriptor(object);addAttr30PropertyDescriptor(object);addAttr31PropertyDescriptor(object);addAttr32PropertyDescriptor(object);addAttr33PropertyDescriptor(object);addAttr34PropertyDescriptor(object);addAttr35PropertyDescriptor(object);addAttr36PropertyDescriptor(object);addAttr37PropertyDescriptor(object);addAttr38PropertyDescriptor(object);addAttr39PropertyDescriptor(object);addAttr40PropertyDescriptor(object);addAttr41PropertyDescriptor(object);addAttr42PropertyDescriptor(object);addAttr43PropertyDescriptor(object);addAttr44PropertyDescriptor(object);addAttr45PropertyDescriptor(object);addAttr46PropertyDescriptor(object);addAttr47PropertyDescriptor(object);addAttr48PropertyDescriptor(object);addAttr49PropertyDescriptor(object);addAttr50PropertyDescriptor(object);
			}
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the ID feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIDPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractWarehouse_ID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_ID_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractWarehouse_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_name_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractWarehouse_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_description_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractWarehouse_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_type_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__TYPE,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr1 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr1PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[0],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr1_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR1,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr2 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr2PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[1],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr2_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR2,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr3 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr3PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[2],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr3_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR3,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr4 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr4PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[3],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr4_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR4,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr5 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr5PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[4],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr5_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR5,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr6 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr6PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[5],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr6_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR6,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr7 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr7PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[6],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr7_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR7,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr8 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr8PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[7],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr8_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR8,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr9 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr9PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[8],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr9_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR9,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr10 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr10PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[9],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr10_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR10,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Att11 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr11PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[10],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_att11_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATT11,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr12 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr12PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[11],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr12_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR12,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr13 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr13PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[12],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr13_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR13,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr14 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr14PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[13],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr14_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR14,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr15 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr15PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[14],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr15_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR15,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr16 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr16PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[15],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr16_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR16,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr17 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr17PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[16],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr17_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR17,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr18 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr18PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[17],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr18_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR18,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr19 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr19PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[18],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr19_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR19,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr20 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr20PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[19],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr20_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR20,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr21 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr21PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[20],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr21_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR21,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr22 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr22PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[21],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr22_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR22,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr23 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr23PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[22],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr23_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR23,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr24 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr24PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[23],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr24_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR24,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr25 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr25PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[24],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr25_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR25,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr26 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr26PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[25],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr26_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR26,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr27 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr27PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[26],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr27_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR27,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr28 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr28PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[27],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr28_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR28,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr29 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr29PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[28],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr29_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR29,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr30 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr30PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[29],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr30_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR30,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr31 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr31PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[30],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr31_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR31,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr32 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr32PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[31],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr32_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR32,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr33 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr33PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[32],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr33_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR33,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr34 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr34PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[33],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr34_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR34,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr35 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr35PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[34],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr35_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR35,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr36 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr36PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[35],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr36_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR36,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr37 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr37PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[36],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr37_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR37,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr38 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr38PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[37],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr38_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR38,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr39 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr39PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[38],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr39_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR39,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr40 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr40PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[39],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr40_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR40,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr41 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr41PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[40],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr41_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR41,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr42 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr42PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[41],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr42_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR42,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr43 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr43PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[42],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr43_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR43,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr44 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr44PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[43],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr44_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR44,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr45 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr45PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[44],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr45_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR45,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr46 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr46PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[45],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr46_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR46,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr47 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr47PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[46],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr47_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR47,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr48 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr48PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[47],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr48_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR48,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr49 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr49PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[48],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr49_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR49,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Attr50 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr50PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 MasterDataEditParts.getNewWarehouseAttr()[49],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractWarehouse_attr50_feature", "_UI_AbstractWarehouse_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__ATTR50,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__WAREHOUSES);
			childrenFeatures.add(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractWarehouse)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractWarehouse_type") :
			getString("_UI_AbstractWarehouse_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AbstractWarehouse.class)) {
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createBizLoc()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createWarehouse()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createRoom()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createSection()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createReadPoint()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createConveyor()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createShelf()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createGate()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createPalletTruck()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createPushArm()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createHandHeldReader()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer1()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer2()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer3()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer4()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer5()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer6()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer7()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer8()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer9()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE__CONTAINERS,
				 MasterDataEditorGMFFactory.eINSTANCE.createContainer10()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return MasterDataEditorGMFEditPlugin.INSTANCE;
	}

}
