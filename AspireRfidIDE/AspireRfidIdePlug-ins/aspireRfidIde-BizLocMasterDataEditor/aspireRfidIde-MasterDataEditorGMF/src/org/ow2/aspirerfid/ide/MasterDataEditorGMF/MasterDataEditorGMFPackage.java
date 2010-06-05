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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory
 * @model kind="package"
 * @generated
 */
public interface MasterDataEditorGMFPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "MasterDataEditorGMF";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://MasterDataEditorGMF";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "MasterDataEditorGMF";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MasterDataEditorGMFPackage eINSTANCE = org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl <em>Company</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getCompany()
	 * @generated
	 */
	int COMPANY = 0;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ADDRESS = 2;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__COUNTRY = 3;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__CITY = 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__DESCRIPTION = 5;

	/**
	 * The feature id for the '<em><b>Company Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__COMPANY_WAREHOUSES = 6;

	/**
	 * The number of structural features of the '<em>Company</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl <em>Abstract Warehouse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractWarehouse()
	 * @generated
	 */
	int ABSTRACT_WAREHOUSE = 1;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__EPCIS_ID = 4;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__WAREHOUSES = 5;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__CONTAINERS = 6;

	/**
	 * The number of structural features of the '<em>Abstract Warehouse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl <em>Biz Loc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getBizLoc()
	 * @generated
	 */
	int BIZ_LOC = 2;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__EPCIS_ID = ABSTRACT_WAREHOUSE__EPCIS_ID;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The number of structural features of the '<em>Biz Loc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl <em>Warehouse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getWarehouse()
	 * @generated
	 */
	int WAREHOUSE = 3;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__EPCIS_ID = ABSTRACT_WAREHOUSE__EPCIS_ID;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The number of structural features of the '<em>Warehouse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl <em>Room</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getRoom()
	 * @generated
	 */
	int ROOM = 4;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__EPCIS_ID = ABSTRACT_WAREHOUSE__EPCIS_ID;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The number of structural features of the '<em>Room</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 5;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__EPCIS_ID = ABSTRACT_WAREHOUSE__EPCIS_ID;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl <em>Abstract Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractContainer()
	 * @generated
	 */
	int ABSTRACT_CONTAINER = 6;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__READ_POINT = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__EPCIS_ID = 5;

	/**
	 * The number of structural features of the '<em>Abstract Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl <em>Read Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getReadPoint()
	 * @generated
	 */
	int READ_POINT = 7;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Read Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 8;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl <em>Conveyor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getConveyor()
	 * @generated
	 */
	int CONVEYOR = 9;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Conveyor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl <em>Shelf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getShelf()
	 * @generated
	 */
	int SHELF = 10;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Shelf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl <em>Gate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getGate()
	 * @generated
	 */
	int GATE = 11;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Gate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl <em>Pallet Truck</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPalletTruck()
	 * @generated
	 */
	int PALLET_TRUCK = 12;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Pallet Truck</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl <em>Push Arm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPushArm()
	 * @generated
	 */
	int PUSH_ARM = 13;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Push Arm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl <em>Hand Held Reader</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getHandHeldReader()
	 * @generated
	 */
	int HAND_HELD_READER = 14;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Hand Held Reader</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl <em>Container1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer1()
	 * @generated
	 */
	int CONTAINER1 = 15;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl <em>Container2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer2()
	 * @generated
	 */
	int CONTAINER2 = 16;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl <em>Container3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer3()
	 * @generated
	 */
	int CONTAINER3 = 17;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl <em>Container4</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer4()
	 * @generated
	 */
	int CONTAINER4 = 18;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container4</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl <em>Container5</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer5()
	 * @generated
	 */
	int CONTAINER5 = 19;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container5</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl <em>Container6</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer6()
	 * @generated
	 */
	int CONTAINER6 = 20;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container6</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl <em>Container7</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer7()
	 * @generated
	 */
	int CONTAINER7 = 21;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container7</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl <em>Container8</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer8()
	 * @generated
	 */
	int CONTAINER8 = 22;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container8</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl <em>Container9</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer9()
	 * @generated
	 */
	int CONTAINER9 = 23;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container9</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl <em>Container10</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer10()
	 * @generated
	 */
	int CONTAINER10 = 24;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__EPCIS_ID = ABSTRACT_CONTAINER__EPCIS_ID;

	/**
	 * The number of structural features of the '<em>Container10</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company
	 * @generated
	 */
	EClass getCompany();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getID()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getName()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAddress()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Address();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCountry()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Country();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCity()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_City();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getDescription()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCompanyWarehouses <em>Company Warehouses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Company Warehouses</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCompanyWarehouses()
	 * @see #getCompany()
	 * @generated
	 */
	EReference getCompany_CompanyWarehouses();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse <em>Abstract Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Warehouse</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse
	 * @generated
	 */
	EClass getAbstractWarehouse();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getEpcisID <em>Epcis ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Epcis ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getEpcisID()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_EpcisID();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getWarehouses <em>Warehouses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Warehouses</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getWarehouses()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EReference getAbstractWarehouse_Warehouses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getContainers()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EReference getAbstractWarehouse_Containers();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc <em>Biz Loc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Biz Loc</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc
	 * @generated
	 */
	EClass getBizLoc();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse <em>Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse
	 * @generated
	 */
	EClass getWarehouse();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room <em>Room</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Room</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room
	 * @generated
	 */
	EClass getRoom();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer <em>Abstract Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Container</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer
	 * @generated
	 */
	EClass getAbstractContainer();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint <em>Read Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Point</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_ReadPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getEpcisID <em>Epcis ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Epcis ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getEpcisID()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_EpcisID();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint <em>Read Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Read Point</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint
	 * @generated
	 */
	EClass getReadPoint();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor <em>Conveyor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conveyor</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor
	 * @generated
	 */
	EClass getConveyor();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf <em>Shelf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shelf</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf
	 * @generated
	 */
	EClass getShelf();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate <em>Gate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gate</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate
	 * @generated
	 */
	EClass getGate();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck <em>Pallet Truck</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pallet Truck</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck
	 * @generated
	 */
	EClass getPalletTruck();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm <em>Push Arm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Arm</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm
	 * @generated
	 */
	EClass getPushArm();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader <em>Hand Held Reader</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hand Held Reader</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader
	 * @generated
	 */
	EClass getHandHeldReader();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1 <em>Container1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container1</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1
	 * @generated
	 */
	EClass getContainer1();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2 <em>Container2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container2</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2
	 * @generated
	 */
	EClass getContainer2();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3 <em>Container3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container3</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3
	 * @generated
	 */
	EClass getContainer3();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4 <em>Container4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container4</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4
	 * @generated
	 */
	EClass getContainer4();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5 <em>Container5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container5</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5
	 * @generated
	 */
	EClass getContainer5();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6 <em>Container6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container6</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6
	 * @generated
	 */
	EClass getContainer6();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7 <em>Container7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container7</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7
	 * @generated
	 */
	EClass getContainer7();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8 <em>Container8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container8</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8
	 * @generated
	 */
	EClass getContainer8();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9 <em>Container9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container9</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9
	 * @generated
	 */
	EClass getContainer9();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10 <em>Container10</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container10</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10
	 * @generated
	 */
	EClass getContainer10();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MasterDataEditorGMFFactory getMasterDataEditorGMFFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl <em>Company</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getCompany()
		 * @generated
		 */
		EClass COMPANY = eINSTANCE.getCompany();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ID = eINSTANCE.getCompany_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__NAME = eINSTANCE.getCompany_Name();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ADDRESS = eINSTANCE.getCompany_Address();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__COUNTRY = eINSTANCE.getCompany_Country();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__CITY = eINSTANCE.getCompany_City();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__DESCRIPTION = eINSTANCE.getCompany_Description();

		/**
		 * The meta object literal for the '<em><b>Company Warehouses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPANY__COMPANY_WAREHOUSES = eINSTANCE.getCompany_CompanyWarehouses();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl <em>Abstract Warehouse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractWarehouse()
		 * @generated
		 */
		EClass ABSTRACT_WAREHOUSE = eINSTANCE.getAbstractWarehouse();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ID = eINSTANCE.getAbstractWarehouse_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__NAME = eINSTANCE.getAbstractWarehouse_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__DESCRIPTION = eINSTANCE.getAbstractWarehouse_Description();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__TYPE = eINSTANCE.getAbstractWarehouse_Type();

		/**
		 * The meta object literal for the '<em><b>Epcis ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__EPCIS_ID = eINSTANCE.getAbstractWarehouse_EpcisID();

		/**
		 * The meta object literal for the '<em><b>Warehouses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_WAREHOUSE__WAREHOUSES = eINSTANCE.getAbstractWarehouse_Warehouses();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_WAREHOUSE__CONTAINERS = eINSTANCE.getAbstractWarehouse_Containers();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl <em>Biz Loc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getBizLoc()
		 * @generated
		 */
		EClass BIZ_LOC = eINSTANCE.getBizLoc();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl <em>Warehouse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getWarehouse()
		 * @generated
		 */
		EClass WAREHOUSE = eINSTANCE.getWarehouse();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl <em>Room</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getRoom()
		 * @generated
		 */
		EClass ROOM = eINSTANCE.getRoom();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl <em>Abstract Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractContainer()
		 * @generated
		 */
		EClass ABSTRACT_CONTAINER = eINSTANCE.getAbstractContainer();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ID = eINSTANCE.getAbstractContainer_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__NAME = eINSTANCE.getAbstractContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__DESCRIPTION = eINSTANCE.getAbstractContainer_Description();

		/**
		 * The meta object literal for the '<em><b>Read Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__READ_POINT = eINSTANCE.getAbstractContainer_ReadPoint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__TYPE = eINSTANCE.getAbstractContainer_Type();

		/**
		 * The meta object literal for the '<em><b>Epcis ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__EPCIS_ID = eINSTANCE.getAbstractContainer_EpcisID();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl <em>Read Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getReadPoint()
		 * @generated
		 */
		EClass READ_POINT = eINSTANCE.getReadPoint();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl <em>Conveyor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getConveyor()
		 * @generated
		 */
		EClass CONVEYOR = eINSTANCE.getConveyor();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl <em>Shelf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getShelf()
		 * @generated
		 */
		EClass SHELF = eINSTANCE.getShelf();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl <em>Gate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getGate()
		 * @generated
		 */
		EClass GATE = eINSTANCE.getGate();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl <em>Pallet Truck</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPalletTruck()
		 * @generated
		 */
		EClass PALLET_TRUCK = eINSTANCE.getPalletTruck();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl <em>Push Arm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPushArm()
		 * @generated
		 */
		EClass PUSH_ARM = eINSTANCE.getPushArm();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl <em>Hand Held Reader</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getHandHeldReader()
		 * @generated
		 */
		EClass HAND_HELD_READER = eINSTANCE.getHandHeldReader();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl <em>Container1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer1()
		 * @generated
		 */
		EClass CONTAINER1 = eINSTANCE.getContainer1();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl <em>Container2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer2()
		 * @generated
		 */
		EClass CONTAINER2 = eINSTANCE.getContainer2();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl <em>Container3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer3()
		 * @generated
		 */
		EClass CONTAINER3 = eINSTANCE.getContainer3();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl <em>Container4</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer4()
		 * @generated
		 */
		EClass CONTAINER4 = eINSTANCE.getContainer4();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl <em>Container5</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer5()
		 * @generated
		 */
		EClass CONTAINER5 = eINSTANCE.getContainer5();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl <em>Container6</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer6()
		 * @generated
		 */
		EClass CONTAINER6 = eINSTANCE.getContainer6();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl <em>Container7</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer7()
		 * @generated
		 */
		EClass CONTAINER7 = eINSTANCE.getContainer7();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl <em>Container8</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer8()
		 * @generated
		 */
		EClass CONTAINER8 = eINSTANCE.getContainer8();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl <em>Container9</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer9()
		 * @generated
		 */
		EClass CONTAINER9 = eINSTANCE.getContainer9();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl <em>Container10</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer10()
		 * @generated
		 */
		EClass CONTAINER10 = eINSTANCE.getContainer10();

	}

} //MasterDataEditorGMFPackage
