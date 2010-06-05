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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Warehouse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID <em>ID</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType <em>Type</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getEpcisID <em>Epcis ID</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getWarehouses <em>Warehouses</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getContainers <em>Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse()
 * @model abstract="true"
 * @generated
 */
public interface AbstractWarehouse extends EObject {
	/**
	 * Returns the value of the '<em><b>ID</b></em>' attribute.
	 * The default value is <code>"urn:epcglobal:fmcg:loc:"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ID</em>' attribute.
	 * @see #setID(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_ID()
	 * @model default="urn:epcglobal:fmcg:loc:"
	 * @generated
	 */
	String getID();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ID</em>' attribute.
	 * @see #getID()
	 * @generated
	 */
	void setID(String value);

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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Epcis ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Epcis ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Epcis ID</em>' attribute.
	 * @see #setEpcisID(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_EpcisID()
	 * @model
	 * @generated
	 */
	String getEpcisID();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getEpcisID <em>Epcis ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Epcis ID</em>' attribute.
	 * @see #getEpcisID()
	 * @generated
	 */
	void setEpcisID(String value);

	/**
	 * Returns the value of the '<em><b>Warehouses</b></em>' containment reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Warehouses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Warehouses</em>' containment reference list.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_Warehouses()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractWarehouse> getWarehouses();

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' containment reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' containment reference list.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractWarehouse_Containers()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractContainer> getContainers();

} // AbstractWarehouse
