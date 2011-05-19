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
 
package org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Warehouse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getID <em>ID</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getWarehouses <em>Warehouses</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr1 <em>Attr1</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr2 <em>Attr2</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr3 <em>Attr3</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr4 <em>Attr4</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr5 <em>Attr5</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr6 <em>Attr6</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr7 <em>Attr7</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr8 <em>Attr8</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr9 <em>Attr9</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr10 <em>Attr10</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr11 <em>Attr11</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr12 <em>Attr12</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr13 <em>Attr13</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr14 <em>Attr14</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr15 <em>Attr15</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr16 <em>Attr16</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr17 <em>Attr17</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr18 <em>Attr18</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr19 <em>Attr19</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr20 <em>Attr20</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractWarehouseImpl extends EObjectImpl implements AbstractWarehouse {
	/**
	 * The default value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "urn:epcglobal:fmcg:loc:";

	/**
	 * The cached value of the '{@link #getID() <em>ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getID()
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
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWarehouses() <em>Warehouses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarehouses()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractWarehouse> warehouses;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractContainer> containers;

	/**
	 * The default value of the '{@link #getAttr1() <em>Attr1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr1()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR1_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr1() <em>Attr1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr1()
	 * @generated
	 * @ordered
	 */
	protected String attr1 = ATTR1_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr2() <em>Attr2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr2()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR2_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr2() <em>Attr2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr2()
	 * @generated
	 * @ordered
	 */
	protected String attr2 = ATTR2_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr3() <em>Attr3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr3()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR3_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr3() <em>Attr3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr3()
	 * @generated
	 * @ordered
	 */
	protected String attr3 = ATTR3_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr4() <em>Attr4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr4()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR4_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr4() <em>Attr4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr4()
	 * @generated
	 * @ordered
	 */
	protected String attr4 = ATTR4_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr5() <em>Attr5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr5()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR5_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr5() <em>Attr5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr5()
	 * @generated
	 * @ordered
	 */
	protected String attr5 = ATTR5_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr6() <em>Attr6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr6()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR6_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr6() <em>Attr6</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr6()
	 * @generated
	 * @ordered
	 */
	protected String attr6 = ATTR6_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr7() <em>Attr7</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr7()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR7_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr7() <em>Attr7</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr7()
	 * @generated
	 * @ordered
	 */
	protected String attr7 = ATTR7_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr8() <em>Attr8</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr8()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR8_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr8() <em>Attr8</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr8()
	 * @generated
	 * @ordered
	 */
	protected String attr8 = ATTR8_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr9() <em>Attr9</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr9()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR9_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr9() <em>Attr9</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr9()
	 * @generated
	 * @ordered
	 */
	protected String attr9 = ATTR9_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr10() <em>Attr10</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr10()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR10_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr10() <em>Attr10</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr10()
	 * @generated
	 * @ordered
	 */
	protected String attr10 = ATTR10_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr11() <em>Attr11</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr11()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR11_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr11() <em>Attr11</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr11()
	 * @generated
	 * @ordered
	 */
	protected String attr11 = ATTR11_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr12() <em>Attr12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr12()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR12_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr12() <em>Attr12</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr12()
	 * @generated
	 * @ordered
	 */
	protected String attr12 = ATTR12_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr13() <em>Attr13</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr13()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR13_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr13() <em>Attr13</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr13()
	 * @generated
	 * @ordered
	 */
	protected String attr13 = ATTR13_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr14() <em>Attr14</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr14()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR14_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr14() <em>Attr14</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr14()
	 * @generated
	 * @ordered
	 */
	protected String attr14 = ATTR14_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr15() <em>Attr15</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr15()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR15_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr15() <em>Attr15</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr15()
	 * @generated
	 * @ordered
	 */
	protected String attr15 = ATTR15_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr16() <em>Attr16</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr16()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR16_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr16() <em>Attr16</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr16()
	 * @generated
	 * @ordered
	 */
	protected String attr16 = ATTR16_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr17() <em>Attr17</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr17()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR17_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr17() <em>Attr17</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr17()
	 * @generated
	 * @ordered
	 */
	protected String attr17 = ATTR17_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr18() <em>Attr18</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr18()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR18_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr18() <em>Attr18</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr18()
	 * @generated
	 * @ordered
	 */
	protected String attr18 = ATTR18_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr19() <em>Attr19</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr19()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR19_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr19() <em>Attr19</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr19()
	 * @generated
	 * @ordered
	 */
	protected String attr19 = ATTR19_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr20() <em>Attr20</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr20()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR20_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr20() <em>Attr20</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr20()
	 * @generated
	 * @ordered
	 */
	protected String attr20 = ATTR20_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractWarehouseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MasterDataEditorGMFPackage.Literals.ABSTRACT_WAREHOUSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getID() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setID(String newID) {
		String oldID = id;
		id = newID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID, oldID, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractWarehouse> getWarehouses() {
		if (warehouses == null) {
			warehouses = new EObjectContainmentEList<AbstractWarehouse>(AbstractWarehouse.class, this, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES);
		}
		return warehouses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractContainer> getContainers() {
		if (containers == null) {
			containers = new EObjectContainmentEList<AbstractContainer>(AbstractContainer.class, this, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr1() {
		return attr1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr1(String newAttr1) {
		String oldAttr1 = attr1;
		attr1 = newAttr1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1, oldAttr1, attr1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr2() {
		return attr2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr2(String newAttr2) {
		String oldAttr2 = attr2;
		attr2 = newAttr2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2, oldAttr2, attr2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr3() {
		return attr3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr3(String newAttr3) {
		String oldAttr3 = attr3;
		attr3 = newAttr3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3, oldAttr3, attr3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr4() {
		return attr4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr4(String newAttr4) {
		String oldAttr4 = attr4;
		attr4 = newAttr4;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4, oldAttr4, attr4));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr5() {
		return attr5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr5(String newAttr5) {
		String oldAttr5 = attr5;
		attr5 = newAttr5;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5, oldAttr5, attr5));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr6() {
		return attr6;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr6(String newAttr6) {
		String oldAttr6 = attr6;
		attr6 = newAttr6;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6, oldAttr6, attr6));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr7() {
		return attr7;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr7(String newAttr7) {
		String oldAttr7 = attr7;
		attr7 = newAttr7;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7, oldAttr7, attr7));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr8() {
		return attr8;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr8(String newAttr8) {
		String oldAttr8 = attr8;
		attr8 = newAttr8;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8, oldAttr8, attr8));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr9() {
		return attr9;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr9(String newAttr9) {
		String oldAttr9 = attr9;
		attr9 = newAttr9;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9, oldAttr9, attr9));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr10() {
		return attr10;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr10(String newAttr10) {
		String oldAttr10 = attr10;
		attr10 = newAttr10;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10, oldAttr10, attr10));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr11() {
		return attr11;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr11(String newAttr11) {
		String oldAttr11 = attr11;
		attr11 = newAttr11;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR11, oldAttr11, attr11));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr12() {
		return attr12;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr12(String newAttr12) {
		String oldAttr12 = attr12;
		attr12 = newAttr12;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12, oldAttr12, attr12));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr13() {
		return attr13;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr13(String newAttr13) {
		String oldAttr13 = attr13;
		attr13 = newAttr13;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13, oldAttr13, attr13));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr14() {
		return attr14;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr14(String newAttr14) {
		String oldAttr14 = attr14;
		attr14 = newAttr14;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14, oldAttr14, attr14));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr15() {
		return attr15;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr15(String newAttr15) {
		String oldAttr15 = attr15;
		attr15 = newAttr15;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15, oldAttr15, attr15));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr16() {
		return attr16;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr16(String newAttr16) {
		String oldAttr16 = attr16;
		attr16 = newAttr16;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16, oldAttr16, attr16));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr17() {
		return attr17;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr17(String newAttr17) {
		String oldAttr17 = attr17;
		attr17 = newAttr17;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17, oldAttr17, attr17));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr18() {
		return attr18;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr18(String newAttr18) {
		String oldAttr18 = attr18;
		attr18 = newAttr18;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18, oldAttr18, attr18));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr19() {
		return attr19;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr19(String newAttr19) {
		String oldAttr19 = attr19;
		attr19 = newAttr19;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19, oldAttr19, attr19));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr20() {
		return attr20;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr20(String newAttr20) {
		String oldAttr20 = attr20;
		attr20 = newAttr20;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20, oldAttr20, attr20));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
				return ((InternalEList<?>)getWarehouses()).basicRemove(otherEnd, msgs);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				return ((InternalEList<?>)getContainers()).basicRemove(otherEnd, msgs);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID:
				return getID();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME:
				return getName();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION:
				return getDescription();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE:
				return getType();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
				return getWarehouses();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				return getContainers();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1:
				return getAttr1();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2:
				return getAttr2();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3:
				return getAttr3();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4:
				return getAttr4();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5:
				return getAttr5();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6:
				return getAttr6();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7:
				return getAttr7();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8:
				return getAttr8();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9:
				return getAttr9();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10:
				return getAttr10();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR11:
				return getAttr11();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12:
				return getAttr12();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13:
				return getAttr13();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14:
				return getAttr14();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15:
				return getAttr15();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16:
				return getAttr16();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17:
				return getAttr17();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18:
				return getAttr18();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19:
				return getAttr19();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20:
				return getAttr20();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID:
				setID((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME:
				setName((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE:
				setType((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
				getWarehouses().clear();
				getWarehouses().addAll((Collection<? extends AbstractWarehouse>)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends AbstractContainer>)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1:
				setAttr1((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2:
				setAttr2((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3:
				setAttr3((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4:
				setAttr4((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5:
				setAttr5((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6:
				setAttr6((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7:
				setAttr7((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8:
				setAttr8((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9:
				setAttr9((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10:
				setAttr10((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR11:
				setAttr11((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12:
				setAttr12((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13:
				setAttr13((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14:
				setAttr14((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15:
				setAttr15((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16:
				setAttr16((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17:
				setAttr17((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18:
				setAttr18((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19:
				setAttr19((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20:
				setAttr20((String)newValue);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID:
				setID(ID_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
				getWarehouses().clear();
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				getContainers().clear();
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1:
				setAttr1(ATTR1_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2:
				setAttr2(ATTR2_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3:
				setAttr3(ATTR3_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4:
				setAttr4(ATTR4_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5:
				setAttr5(ATTR5_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6:
				setAttr6(ATTR6_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7:
				setAttr7(ATTR7_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8:
				setAttr8(ATTR8_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9:
				setAttr9(ATTR9_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10:
				setAttr10(ATTR10_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR11:
				setAttr11(ATTR11_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12:
				setAttr12(ATTR12_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13:
				setAttr13(ATTR13_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14:
				setAttr14(ATTR14_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15:
				setAttr15(ATTR15_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16:
				setAttr16(ATTR16_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17:
				setAttr17(ATTR17_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18:
				setAttr18(ATTR18_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19:
				setAttr19(ATTR19_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20:
				setAttr20(ATTR20_EDEFAULT);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__WAREHOUSES:
				return warehouses != null && !warehouses.isEmpty();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR1:
				return ATTR1_EDEFAULT == null ? attr1 != null : !ATTR1_EDEFAULT.equals(attr1);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR2:
				return ATTR2_EDEFAULT == null ? attr2 != null : !ATTR2_EDEFAULT.equals(attr2);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR3:
				return ATTR3_EDEFAULT == null ? attr3 != null : !ATTR3_EDEFAULT.equals(attr3);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR4:
				return ATTR4_EDEFAULT == null ? attr4 != null : !ATTR4_EDEFAULT.equals(attr4);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR5:
				return ATTR5_EDEFAULT == null ? attr5 != null : !ATTR5_EDEFAULT.equals(attr5);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR6:
				return ATTR6_EDEFAULT == null ? attr6 != null : !ATTR6_EDEFAULT.equals(attr6);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR7:
				return ATTR7_EDEFAULT == null ? attr7 != null : !ATTR7_EDEFAULT.equals(attr7);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR8:
				return ATTR8_EDEFAULT == null ? attr8 != null : !ATTR8_EDEFAULT.equals(attr8);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR9:
				return ATTR9_EDEFAULT == null ? attr9 != null : !ATTR9_EDEFAULT.equals(attr9);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR10:
				return ATTR10_EDEFAULT == null ? attr10 != null : !ATTR10_EDEFAULT.equals(attr10);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR11:
				return ATTR11_EDEFAULT == null ? attr11 != null : !ATTR11_EDEFAULT.equals(attr11);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR12:
				return ATTR12_EDEFAULT == null ? attr12 != null : !ATTR12_EDEFAULT.equals(attr12);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR13:
				return ATTR13_EDEFAULT == null ? attr13 != null : !ATTR13_EDEFAULT.equals(attr13);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR14:
				return ATTR14_EDEFAULT == null ? attr14 != null : !ATTR14_EDEFAULT.equals(attr14);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR15:
				return ATTR15_EDEFAULT == null ? attr15 != null : !ATTR15_EDEFAULT.equals(attr15);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR16:
				return ATTR16_EDEFAULT == null ? attr16 != null : !ATTR16_EDEFAULT.equals(attr16);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR17:
				return ATTR17_EDEFAULT == null ? attr17 != null : !ATTR17_EDEFAULT.equals(attr17);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR18:
				return ATTR18_EDEFAULT == null ? attr18 != null : !ATTR18_EDEFAULT.equals(attr18);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR19:
				return ATTR19_EDEFAULT == null ? attr19 != null : !ATTR19_EDEFAULT.equals(attr19);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR20:
				return ATTR20_EDEFAULT == null ? attr20 != null : !ATTR20_EDEFAULT.equals(attr20);
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
		result.append(" (ID: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", type: ");
		result.append(type);
		result.append(", attr1: ");
		result.append(attr1);
		result.append(", attr2: ");
		result.append(attr2);
		result.append(", attr3: ");
		result.append(attr3);
		result.append(", attr4: ");
		result.append(attr4);
		result.append(", attr5: ");
		result.append(attr5);
		result.append(", attr6: ");
		result.append(attr6);
		result.append(", attr7: ");
		result.append(attr7);
		result.append(", attr8: ");
		result.append(attr8);
		result.append(", attr9: ");
		result.append(attr9);
		result.append(", attr10: ");
		result.append(attr10);
		result.append(", attr11: ");
		result.append(attr11);
		result.append(", attr12: ");
		result.append(attr12);
		result.append(", attr13: ");
		result.append(attr13);
		result.append(", attr14: ");
		result.append(attr14);
		result.append(", attr15: ");
		result.append(attr15);
		result.append(", attr16: ");
		result.append(attr16);
		result.append(", attr17: ");
		result.append(attr17);
		result.append(", attr18: ");
		result.append(attr18);
		result.append(", attr19: ");
		result.append(attr19);
		result.append(", attr20: ");
		result.append(attr20);
		result.append(')');
		return result.toString();
	}

} //AbstractWarehouseImpl
