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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage
 * @generated
 */
public class MasterDataEditorGMFSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MasterDataEditorGMFPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterDataEditorGMFSwitch() {
		if (modelPackage == null) {
			modelPackage = MasterDataEditorGMFPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MasterDataEditorGMFPackage.COMPANY: {
				Company company = (Company)theEObject;
				T result = caseCompany(company);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE: {
				AbstractWarehouse abstractWarehouse = (AbstractWarehouse)theEObject;
				T result = caseAbstractWarehouse(abstractWarehouse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.BIZ_LOC: {
				BizLoc bizLoc = (BizLoc)theEObject;
				T result = caseBizLoc(bizLoc);
				if (result == null) result = caseAbstractWarehouse(bizLoc);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.WAREHOUSE: {
				Warehouse warehouse = (Warehouse)theEObject;
				T result = caseWarehouse(warehouse);
				if (result == null) result = caseAbstractWarehouse(warehouse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.ROOM: {
				Room room = (Room)theEObject;
				T result = caseRoom(room);
				if (result == null) result = caseAbstractWarehouse(room);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.SECTION: {
				Section section = (Section)theEObject;
				T result = caseSection(section);
				if (result == null) result = caseAbstractWarehouse(section);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.ABSTRACT_CONTAINER: {
				AbstractContainer abstractContainer = (AbstractContainer)theEObject;
				T result = caseAbstractContainer(abstractContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.READ_POINT: {
				ReadPoint readPoint = (ReadPoint)theEObject;
				T result = caseReadPoint(readPoint);
				if (result == null) result = caseAbstractContainer(readPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T result = caseContainer(container);
				if (result == null) result = caseAbstractContainer(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONVEYOR: {
				Conveyor conveyor = (Conveyor)theEObject;
				T result = caseConveyor(conveyor);
				if (result == null) result = caseAbstractContainer(conveyor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.SHELF: {
				Shelf shelf = (Shelf)theEObject;
				T result = caseShelf(shelf);
				if (result == null) result = caseAbstractContainer(shelf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.GATE: {
				Gate gate = (Gate)theEObject;
				T result = caseGate(gate);
				if (result == null) result = caseAbstractContainer(gate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.PALLET_TRUCK: {
				PalletTruck palletTruck = (PalletTruck)theEObject;
				T result = casePalletTruck(palletTruck);
				if (result == null) result = caseAbstractContainer(palletTruck);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.PUSH_ARM: {
				PushArm pushArm = (PushArm)theEObject;
				T result = casePushArm(pushArm);
				if (result == null) result = caseAbstractContainer(pushArm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.HAND_HELD_READER: {
				HandHeldReader handHeldReader = (HandHeldReader)theEObject;
				T result = caseHandHeldReader(handHeldReader);
				if (result == null) result = caseAbstractContainer(handHeldReader);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER1: {
				Container1 container1 = (Container1)theEObject;
				T result = caseContainer1(container1);
				if (result == null) result = caseAbstractContainer(container1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER2: {
				Container2 container2 = (Container2)theEObject;
				T result = caseContainer2(container2);
				if (result == null) result = caseAbstractContainer(container2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER3: {
				Container3 container3 = (Container3)theEObject;
				T result = caseContainer3(container3);
				if (result == null) result = caseAbstractContainer(container3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER4: {
				Container4 container4 = (Container4)theEObject;
				T result = caseContainer4(container4);
				if (result == null) result = caseAbstractContainer(container4);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER5: {
				Container5 container5 = (Container5)theEObject;
				T result = caseContainer5(container5);
				if (result == null) result = caseAbstractContainer(container5);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER6: {
				Container6 container6 = (Container6)theEObject;
				T result = caseContainer6(container6);
				if (result == null) result = caseAbstractContainer(container6);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER7: {
				Container7 container7 = (Container7)theEObject;
				T result = caseContainer7(container7);
				if (result == null) result = caseAbstractContainer(container7);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER8: {
				Container8 container8 = (Container8)theEObject;
				T result = caseContainer8(container8);
				if (result == null) result = caseAbstractContainer(container8);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER9: {
				Container9 container9 = (Container9)theEObject;
				T result = caseContainer9(container9);
				if (result == null) result = caseAbstractContainer(container9);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MasterDataEditorGMFPackage.CONTAINER10: {
				Container10 container10 = (Container10)theEObject;
				T result = caseContainer10(container10);
				if (result == null) result = caseAbstractContainer(container10);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Company</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Company</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompany(Company object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Warehouse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Warehouse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractWarehouse(AbstractWarehouse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Biz Loc</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Biz Loc</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBizLoc(BizLoc object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Warehouse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Warehouse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWarehouse(Warehouse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Room</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Room</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoom(Room object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Section</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Section</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSection(Section object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractContainer(AbstractContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Read Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Read Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReadPoint(ReadPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer(Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conveyor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conveyor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConveyor(Conveyor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shelf</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shelf</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShelf(Shelf object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gate</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gate</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGate(Gate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pallet Truck</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pallet Truck</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePalletTruck(PalletTruck object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Push Arm</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Push Arm</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePushArm(PushArm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hand Held Reader</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hand Held Reader</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHandHeldReader(HandHeldReader object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer1(Container1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer2(Container2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer3(Container3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container4</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container4</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer4(Container4 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container5</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container5</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer5(Container5 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container6</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container6</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer6(Container6 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container7</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container7</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer7(Container7 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container8</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container8</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer8(Container8 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container9</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container9</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer9(Container9 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container10</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container10</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer10(Container10 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //MasterDataEditorGMFSwitch
