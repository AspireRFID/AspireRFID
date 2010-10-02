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
				 getString("_UI_AbstractContainer_attr1_feature"),
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
				 getString("_UI_AbstractContainer_attr2_feature"),
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
				 getString("_UI_AbstractContainer_attr3_feature"),
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
				 getString("_UI_AbstractContainer_attr4_feature"),
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
				 getString("_UI_AbstractContainer_attr5_feature"),
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
				 getString("_UI_AbstractContainer_attr6_feature"),
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
				 getString("_UI_AbstractContainer_attr7_feature"),
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
				 getString("_UI_AbstractContainer_attr8_feature"),
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
				 getString("_UI_AbstractContainer_attr9_feature"),
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
				 getString("_UI_AbstractContainer_attr10_feature"),
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
				 getString("_UI_AbstractContainer_attr11_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_AbstractContainer_attr11_feature", "_UI_AbstractContainer_type"),
				 MasterDataEditorGMFPackage.Literals.ABSTRACT_CONTAINER__ATTR11,
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
				 getString("_UI_AbstractContainer_attr12_feature"),
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
				 getString("_UI_AbstractContainer_attr13_feature"),
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
				 getString("_UI_AbstractContainer_attr14_feature"),
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
				 getString("_UI_AbstractContainer_attr15_feature"),
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
				 getString("_UI_AbstractContainer_attr16_feature"),
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
				 getString("_UI_AbstractContainer_attr17_feature"),
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
				 getString("_UI_AbstractContainer_attr18_feature"),
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
				 getString("_UI_AbstractContainer_attr19_feature"),
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
				 getString("_UI_AbstractContainer_attr20_feature"),
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
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR11:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR12:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR13:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR14:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR15:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR16:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR17:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR18:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR19:
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER__ATTR20:
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
