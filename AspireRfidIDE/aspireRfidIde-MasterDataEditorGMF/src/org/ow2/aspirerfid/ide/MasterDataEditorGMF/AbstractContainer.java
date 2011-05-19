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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID <em>ID</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint <em>Read Point</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType <em>Type</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr1 <em>Attr1</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr2 <em>Attr2</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr3 <em>Attr3</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr4 <em>Attr4</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr5 <em>Attr5</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr6 <em>Attr6</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr7 <em>Attr7</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr8 <em>Attr8</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr9 <em>Attr9</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr10 <em>Attr10</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr11 <em>Attr11</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr12 <em>Attr12</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr13 <em>Attr13</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr14 <em>Attr14</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr15 <em>Attr15</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr16 <em>Attr16</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr17 <em>Attr17</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr18 <em>Attr18</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr19 <em>Attr19</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr20 <em>Attr20</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer()
 * @model abstract="true"
 * @generated
 */
public interface AbstractContainer extends EObject {
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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_ID()
	 * @model default="urn:epcglobal:fmcg:loc:"
	 * @generated
	 */
	String getID();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID <em>ID</em>}' attribute.
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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName <em>Name</em>}' attribute.
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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Read Point</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Point</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Point</em>' attribute.
	 * @see #setReadPoint(boolean)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_ReadPoint()
	 * @model default="true"
	 * @generated
	 */
	boolean isReadPoint();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint <em>Read Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Point</em>' attribute.
	 * @see #isReadPoint()
	 * @generated
	 */
	void setReadPoint(boolean value);

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
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Type()
	 * @model default=""
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Attr1</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr1</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr1</em>' attribute.
	 * @see #setAttr1(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr1()
	 * @model default=""
	 * @generated
	 */
	String getAttr1();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr1 <em>Attr1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr1</em>' attribute.
	 * @see #getAttr1()
	 * @generated
	 */
	void setAttr1(String value);

	/**
	 * Returns the value of the '<em><b>Attr2</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr2</em>' attribute.
	 * @see #setAttr2(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr2()
	 * @model default=""
	 * @generated
	 */
	String getAttr2();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr2 <em>Attr2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr2</em>' attribute.
	 * @see #getAttr2()
	 * @generated
	 */
	void setAttr2(String value);

	/**
	 * Returns the value of the '<em><b>Attr3</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr3</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr3</em>' attribute.
	 * @see #setAttr3(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr3()
	 * @model default=""
	 * @generated
	 */
	String getAttr3();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr3 <em>Attr3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr3</em>' attribute.
	 * @see #getAttr3()
	 * @generated
	 */
	void setAttr3(String value);

	/**
	 * Returns the value of the '<em><b>Attr4</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr4</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr4</em>' attribute.
	 * @see #setAttr4(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr4()
	 * @model default=""
	 * @generated
	 */
	String getAttr4();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr4 <em>Attr4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr4</em>' attribute.
	 * @see #getAttr4()
	 * @generated
	 */
	void setAttr4(String value);

	/**
	 * Returns the value of the '<em><b>Attr5</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr5</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr5</em>' attribute.
	 * @see #setAttr5(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr5()
	 * @model default=""
	 * @generated
	 */
	String getAttr5();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr5 <em>Attr5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr5</em>' attribute.
	 * @see #getAttr5()
	 * @generated
	 */
	void setAttr5(String value);

	/**
	 * Returns the value of the '<em><b>Attr6</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr6</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr6</em>' attribute.
	 * @see #setAttr6(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr6()
	 * @model default=""
	 * @generated
	 */
	String getAttr6();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr6 <em>Attr6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr6</em>' attribute.
	 * @see #getAttr6()
	 * @generated
	 */
	void setAttr6(String value);

	/**
	 * Returns the value of the '<em><b>Attr7</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr7</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr7</em>' attribute.
	 * @see #setAttr7(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr7()
	 * @model default=""
	 * @generated
	 */
	String getAttr7();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr7 <em>Attr7</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr7</em>' attribute.
	 * @see #getAttr7()
	 * @generated
	 */
	void setAttr7(String value);

	/**
	 * Returns the value of the '<em><b>Attr8</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr8</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr8</em>' attribute.
	 * @see #setAttr8(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr8()
	 * @model default=""
	 * @generated
	 */
	String getAttr8();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr8 <em>Attr8</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr8</em>' attribute.
	 * @see #getAttr8()
	 * @generated
	 */
	void setAttr8(String value);

	/**
	 * Returns the value of the '<em><b>Attr9</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr9</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr9</em>' attribute.
	 * @see #setAttr9(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr9()
	 * @model default=""
	 * @generated
	 */
	String getAttr9();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr9 <em>Attr9</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr9</em>' attribute.
	 * @see #getAttr9()
	 * @generated
	 */
	void setAttr9(String value);

	/**
	 * Returns the value of the '<em><b>Attr10</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr10</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr10</em>' attribute.
	 * @see #setAttr10(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr10()
	 * @model default=""
	 * @generated
	 */
	String getAttr10();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr10 <em>Attr10</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr10</em>' attribute.
	 * @see #getAttr10()
	 * @generated
	 */
	void setAttr10(String value);

	/**
	 * Returns the value of the '<em><b>Attr11</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr11</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr11</em>' attribute.
	 * @see #setAttr11(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr11()
	 * @model default=""
	 * @generated
	 */
	String getAttr11();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr11 <em>Attr11</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr11</em>' attribute.
	 * @see #getAttr11()
	 * @generated
	 */
	void setAttr11(String value);

	/**
	 * Returns the value of the '<em><b>Attr12</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr12</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr12</em>' attribute.
	 * @see #setAttr12(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr12()
	 * @model default=""
	 * @generated
	 */
	String getAttr12();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr12 <em>Attr12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr12</em>' attribute.
	 * @see #getAttr12()
	 * @generated
	 */
	void setAttr12(String value);

	/**
	 * Returns the value of the '<em><b>Attr13</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr13</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr13</em>' attribute.
	 * @see #setAttr13(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr13()
	 * @model default=""
	 * @generated
	 */
	String getAttr13();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr13 <em>Attr13</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr13</em>' attribute.
	 * @see #getAttr13()
	 * @generated
	 */
	void setAttr13(String value);

	/**
	 * Returns the value of the '<em><b>Attr14</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr14</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr14</em>' attribute.
	 * @see #setAttr14(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr14()
	 * @model default=""
	 * @generated
	 */
	String getAttr14();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr14 <em>Attr14</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr14</em>' attribute.
	 * @see #getAttr14()
	 * @generated
	 */
	void setAttr14(String value);

	/**
	 * Returns the value of the '<em><b>Attr15</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr15</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr15</em>' attribute.
	 * @see #setAttr15(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr15()
	 * @model default=""
	 * @generated
	 */
	String getAttr15();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr15 <em>Attr15</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr15</em>' attribute.
	 * @see #getAttr15()
	 * @generated
	 */
	void setAttr15(String value);

	/**
	 * Returns the value of the '<em><b>Attr16</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr16</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr16</em>' attribute.
	 * @see #setAttr16(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr16()
	 * @model default=""
	 * @generated
	 */
	String getAttr16();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr16 <em>Attr16</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr16</em>' attribute.
	 * @see #getAttr16()
	 * @generated
	 */
	void setAttr16(String value);

	/**
	 * Returns the value of the '<em><b>Attr17</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr17</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr17</em>' attribute.
	 * @see #setAttr17(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr17()
	 * @model default=""
	 * @generated
	 */
	String getAttr17();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr17 <em>Attr17</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr17</em>' attribute.
	 * @see #getAttr17()
	 * @generated
	 */
	void setAttr17(String value);

	/**
	 * Returns the value of the '<em><b>Attr18</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr18</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr18</em>' attribute.
	 * @see #setAttr18(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr18()
	 * @model default=""
	 * @generated
	 */
	String getAttr18();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr18 <em>Attr18</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr18</em>' attribute.
	 * @see #getAttr18()
	 * @generated
	 */
	void setAttr18(String value);

	/**
	 * Returns the value of the '<em><b>Attr19</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr19</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr19</em>' attribute.
	 * @see #setAttr19(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr19()
	 * @model default=""
	 * @generated
	 */
	String getAttr19();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr19 <em>Attr19</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr19</em>' attribute.
	 * @see #getAttr19()
	 * @generated
	 */
	void setAttr19(String value);

	/**
	 * Returns the value of the '<em><b>Attr20</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attr20</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attr20</em>' attribute.
	 * @see #setAttr20(String)
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage#getAbstractContainer_Attr20()
	 * @model default=""
	 * @generated
	 */
	String getAttr20();

	/**
	 * Sets the value of the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr20 <em>Attr20</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attr20</em>' attribute.
	 * @see #getAttr20()
	 * @generated
	 */
	void setAttr20(String value);

} // AbstractContainer
