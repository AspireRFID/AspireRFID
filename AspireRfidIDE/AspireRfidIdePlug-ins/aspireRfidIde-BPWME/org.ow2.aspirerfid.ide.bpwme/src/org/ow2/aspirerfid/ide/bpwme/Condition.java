/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Condition#getContent <em>Content</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Condition#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCondition()
 * @model
 * @generated
 */
public interface Condition extends EObject {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Object}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute list.
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCondition_Content()
	 * @model
	 * @generated
	 */
	EList<Object> getContent();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getCondition_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.Condition#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // Condition
