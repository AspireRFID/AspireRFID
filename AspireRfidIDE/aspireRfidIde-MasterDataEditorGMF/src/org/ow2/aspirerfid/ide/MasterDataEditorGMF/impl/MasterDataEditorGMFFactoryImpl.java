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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MasterDataEditorGMFFactoryImpl extends EFactoryImpl implements MasterDataEditorGMFFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MasterDataEditorGMFFactory init() {
		try {
			MasterDataEditorGMFFactory theMasterDataEditorGMFFactory = (MasterDataEditorGMFFactory)EPackage.Registry.INSTANCE.getEFactory("http://MasterDataEditorGMF"); 
			if (theMasterDataEditorGMFFactory != null) {
				return theMasterDataEditorGMFFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MasterDataEditorGMFFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterDataEditorGMFFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MasterDataEditorGMFPackage.COMPANY: return createCompany();
			case MasterDataEditorGMFPackage.BIZ_LOC: return createBizLoc();
			case MasterDataEditorGMFPackage.WAREHOUSE: return createWarehouse();
			case MasterDataEditorGMFPackage.ROOM: return createRoom();
			case MasterDataEditorGMFPackage.SECTION: return createSection();
			case MasterDataEditorGMFPackage.READ_POINT: return createReadPoint();
			case MasterDataEditorGMFPackage.CONTAINER: return createContainer();
			case MasterDataEditorGMFPackage.CONVEYOR: return createConveyor();
			case MasterDataEditorGMFPackage.SHELF: return createShelf();
			case MasterDataEditorGMFPackage.GATE: return createGate();
			case MasterDataEditorGMFPackage.PALLET_TRUCK: return createPalletTruck();
			case MasterDataEditorGMFPackage.PUSH_ARM: return createPushArm();
			case MasterDataEditorGMFPackage.HAND_HELD_READER: return createHandHeldReader();
			case MasterDataEditorGMFPackage.CONTAINER1: return createContainer1();
			case MasterDataEditorGMFPackage.CONTAINER2: return createContainer2();
			case MasterDataEditorGMFPackage.CONTAINER3: return createContainer3();
			case MasterDataEditorGMFPackage.CONTAINER4: return createContainer4();
			case MasterDataEditorGMFPackage.CONTAINER5: return createContainer5();
			case MasterDataEditorGMFPackage.CONTAINER6: return createContainer6();
			case MasterDataEditorGMFPackage.CONTAINER7: return createContainer7();
			case MasterDataEditorGMFPackage.CONTAINER8: return createContainer8();
			case MasterDataEditorGMFPackage.CONTAINER9: return createContainer9();
			case MasterDataEditorGMFPackage.CONTAINER10: return createContainer10();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Company createCompany() {
		CompanyImpl company = new CompanyImpl();
		return company;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BizLoc createBizLoc() {
		BizLocImpl bizLoc = new BizLocImpl();
		return bizLoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Warehouse createWarehouse() {
		WarehouseImpl warehouse = new WarehouseImpl();
		return warehouse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Room createRoom() {
		RoomImpl room = new RoomImpl();
		return room;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Section createSection() {
		SectionImpl section = new SectionImpl();
		return section;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadPoint createReadPoint() {
		ReadPointImpl readPoint = new ReadPointImpl();
		return readPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conveyor createConveyor() {
		ConveyorImpl conveyor = new ConveyorImpl();
		return conveyor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shelf createShelf() {
		ShelfImpl shelf = new ShelfImpl();
		return shelf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Gate createGate() {
		GateImpl gate = new GateImpl();
		return gate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PalletTruck createPalletTruck() {
		PalletTruckImpl palletTruck = new PalletTruckImpl();
		return palletTruck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PushArm createPushArm() {
		PushArmImpl pushArm = new PushArmImpl();
		return pushArm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HandHeldReader createHandHeldReader() {
		HandHeldReaderImpl handHeldReader = new HandHeldReaderImpl();
		return handHeldReader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container1 createContainer1() {
		Container1Impl container1 = new Container1Impl();
		return container1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container2 createContainer2() {
		Container2Impl container2 = new Container2Impl();
		return container2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container3 createContainer3() {
		Container3Impl container3 = new Container3Impl();
		return container3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container4 createContainer4() {
		Container4Impl container4 = new Container4Impl();
		return container4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container5 createContainer5() {
		Container5Impl container5 = new Container5Impl();
		return container5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container6 createContainer6() {
		Container6Impl container6 = new Container6Impl();
		return container6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container7 createContainer7() {
		Container7Impl container7 = new Container7Impl();
		return container7;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container8 createContainer8() {
		Container8Impl container8 = new Container8Impl();
		return container8;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container9 createContainer9() {
		Container9Impl container9 = new Container9Impl();
		return container9;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container10 createContainer10() {
		Container10Impl container10 = new Container10Impl();
		return container10;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterDataEditorGMFPackage getMasterDataEditorGMFPackage() {
		return (MasterDataEditorGMFPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MasterDataEditorGMFPackage getPackage() {
		return MasterDataEditorGMFPackage.eINSTANCE;
	}

} //MasterDataEditorGMFFactoryImpl
