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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage
 * @generated
 */
public class MasterDataEditorGMFAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MasterDataEditorGMFPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterDataEditorGMFAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MasterDataEditorGMFPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MasterDataEditorGMFSwitch<Adapter> modelSwitch =
		new MasterDataEditorGMFSwitch<Adapter>() {
			@Override
			public Adapter caseCompany(Company object) {
				return createCompanyAdapter();
			}
			@Override
			public Adapter caseAbstractWarehouse(AbstractWarehouse object) {
				return createAbstractWarehouseAdapter();
			}
			@Override
			public Adapter caseBizLoc(BizLoc object) {
				return createBizLocAdapter();
			}
			@Override
			public Adapter caseWarehouse(Warehouse object) {
				return createWarehouseAdapter();
			}
			@Override
			public Adapter caseRoom(Room object) {
				return createRoomAdapter();
			}
			@Override
			public Adapter caseSection(Section object) {
				return createSectionAdapter();
			}
			@Override
			public Adapter caseAbstractContainer(AbstractContainer object) {
				return createAbstractContainerAdapter();
			}
			@Override
			public Adapter caseReadPoint(ReadPoint object) {
				return createReadPointAdapter();
			}
			@Override
			public Adapter caseContainer(Container object) {
				return createContainerAdapter();
			}
			@Override
			public Adapter caseConveyor(Conveyor object) {
				return createConveyorAdapter();
			}
			@Override
			public Adapter caseShelf(Shelf object) {
				return createShelfAdapter();
			}
			@Override
			public Adapter caseGate(Gate object) {
				return createGateAdapter();
			}
			@Override
			public Adapter casePalletTruck(PalletTruck object) {
				return createPalletTruckAdapter();
			}
			@Override
			public Adapter casePushArm(PushArm object) {
				return createPushArmAdapter();
			}
			@Override
			public Adapter caseHandHeldReader(HandHeldReader object) {
				return createHandHeldReaderAdapter();
			}
			@Override
			public Adapter caseContainer1(Container1 object) {
				return createContainer1Adapter();
			}
			@Override
			public Adapter caseContainer2(Container2 object) {
				return createContainer2Adapter();
			}
			@Override
			public Adapter caseContainer3(Container3 object) {
				return createContainer3Adapter();
			}
			@Override
			public Adapter caseContainer4(Container4 object) {
				return createContainer4Adapter();
			}
			@Override
			public Adapter caseContainer5(Container5 object) {
				return createContainer5Adapter();
			}
			@Override
			public Adapter caseContainer6(Container6 object) {
				return createContainer6Adapter();
			}
			@Override
			public Adapter caseContainer7(Container7 object) {
				return createContainer7Adapter();
			}
			@Override
			public Adapter caseContainer8(Container8 object) {
				return createContainer8Adapter();
			}
			@Override
			public Adapter caseContainer9(Container9 object) {
				return createContainer9Adapter();
			}
			@Override
			public Adapter caseContainer10(Container10 object) {
				return createContainer10Adapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company
	 * @generated
	 */
	public Adapter createCompanyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse <em>Abstract Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse
	 * @generated
	 */
	public Adapter createAbstractWarehouseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc <em>Biz Loc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc
	 * @generated
	 */
	public Adapter createBizLocAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse <em>Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse
	 * @generated
	 */
	public Adapter createWarehouseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room <em>Room</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room
	 * @generated
	 */
	public Adapter createRoomAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section
	 * @generated
	 */
	public Adapter createSectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer <em>Abstract Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer
	 * @generated
	 */
	public Adapter createAbstractContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint <em>Read Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint
	 * @generated
	 */
	public Adapter createReadPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container
	 * @generated
	 */
	public Adapter createContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor <em>Conveyor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor
	 * @generated
	 */
	public Adapter createConveyorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf <em>Shelf</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf
	 * @generated
	 */
	public Adapter createShelfAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate <em>Gate</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate
	 * @generated
	 */
	public Adapter createGateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck <em>Pallet Truck</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck
	 * @generated
	 */
	public Adapter createPalletTruckAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm <em>Push Arm</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm
	 * @generated
	 */
	public Adapter createPushArmAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader <em>Hand Held Reader</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader
	 * @generated
	 */
	public Adapter createHandHeldReaderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1 <em>Container1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1
	 * @generated
	 */
	public Adapter createContainer1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2 <em>Container2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2
	 * @generated
	 */
	public Adapter createContainer2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3 <em>Container3</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3
	 * @generated
	 */
	public Adapter createContainer3Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4 <em>Container4</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4
	 * @generated
	 */
	public Adapter createContainer4Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5 <em>Container5</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5
	 * @generated
	 */
	public Adapter createContainer5Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6 <em>Container6</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6
	 * @generated
	 */
	public Adapter createContainer6Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7 <em>Container7</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7
	 * @generated
	 */
	public Adapter createContainer7Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8 <em>Container8</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8
	 * @generated
	 */
	public Adapter createContainer8Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9 <em>Container9</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9
	 * @generated
	 */
	public Adapter createContainer9Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10 <em>Container10</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10
	 * @generated
	 */
	public Adapter createContainer10Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //MasterDataEditorGMFAdapterFactory
