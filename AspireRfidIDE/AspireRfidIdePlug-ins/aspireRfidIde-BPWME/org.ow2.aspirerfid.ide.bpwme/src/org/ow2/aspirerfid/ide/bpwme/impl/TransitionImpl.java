/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.Condition;
import org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes;
import org.ow2.aspirerfid.ide.bpwme.Transition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionImpl extends EObjectImpl implements Transition {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Condition condition;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtendedAttributes() <em>Extended Attributes</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedAttributes()
	 * @generated
	 * @ordered
	 */
	protected ExtendedAttributes extendedAttributes;

	/**
	 * The default value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected String from = FROM_EDEFAULT;

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
	protected String id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected String to = TO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpwmePackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getCondition() {
		if (condition != null && condition.eIsProxy()) {
			InternalEObject oldCondition = (InternalEObject)condition;
			condition = (Condition)eResolveProxy(oldCondition);
			if (condition != oldCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpwmePackage.TRANSITION__CONDITION, oldCondition, condition));
			}
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition basicGetCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Condition newCondition) {
		Condition oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributes getExtendedAttributes() {
		if (extendedAttributes != null && extendedAttributes.eIsProxy()) {
			InternalEObject oldExtendedAttributes = (InternalEObject)extendedAttributes;
			extendedAttributes = (ExtendedAttributes)eResolveProxy(oldExtendedAttributes);
			if (extendedAttributes != oldExtendedAttributes) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES, oldExtendedAttributes, extendedAttributes));
			}
		}
		return extendedAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributes basicGetExtendedAttributes() {
		return extendedAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedAttributes(ExtendedAttributes newExtendedAttributes) {
		ExtendedAttributes oldExtendedAttributes = extendedAttributes;
		extendedAttributes = newExtendedAttributes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES, oldExtendedAttributes, extendedAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(String newFrom) {
		String oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__FROM, oldFrom, from));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		String oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpwmePackage.TRANSITION__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpwmePackage.TRANSITION__CONDITION:
				if (resolve) return getCondition();
				return basicGetCondition();
			case BpwmePackage.TRANSITION__DESCRIPTION:
				return getDescription();
			case BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES:
				if (resolve) return getExtendedAttributes();
				return basicGetExtendedAttributes();
			case BpwmePackage.TRANSITION__FROM:
				return getFrom();
			case BpwmePackage.TRANSITION__ID:
				return getId();
			case BpwmePackage.TRANSITION__NAME:
				return getName();
			case BpwmePackage.TRANSITION__TO:
				return getTo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BpwmePackage.TRANSITION__CONDITION:
				setCondition((Condition)newValue);
				return;
			case BpwmePackage.TRANSITION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributes)newValue);
				return;
			case BpwmePackage.TRANSITION__FROM:
				setFrom((String)newValue);
				return;
			case BpwmePackage.TRANSITION__ID:
				setId((String)newValue);
				return;
			case BpwmePackage.TRANSITION__NAME:
				setName((String)newValue);
				return;
			case BpwmePackage.TRANSITION__TO:
				setTo((String)newValue);
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
			case BpwmePackage.TRANSITION__CONDITION:
				setCondition((Condition)null);
				return;
			case BpwmePackage.TRANSITION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributes)null);
				return;
			case BpwmePackage.TRANSITION__FROM:
				setFrom(FROM_EDEFAULT);
				return;
			case BpwmePackage.TRANSITION__ID:
				setId(ID_EDEFAULT);
				return;
			case BpwmePackage.TRANSITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BpwmePackage.TRANSITION__TO:
				setTo(TO_EDEFAULT);
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
			case BpwmePackage.TRANSITION__CONDITION:
				return condition != null;
			case BpwmePackage.TRANSITION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case BpwmePackage.TRANSITION__EXTENDED_ATTRIBUTES:
				return extendedAttributes != null;
			case BpwmePackage.TRANSITION__FROM:
				return FROM_EDEFAULT == null ? from != null : !FROM_EDEFAULT.equals(from);
			case BpwmePackage.TRANSITION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case BpwmePackage.TRANSITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BpwmePackage.TRANSITION__TO:
				return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
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
		result.append(" (description: ");
		result.append(description);
		result.append(", from: ");
		result.append(from);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", to: ");
		result.append(to);
		result.append(')');
		return result.toString();
	}

} //TransitionImpl
