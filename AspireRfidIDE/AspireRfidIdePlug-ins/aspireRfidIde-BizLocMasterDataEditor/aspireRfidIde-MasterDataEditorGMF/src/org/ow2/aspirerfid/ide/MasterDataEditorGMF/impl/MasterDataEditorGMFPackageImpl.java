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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MasterDataEditorGMFPackageImpl extends EPackageImpl implements MasterDataEditorGMFPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass companyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractWarehouseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bizLocEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass warehouseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conveyorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shelfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass palletTruckEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pushArmEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handHeldReaderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container4EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container5EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container6EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container7EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container8EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container9EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass container10EClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MasterDataEditorGMFPackageImpl() {
		super(eNS_URI, MasterDataEditorGMFFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MasterDataEditorGMFPackage init() {
		if (isInited) return (MasterDataEditorGMFPackage)EPackage.Registry.INSTANCE.getEPackage(MasterDataEditorGMFPackage.eNS_URI);

		// Obtain or create and register package
		MasterDataEditorGMFPackageImpl theMasterDataEditorGMFPackage = (MasterDataEditorGMFPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof MasterDataEditorGMFPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new MasterDataEditorGMFPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theMasterDataEditorGMFPackage.createPackageContents();

		// Initialize created meta-data
		theMasterDataEditorGMFPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMasterDataEditorGMFPackage.freeze();

		return theMasterDataEditorGMFPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompany() {
		return companyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_ID() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Name() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Address() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Country() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_City() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Description() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompany_CompanyWarehouses() {
		return (EReference)companyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractWarehouse() {
		return abstractWarehouseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_ID() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Name() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Description() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Type() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_EpcisID() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractWarehouse_Warehouses() {
		return (EReference)abstractWarehouseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractWarehouse_Containers() {
		return (EReference)abstractWarehouseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBizLoc() {
		return bizLocEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWarehouse() {
		return warehouseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoom() {
		return roomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSection() {
		return sectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractContainer() {
		return abstractContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_ID() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Name() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Description() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_ReadPoint() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Type() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_EpcisID() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReadPoint() {
		return readPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConveyor() {
		return conveyorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShelf() {
		return shelfEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGate() {
		return gateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPalletTruck() {
		return palletTruckEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPushArm() {
		return pushArmEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandHeldReader() {
		return handHeldReaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer1() {
		return container1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer2() {
		return container2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer3() {
		return container3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer4() {
		return container4EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer5() {
		return container5EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer6() {
		return container6EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer7() {
		return container7EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer8() {
		return container8EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer9() {
		return container9EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer10() {
		return container10EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterDataEditorGMFFactory getMasterDataEditorGMFFactory() {
		return (MasterDataEditorGMFFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		companyEClass = createEClass(COMPANY);
		createEAttribute(companyEClass, COMPANY__ID);
		createEAttribute(companyEClass, COMPANY__NAME);
		createEAttribute(companyEClass, COMPANY__ADDRESS);
		createEAttribute(companyEClass, COMPANY__COUNTRY);
		createEAttribute(companyEClass, COMPANY__CITY);
		createEAttribute(companyEClass, COMPANY__DESCRIPTION);
		createEReference(companyEClass, COMPANY__COMPANY_WAREHOUSES);

		abstractWarehouseEClass = createEClass(ABSTRACT_WAREHOUSE);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ID);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__NAME);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__DESCRIPTION);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__TYPE);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__EPCIS_ID);
		createEReference(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__WAREHOUSES);
		createEReference(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__CONTAINERS);

		bizLocEClass = createEClass(BIZ_LOC);

		warehouseEClass = createEClass(WAREHOUSE);

		roomEClass = createEClass(ROOM);

		sectionEClass = createEClass(SECTION);

		abstractContainerEClass = createEClass(ABSTRACT_CONTAINER);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ID);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__NAME);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__DESCRIPTION);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__READ_POINT);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__TYPE);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__EPCIS_ID);

		readPointEClass = createEClass(READ_POINT);

		containerEClass = createEClass(CONTAINER);

		conveyorEClass = createEClass(CONVEYOR);

		shelfEClass = createEClass(SHELF);

		gateEClass = createEClass(GATE);

		palletTruckEClass = createEClass(PALLET_TRUCK);

		pushArmEClass = createEClass(PUSH_ARM);

		handHeldReaderEClass = createEClass(HAND_HELD_READER);

		container1EClass = createEClass(CONTAINER1);

		container2EClass = createEClass(CONTAINER2);

		container3EClass = createEClass(CONTAINER3);

		container4EClass = createEClass(CONTAINER4);

		container5EClass = createEClass(CONTAINER5);

		container6EClass = createEClass(CONTAINER6);

		container7EClass = createEClass(CONTAINER7);

		container8EClass = createEClass(CONTAINER8);

		container9EClass = createEClass(CONTAINER9);

		container10EClass = createEClass(CONTAINER10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		bizLocEClass.getESuperTypes().add(this.getAbstractWarehouse());
		warehouseEClass.getESuperTypes().add(this.getAbstractWarehouse());
		roomEClass.getESuperTypes().add(this.getAbstractWarehouse());
		sectionEClass.getESuperTypes().add(this.getAbstractWarehouse());
		readPointEClass.getESuperTypes().add(this.getAbstractContainer());
		containerEClass.getESuperTypes().add(this.getAbstractContainer());
		conveyorEClass.getESuperTypes().add(this.getAbstractContainer());
		shelfEClass.getESuperTypes().add(this.getAbstractContainer());
		gateEClass.getESuperTypes().add(this.getAbstractContainer());
		palletTruckEClass.getESuperTypes().add(this.getAbstractContainer());
		pushArmEClass.getESuperTypes().add(this.getAbstractContainer());
		handHeldReaderEClass.getESuperTypes().add(this.getAbstractContainer());
		container1EClass.getESuperTypes().add(this.getAbstractContainer());
		container2EClass.getESuperTypes().add(this.getAbstractContainer());
		container3EClass.getESuperTypes().add(this.getAbstractContainer());
		container4EClass.getESuperTypes().add(this.getAbstractContainer());
		container5EClass.getESuperTypes().add(this.getAbstractContainer());
		container6EClass.getESuperTypes().add(this.getAbstractContainer());
		container7EClass.getESuperTypes().add(this.getAbstractContainer());
		container8EClass.getESuperTypes().add(this.getAbstractContainer());
		container9EClass.getESuperTypes().add(this.getAbstractContainer());
		container10EClass.getESuperTypes().add(this.getAbstractContainer());

		// Initialize classes and features; add operations and parameters
		initEClass(companyEClass, Company.class, "Company", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompany_ID(), ecorePackage.getEString(), "ID", "urn:epcglobal:fmcg:loc:", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Name(), ecorePackage.getEString(), "name", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Address(), ecorePackage.getEString(), "address", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Country(), ecorePackage.getEString(), "country", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_City(), ecorePackage.getEString(), "city", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Description(), ecorePackage.getEString(), "description", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompany_CompanyWarehouses(), this.getAbstractWarehouse(), null, "companyWarehouses", null, 0, -1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractWarehouseEClass, AbstractWarehouse.class, "AbstractWarehouse", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractWarehouse_ID(), ecorePackage.getEString(), "ID", "urn:epcglobal:fmcg:loc:", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Name(), ecorePackage.getEString(), "name", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Description(), ecorePackage.getEString(), "description", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Type(), ecorePackage.getEString(), "type", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_EpcisID(), ecorePackage.getEString(), "EpcisID", null, 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractWarehouse_Warehouses(), this.getAbstractWarehouse(), null, "warehouses", null, 0, -1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractWarehouse_Containers(), this.getAbstractContainer(), null, "containers", null, 0, -1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bizLocEClass, BizLoc.class, "BizLoc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(warehouseEClass, Warehouse.class, "Warehouse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roomEClass, Room.class, "Room", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sectionEClass, Section.class, "Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractContainerEClass, AbstractContainer.class, "AbstractContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractContainer_ID(), ecorePackage.getEString(), "ID", "urn:epcglobal:fmcg:loc:", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Name(), ecorePackage.getEString(), "name", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Description(), ecorePackage.getEString(), "description", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_ReadPoint(), ecorePackage.getEBoolean(), "ReadPoint", "true", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Type(), ecorePackage.getEString(), "type", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_EpcisID(), ecorePackage.getEString(), "EpcisID", null, 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(readPointEClass, ReadPoint.class, "ReadPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conveyorEClass, Conveyor.class, "Conveyor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(shelfEClass, Shelf.class, "Shelf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gateEClass, Gate.class, "Gate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(palletTruckEClass, PalletTruck.class, "PalletTruck", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pushArmEClass, PushArm.class, "PushArm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(handHeldReaderEClass, HandHeldReader.class, "HandHeldReader", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container1EClass, Container1.class, "Container1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container2EClass, Container2.class, "Container2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container3EClass, Container3.class, "Container3", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container4EClass, Container4.class, "Container4", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container5EClass, Container5.class, "Container5", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container6EClass, Container6.class, "Container6", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container7EClass, Container7.class, "Container7", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container8EClass, Container8.class, "Container8", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container9EClass, Container9.class, "Container9", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(container10EClass, Container10.class, "Container10", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //MasterDataEditorGMFPackageImpl
