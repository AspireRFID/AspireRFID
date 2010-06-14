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
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAtt11 <em>Att11</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr12 <em>Attr12</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr13 <em>Attr13</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr14 <em>Attr14</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr15 <em>Attr15</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr16 <em>Attr16</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr17 <em>Attr17</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr18 <em>Attr18</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr19 <em>Attr19</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr20 <em>Attr20</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr21 <em>Attr21</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr22 <em>Attr22</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr23 <em>Attr23</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr24 <em>Attr24</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr25 <em>Attr25</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr26 <em>Attr26</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr27 <em>Attr27</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr28 <em>Attr28</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr29 <em>Attr29</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr30 <em>Attr30</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr31 <em>Attr31</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr32 <em>Attr32</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr33 <em>Attr33</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr34 <em>Attr34</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr35 <em>Attr35</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr36 <em>Attr36</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr37 <em>Attr37</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr38 <em>Attr38</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr39 <em>Attr39</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr40 <em>Attr40</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr41 <em>Attr41</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr42 <em>Attr42</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr43 <em>Attr43</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr44 <em>Attr44</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr45 <em>Attr45</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr46 <em>Attr46</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr47 <em>Attr47</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr48 <em>Attr48</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr49 <em>Attr49</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl#getAttr50 <em>Attr50</em>}</li>
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
	 * The default value of the '{@link #getAtt11() <em>Att11</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtt11()
	 * @generated
	 * @ordered
	 */
	protected static final String ATT11_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAtt11() <em>Att11</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtt11()
	 * @generated
	 * @ordered
	 */
	protected String att11 = ATT11_EDEFAULT;

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
	 * The default value of the '{@link #getAttr21() <em>Attr21</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr21()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR21_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr21() <em>Attr21</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr21()
	 * @generated
	 * @ordered
	 */
	protected String attr21 = ATTR21_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr22() <em>Attr22</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr22()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR22_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr22() <em>Attr22</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr22()
	 * @generated
	 * @ordered
	 */
	protected String attr22 = ATTR22_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr23() <em>Attr23</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr23()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR23_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr23() <em>Attr23</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr23()
	 * @generated
	 * @ordered
	 */
	protected String attr23 = ATTR23_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr24() <em>Attr24</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr24()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR24_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr24() <em>Attr24</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr24()
	 * @generated
	 * @ordered
	 */
	protected String attr24 = ATTR24_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr25() <em>Attr25</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr25()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR25_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr25() <em>Attr25</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr25()
	 * @generated
	 * @ordered
	 */
	protected String attr25 = ATTR25_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr26() <em>Attr26</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr26()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR26_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr26() <em>Attr26</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr26()
	 * @generated
	 * @ordered
	 */
	protected String attr26 = ATTR26_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr27() <em>Attr27</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr27()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR27_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr27() <em>Attr27</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr27()
	 * @generated
	 * @ordered
	 */
	protected String attr27 = ATTR27_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr28() <em>Attr28</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr28()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR28_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr28() <em>Attr28</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr28()
	 * @generated
	 * @ordered
	 */
	protected String attr28 = ATTR28_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr29() <em>Attr29</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr29()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR29_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr29() <em>Attr29</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr29()
	 * @generated
	 * @ordered
	 */
	protected String attr29 = ATTR29_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr30() <em>Attr30</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr30()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR30_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr30() <em>Attr30</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr30()
	 * @generated
	 * @ordered
	 */
	protected String attr30 = ATTR30_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr31() <em>Attr31</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr31()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR31_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr31() <em>Attr31</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr31()
	 * @generated
	 * @ordered
	 */
	protected String attr31 = ATTR31_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr32() <em>Attr32</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr32()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR32_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr32() <em>Attr32</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr32()
	 * @generated
	 * @ordered
	 */
	protected String attr32 = ATTR32_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr33() <em>Attr33</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr33()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR33_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr33() <em>Attr33</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr33()
	 * @generated
	 * @ordered
	 */
	protected String attr33 = ATTR33_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr34() <em>Attr34</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr34()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR34_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr34() <em>Attr34</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr34()
	 * @generated
	 * @ordered
	 */
	protected String attr34 = ATTR34_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr35() <em>Attr35</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr35()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR35_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr35() <em>Attr35</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr35()
	 * @generated
	 * @ordered
	 */
	protected String attr35 = ATTR35_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr36() <em>Attr36</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr36()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR36_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr36() <em>Attr36</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr36()
	 * @generated
	 * @ordered
	 */
	protected String attr36 = ATTR36_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr37() <em>Attr37</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr37()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR37_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr37() <em>Attr37</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr37()
	 * @generated
	 * @ordered
	 */
	protected String attr37 = ATTR37_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr38() <em>Attr38</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr38()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR38_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr38() <em>Attr38</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr38()
	 * @generated
	 * @ordered
	 */
	protected String attr38 = ATTR38_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr39() <em>Attr39</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr39()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR39_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr39() <em>Attr39</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr39()
	 * @generated
	 * @ordered
	 */
	protected String attr39 = ATTR39_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr40() <em>Attr40</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr40()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR40_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr40() <em>Attr40</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr40()
	 * @generated
	 * @ordered
	 */
	protected String attr40 = ATTR40_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr41() <em>Attr41</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr41()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR41_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr41() <em>Attr41</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr41()
	 * @generated
	 * @ordered
	 */
	protected String attr41 = ATTR41_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr42() <em>Attr42</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr42()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR42_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr42() <em>Attr42</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr42()
	 * @generated
	 * @ordered
	 */
	protected String attr42 = ATTR42_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr43() <em>Attr43</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr43()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR43_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr43() <em>Attr43</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr43()
	 * @generated
	 * @ordered
	 */
	protected String attr43 = ATTR43_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr44() <em>Attr44</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr44()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR44_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr44() <em>Attr44</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr44()
	 * @generated
	 * @ordered
	 */
	protected String attr44 = ATTR44_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr45() <em>Attr45</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr45()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR45_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr45() <em>Attr45</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr45()
	 * @generated
	 * @ordered
	 */
	protected String attr45 = ATTR45_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr46() <em>Attr46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr46()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR46_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr46() <em>Attr46</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr46()
	 * @generated
	 * @ordered
	 */
	protected String attr46 = ATTR46_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr47() <em>Attr47</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr47()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR47_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr47() <em>Attr47</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr47()
	 * @generated
	 * @ordered
	 */
	protected String attr47 = ATTR47_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr48() <em>Attr48</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr48()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR48_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr48() <em>Attr48</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr48()
	 * @generated
	 * @ordered
	 */
	protected String attr48 = ATTR48_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr49() <em>Attr49</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr49()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR49_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr49() <em>Attr49</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr49()
	 * @generated
	 * @ordered
	 */
	protected String attr49 = ATTR49_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttr50() <em>Attr50</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr50()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTR50_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getAttr50() <em>Attr50</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttr50()
	 * @generated
	 * @ordered
	 */
	protected String attr50 = ATTR50_EDEFAULT;

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
	public String getAtt11() {
		return att11;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAtt11(String newAtt11) {
		String oldAtt11 = att11;
		att11 = newAtt11;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11, oldAtt11, att11));
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
	public String getAttr21() {
		return attr21;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr21(String newAttr21) {
		String oldAttr21 = attr21;
		attr21 = newAttr21;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21, oldAttr21, attr21));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr22() {
		return attr22;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr22(String newAttr22) {
		String oldAttr22 = attr22;
		attr22 = newAttr22;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22, oldAttr22, attr22));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr23() {
		return attr23;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr23(String newAttr23) {
		String oldAttr23 = attr23;
		attr23 = newAttr23;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23, oldAttr23, attr23));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr24() {
		return attr24;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr24(String newAttr24) {
		String oldAttr24 = attr24;
		attr24 = newAttr24;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24, oldAttr24, attr24));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr25() {
		return attr25;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr25(String newAttr25) {
		String oldAttr25 = attr25;
		attr25 = newAttr25;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25, oldAttr25, attr25));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr26() {
		return attr26;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr26(String newAttr26) {
		String oldAttr26 = attr26;
		attr26 = newAttr26;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26, oldAttr26, attr26));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr27() {
		return attr27;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr27(String newAttr27) {
		String oldAttr27 = attr27;
		attr27 = newAttr27;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27, oldAttr27, attr27));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr28() {
		return attr28;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr28(String newAttr28) {
		String oldAttr28 = attr28;
		attr28 = newAttr28;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28, oldAttr28, attr28));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr29() {
		return attr29;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr29(String newAttr29) {
		String oldAttr29 = attr29;
		attr29 = newAttr29;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29, oldAttr29, attr29));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr30() {
		return attr30;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr30(String newAttr30) {
		String oldAttr30 = attr30;
		attr30 = newAttr30;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30, oldAttr30, attr30));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr31() {
		return attr31;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr31(String newAttr31) {
		String oldAttr31 = attr31;
		attr31 = newAttr31;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31, oldAttr31, attr31));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr32() {
		return attr32;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr32(String newAttr32) {
		String oldAttr32 = attr32;
		attr32 = newAttr32;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32, oldAttr32, attr32));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr33() {
		return attr33;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr33(String newAttr33) {
		String oldAttr33 = attr33;
		attr33 = newAttr33;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33, oldAttr33, attr33));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr34() {
		return attr34;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr34(String newAttr34) {
		String oldAttr34 = attr34;
		attr34 = newAttr34;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34, oldAttr34, attr34));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr35() {
		return attr35;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr35(String newAttr35) {
		String oldAttr35 = attr35;
		attr35 = newAttr35;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35, oldAttr35, attr35));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr36() {
		return attr36;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr36(String newAttr36) {
		String oldAttr36 = attr36;
		attr36 = newAttr36;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36, oldAttr36, attr36));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr37() {
		return attr37;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr37(String newAttr37) {
		String oldAttr37 = attr37;
		attr37 = newAttr37;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37, oldAttr37, attr37));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr38() {
		return attr38;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr38(String newAttr38) {
		String oldAttr38 = attr38;
		attr38 = newAttr38;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38, oldAttr38, attr38));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr39() {
		return attr39;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr39(String newAttr39) {
		String oldAttr39 = attr39;
		attr39 = newAttr39;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39, oldAttr39, attr39));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr40() {
		return attr40;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr40(String newAttr40) {
		String oldAttr40 = attr40;
		attr40 = newAttr40;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40, oldAttr40, attr40));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr41() {
		return attr41;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr41(String newAttr41) {
		String oldAttr41 = attr41;
		attr41 = newAttr41;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41, oldAttr41, attr41));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr42() {
		return attr42;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr42(String newAttr42) {
		String oldAttr42 = attr42;
		attr42 = newAttr42;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42, oldAttr42, attr42));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr43() {
		return attr43;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr43(String newAttr43) {
		String oldAttr43 = attr43;
		attr43 = newAttr43;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43, oldAttr43, attr43));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr44() {
		return attr44;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr44(String newAttr44) {
		String oldAttr44 = attr44;
		attr44 = newAttr44;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44, oldAttr44, attr44));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr45() {
		return attr45;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr45(String newAttr45) {
		String oldAttr45 = attr45;
		attr45 = newAttr45;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45, oldAttr45, attr45));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr46() {
		return attr46;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr46(String newAttr46) {
		String oldAttr46 = attr46;
		attr46 = newAttr46;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46, oldAttr46, attr46));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr47() {
		return attr47;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr47(String newAttr47) {
		String oldAttr47 = attr47;
		attr47 = newAttr47;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47, oldAttr47, attr47));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr48() {
		return attr48;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr48(String newAttr48) {
		String oldAttr48 = attr48;
		attr48 = newAttr48;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48, oldAttr48, attr48));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr49() {
		return attr49;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr49(String newAttr49) {
		String oldAttr49 = attr49;
		attr49 = newAttr49;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49, oldAttr49, attr49));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttr50() {
		return attr50;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttr50(String newAttr50) {
		String oldAttr50 = attr50;
		attr50 = newAttr50;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50, oldAttr50, attr50));
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11:
				return getAtt11();
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21:
				return getAttr21();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22:
				return getAttr22();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23:
				return getAttr23();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24:
				return getAttr24();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25:
				return getAttr25();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26:
				return getAttr26();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27:
				return getAttr27();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28:
				return getAttr28();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29:
				return getAttr29();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30:
				return getAttr30();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31:
				return getAttr31();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32:
				return getAttr32();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33:
				return getAttr33();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34:
				return getAttr34();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35:
				return getAttr35();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36:
				return getAttr36();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37:
				return getAttr37();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38:
				return getAttr38();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39:
				return getAttr39();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40:
				return getAttr40();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41:
				return getAttr41();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42:
				return getAttr42();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43:
				return getAttr43();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44:
				return getAttr44();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45:
				return getAttr45();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46:
				return getAttr46();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47:
				return getAttr47();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48:
				return getAttr48();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49:
				return getAttr49();
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50:
				return getAttr50();
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11:
				setAtt11((String)newValue);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21:
				setAttr21((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22:
				setAttr22((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23:
				setAttr23((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24:
				setAttr24((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25:
				setAttr25((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26:
				setAttr26((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27:
				setAttr27((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28:
				setAttr28((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29:
				setAttr29((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30:
				setAttr30((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31:
				setAttr31((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32:
				setAttr32((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33:
				setAttr33((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34:
				setAttr34((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35:
				setAttr35((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36:
				setAttr36((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37:
				setAttr37((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38:
				setAttr38((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39:
				setAttr39((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40:
				setAttr40((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41:
				setAttr41((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42:
				setAttr42((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43:
				setAttr43((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44:
				setAttr44((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45:
				setAttr45((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46:
				setAttr46((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47:
				setAttr47((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48:
				setAttr48((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49:
				setAttr49((String)newValue);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50:
				setAttr50((String)newValue);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11:
				setAtt11(ATT11_EDEFAULT);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21:
				setAttr21(ATTR21_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22:
				setAttr22(ATTR22_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23:
				setAttr23(ATTR23_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24:
				setAttr24(ATTR24_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25:
				setAttr25(ATTR25_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26:
				setAttr26(ATTR26_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27:
				setAttr27(ATTR27_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28:
				setAttr28(ATTR28_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29:
				setAttr29(ATTR29_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30:
				setAttr30(ATTR30_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31:
				setAttr31(ATTR31_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32:
				setAttr32(ATTR32_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33:
				setAttr33(ATTR33_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34:
				setAttr34(ATTR34_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35:
				setAttr35(ATTR35_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36:
				setAttr36(ATTR36_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37:
				setAttr37(ATTR37_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38:
				setAttr38(ATTR38_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39:
				setAttr39(ATTR39_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40:
				setAttr40(ATTR40_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41:
				setAttr41(ATTR41_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42:
				setAttr42(ATTR42_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43:
				setAttr43(ATTR43_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44:
				setAttr44(ATTR44_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45:
				setAttr45(ATTR45_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46:
				setAttr46(ATTR46_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47:
				setAttr47(ATTR47_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48:
				setAttr48(ATTR48_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49:
				setAttr49(ATTR49_EDEFAULT);
				return;
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50:
				setAttr50(ATTR50_EDEFAULT);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATT11:
				return ATT11_EDEFAULT == null ? att11 != null : !ATT11_EDEFAULT.equals(att11);
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
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR21:
				return ATTR21_EDEFAULT == null ? attr21 != null : !ATTR21_EDEFAULT.equals(attr21);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR22:
				return ATTR22_EDEFAULT == null ? attr22 != null : !ATTR22_EDEFAULT.equals(attr22);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR23:
				return ATTR23_EDEFAULT == null ? attr23 != null : !ATTR23_EDEFAULT.equals(attr23);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR24:
				return ATTR24_EDEFAULT == null ? attr24 != null : !ATTR24_EDEFAULT.equals(attr24);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR25:
				return ATTR25_EDEFAULT == null ? attr25 != null : !ATTR25_EDEFAULT.equals(attr25);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR26:
				return ATTR26_EDEFAULT == null ? attr26 != null : !ATTR26_EDEFAULT.equals(attr26);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR27:
				return ATTR27_EDEFAULT == null ? attr27 != null : !ATTR27_EDEFAULT.equals(attr27);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR28:
				return ATTR28_EDEFAULT == null ? attr28 != null : !ATTR28_EDEFAULT.equals(attr28);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR29:
				return ATTR29_EDEFAULT == null ? attr29 != null : !ATTR29_EDEFAULT.equals(attr29);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR30:
				return ATTR30_EDEFAULT == null ? attr30 != null : !ATTR30_EDEFAULT.equals(attr30);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR31:
				return ATTR31_EDEFAULT == null ? attr31 != null : !ATTR31_EDEFAULT.equals(attr31);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR32:
				return ATTR32_EDEFAULT == null ? attr32 != null : !ATTR32_EDEFAULT.equals(attr32);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR33:
				return ATTR33_EDEFAULT == null ? attr33 != null : !ATTR33_EDEFAULT.equals(attr33);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR34:
				return ATTR34_EDEFAULT == null ? attr34 != null : !ATTR34_EDEFAULT.equals(attr34);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR35:
				return ATTR35_EDEFAULT == null ? attr35 != null : !ATTR35_EDEFAULT.equals(attr35);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR36:
				return ATTR36_EDEFAULT == null ? attr36 != null : !ATTR36_EDEFAULT.equals(attr36);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR37:
				return ATTR37_EDEFAULT == null ? attr37 != null : !ATTR37_EDEFAULT.equals(attr37);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR38:
				return ATTR38_EDEFAULT == null ? attr38 != null : !ATTR38_EDEFAULT.equals(attr38);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR39:
				return ATTR39_EDEFAULT == null ? attr39 != null : !ATTR39_EDEFAULT.equals(attr39);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR40:
				return ATTR40_EDEFAULT == null ? attr40 != null : !ATTR40_EDEFAULT.equals(attr40);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR41:
				return ATTR41_EDEFAULT == null ? attr41 != null : !ATTR41_EDEFAULT.equals(attr41);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR42:
				return ATTR42_EDEFAULT == null ? attr42 != null : !ATTR42_EDEFAULT.equals(attr42);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR43:
				return ATTR43_EDEFAULT == null ? attr43 != null : !ATTR43_EDEFAULT.equals(attr43);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR44:
				return ATTR44_EDEFAULT == null ? attr44 != null : !ATTR44_EDEFAULT.equals(attr44);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR45:
				return ATTR45_EDEFAULT == null ? attr45 != null : !ATTR45_EDEFAULT.equals(attr45);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR46:
				return ATTR46_EDEFAULT == null ? attr46 != null : !ATTR46_EDEFAULT.equals(attr46);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR47:
				return ATTR47_EDEFAULT == null ? attr47 != null : !ATTR47_EDEFAULT.equals(attr47);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR48:
				return ATTR48_EDEFAULT == null ? attr48 != null : !ATTR48_EDEFAULT.equals(attr48);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR49:
				return ATTR49_EDEFAULT == null ? attr49 != null : !ATTR49_EDEFAULT.equals(attr49);
			case MasterDataEditorGMFPackage.ABSTRACT_WAREHOUSE__ATTR50:
				return ATTR50_EDEFAULT == null ? attr50 != null : !ATTR50_EDEFAULT.equals(attr50);
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
		result.append(", att11: ");
		result.append(att11);
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
		result.append(", attr21: ");
		result.append(attr21);
		result.append(", attr22: ");
		result.append(attr22);
		result.append(", attr23: ");
		result.append(attr23);
		result.append(", attr24: ");
		result.append(attr24);
		result.append(", attr25: ");
		result.append(attr25);
		result.append(", attr26: ");
		result.append(attr26);
		result.append(", attr27: ");
		result.append(attr27);
		result.append(", attr28: ");
		result.append(attr28);
		result.append(", attr29: ");
		result.append(attr29);
		result.append(", attr30: ");
		result.append(attr30);
		result.append(", attr31: ");
		result.append(attr31);
		result.append(", attr32: ");
		result.append(attr32);
		result.append(", attr33: ");
		result.append(attr33);
		result.append(", attr34: ");
		result.append(attr34);
		result.append(", attr35: ");
		result.append(attr35);
		result.append(", attr36: ");
		result.append(attr36);
		result.append(", attr37: ");
		result.append(attr37);
		result.append(", attr38: ");
		result.append(attr38);
		result.append(", attr39: ");
		result.append(attr39);
		result.append(", attr40: ");
		result.append(attr40);
		result.append(", attr41: ");
		result.append(attr41);
		result.append(", attr42: ");
		result.append(attr42);
		result.append(", attr43: ");
		result.append(attr43);
		result.append(", attr44: ");
		result.append(attr44);
		result.append(", attr45: ");
		result.append(attr45);
		result.append(", attr46: ");
		result.append(attr46);
		result.append(", attr47: ");
		result.append(attr47);
		result.append(", attr48: ");
		result.append(attr48);
		result.append(", attr49: ");
		result.append(attr49);
		result.append(", attr50: ");
		result.append(attr50);
		result.append(')');
		return result.toString();
	}

} //AbstractWarehouseImpl
