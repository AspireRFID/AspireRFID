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

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * This is the item provider adapter for a {@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CompanyItemProvider
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
	public CompanyItemProvider(AdapterFactory adapterFactory) {
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
			addAddressPropertyDescriptor(object);
			addCountryPropertyDescriptor(object);
			addCityPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			
			int i = MasterDataEditParts.getNewCompanyAttr().length;
			
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
				 getString("_UI_Company_ID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_ID_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ID,
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
				 getString("_UI_Company_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_name_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Address feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAddressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Company_address_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_address_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ADDRESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Country feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCountryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Company_country_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_country_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__COUNTRY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the City feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Company_city_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_city_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__CITY,
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
				 getString("_UI_Company_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_description_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__DESCRIPTION,
				 true,
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
				 MasterDataEditParts.getNewCompanyAttr()[0],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr1_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR1,
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
				 MasterDataEditParts.getNewCompanyAttr()[1],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr2_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR2,
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
				 MasterDataEditParts.getNewCompanyAttr()[2],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr3_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR3,
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
				 MasterDataEditParts.getNewCompanyAttr()[3],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr4_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR4,
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
				 MasterDataEditParts.getNewCompanyAttr()[4],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr5_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR5,
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
				 MasterDataEditParts.getNewCompanyAttr()[5],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr6_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR6,
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
				 MasterDataEditParts.getNewCompanyAttr()[6],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr7_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR7,
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
				 MasterDataEditParts.getNewCompanyAttr()[7],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr8_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR8,
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
				 MasterDataEditParts.getNewCompanyAttr()[8],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr9_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR9,
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
				 MasterDataEditParts.getNewCompanyAttr()[9],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr10_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR10,
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
				 MasterDataEditParts.getNewCompanyAttr()[10],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_att11_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATT11,
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
				 MasterDataEditParts.getNewCompanyAttr()[11],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr12_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR12,
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
				 MasterDataEditParts.getNewCompanyAttr()[12],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr13_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR13,
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
				 MasterDataEditParts.getNewCompanyAttr()[13],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr14_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR14,
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
				 MasterDataEditParts.getNewCompanyAttr()[14],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr15_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR15,
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
				 MasterDataEditParts.getNewCompanyAttr()[15],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr16_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR16,
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
				 MasterDataEditParts.getNewCompanyAttr()[16],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr17_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR17,
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
				 MasterDataEditParts.getNewCompanyAttr()[17],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr18_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR18,
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
				 MasterDataEditParts.getNewCompanyAttr()[18],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr19_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR19,
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
				 MasterDataEditParts.getNewCompanyAttr()[18],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr20_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR20,
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
				 MasterDataEditParts.getNewCompanyAttr()[20],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr21_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR21,
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
				 MasterDataEditParts.getNewCompanyAttr()[21],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr22_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR22,
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
				 MasterDataEditParts.getNewCompanyAttr()[22],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr23_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR23,
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
				 MasterDataEditParts.getNewCompanyAttr()[23],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr24_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR24,
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
				 MasterDataEditParts.getNewCompanyAttr()[24],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr25_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR25,
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
				 MasterDataEditParts.getNewCompanyAttr()[25],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr26_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR26,
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
				 MasterDataEditParts.getNewCompanyAttr()[26],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr27_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR27,
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
				 MasterDataEditParts.getNewCompanyAttr()[27],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr28_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR28,
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
				 MasterDataEditParts.getNewCompanyAttr()[28],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr29_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR29,
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
				 MasterDataEditParts.getNewCompanyAttr()[29],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr30_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR30,
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
				 MasterDataEditParts.getNewCompanyAttr()[30],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr31_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR31,
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
				 MasterDataEditParts.getNewCompanyAttr()[31],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr32_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR32,
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
				 MasterDataEditParts.getNewCompanyAttr()[32],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr33_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR33,
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
				 MasterDataEditParts.getNewCompanyAttr()[33],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr34_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR34,
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
				 MasterDataEditParts.getNewCompanyAttr()[34],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr35_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR35,
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
				 MasterDataEditParts.getNewCompanyAttr()[35],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr36_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR36,
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
				 MasterDataEditParts.getNewCompanyAttr()[36],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr37_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR37,
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
				 MasterDataEditParts.getNewCompanyAttr()[37],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr38_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR38,
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
				 MasterDataEditParts.getNewCompanyAttr()[38],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr39_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR39,
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
				 MasterDataEditParts.getNewCompanyAttr()[39],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr40_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR40,
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
				 MasterDataEditParts.getNewCompanyAttr()[40],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr41_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR41,
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
				 MasterDataEditParts.getNewCompanyAttr()[41],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr42_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR42,
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
				 MasterDataEditParts.getNewCompanyAttr()[42],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr43_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR43,
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
				 MasterDataEditParts.getNewCompanyAttr()[43],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr44_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR44,
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
				 MasterDataEditParts.getNewCompanyAttr()[44],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr45_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR45,
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
				 MasterDataEditParts.getNewCompanyAttr()[45],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr46_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR46,
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
				 MasterDataEditParts.getNewCompanyAttr()[46],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr47_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR47,
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
				 MasterDataEditParts.getNewCompanyAttr()[47],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr48_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR48,
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
				 MasterDataEditParts.getNewCompanyAttr()[48],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr49_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR49,
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
				 MasterDataEditParts.getNewCompanyAttr()[49],
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr50_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR50,
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
			childrenFeatures.add(MasterDataEditorGMFPackage.Literals.COMPANY__COMPANY_WAREHOUSES);
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
	 * This returns Company.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Company"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Company)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Company_type") :
			getString("_UI_Company_type") + " " + label;
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

		switch (notification.getFeatureID(Company.class)) {
			case MasterDataEditorGMFPackage.COMPANY__ID:
			case MasterDataEditorGMFPackage.COMPANY__NAME:
			case MasterDataEditorGMFPackage.COMPANY__ADDRESS:
			case MasterDataEditorGMFPackage.COMPANY__COUNTRY:
			case MasterDataEditorGMFPackage.COMPANY__CITY:
			case MasterDataEditorGMFPackage.COMPANY__DESCRIPTION:
			case MasterDataEditorGMFPackage.COMPANY__ATTR1:
			case MasterDataEditorGMFPackage.COMPANY__ATTR2:
			case MasterDataEditorGMFPackage.COMPANY__ATTR3:
			case MasterDataEditorGMFPackage.COMPANY__ATTR4:
			case MasterDataEditorGMFPackage.COMPANY__ATTR5:
			case MasterDataEditorGMFPackage.COMPANY__ATTR6:
			case MasterDataEditorGMFPackage.COMPANY__ATTR7:
			case MasterDataEditorGMFPackage.COMPANY__ATTR8:
			case MasterDataEditorGMFPackage.COMPANY__ATTR9:
			case MasterDataEditorGMFPackage.COMPANY__ATTR10:
			case MasterDataEditorGMFPackage.COMPANY__ATT11:
			case MasterDataEditorGMFPackage.COMPANY__ATTR12:
			case MasterDataEditorGMFPackage.COMPANY__ATTR13:
			case MasterDataEditorGMFPackage.COMPANY__ATTR14:
			case MasterDataEditorGMFPackage.COMPANY__ATTR15:
			case MasterDataEditorGMFPackage.COMPANY__ATTR16:
			case MasterDataEditorGMFPackage.COMPANY__ATTR17:
			case MasterDataEditorGMFPackage.COMPANY__ATTR18:
			case MasterDataEditorGMFPackage.COMPANY__ATTR19:
			case MasterDataEditorGMFPackage.COMPANY__ATTR20:
			case MasterDataEditorGMFPackage.COMPANY__ATTR21:
			case MasterDataEditorGMFPackage.COMPANY__ATTR22:
			case MasterDataEditorGMFPackage.COMPANY__ATTR23:
			case MasterDataEditorGMFPackage.COMPANY__ATTR24:
			case MasterDataEditorGMFPackage.COMPANY__ATTR25:
			case MasterDataEditorGMFPackage.COMPANY__ATTR26:
			case MasterDataEditorGMFPackage.COMPANY__ATTR27:
			case MasterDataEditorGMFPackage.COMPANY__ATTR28:
			case MasterDataEditorGMFPackage.COMPANY__ATTR29:
			case MasterDataEditorGMFPackage.COMPANY__ATTR30:
			case MasterDataEditorGMFPackage.COMPANY__ATTR31:
			case MasterDataEditorGMFPackage.COMPANY__ATTR32:
			case MasterDataEditorGMFPackage.COMPANY__ATTR33:
			case MasterDataEditorGMFPackage.COMPANY__ATTR34:
			case MasterDataEditorGMFPackage.COMPANY__ATTR35:
			case MasterDataEditorGMFPackage.COMPANY__ATTR36:
			case MasterDataEditorGMFPackage.COMPANY__ATTR37:
			case MasterDataEditorGMFPackage.COMPANY__ATTR38:
			case MasterDataEditorGMFPackage.COMPANY__ATTR39:
			case MasterDataEditorGMFPackage.COMPANY__ATTR40:
			case MasterDataEditorGMFPackage.COMPANY__ATTR41:
			case MasterDataEditorGMFPackage.COMPANY__ATTR42:
			case MasterDataEditorGMFPackage.COMPANY__ATTR43:
			case MasterDataEditorGMFPackage.COMPANY__ATTR44:
			case MasterDataEditorGMFPackage.COMPANY__ATTR45:
			case MasterDataEditorGMFPackage.COMPANY__ATTR46:
			case MasterDataEditorGMFPackage.COMPANY__ATTR47:
			case MasterDataEditorGMFPackage.COMPANY__ATTR48:
			case MasterDataEditorGMFPackage.COMPANY__ATTR49:
			case MasterDataEditorGMFPackage.COMPANY__ATTR50:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case MasterDataEditorGMFPackage.COMPANY__COMPANY_WAREHOUSES:
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
				(MasterDataEditorGMFPackage.Literals.COMPANY__COMPANY_WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createBizLoc()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.COMPANY__COMPANY_WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createWarehouse()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.COMPANY__COMPANY_WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createRoom()));

		newChildDescriptors.add
			(createChildParameter
				(MasterDataEditorGMFPackage.Literals.COMPANY__COMPANY_WAREHOUSES,
				 MasterDataEditorGMFFactory.eINSTANCE.createSection()));
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
