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
			addAttr1PropertyDescriptor(object);
			addAttr2PropertyDescriptor(object);
			addAttr3PropertyDescriptor(object);
			addAttr4PropertyDescriptor(object);
			addAttr5PropertyDescriptor(object);
			addAttr6PropertyDescriptor(object);
			addAttr7PropertyDescriptor(object);
			addAttr8PropertyDescriptor(object);
			addAttr9PropertyDescriptor(object);
			addAttr10PropertyDescriptor(object);
			addAttr11PropertyDescriptor(object);
			addAttr12PropertyDescriptor(object);
			addAttr13PropertyDescriptor(object);
			addAttr14PropertyDescriptor(object);
			addAttr15PropertyDescriptor(object);
			addAttr16PropertyDescriptor(object);
			addAttr17PropertyDescriptor(object);
			addAttr18PropertyDescriptor(object);
			addAttr19PropertyDescriptor(object);
			addAttr20PropertyDescriptor(object);
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
				 getString("_UI_Company_attr1_feature"),
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
				 getString("_UI_Company_attr2_feature"),
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
				 getString("_UI_Company_attr3_feature"),
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
				 getString("_UI_Company_attr4_feature"),
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
				 getString("_UI_Company_attr5_feature"),
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
				 getString("_UI_Company_attr6_feature"),
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
				 getString("_UI_Company_attr7_feature"),
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
				 getString("_UI_Company_attr8_feature"),
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
				 getString("_UI_Company_attr9_feature"),
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
				 getString("_UI_Company_attr10_feature"),
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
	 * This adds a property descriptor for the Attr11 feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttr11PropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Company_attr11_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Company_attr11_feature", "_UI_Company_type"),
				 MasterDataEditorGMFPackage.Literals.COMPANY__ATTR11,
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
				 getString("_UI_Company_attr12_feature"),
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
				 getString("_UI_Company_attr13_feature"),
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
				 getString("_UI_Company_attr14_feature"),
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
				 getString("_UI_Company_attr15_feature"),
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
				 getString("_UI_Company_attr16_feature"),
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
				 getString("_UI_Company_attr17_feature"),
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
				 getString("_UI_Company_attr18_feature"),
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
				 getString("_UI_Company_attr19_feature"),
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
				 getString("_UI_Company_attr20_feature"),
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
			case MasterDataEditorGMFPackage.COMPANY__ATTR11:
			case MasterDataEditorGMFPackage.COMPANY__ATTR12:
			case MasterDataEditorGMFPackage.COMPANY__ATTR13:
			case MasterDataEditorGMFPackage.COMPANY__ATTR14:
			case MasterDataEditorGMFPackage.COMPANY__ATTR15:
			case MasterDataEditorGMFPackage.COMPANY__ATTR16:
			case MasterDataEditorGMFPackage.COMPANY__ATTR17:
			case MasterDataEditorGMFPackage.COMPANY__ATTR18:
			case MasterDataEditorGMFPackage.COMPANY__ATTR19:
			case MasterDataEditorGMFPackage.COMPANY__ATTR20:
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
