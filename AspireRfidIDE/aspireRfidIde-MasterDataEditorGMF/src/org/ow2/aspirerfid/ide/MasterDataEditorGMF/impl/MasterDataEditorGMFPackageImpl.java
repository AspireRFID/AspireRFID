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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MasterDataEditorGMFPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
		MasterDataEditorGMFPackageImpl theMasterDataEditorGMFPackage = (MasterDataEditorGMFPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MasterDataEditorGMFPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MasterDataEditorGMFPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theMasterDataEditorGMFPackage.createPackageContents();

		// Initialize created meta-data
		theMasterDataEditorGMFPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMasterDataEditorGMFPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MasterDataEditorGMFPackage.eNS_URI, theMasterDataEditorGMFPackage);
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
	public EAttribute getCompany_Attr1() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr2() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr3() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr4() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr5() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr6() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr7() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr8() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr9() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr10() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr11() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr12() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr13() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr14() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr15() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr16() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr17() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr18() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr19() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompany_Attr20() {
		return (EAttribute)companyEClass.getEStructuralFeatures().get(26);
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
	public EReference getAbstractWarehouse_Warehouses() {
		return (EReference)abstractWarehouseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractWarehouse_Containers() {
		return (EReference)abstractWarehouseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr1() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr2() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr3() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr4() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr5() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr6() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr7() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr8() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr9() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr10() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr11() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr12() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr13() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr14() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr15() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr16() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr17() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr18() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr19() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractWarehouse_Attr20() {
		return (EAttribute)abstractWarehouseEClass.getEStructuralFeatures().get(25);
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
	public EAttribute getAbstractContainer_Attr1() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr2() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr3() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr4() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr5() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr6() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr7() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr8() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr9() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr10() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr11() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr12() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr13() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr14() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr15() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr16() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr17() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr18() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr19() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractContainer_Attr20() {
		return (EAttribute)abstractContainerEClass.getEStructuralFeatures().get(24);
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
		createEAttribute(companyEClass, COMPANY__ATTR1);
		createEAttribute(companyEClass, COMPANY__ATTR2);
		createEAttribute(companyEClass, COMPANY__ATTR3);
		createEAttribute(companyEClass, COMPANY__ATTR4);
		createEAttribute(companyEClass, COMPANY__ATTR5);
		createEAttribute(companyEClass, COMPANY__ATTR6);
		createEAttribute(companyEClass, COMPANY__ATTR7);
		createEAttribute(companyEClass, COMPANY__ATTR8);
		createEAttribute(companyEClass, COMPANY__ATTR9);
		createEAttribute(companyEClass, COMPANY__ATTR10);
		createEAttribute(companyEClass, COMPANY__ATTR11);
		createEAttribute(companyEClass, COMPANY__ATTR12);
		createEAttribute(companyEClass, COMPANY__ATTR13);
		createEAttribute(companyEClass, COMPANY__ATTR14);
		createEAttribute(companyEClass, COMPANY__ATTR15);
		createEAttribute(companyEClass, COMPANY__ATTR16);
		createEAttribute(companyEClass, COMPANY__ATTR17);
		createEAttribute(companyEClass, COMPANY__ATTR18);
		createEAttribute(companyEClass, COMPANY__ATTR19);
		createEAttribute(companyEClass, COMPANY__ATTR20);

		abstractWarehouseEClass = createEClass(ABSTRACT_WAREHOUSE);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ID);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__NAME);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__DESCRIPTION);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__TYPE);
		createEReference(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__WAREHOUSES);
		createEReference(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__CONTAINERS);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR1);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR2);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR3);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR4);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR5);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR6);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR7);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR8);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR9);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR10);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR11);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR12);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR13);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR14);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR15);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR16);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR17);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR18);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR19);
		createEAttribute(abstractWarehouseEClass, ABSTRACT_WAREHOUSE__ATTR20);

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
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR1);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR2);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR3);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR4);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR5);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR6);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR7);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR8);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR9);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR10);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR11);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR12);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR13);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR14);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR15);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR16);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR17);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR18);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR19);
		createEAttribute(abstractContainerEClass, ABSTRACT_CONTAINER__ATTR20);

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
		initEAttribute(getCompany_Attr1(), ecorePackage.getEString(), "attr1", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr2(), ecorePackage.getEString(), "attr2", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr3(), ecorePackage.getEString(), "attr3", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr4(), ecorePackage.getEString(), "attr4", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr5(), ecorePackage.getEString(), "attr5", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr6(), ecorePackage.getEString(), "attr6", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr7(), ecorePackage.getEString(), "attr7", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr8(), ecorePackage.getEString(), "attr8", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr9(), ecorePackage.getEString(), "attr9", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr10(), ecorePackage.getEString(), "attr10", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr11(), ecorePackage.getEString(), "attr11", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr12(), ecorePackage.getEString(), "attr12", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr13(), ecorePackage.getEString(), "attr13", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr14(), ecorePackage.getEString(), "attr14", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr15(), ecorePackage.getEString(), "attr15", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr16(), ecorePackage.getEString(), "attr16", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr17(), ecorePackage.getEString(), "attr17", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr18(), ecorePackage.getEString(), "attr18", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr19(), ecorePackage.getEString(), "attr19", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompany_Attr20(), ecorePackage.getEString(), "attr20", "", 0, 1, Company.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractWarehouseEClass, AbstractWarehouse.class, "AbstractWarehouse", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractWarehouse_ID(), ecorePackage.getEString(), "ID", "urn:epcglobal:fmcg:loc:", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Name(), ecorePackage.getEString(), "name", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Description(), ecorePackage.getEString(), "description", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Type(), ecorePackage.getEString(), "type", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractWarehouse_Warehouses(), this.getAbstractWarehouse(), null, "warehouses", null, 0, -1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractWarehouse_Containers(), this.getAbstractContainer(), null, "containers", null, 0, -1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr1(), ecorePackage.getEString(), "attr1", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr2(), ecorePackage.getEString(), "attr2", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr3(), ecorePackage.getEString(), "attr3", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr4(), ecorePackage.getEString(), "attr4", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr5(), ecorePackage.getEString(), "attr5", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr6(), ecorePackage.getEString(), "attr6", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr7(), ecorePackage.getEString(), "attr7", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr8(), ecorePackage.getEString(), "attr8", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr9(), ecorePackage.getEString(), "attr9", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr10(), ecorePackage.getEString(), "attr10", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr11(), ecorePackage.getEString(), "attr11", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr12(), ecorePackage.getEString(), "attr12", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr13(), ecorePackage.getEString(), "attr13", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr14(), ecorePackage.getEString(), "attr14", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr15(), ecorePackage.getEString(), "attr15", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr16(), ecorePackage.getEString(), "attr16", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr17(), ecorePackage.getEString(), "attr17", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr18(), ecorePackage.getEString(), "attr18", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr19(), ecorePackage.getEString(), "attr19", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractWarehouse_Attr20(), ecorePackage.getEString(), "attr20", "", 0, 1, AbstractWarehouse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEAttribute(getAbstractContainer_Attr1(), ecorePackage.getEString(), "attr1", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr2(), ecorePackage.getEString(), "attr2", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr3(), ecorePackage.getEString(), "attr3", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr4(), ecorePackage.getEString(), "attr4", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr5(), ecorePackage.getEString(), "attr5", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr6(), ecorePackage.getEString(), "attr6", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr7(), ecorePackage.getEString(), "attr7", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr8(), ecorePackage.getEString(), "attr8", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr9(), ecorePackage.getEString(), "attr9", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr10(), ecorePackage.getEString(), "attr10", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr11(), ecorePackage.getEString(), "attr11", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr12(), ecorePackage.getEString(), "attr12", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr13(), ecorePackage.getEString(), "attr13", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr14(), ecorePackage.getEString(), "attr14", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr15(), ecorePackage.getEString(), "attr15", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr16(), ecorePackage.getEString(), "attr16", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr17(), ecorePackage.getEString(), "attr17", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr18(), ecorePackage.getEString(), "attr18", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr19(), ecorePackage.getEString(), "attr19", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractContainer_Attr20(), ecorePackage.getEString(), "attr20", "", 0, 1, AbstractContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(readPointEClass, ReadPoint.class, "ReadPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
