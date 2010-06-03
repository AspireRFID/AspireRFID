/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OLCB Proc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getId <em>Id</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getCLCBProc <em>CLCB Proc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getOLCBProc()
 * @model
 * @generated
 */
public interface OLCBProc extends Node {
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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getOLCBProc_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getOLCBProc_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' reference.
	 * @see #setTransitions(Transitions)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getOLCBProc_Transitions()
	 * @model
	 * @generated
	 */
	Transitions getTransitions();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getTransitions <em>Transitions</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transitions</em>' reference.
	 * @see #getTransitions()
	 * @generated
	 */
	void setTransitions(Transitions value);

	/**
	 * Returns the value of the '<em><b>CLCB Proc</b></em>' containment reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.bpwme.CLCBProc}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CLCB Proc</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CLCB Proc</em>' containment reference list.
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getOLCBProc_CLCBProc()
	 * @model containment="true"
	 * @generated
	 */
	EList<CLCBProc> getCLCBProc();

} // OLCBProc
