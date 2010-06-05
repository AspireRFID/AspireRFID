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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__EPCIS_ID:
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
