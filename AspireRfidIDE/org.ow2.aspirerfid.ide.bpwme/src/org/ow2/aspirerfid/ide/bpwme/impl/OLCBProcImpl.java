/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.util.BpwmeAssistant;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OLCB Proc</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl#getCLCBProc <em>CLCB Proc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OLCBProcImpl extends NodeImpl implements OLCBProc {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = BpwmeAssistant.getUniqueOLCBID();


	/**
	 * The cached value of the '{@link #getCLCBProc() <em>CLCB Proc</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCLCBProc()
	 * @generated
	 * @ordered
	 */
	protected EList<CLCBProc> cLCBProc;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OLCBProcImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpwmePackage.Literals.OLCB_PROC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.OLCB_PROC__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.OLCB_PROC__ID, oldId, id));
	}


	public void setFake() {
		eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.OLCB_PROC_FAKE_FEATURE, "fake", "fake"));
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CLCBProc> getCLCBProc() {
		if (cLCBProc == null) {
			cLCBProc = new EObjectContainmentEList<CLCBProc>(CLCBProc.class, this, BpwmePackage.OLCB_PROC__CLCB_PROC);
		}
		return cLCBProc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BpwmePackage.OLCB_PROC__CLCB_PROC:
				return ((InternalEList<?>)getCLCBProc()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpwmePackage.OLCB_PROC__NAME:
				return getName();
			case BpwmePackage.OLCB_PROC__ID:
				return getId();
			case BpwmePackage.OLCB_PROC__CLCB_PROC:
				return getCLCBProc();
			case BpwmePackage.OLCB_PROC_FAKE_FEATURE:
				return getFake();
		}
		return super.eGet(featureID, resolve, coreType);
	}
	
	
	public String getFake() {
		return "fake";
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BpwmePackage.OLCB_PROC__NAME:
				setName((String)newValue);
				return;
			case BpwmePackage.OLCB_PROC__ID:
				setId((String)newValue);
				return;
			case BpwmePackage.OLCB_PROC__CLCB_PROC:
				getCLCBProc().clear();
				getCLCBProc().addAll((Collection<? extends CLCBProc>)newValue);
				return;
			case BpwmePackage.OLCB_PROC_FAKE_FEATURE:
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BpwmePackage.OLCB_PROC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BpwmePackage.OLCB_PROC__ID:
				setId(ID_EDEFAULT);
				return;
			case BpwmePackage.OLCB_PROC__CLCB_PROC:
				getCLCBProc().clear();
				return;
			case BpwmePackage.OLCB_PROC_FAKE_FEATURE:
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BpwmePackage.OLCB_PROC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BpwmePackage.OLCB_PROC__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case BpwmePackage.OLCB_PROC__CLCB_PROC:
				return cLCBProc != null && !cLCBProc.isEmpty();
			case BpwmePackage.OLCB_PROC_FAKE_FEATURE:
				return true;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //OLCBProcImpl
