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
 * A representation of the model object '<em><b>Extended Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getContent <em>Content</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getExtendedAttribute()
 * @model
 * @generated
 */
public interface ExtendedAttribute extends EObject {
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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getExtendedAttribute_Content()
	 * @model
	 * @generated
	 */
	EList<Object> getContent();

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
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getExtendedAttribute_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getExtendedAttribute_Value()
	 * @model default=""
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // ExtendedAttribute
