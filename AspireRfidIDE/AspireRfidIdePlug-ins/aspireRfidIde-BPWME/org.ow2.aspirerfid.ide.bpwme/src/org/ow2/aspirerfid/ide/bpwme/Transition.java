/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getFrom <em>From</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getId <em>Id</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Transition#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference.
	 * @see #setCondition(Condition)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_Condition()
	 * @model
	 * @generated
	 */
	Condition getCondition();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getCondition <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Extended Attributes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Attributes</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Attributes</em>' reference.
	 * @see #setExtendedAttributes(ExtendedAttributes)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_ExtendedAttributes()
	 * @model
	 * @generated
	 */
	ExtendedAttributes getExtendedAttributes();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getExtendedAttributes <em>Extended Attributes</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Attributes</em>' reference.
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	void setExtendedAttributes(ExtendedAttributes value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_From()
	 * @model default=""
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getTransition_To()
	 * @model default=""
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

} // Transition
