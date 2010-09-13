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

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.querycapture.MasterDataEditParts;

/**
 * This is the item provider adapter for a {@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractContainerItemProvider
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
	public AbstractContainerItemProvider(AdapterFactory adapterFactory) {
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
			//addReadPointPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			
			int i = MasterDataEditParts.getNewReadPointAttr().length;
			
			switch (i) {
			case 0:break;
			case 1:if (!(MasterDataEditParts.getNewReadPointAttr()[0].isEmpty() && MasterDataEditParts.getNewReadPointAttr()[0] == "")) addAttr1PropertyDescriptor(object);break;
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
				 getString("_UI_AbstractContainer_ID_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_ID_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ID,
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
				 getString("_UI_AbstractContainer_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_name_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__NAME,
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
				 getString("_UI_AbstractContainer_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_description_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Read Point feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReadPointPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_AbstractContainer_ReadPoint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_ReadPoint_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__READ_POINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
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
				 getString("_UI_AbstractContainer_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_type_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__TYPE,
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
				 MasterDataEditParts.getNewReadPointAttr()[0],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr1_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR1,
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
				 MasterDataEditParts.getNewReadPointAttr()[1],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr2_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR2,
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
				 MasterDataEditParts.getNewReadPointAttr()[2],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr3_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR3,
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
				 MasterDataEditParts.getNewReadPointAttr()[3],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr4_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR4,
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
				 MasterDataEditParts.getNewReadPointAttr()[4],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr5_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR5,
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
				 MasterDataEditParts.getNewReadPointAttr()[5],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr6_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR6,
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
				 MasterDataEditParts.getNewReadPointAttr()[6],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr7_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR7,
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
				 MasterDataEditParts.getNewReadPointAttr()[7],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr8_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR8,
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
				 MasterDataEditParts.getNewReadPointAttr()[8],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr9_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR9,
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
				 MasterDataEditParts.getNewReadPointAttr()[9],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr10_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR10,
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
				 MasterDataEditParts.getNewReadPointAttr()[10],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_att11_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATT11,
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
				 MasterDataEditParts.getNewReadPointAttr()[11],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr12_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR12,
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
				 MasterDataEditParts.getNewReadPointAttr()[12],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr13_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR13,
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
				 MasterDataEditParts.getNewReadPointAttr()[13],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr14_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR14,
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
				 MasterDataEditParts.getNewReadPointAttr()[14],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr15_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR15,
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
				 MasterDataEditParts.getNewReadPointAttr()[15],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr16_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR16,
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
				 MasterDataEditParts.getNewReadPointAttr()[16],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr17_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR17,
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
				 MasterDataEditParts.getNewReadPointAttr()[17],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr18_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR18,
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
				 MasterDataEditParts.getNewReadPointAttr()[18],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr19_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR19,
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
				 MasterDataEditParts.getNewReadPointAttr()[19],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr20_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR20,
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
				 MasterDataEditParts.getNewReadPointAttr()[20],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr21_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR21,
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
				 MasterDataEditParts.getNewReadPointAttr()[21],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr22_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR22,
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
				 MasterDataEditParts.getNewReadPointAttr()[22],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr23_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR23,
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
				 MasterDataEditParts.getNewReadPointAttr()[23],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr24_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR24,
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
				 MasterDataEditParts.getNewReadPointAttr()[24],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr25_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR25,
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
				 MasterDataEditParts.getNewReadPointAttr()[25],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr26_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR26,
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
				 MasterDataEditParts.getNewReadPointAttr()[26],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr27_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR27,
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
				 MasterDataEditParts.getNewReadPointAttr()[27],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr28_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR28,
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
				 MasterDataEditParts.getNewReadPointAttr()[28],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr29_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR29,
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
				 MasterDataEditParts.getNewReadPointAttr()[29],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr30_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR30,
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
				 MasterDataEditParts.getNewReadPointAttr()[30],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr31_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR31,
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
				 MasterDataEditParts.getNewReadPointAttr()[31],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr32_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR32,
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
				 MasterDataEditParts.getNewReadPointAttr()[32],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr33_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR33,
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
				 MasterDataEditParts.getNewReadPointAttr()[33],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr34_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR34,
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
				 MasterDataEditParts.getNewReadPointAttr()[34],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr35_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR35,
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
				 MasterDataEditParts.getNewReadPointAttr()[35],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr36_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR36,
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
				 MasterDataEditParts.getNewReadPointAttr()[36],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr37_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR37,
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
				 MasterDataEditParts.getNewReadPointAttr()[37],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr38_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR38,
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
				 MasterDataEditParts.getNewReadPointAttr()[38],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr39_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR39,
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
				 MasterDataEditParts.getNewReadPointAttr()[39],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr40_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR40,
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
				 MasterDataEditParts.getNewReadPointAttr()[40],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr41_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR41,
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
				 MasterDataEditParts.getNewReadPointAttr()[41],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr42_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR42,
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
				 MasterDataEditParts.getNewReadPointAttr()[42],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr43_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR43,
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
				 MasterDataEditParts.getNewReadPointAttr()[43],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr44_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR44,
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
				 MasterDataEditParts.getNewReadPointAttr()[44],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr45_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR45,
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
				 MasterDataEditParts.getNewReadPointAttr()[45],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr46_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR46,
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
				 MasterDataEditParts.getNewReadPointAttr()[46],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr47_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR47,
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
				 MasterDataEditParts.getNewReadPointAttr()[47],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr48_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR48,
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
				 MasterDataEditParts.getNewReadPointAttr()[48],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr49_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR49,
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
				 MasterDataEditParts.getNewReadPointAttr()[49],
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr50_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR50,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((AbstractContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_AbstractContainer_type") :
			getString("_UI_AbstractContainer_type") + " " + label;
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

		switch (notification.getFeatureID(AbstractContainer.class)) {
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ID:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__NAME:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__DESCRIPTION:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__READ_POINT:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__TYPE:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR1:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR2:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR3:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR4:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR5:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR6:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR7:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR8:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR9:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR10:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATT11:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR12:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR13:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR14:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR15:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR16:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR17:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR18:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR19:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR20:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR21:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR22:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR23:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR24:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR25:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR26:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR27:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR28:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR29:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR30:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR31:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR32:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR33:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR34:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR35:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR36:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR37:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR38:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR39:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR40:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR41:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR42:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR43:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR44:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR45:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR46:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR47:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR48:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR49:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR50:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
