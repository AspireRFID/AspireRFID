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
 * A representation of the model object '<em><b>CLCB Proc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getId <em>Id</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getEBProc <em>EB Proc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCLCBProc()
 * @model
 * @generated
 */
public interface CLCBProc extends Node {
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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCLCBProc_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCLCBProc_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getName <em>Name</em>}' attribute.
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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCLCBProc_Id()
	 * @model default=""
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Biz Location File</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Biz Location File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Biz Location File</em>' attribute.
	 * @see #setBizLocationFile(String)
	 * @see bpwme.BpwmePackage#getCLCBProc_BizLocationFile()
	 * @model default=""
	 * @generated
	 */
	String getBizLocationFile();

	/**
	 * Sets the value of the '{@link bpwme.CLCBProc#getBizLocationFile <em>Biz Location File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Biz Location File</em>' attribute.
	 * @see #getBizLocationFile()
	 * @generated
	 */
	void setBizLocationFile(String value);

	/**
	 * Returns the value of the '<em><b>EB Proc</b></em>' containment reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.bpwme.EBProc}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EB Proc</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EB Proc</em>' containment reference list.
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCLCBProc_EBProc()
	 * @model containment="true"
	 * @generated
	 */
	EList<EBProc> getEBProc();

} // CLCBProc
