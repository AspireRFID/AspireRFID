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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFFactory
 * @model kind="package"
 * @generated
 */
public interface MasterDataEditorGMFPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "MasterDataEditorGMF";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://MasterDataEditorGMF";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "MasterDataEditorGMF";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MasterDataEditorGMFPackage eINSTANCE = org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl <em>Company</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getCompany()
	 * @generated
	 */
	int COMPANY = 0;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ADDRESS = 2;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__COUNTRY = 3;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__CITY = 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__DESCRIPTION = 5;

	/**
	 * The feature id for the '<em><b>Company Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__COMPANY_WAREHOUSES = 6;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR1 = 7;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR2 = 8;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR3 = 9;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR4 = 10;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR5 = 11;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR6 = 12;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR7 = 13;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR8 = 14;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR9 = 15;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR10 = 16;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATT11 = 17;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR12 = 18;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR13 = 19;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR14 = 20;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR15 = 21;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR16 = 22;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR17 = 23;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR18 = 24;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR19 = 25;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR20 = 26;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR21 = 27;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR22 = 28;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR23 = 29;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR24 = 30;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR25 = 31;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR26 = 32;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR27 = 33;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR28 = 34;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR29 = 35;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR30 = 36;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR31 = 37;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR32 = 38;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR33 = 39;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR34 = 40;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR35 = 41;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR36 = 42;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR37 = 43;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR38 = 44;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR39 = 45;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR40 = 46;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR41 = 47;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR42 = 48;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR43 = 49;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR44 = 50;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR45 = 51;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR46 = 52;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR47 = 53;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR48 = 54;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR49 = 55;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY__ATTR50 = 56;

	/**
	 * The number of structural features of the '<em>Company</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPANY_FEATURE_COUNT = 57;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl <em>Abstract Warehouse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractWarehouse()
	 * @generated
	 */
	int ABSTRACT_WAREHOUSE = 1;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__WAREHOUSES = 4;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__CONTAINERS = 5;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR1 = 6;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR2 = 7;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR3 = 8;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR4 = 9;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR5 = 10;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR6 = 11;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR7 = 12;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR8 = 13;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR9 = 14;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR10 = 15;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATT11 = 16;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR12 = 17;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR13 = 18;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR14 = 19;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR15 = 20;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR16 = 21;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR17 = 22;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR18 = 23;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR19 = 24;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR20 = 25;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR21 = 26;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR22 = 27;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR23 = 28;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR24 = 29;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR25 = 30;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR26 = 31;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR27 = 32;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR28 = 33;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR29 = 34;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR30 = 35;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR31 = 36;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR32 = 37;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR33 = 38;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR34 = 39;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR35 = 40;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR36 = 41;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR37 = 42;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR38 = 43;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR39 = 44;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR40 = 45;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR41 = 46;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR42 = 47;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR43 = 48;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR44 = 49;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR45 = 50;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR46 = 51;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR47 = 52;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR48 = 53;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR49 = 54;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE__ATTR50 = 55;

	/**
	 * The number of structural features of the '<em>Abstract Warehouse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_WAREHOUSE_FEATURE_COUNT = 56;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl <em>Biz Loc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getBizLoc()
	 * @generated
	 */
	int BIZ_LOC = 2;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR1 = ABSTRACT_WAREHOUSE__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR2 = ABSTRACT_WAREHOUSE__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR3 = ABSTRACT_WAREHOUSE__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR4 = ABSTRACT_WAREHOUSE__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR5 = ABSTRACT_WAREHOUSE__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR6 = ABSTRACT_WAREHOUSE__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR7 = ABSTRACT_WAREHOUSE__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR8 = ABSTRACT_WAREHOUSE__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR9 = ABSTRACT_WAREHOUSE__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR10 = ABSTRACT_WAREHOUSE__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATT11 = ABSTRACT_WAREHOUSE__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR12 = ABSTRACT_WAREHOUSE__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR13 = ABSTRACT_WAREHOUSE__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR14 = ABSTRACT_WAREHOUSE__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR15 = ABSTRACT_WAREHOUSE__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR16 = ABSTRACT_WAREHOUSE__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR17 = ABSTRACT_WAREHOUSE__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR18 = ABSTRACT_WAREHOUSE__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR19 = ABSTRACT_WAREHOUSE__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR20 = ABSTRACT_WAREHOUSE__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR21 = ABSTRACT_WAREHOUSE__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR22 = ABSTRACT_WAREHOUSE__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR23 = ABSTRACT_WAREHOUSE__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR24 = ABSTRACT_WAREHOUSE__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR25 = ABSTRACT_WAREHOUSE__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR26 = ABSTRACT_WAREHOUSE__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR27 = ABSTRACT_WAREHOUSE__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR28 = ABSTRACT_WAREHOUSE__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR29 = ABSTRACT_WAREHOUSE__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR30 = ABSTRACT_WAREHOUSE__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR31 = ABSTRACT_WAREHOUSE__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR32 = ABSTRACT_WAREHOUSE__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR33 = ABSTRACT_WAREHOUSE__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR34 = ABSTRACT_WAREHOUSE__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR35 = ABSTRACT_WAREHOUSE__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR36 = ABSTRACT_WAREHOUSE__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR37 = ABSTRACT_WAREHOUSE__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR38 = ABSTRACT_WAREHOUSE__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR39 = ABSTRACT_WAREHOUSE__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR40 = ABSTRACT_WAREHOUSE__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR41 = ABSTRACT_WAREHOUSE__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR42 = ABSTRACT_WAREHOUSE__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR43 = ABSTRACT_WAREHOUSE__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR44 = ABSTRACT_WAREHOUSE__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR45 = ABSTRACT_WAREHOUSE__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR46 = ABSTRACT_WAREHOUSE__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR47 = ABSTRACT_WAREHOUSE__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR48 = ABSTRACT_WAREHOUSE__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR49 = ABSTRACT_WAREHOUSE__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC__ATTR50 = ABSTRACT_WAREHOUSE__ATTR50;

	/**
	 * The number of structural features of the '<em>Biz Loc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BIZ_LOC_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl <em>Warehouse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getWarehouse()
	 * @generated
	 */
	int WAREHOUSE = 3;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR1 = ABSTRACT_WAREHOUSE__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR2 = ABSTRACT_WAREHOUSE__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR3 = ABSTRACT_WAREHOUSE__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR4 = ABSTRACT_WAREHOUSE__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR5 = ABSTRACT_WAREHOUSE__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR6 = ABSTRACT_WAREHOUSE__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR7 = ABSTRACT_WAREHOUSE__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR8 = ABSTRACT_WAREHOUSE__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR9 = ABSTRACT_WAREHOUSE__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR10 = ABSTRACT_WAREHOUSE__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATT11 = ABSTRACT_WAREHOUSE__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR12 = ABSTRACT_WAREHOUSE__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR13 = ABSTRACT_WAREHOUSE__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR14 = ABSTRACT_WAREHOUSE__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR15 = ABSTRACT_WAREHOUSE__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR16 = ABSTRACT_WAREHOUSE__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR17 = ABSTRACT_WAREHOUSE__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR18 = ABSTRACT_WAREHOUSE__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR19 = ABSTRACT_WAREHOUSE__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR20 = ABSTRACT_WAREHOUSE__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR21 = ABSTRACT_WAREHOUSE__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR22 = ABSTRACT_WAREHOUSE__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR23 = ABSTRACT_WAREHOUSE__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR24 = ABSTRACT_WAREHOUSE__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR25 = ABSTRACT_WAREHOUSE__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR26 = ABSTRACT_WAREHOUSE__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR27 = ABSTRACT_WAREHOUSE__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR28 = ABSTRACT_WAREHOUSE__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR29 = ABSTRACT_WAREHOUSE__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR30 = ABSTRACT_WAREHOUSE__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR31 = ABSTRACT_WAREHOUSE__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR32 = ABSTRACT_WAREHOUSE__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR33 = ABSTRACT_WAREHOUSE__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR34 = ABSTRACT_WAREHOUSE__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR35 = ABSTRACT_WAREHOUSE__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR36 = ABSTRACT_WAREHOUSE__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR37 = ABSTRACT_WAREHOUSE__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR38 = ABSTRACT_WAREHOUSE__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR39 = ABSTRACT_WAREHOUSE__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR40 = ABSTRACT_WAREHOUSE__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR41 = ABSTRACT_WAREHOUSE__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR42 = ABSTRACT_WAREHOUSE__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR43 = ABSTRACT_WAREHOUSE__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR44 = ABSTRACT_WAREHOUSE__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR45 = ABSTRACT_WAREHOUSE__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR46 = ABSTRACT_WAREHOUSE__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR47 = ABSTRACT_WAREHOUSE__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR48 = ABSTRACT_WAREHOUSE__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR49 = ABSTRACT_WAREHOUSE__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE__ATTR50 = ABSTRACT_WAREHOUSE__ATTR50;

	/**
	 * The number of structural features of the '<em>Warehouse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl <em>Room</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getRoom()
	 * @generated
	 */
	int ROOM = 4;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR1 = ABSTRACT_WAREHOUSE__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR2 = ABSTRACT_WAREHOUSE__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR3 = ABSTRACT_WAREHOUSE__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR4 = ABSTRACT_WAREHOUSE__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR5 = ABSTRACT_WAREHOUSE__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR6 = ABSTRACT_WAREHOUSE__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR7 = ABSTRACT_WAREHOUSE__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR8 = ABSTRACT_WAREHOUSE__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR9 = ABSTRACT_WAREHOUSE__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR10 = ABSTRACT_WAREHOUSE__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATT11 = ABSTRACT_WAREHOUSE__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR12 = ABSTRACT_WAREHOUSE__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR13 = ABSTRACT_WAREHOUSE__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR14 = ABSTRACT_WAREHOUSE__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR15 = ABSTRACT_WAREHOUSE__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR16 = ABSTRACT_WAREHOUSE__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR17 = ABSTRACT_WAREHOUSE__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR18 = ABSTRACT_WAREHOUSE__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR19 = ABSTRACT_WAREHOUSE__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR20 = ABSTRACT_WAREHOUSE__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR21 = ABSTRACT_WAREHOUSE__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR22 = ABSTRACT_WAREHOUSE__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR23 = ABSTRACT_WAREHOUSE__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR24 = ABSTRACT_WAREHOUSE__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR25 = ABSTRACT_WAREHOUSE__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR26 = ABSTRACT_WAREHOUSE__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR27 = ABSTRACT_WAREHOUSE__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR28 = ABSTRACT_WAREHOUSE__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR29 = ABSTRACT_WAREHOUSE__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR30 = ABSTRACT_WAREHOUSE__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR31 = ABSTRACT_WAREHOUSE__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR32 = ABSTRACT_WAREHOUSE__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR33 = ABSTRACT_WAREHOUSE__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR34 = ABSTRACT_WAREHOUSE__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR35 = ABSTRACT_WAREHOUSE__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR36 = ABSTRACT_WAREHOUSE__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR37 = ABSTRACT_WAREHOUSE__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR38 = ABSTRACT_WAREHOUSE__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR39 = ABSTRACT_WAREHOUSE__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR40 = ABSTRACT_WAREHOUSE__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR41 = ABSTRACT_WAREHOUSE__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR42 = ABSTRACT_WAREHOUSE__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR43 = ABSTRACT_WAREHOUSE__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR44 = ABSTRACT_WAREHOUSE__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR45 = ABSTRACT_WAREHOUSE__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR46 = ABSTRACT_WAREHOUSE__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR47 = ABSTRACT_WAREHOUSE__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR48 = ABSTRACT_WAREHOUSE__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR49 = ABSTRACT_WAREHOUSE__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM__ATTR50 = ABSTRACT_WAREHOUSE__ATTR50;

	/**
	 * The number of structural features of the '<em>Room</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOM_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl <em>Section</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getSection()
	 * @generated
	 */
	int SECTION = 5;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ID = ABSTRACT_WAREHOUSE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__NAME = ABSTRACT_WAREHOUSE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__DESCRIPTION = ABSTRACT_WAREHOUSE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__TYPE = ABSTRACT_WAREHOUSE__TYPE;

	/**
	 * The feature id for the '<em><b>Warehouses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__WAREHOUSES = ABSTRACT_WAREHOUSE__WAREHOUSES;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__CONTAINERS = ABSTRACT_WAREHOUSE__CONTAINERS;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR1 = ABSTRACT_WAREHOUSE__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR2 = ABSTRACT_WAREHOUSE__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR3 = ABSTRACT_WAREHOUSE__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR4 = ABSTRACT_WAREHOUSE__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR5 = ABSTRACT_WAREHOUSE__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR6 = ABSTRACT_WAREHOUSE__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR7 = ABSTRACT_WAREHOUSE__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR8 = ABSTRACT_WAREHOUSE__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR9 = ABSTRACT_WAREHOUSE__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR10 = ABSTRACT_WAREHOUSE__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATT11 = ABSTRACT_WAREHOUSE__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR12 = ABSTRACT_WAREHOUSE__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR13 = ABSTRACT_WAREHOUSE__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR14 = ABSTRACT_WAREHOUSE__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR15 = ABSTRACT_WAREHOUSE__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR16 = ABSTRACT_WAREHOUSE__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR17 = ABSTRACT_WAREHOUSE__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR18 = ABSTRACT_WAREHOUSE__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR19 = ABSTRACT_WAREHOUSE__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR20 = ABSTRACT_WAREHOUSE__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR21 = ABSTRACT_WAREHOUSE__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR22 = ABSTRACT_WAREHOUSE__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR23 = ABSTRACT_WAREHOUSE__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR24 = ABSTRACT_WAREHOUSE__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR25 = ABSTRACT_WAREHOUSE__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR26 = ABSTRACT_WAREHOUSE__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR27 = ABSTRACT_WAREHOUSE__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR28 = ABSTRACT_WAREHOUSE__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR29 = ABSTRACT_WAREHOUSE__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR30 = ABSTRACT_WAREHOUSE__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR31 = ABSTRACT_WAREHOUSE__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR32 = ABSTRACT_WAREHOUSE__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR33 = ABSTRACT_WAREHOUSE__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR34 = ABSTRACT_WAREHOUSE__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR35 = ABSTRACT_WAREHOUSE__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR36 = ABSTRACT_WAREHOUSE__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR37 = ABSTRACT_WAREHOUSE__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR38 = ABSTRACT_WAREHOUSE__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR39 = ABSTRACT_WAREHOUSE__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR40 = ABSTRACT_WAREHOUSE__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR41 = ABSTRACT_WAREHOUSE__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR42 = ABSTRACT_WAREHOUSE__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR43 = ABSTRACT_WAREHOUSE__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR44 = ABSTRACT_WAREHOUSE__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR45 = ABSTRACT_WAREHOUSE__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR46 = ABSTRACT_WAREHOUSE__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR47 = ABSTRACT_WAREHOUSE__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR48 = ABSTRACT_WAREHOUSE__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR49 = ABSTRACT_WAREHOUSE__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION__ATTR50 = ABSTRACT_WAREHOUSE__ATTR50;

	/**
	 * The number of structural features of the '<em>Section</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_FEATURE_COUNT = ABSTRACT_WAREHOUSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl <em>Abstract Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractContainer()
	 * @generated
	 */
	int ABSTRACT_CONTAINER = 6;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__READ_POINT = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR1 = 5;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR2 = 6;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR3 = 7;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR4 = 8;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR5 = 9;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR6 = 10;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR7 = 11;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR8 = 12;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR9 = 13;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR10 = 14;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATT11 = 15;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR12 = 16;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR13 = 17;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR14 = 18;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR15 = 19;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR16 = 20;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR17 = 21;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR18 = 22;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR19 = 23;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR20 = 24;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR21 = 25;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR22 = 26;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR23 = 27;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR24 = 28;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR25 = 29;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR26 = 30;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR27 = 31;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR28 = 32;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR29 = 33;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR30 = 34;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR31 = 35;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR32 = 36;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR33 = 37;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR34 = 38;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR35 = 39;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR36 = 40;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR37 = 41;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR38 = 42;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR39 = 43;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR40 = 44;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR41 = 45;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR42 = 46;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR43 = 47;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR44 = 48;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR45 = 49;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR46 = 50;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR47 = 51;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR48 = 52;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR49 = 53;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER__ATTR50 = 54;

	/**
	 * The number of structural features of the '<em>Abstract Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTAINER_FEATURE_COUNT = 55;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl <em>Read Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getReadPoint()
	 * @generated
	 */
	int READ_POINT = 7;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Read Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int READ_POINT_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 8;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl <em>Conveyor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getConveyor()
	 * @generated
	 */
	int CONVEYOR = 9;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Conveyor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVEYOR_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl <em>Shelf</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getShelf()
	 * @generated
	 */
	int SHELF = 10;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Shelf</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHELF_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl <em>Gate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getGate()
	 * @generated
	 */
	int GATE = 11;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Gate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl <em>Pallet Truck</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPalletTruck()
	 * @generated
	 */
	int PALLET_TRUCK = 12;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Pallet Truck</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALLET_TRUCK_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl <em>Push Arm</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPushArm()
	 * @generated
	 */
	int PUSH_ARM = 13;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Push Arm</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_ARM_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl <em>Hand Held Reader</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getHandHeldReader()
	 * @generated
	 */
	int HAND_HELD_READER = 14;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Hand Held Reader</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAND_HELD_READER_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl <em>Container1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer1()
	 * @generated
	 */
	int CONTAINER1 = 15;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER1_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl <em>Container2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer2()
	 * @generated
	 */
	int CONTAINER2 = 16;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER2_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl <em>Container3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer3()
	 * @generated
	 */
	int CONTAINER3 = 17;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER3_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl <em>Container4</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer4()
	 * @generated
	 */
	int CONTAINER4 = 18;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container4</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER4_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl <em>Container5</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer5()
	 * @generated
	 */
	int CONTAINER5 = 19;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container5</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER5_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl <em>Container6</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer6()
	 * @generated
	 */
	int CONTAINER6 = 20;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container6</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER6_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl <em>Container7</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer7()
	 * @generated
	 */
	int CONTAINER7 = 21;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container7</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER7_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl <em>Container8</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer8()
	 * @generated
	 */
	int CONTAINER8 = 22;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container8</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER8_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl <em>Container9</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer9()
	 * @generated
	 */
	int CONTAINER9 = 23;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container9</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER9_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl <em>Container10</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer10()
	 * @generated
	 */
	int CONTAINER10 = 24;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ID = ABSTRACT_CONTAINER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__NAME = ABSTRACT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__DESCRIPTION = ABSTRACT_CONTAINER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Read Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__READ_POINT = ABSTRACT_CONTAINER__READ_POINT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__TYPE = ABSTRACT_CONTAINER__TYPE;

	/**
	 * The feature id for the '<em><b>Attr1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR1 = ABSTRACT_CONTAINER__ATTR1;

	/**
	 * The feature id for the '<em><b>Attr2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR2 = ABSTRACT_CONTAINER__ATTR2;

	/**
	 * The feature id for the '<em><b>Attr3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR3 = ABSTRACT_CONTAINER__ATTR3;

	/**
	 * The feature id for the '<em><b>Attr4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR4 = ABSTRACT_CONTAINER__ATTR4;

	/**
	 * The feature id for the '<em><b>Attr5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR5 = ABSTRACT_CONTAINER__ATTR5;

	/**
	 * The feature id for the '<em><b>Attr6</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR6 = ABSTRACT_CONTAINER__ATTR6;

	/**
	 * The feature id for the '<em><b>Attr7</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR7 = ABSTRACT_CONTAINER__ATTR7;

	/**
	 * The feature id for the '<em><b>Attr8</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR8 = ABSTRACT_CONTAINER__ATTR8;

	/**
	 * The feature id for the '<em><b>Attr9</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR9 = ABSTRACT_CONTAINER__ATTR9;

	/**
	 * The feature id for the '<em><b>Attr10</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR10 = ABSTRACT_CONTAINER__ATTR10;

	/**
	 * The feature id for the '<em><b>Att11</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATT11 = ABSTRACT_CONTAINER__ATT11;

	/**
	 * The feature id for the '<em><b>Attr12</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR12 = ABSTRACT_CONTAINER__ATTR12;

	/**
	 * The feature id for the '<em><b>Attr13</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR13 = ABSTRACT_CONTAINER__ATTR13;

	/**
	 * The feature id for the '<em><b>Attr14</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR14 = ABSTRACT_CONTAINER__ATTR14;

	/**
	 * The feature id for the '<em><b>Attr15</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR15 = ABSTRACT_CONTAINER__ATTR15;

	/**
	 * The feature id for the '<em><b>Attr16</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR16 = ABSTRACT_CONTAINER__ATTR16;

	/**
	 * The feature id for the '<em><b>Attr17</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR17 = ABSTRACT_CONTAINER__ATTR17;

	/**
	 * The feature id for the '<em><b>Attr18</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR18 = ABSTRACT_CONTAINER__ATTR18;

	/**
	 * The feature id for the '<em><b>Attr19</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR19 = ABSTRACT_CONTAINER__ATTR19;

	/**
	 * The feature id for the '<em><b>Attr20</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR20 = ABSTRACT_CONTAINER__ATTR20;

	/**
	 * The feature id for the '<em><b>Attr21</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR21 = ABSTRACT_CONTAINER__ATTR21;

	/**
	 * The feature id for the '<em><b>Attr22</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR22 = ABSTRACT_CONTAINER__ATTR22;

	/**
	 * The feature id for the '<em><b>Attr23</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR23 = ABSTRACT_CONTAINER__ATTR23;

	/**
	 * The feature id for the '<em><b>Attr24</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR24 = ABSTRACT_CONTAINER__ATTR24;

	/**
	 * The feature id for the '<em><b>Attr25</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR25 = ABSTRACT_CONTAINER__ATTR25;

	/**
	 * The feature id for the '<em><b>Attr26</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR26 = ABSTRACT_CONTAINER__ATTR26;

	/**
	 * The feature id for the '<em><b>Attr27</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR27 = ABSTRACT_CONTAINER__ATTR27;

	/**
	 * The feature id for the '<em><b>Attr28</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR28 = ABSTRACT_CONTAINER__ATTR28;

	/**
	 * The feature id for the '<em><b>Attr29</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR29 = ABSTRACT_CONTAINER__ATTR29;

	/**
	 * The feature id for the '<em><b>Attr30</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR30 = ABSTRACT_CONTAINER__ATTR30;

	/**
	 * The feature id for the '<em><b>Attr31</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR31 = ABSTRACT_CONTAINER__ATTR31;

	/**
	 * The feature id for the '<em><b>Attr32</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR32 = ABSTRACT_CONTAINER__ATTR32;

	/**
	 * The feature id for the '<em><b>Attr33</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR33 = ABSTRACT_CONTAINER__ATTR33;

	/**
	 * The feature id for the '<em><b>Attr34</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR34 = ABSTRACT_CONTAINER__ATTR34;

	/**
	 * The feature id for the '<em><b>Attr35</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR35 = ABSTRACT_CONTAINER__ATTR35;

	/**
	 * The feature id for the '<em><b>Attr36</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR36 = ABSTRACT_CONTAINER__ATTR36;

	/**
	 * The feature id for the '<em><b>Attr37</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR37 = ABSTRACT_CONTAINER__ATTR37;

	/**
	 * The feature id for the '<em><b>Attr38</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR38 = ABSTRACT_CONTAINER__ATTR38;

	/**
	 * The feature id for the '<em><b>Attr39</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR39 = ABSTRACT_CONTAINER__ATTR39;

	/**
	 * The feature id for the '<em><b>Attr40</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR40 = ABSTRACT_CONTAINER__ATTR40;

	/**
	 * The feature id for the '<em><b>Attr41</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR41 = ABSTRACT_CONTAINER__ATTR41;

	/**
	 * The feature id for the '<em><b>Attr42</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR42 = ABSTRACT_CONTAINER__ATTR42;

	/**
	 * The feature id for the '<em><b>Attr43</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR43 = ABSTRACT_CONTAINER__ATTR43;

	/**
	 * The feature id for the '<em><b>Attr44</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR44 = ABSTRACT_CONTAINER__ATTR44;

	/**
	 * The feature id for the '<em><b>Attr45</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR45 = ABSTRACT_CONTAINER__ATTR45;

	/**
	 * The feature id for the '<em><b>Attr46</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR46 = ABSTRACT_CONTAINER__ATTR46;

	/**
	 * The feature id for the '<em><b>Attr47</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR47 = ABSTRACT_CONTAINER__ATTR47;

	/**
	 * The feature id for the '<em><b>Attr48</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR48 = ABSTRACT_CONTAINER__ATTR48;

	/**
	 * The feature id for the '<em><b>Attr49</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR49 = ABSTRACT_CONTAINER__ATTR49;

	/**
	 * The feature id for the '<em><b>Attr50</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10__ATTR50 = ABSTRACT_CONTAINER__ATTR50;

	/**
	 * The number of structural features of the '<em>Container10</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER10_FEATURE_COUNT = ABSTRACT_CONTAINER_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company
	 * @generated
	 */
	EClass getCompany();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getID()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getName()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAddress()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Address();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCountry()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Country();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCity()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_City();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getDescription()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCompanyWarehouses <em>Company Warehouses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Company Warehouses</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getCompanyWarehouses()
	 * @see #getCompany()
	 * @generated
	 */
	EReference getCompany_CompanyWarehouses();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr1 <em>Attr1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr1</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr1()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr1();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr2 <em>Attr2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr2</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr2()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr2();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr3 <em>Attr3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr3</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr3()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr3();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr4 <em>Attr4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr4</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr4()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr4();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr5 <em>Attr5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr5</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr5()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr5();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr6 <em>Attr6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr6</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr6()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr6();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr7 <em>Attr7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr7</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr7()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr7();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr8 <em>Attr8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr8</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr8()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr8();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr9 <em>Attr9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr9</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr9()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr9();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr10 <em>Attr10</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr10</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr10()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr10();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAtt11 <em>Att11</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Att11</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAtt11()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Att11();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr12 <em>Attr12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr12</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr12()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr12();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr13 <em>Attr13</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr13</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr13()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr13();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr14 <em>Attr14</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr14</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr14()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr14();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr15 <em>Attr15</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr15</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr15()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr15();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr16 <em>Attr16</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr16</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr16()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr16();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr17 <em>Attr17</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr17</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr17()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr17();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr18 <em>Attr18</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr18</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr18()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr18();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr19 <em>Attr19</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr19</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr19()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr19();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr20 <em>Attr20</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr20</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr20()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr20();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr21 <em>Attr21</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr21</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr21()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr21();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr22 <em>Attr22</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr22</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr22()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr22();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr23 <em>Attr23</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr23</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr23()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr23();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr24 <em>Attr24</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr24</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr24()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr24();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr25 <em>Attr25</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr25</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr25()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr25();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr26 <em>Attr26</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr26</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr26()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr26();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr27 <em>Attr27</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr27</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr27()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr27();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr28 <em>Attr28</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr28</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr28()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr28();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr29 <em>Attr29</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr29</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr29()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr29();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr30 <em>Attr30</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr30</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr30()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr30();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr31 <em>Attr31</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr31</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr31()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr31();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr32 <em>Attr32</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr32</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr32()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr32();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr33 <em>Attr33</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr33</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr33()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr33();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr34 <em>Attr34</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr34</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr34()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr34();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr35 <em>Attr35</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr35</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr35()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr35();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr36 <em>Attr36</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr36</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr36()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr36();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr37 <em>Attr37</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr37</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr37()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr37();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr38 <em>Attr38</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr38</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr38()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr38();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr39 <em>Attr39</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr39</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr39()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr39();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr40 <em>Attr40</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr40</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr40()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr40();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr41 <em>Attr41</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr41</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr41()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr41();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr42 <em>Attr42</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr42</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr42()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr42();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr43 <em>Attr43</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr43</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr43()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr43();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr44 <em>Attr44</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr44</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr44()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr44();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr45 <em>Attr45</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr45</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr45()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr45();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr46 <em>Attr46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr46</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr46()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr46();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr47 <em>Attr47</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr47</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr47()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr47();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr48 <em>Attr48</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr48</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr48()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr48();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr49 <em>Attr49</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr49</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr49()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr49();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr50 <em>Attr50</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr50</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Company#getAttr50()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Attr50();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse <em>Abstract Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Warehouse</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse
	 * @generated
	 */
	EClass getAbstractWarehouse();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getID()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getName()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getDescription()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getType()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getWarehouses <em>Warehouses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Warehouses</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getWarehouses()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EReference getAbstractWarehouse_Warehouses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getContainers()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EReference getAbstractWarehouse_Containers();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr1 <em>Attr1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr1</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr1()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr1();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr2 <em>Attr2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr2</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr2()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr2();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr3 <em>Attr3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr3</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr3()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr3();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr4 <em>Attr4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr4</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr4()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr4();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr5 <em>Attr5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr5</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr5()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr5();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr6 <em>Attr6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr6</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr6()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr6();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr7 <em>Attr7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr7</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr7()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr7();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr8 <em>Attr8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr8</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr8()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr8();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr9 <em>Attr9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr9</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr9()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr9();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr10 <em>Attr10</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr10</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr10()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr10();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAtt11 <em>Att11</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Att11</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAtt11()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Att11();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr12 <em>Attr12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr12</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr12()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr12();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr13 <em>Attr13</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr13</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr13()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr13();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr14 <em>Attr14</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr14</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr14()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr14();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr15 <em>Attr15</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr15</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr15()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr15();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr16 <em>Attr16</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr16</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr16()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr16();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr17 <em>Attr17</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr17</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr17()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr17();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr18 <em>Attr18</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr18</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr18()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr18();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr19 <em>Attr19</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr19</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr19()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr19();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr20 <em>Attr20</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr20</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr20()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr20();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr21 <em>Attr21</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr21</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr21()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr21();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr22 <em>Attr22</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr22</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr22()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr22();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr23 <em>Attr23</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr23</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr23()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr23();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr24 <em>Attr24</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr24</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr24()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr24();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr25 <em>Attr25</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr25</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr25()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr25();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr26 <em>Attr26</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr26</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr26()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr26();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr27 <em>Attr27</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr27</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr27()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr27();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr28 <em>Attr28</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr28</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr28()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr28();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr29 <em>Attr29</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr29</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr29()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr29();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr30 <em>Attr30</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr30</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr30()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr30();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr31 <em>Attr31</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr31</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr31()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr31();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr32 <em>Attr32</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr32</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr32()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr32();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr33 <em>Attr33</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr33</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr33()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr33();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr34 <em>Attr34</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr34</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr34()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr34();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr35 <em>Attr35</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr35</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr35()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr35();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr36 <em>Attr36</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr36</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr36()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr36();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr37 <em>Attr37</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr37</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr37()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr37();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr38 <em>Attr38</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr38</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr38()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr38();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr39 <em>Attr39</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr39</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr39()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr39();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr40 <em>Attr40</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr40</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr40()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr40();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr41 <em>Attr41</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr41</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr41()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr41();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr42 <em>Attr42</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr42</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr42()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr42();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr43 <em>Attr43</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr43</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr43()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr43();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr44 <em>Attr44</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr44</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr44()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr44();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr45 <em>Attr45</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr45</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr45()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr45();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr46 <em>Attr46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr46</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr46()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr46();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr47 <em>Attr47</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr47</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr47()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr47();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr48 <em>Attr48</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr48</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr48()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr48();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr49 <em>Attr49</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr49</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr49()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr49();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr50 <em>Attr50</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr50</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractWarehouse#getAttr50()
	 * @see #getAbstractWarehouse()
	 * @generated
	 */
	EAttribute getAbstractWarehouse_Attr50();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc <em>Biz Loc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Biz Loc</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.BizLoc
	 * @generated
	 */
	EClass getBizLoc();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse <em>Warehouse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Warehouse
	 * @generated
	 */
	EClass getWarehouse();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room <em>Room</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Room</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Room
	 * @generated
	 */
	EClass getRoom();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section <em>Section</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Section
	 * @generated
	 */
	EClass getSection();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer <em>Abstract Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Container</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer
	 * @generated
	 */
	EClass getAbstractContainer();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getID()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_ID();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getName()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getDescription()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint <em>Read Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Point</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#isReadPoint()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_ReadPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getType()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr1 <em>Attr1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr1</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr1()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr1();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr2 <em>Attr2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr2</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr2()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr2();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr3 <em>Attr3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr3</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr3()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr3();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr4 <em>Attr4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr4</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr4()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr4();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr5 <em>Attr5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr5</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr5()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr5();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr6 <em>Attr6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr6</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr6()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr6();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr7 <em>Attr7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr7</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr7()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr7();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr8 <em>Attr8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr8</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr8()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr8();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr9 <em>Attr9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr9</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr9()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr9();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr10 <em>Attr10</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr10</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr10()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr10();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAtt11 <em>Att11</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Att11</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAtt11()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Att11();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr12 <em>Attr12</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr12</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr12()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr12();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr13 <em>Attr13</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr13</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr13()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr13();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr14 <em>Attr14</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr14</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr14()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr14();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr15 <em>Attr15</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr15</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr15()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr15();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr16 <em>Attr16</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr16</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr16()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr16();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr17 <em>Attr17</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr17</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr17()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr17();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr18 <em>Attr18</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr18</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr18()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr18();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr19 <em>Attr19</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr19</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr19()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr19();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr20 <em>Attr20</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr20</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr20()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr20();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr21 <em>Attr21</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr21</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr21()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr21();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr22 <em>Attr22</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr22</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr22()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr22();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr23 <em>Attr23</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr23</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr23()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr23();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr24 <em>Attr24</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr24</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr24()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr24();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr25 <em>Attr25</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr25</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr25()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr25();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr26 <em>Attr26</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr26</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr26()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr26();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr27 <em>Attr27</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr27</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr27()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr27();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr28 <em>Attr28</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr28</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr28()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr28();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr29 <em>Attr29</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr29</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr29()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr29();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr30 <em>Attr30</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr30</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr30()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr30();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr31 <em>Attr31</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr31</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr31()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr31();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr32 <em>Attr32</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr32</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr32()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr32();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr33 <em>Attr33</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr33</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr33()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr33();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr34 <em>Attr34</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr34</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr34()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr34();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr35 <em>Attr35</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr35</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr35()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr35();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr36 <em>Attr36</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr36</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr36()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr36();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr37 <em>Attr37</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr37</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr37()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr37();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr38 <em>Attr38</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr38</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr38()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr38();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr39 <em>Attr39</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr39</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr39()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr39();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr40 <em>Attr40</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr40</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr40()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr40();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr41 <em>Attr41</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr41</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr41()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr41();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr42 <em>Attr42</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr42</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr42()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr42();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr43 <em>Attr43</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr43</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr43()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr43();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr44 <em>Attr44</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr44</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr44()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr44();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr45 <em>Attr45</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr45</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr45()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr45();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr46 <em>Attr46</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr46</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr46()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr46();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr47 <em>Attr47</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr47</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr47()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr47();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr48 <em>Attr48</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr48</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr48()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr48();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr49 <em>Attr49</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr49</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr49()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr49();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr50 <em>Attr50</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attr50</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.AbstractContainer#getAttr50()
	 * @see #getAbstractContainer()
	 * @generated
	 */
	EAttribute getAbstractContainer_Attr50();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint <em>Read Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Read Point</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.ReadPoint
	 * @generated
	 */
	EClass getReadPoint();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor <em>Conveyor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conveyor</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Conveyor
	 * @generated
	 */
	EClass getConveyor();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf <em>Shelf</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shelf</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Shelf
	 * @generated
	 */
	EClass getShelf();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate <em>Gate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gate</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Gate
	 * @generated
	 */
	EClass getGate();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck <em>Pallet Truck</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pallet Truck</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PalletTruck
	 * @generated
	 */
	EClass getPalletTruck();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm <em>Push Arm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Arm</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.PushArm
	 * @generated
	 */
	EClass getPushArm();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader <em>Hand Held Reader</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hand Held Reader</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.HandHeldReader
	 * @generated
	 */
	EClass getHandHeldReader();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1 <em>Container1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container1</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container1
	 * @generated
	 */
	EClass getContainer1();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2 <em>Container2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container2</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container2
	 * @generated
	 */
	EClass getContainer2();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3 <em>Container3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container3</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container3
	 * @generated
	 */
	EClass getContainer3();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4 <em>Container4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container4</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container4
	 * @generated
	 */
	EClass getContainer4();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5 <em>Container5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container5</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container5
	 * @generated
	 */
	EClass getContainer5();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6 <em>Container6</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container6</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container6
	 * @generated
	 */
	EClass getContainer6();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7 <em>Container7</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container7</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container7
	 * @generated
	 */
	EClass getContainer7();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8 <em>Container8</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container8</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container8
	 * @generated
	 */
	EClass getContainer8();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9 <em>Container9</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container9</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container9
	 * @generated
	 */
	EClass getContainer9();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10 <em>Container10</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container10</em>'.
	 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.Container10
	 * @generated
	 */
	EClass getContainer10();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MasterDataEditorGMFFactory getMasterDataEditorGMFFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl <em>Company</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.CompanyImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getCompany()
		 * @generated
		 */
		EClass COMPANY = eINSTANCE.getCompany();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ID = eINSTANCE.getCompany_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__NAME = eINSTANCE.getCompany_Name();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ADDRESS = eINSTANCE.getCompany_Address();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__COUNTRY = eINSTANCE.getCompany_Country();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__CITY = eINSTANCE.getCompany_City();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__DESCRIPTION = eINSTANCE.getCompany_Description();

		/**
		 * The meta object literal for the '<em><b>Company Warehouses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPANY__COMPANY_WAREHOUSES = eINSTANCE.getCompany_CompanyWarehouses();

		/**
		 * The meta object literal for the '<em><b>Attr1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR1 = eINSTANCE.getCompany_Attr1();

		/**
		 * The meta object literal for the '<em><b>Attr2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR2 = eINSTANCE.getCompany_Attr2();

		/**
		 * The meta object literal for the '<em><b>Attr3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR3 = eINSTANCE.getCompany_Attr3();

		/**
		 * The meta object literal for the '<em><b>Attr4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR4 = eINSTANCE.getCompany_Attr4();

		/**
		 * The meta object literal for the '<em><b>Attr5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR5 = eINSTANCE.getCompany_Attr5();

		/**
		 * The meta object literal for the '<em><b>Attr6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR6 = eINSTANCE.getCompany_Attr6();

		/**
		 * The meta object literal for the '<em><b>Attr7</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR7 = eINSTANCE.getCompany_Attr7();

		/**
		 * The meta object literal for the '<em><b>Attr8</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR8 = eINSTANCE.getCompany_Attr8();

		/**
		 * The meta object literal for the '<em><b>Attr9</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR9 = eINSTANCE.getCompany_Attr9();

		/**
		 * The meta object literal for the '<em><b>Attr10</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR10 = eINSTANCE.getCompany_Attr10();

		/**
		 * The meta object literal for the '<em><b>Att11</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATT11 = eINSTANCE.getCompany_Att11();

		/**
		 * The meta object literal for the '<em><b>Attr12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR12 = eINSTANCE.getCompany_Attr12();

		/**
		 * The meta object literal for the '<em><b>Attr13</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR13 = eINSTANCE.getCompany_Attr13();

		/**
		 * The meta object literal for the '<em><b>Attr14</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR14 = eINSTANCE.getCompany_Attr14();

		/**
		 * The meta object literal for the '<em><b>Attr15</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR15 = eINSTANCE.getCompany_Attr15();

		/**
		 * The meta object literal for the '<em><b>Attr16</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR16 = eINSTANCE.getCompany_Attr16();

		/**
		 * The meta object literal for the '<em><b>Attr17</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR17 = eINSTANCE.getCompany_Attr17();

		/**
		 * The meta object literal for the '<em><b>Attr18</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR18 = eINSTANCE.getCompany_Attr18();

		/**
		 * The meta object literal for the '<em><b>Attr19</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR19 = eINSTANCE.getCompany_Attr19();

		/**
		 * The meta object literal for the '<em><b>Attr20</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR20 = eINSTANCE.getCompany_Attr20();

		/**
		 * The meta object literal for the '<em><b>Attr21</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR21 = eINSTANCE.getCompany_Attr21();

		/**
		 * The meta object literal for the '<em><b>Attr22</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR22 = eINSTANCE.getCompany_Attr22();

		/**
		 * The meta object literal for the '<em><b>Attr23</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR23 = eINSTANCE.getCompany_Attr23();

		/**
		 * The meta object literal for the '<em><b>Attr24</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR24 = eINSTANCE.getCompany_Attr24();

		/**
		 * The meta object literal for the '<em><b>Attr25</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR25 = eINSTANCE.getCompany_Attr25();

		/**
		 * The meta object literal for the '<em><b>Attr26</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR26 = eINSTANCE.getCompany_Attr26();

		/**
		 * The meta object literal for the '<em><b>Attr27</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR27 = eINSTANCE.getCompany_Attr27();

		/**
		 * The meta object literal for the '<em><b>Attr28</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR28 = eINSTANCE.getCompany_Attr28();

		/**
		 * The meta object literal for the '<em><b>Attr29</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR29 = eINSTANCE.getCompany_Attr29();

		/**
		 * The meta object literal for the '<em><b>Attr30</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR30 = eINSTANCE.getCompany_Attr30();

		/**
		 * The meta object literal for the '<em><b>Attr31</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR31 = eINSTANCE.getCompany_Attr31();

		/**
		 * The meta object literal for the '<em><b>Attr32</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR32 = eINSTANCE.getCompany_Attr32();

		/**
		 * The meta object literal for the '<em><b>Attr33</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR33 = eINSTANCE.getCompany_Attr33();

		/**
		 * The meta object literal for the '<em><b>Attr34</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR34 = eINSTANCE.getCompany_Attr34();

		/**
		 * The meta object literal for the '<em><b>Attr35</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR35 = eINSTANCE.getCompany_Attr35();

		/**
		 * The meta object literal for the '<em><b>Attr36</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR36 = eINSTANCE.getCompany_Attr36();

		/**
		 * The meta object literal for the '<em><b>Attr37</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR37 = eINSTANCE.getCompany_Attr37();

		/**
		 * The meta object literal for the '<em><b>Attr38</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR38 = eINSTANCE.getCompany_Attr38();

		/**
		 * The meta object literal for the '<em><b>Attr39</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR39 = eINSTANCE.getCompany_Attr39();

		/**
		 * The meta object literal for the '<em><b>Attr40</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR40 = eINSTANCE.getCompany_Attr40();

		/**
		 * The meta object literal for the '<em><b>Attr41</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR41 = eINSTANCE.getCompany_Attr41();

		/**
		 * The meta object literal for the '<em><b>Attr42</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR42 = eINSTANCE.getCompany_Attr42();

		/**
		 * The meta object literal for the '<em><b>Attr43</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR43 = eINSTANCE.getCompany_Attr43();

		/**
		 * The meta object literal for the '<em><b>Attr44</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR44 = eINSTANCE.getCompany_Attr44();

		/**
		 * The meta object literal for the '<em><b>Attr45</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR45 = eINSTANCE.getCompany_Attr45();

		/**
		 * The meta object literal for the '<em><b>Attr46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR46 = eINSTANCE.getCompany_Attr46();

		/**
		 * The meta object literal for the '<em><b>Attr47</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR47 = eINSTANCE.getCompany_Attr47();

		/**
		 * The meta object literal for the '<em><b>Attr48</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR48 = eINSTANCE.getCompany_Attr48();

		/**
		 * The meta object literal for the '<em><b>Attr49</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR49 = eINSTANCE.getCompany_Attr49();

		/**
		 * The meta object literal for the '<em><b>Attr50</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__ATTR50 = eINSTANCE.getCompany_Attr50();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl <em>Abstract Warehouse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractWarehouseImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractWarehouse()
		 * @generated
		 */
		EClass ABSTRACT_WAREHOUSE = eINSTANCE.getAbstractWarehouse();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ID = eINSTANCE.getAbstractWarehouse_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__NAME = eINSTANCE.getAbstractWarehouse_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__DESCRIPTION = eINSTANCE.getAbstractWarehouse_Description();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__TYPE = eINSTANCE.getAbstractWarehouse_Type();

		/**
		 * The meta object literal for the '<em><b>Warehouses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_WAREHOUSE__WAREHOUSES = eINSTANCE.getAbstractWarehouse_Warehouses();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_WAREHOUSE__CONTAINERS = eINSTANCE.getAbstractWarehouse_Containers();

		/**
		 * The meta object literal for the '<em><b>Attr1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR1 = eINSTANCE.getAbstractWarehouse_Attr1();

		/**
		 * The meta object literal for the '<em><b>Attr2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR2 = eINSTANCE.getAbstractWarehouse_Attr2();

		/**
		 * The meta object literal for the '<em><b>Attr3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR3 = eINSTANCE.getAbstractWarehouse_Attr3();

		/**
		 * The meta object literal for the '<em><b>Attr4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR4 = eINSTANCE.getAbstractWarehouse_Attr4();

		/**
		 * The meta object literal for the '<em><b>Attr5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR5 = eINSTANCE.getAbstractWarehouse_Attr5();

		/**
		 * The meta object literal for the '<em><b>Attr6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR6 = eINSTANCE.getAbstractWarehouse_Attr6();

		/**
		 * The meta object literal for the '<em><b>Attr7</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR7 = eINSTANCE.getAbstractWarehouse_Attr7();

		/**
		 * The meta object literal for the '<em><b>Attr8</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR8 = eINSTANCE.getAbstractWarehouse_Attr8();

		/**
		 * The meta object literal for the '<em><b>Attr9</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR9 = eINSTANCE.getAbstractWarehouse_Attr9();

		/**
		 * The meta object literal for the '<em><b>Attr10</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR10 = eINSTANCE.getAbstractWarehouse_Attr10();

		/**
		 * The meta object literal for the '<em><b>Att11</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATT11 = eINSTANCE.getAbstractWarehouse_Att11();

		/**
		 * The meta object literal for the '<em><b>Attr12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR12 = eINSTANCE.getAbstractWarehouse_Attr12();

		/**
		 * The meta object literal for the '<em><b>Attr13</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR13 = eINSTANCE.getAbstractWarehouse_Attr13();

		/**
		 * The meta object literal for the '<em><b>Attr14</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR14 = eINSTANCE.getAbstractWarehouse_Attr14();

		/**
		 * The meta object literal for the '<em><b>Attr15</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR15 = eINSTANCE.getAbstractWarehouse_Attr15();

		/**
		 * The meta object literal for the '<em><b>Attr16</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR16 = eINSTANCE.getAbstractWarehouse_Attr16();

		/**
		 * The meta object literal for the '<em><b>Attr17</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR17 = eINSTANCE.getAbstractWarehouse_Attr17();

		/**
		 * The meta object literal for the '<em><b>Attr18</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR18 = eINSTANCE.getAbstractWarehouse_Attr18();

		/**
		 * The meta object literal for the '<em><b>Attr19</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR19 = eINSTANCE.getAbstractWarehouse_Attr19();

		/**
		 * The meta object literal for the '<em><b>Attr20</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR20 = eINSTANCE.getAbstractWarehouse_Attr20();

		/**
		 * The meta object literal for the '<em><b>Attr21</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR21 = eINSTANCE.getAbstractWarehouse_Attr21();

		/**
		 * The meta object literal for the '<em><b>Attr22</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR22 = eINSTANCE.getAbstractWarehouse_Attr22();

		/**
		 * The meta object literal for the '<em><b>Attr23</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR23 = eINSTANCE.getAbstractWarehouse_Attr23();

		/**
		 * The meta object literal for the '<em><b>Attr24</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR24 = eINSTANCE.getAbstractWarehouse_Attr24();

		/**
		 * The meta object literal for the '<em><b>Attr25</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR25 = eINSTANCE.getAbstractWarehouse_Attr25();

		/**
		 * The meta object literal for the '<em><b>Attr26</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR26 = eINSTANCE.getAbstractWarehouse_Attr26();

		/**
		 * The meta object literal for the '<em><b>Attr27</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR27 = eINSTANCE.getAbstractWarehouse_Attr27();

		/**
		 * The meta object literal for the '<em><b>Attr28</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR28 = eINSTANCE.getAbstractWarehouse_Attr28();

		/**
		 * The meta object literal for the '<em><b>Attr29</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR29 = eINSTANCE.getAbstractWarehouse_Attr29();

		/**
		 * The meta object literal for the '<em><b>Attr30</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR30 = eINSTANCE.getAbstractWarehouse_Attr30();

		/**
		 * The meta object literal for the '<em><b>Attr31</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR31 = eINSTANCE.getAbstractWarehouse_Attr31();

		/**
		 * The meta object literal for the '<em><b>Attr32</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR32 = eINSTANCE.getAbstractWarehouse_Attr32();

		/**
		 * The meta object literal for the '<em><b>Attr33</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR33 = eINSTANCE.getAbstractWarehouse_Attr33();

		/**
		 * The meta object literal for the '<em><b>Attr34</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR34 = eINSTANCE.getAbstractWarehouse_Attr34();

		/**
		 * The meta object literal for the '<em><b>Attr35</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR35 = eINSTANCE.getAbstractWarehouse_Attr35();

		/**
		 * The meta object literal for the '<em><b>Attr36</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR36 = eINSTANCE.getAbstractWarehouse_Attr36();

		/**
		 * The meta object literal for the '<em><b>Attr37</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR37 = eINSTANCE.getAbstractWarehouse_Attr37();

		/**
		 * The meta object literal for the '<em><b>Attr38</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR38 = eINSTANCE.getAbstractWarehouse_Attr38();

		/**
		 * The meta object literal for the '<em><b>Attr39</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR39 = eINSTANCE.getAbstractWarehouse_Attr39();

		/**
		 * The meta object literal for the '<em><b>Attr40</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR40 = eINSTANCE.getAbstractWarehouse_Attr40();

		/**
		 * The meta object literal for the '<em><b>Attr41</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR41 = eINSTANCE.getAbstractWarehouse_Attr41();

		/**
		 * The meta object literal for the '<em><b>Attr42</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR42 = eINSTANCE.getAbstractWarehouse_Attr42();

		/**
		 * The meta object literal for the '<em><b>Attr43</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR43 = eINSTANCE.getAbstractWarehouse_Attr43();

		/**
		 * The meta object literal for the '<em><b>Attr44</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR44 = eINSTANCE.getAbstractWarehouse_Attr44();

		/**
		 * The meta object literal for the '<em><b>Attr45</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR45 = eINSTANCE.getAbstractWarehouse_Attr45();

		/**
		 * The meta object literal for the '<em><b>Attr46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR46 = eINSTANCE.getAbstractWarehouse_Attr46();

		/**
		 * The meta object literal for the '<em><b>Attr47</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR47 = eINSTANCE.getAbstractWarehouse_Attr47();

		/**
		 * The meta object literal for the '<em><b>Attr48</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR48 = eINSTANCE.getAbstractWarehouse_Attr48();

		/**
		 * The meta object literal for the '<em><b>Attr49</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR49 = eINSTANCE.getAbstractWarehouse_Attr49();

		/**
		 * The meta object literal for the '<em><b>Attr50</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_WAREHOUSE__ATTR50 = eINSTANCE.getAbstractWarehouse_Attr50();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl <em>Biz Loc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.BizLocImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getBizLoc()
		 * @generated
		 */
		EClass BIZ_LOC = eINSTANCE.getBizLoc();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl <em>Warehouse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.WarehouseImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getWarehouse()
		 * @generated
		 */
		EClass WAREHOUSE = eINSTANCE.getWarehouse();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl <em>Room</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.RoomImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getRoom()
		 * @generated
		 */
		EClass ROOM = eINSTANCE.getRoom();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl <em>Section</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.SectionImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getSection()
		 * @generated
		 */
		EClass SECTION = eINSTANCE.getSection();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl <em>Abstract Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.AbstractContainerImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getAbstractContainer()
		 * @generated
		 */
		EClass ABSTRACT_CONTAINER = eINSTANCE.getAbstractContainer();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ID = eINSTANCE.getAbstractContainer_ID();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__NAME = eINSTANCE.getAbstractContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__DESCRIPTION = eINSTANCE.getAbstractContainer_Description();

		/**
		 * The meta object literal for the '<em><b>Read Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__READ_POINT = eINSTANCE.getAbstractContainer_ReadPoint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__TYPE = eINSTANCE.getAbstractContainer_Type();

		/**
		 * The meta object literal for the '<em><b>Attr1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR1 = eINSTANCE.getAbstractContainer_Attr1();

		/**
		 * The meta object literal for the '<em><b>Attr2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR2 = eINSTANCE.getAbstractContainer_Attr2();

		/**
		 * The meta object literal for the '<em><b>Attr3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR3 = eINSTANCE.getAbstractContainer_Attr3();

		/**
		 * The meta object literal for the '<em><b>Attr4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR4 = eINSTANCE.getAbstractContainer_Attr4();

		/**
		 * The meta object literal for the '<em><b>Attr5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR5 = eINSTANCE.getAbstractContainer_Attr5();

		/**
		 * The meta object literal for the '<em><b>Attr6</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR6 = eINSTANCE.getAbstractContainer_Attr6();

		/**
		 * The meta object literal for the '<em><b>Attr7</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR7 = eINSTANCE.getAbstractContainer_Attr7();

		/**
		 * The meta object literal for the '<em><b>Attr8</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR8 = eINSTANCE.getAbstractContainer_Attr8();

		/**
		 * The meta object literal for the '<em><b>Attr9</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR9 = eINSTANCE.getAbstractContainer_Attr9();

		/**
		 * The meta object literal for the '<em><b>Attr10</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR10 = eINSTANCE.getAbstractContainer_Attr10();

		/**
		 * The meta object literal for the '<em><b>Att11</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATT11 = eINSTANCE.getAbstractContainer_Att11();

		/**
		 * The meta object literal for the '<em><b>Attr12</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR12 = eINSTANCE.getAbstractContainer_Attr12();

		/**
		 * The meta object literal for the '<em><b>Attr13</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR13 = eINSTANCE.getAbstractContainer_Attr13();

		/**
		 * The meta object literal for the '<em><b>Attr14</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR14 = eINSTANCE.getAbstractContainer_Attr14();

		/**
		 * The meta object literal for the '<em><b>Attr15</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR15 = eINSTANCE.getAbstractContainer_Attr15();

		/**
		 * The meta object literal for the '<em><b>Attr16</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR16 = eINSTANCE.getAbstractContainer_Attr16();

		/**
		 * The meta object literal for the '<em><b>Attr17</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR17 = eINSTANCE.getAbstractContainer_Attr17();

		/**
		 * The meta object literal for the '<em><b>Attr18</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR18 = eINSTANCE.getAbstractContainer_Attr18();

		/**
		 * The meta object literal for the '<em><b>Attr19</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR19 = eINSTANCE.getAbstractContainer_Attr19();

		/**
		 * The meta object literal for the '<em><b>Attr20</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR20 = eINSTANCE.getAbstractContainer_Attr20();

		/**
		 * The meta object literal for the '<em><b>Attr21</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR21 = eINSTANCE.getAbstractContainer_Attr21();

		/**
		 * The meta object literal for the '<em><b>Attr22</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR22 = eINSTANCE.getAbstractContainer_Attr22();

		/**
		 * The meta object literal for the '<em><b>Attr23</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR23 = eINSTANCE.getAbstractContainer_Attr23();

		/**
		 * The meta object literal for the '<em><b>Attr24</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR24 = eINSTANCE.getAbstractContainer_Attr24();

		/**
		 * The meta object literal for the '<em><b>Attr25</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR25 = eINSTANCE.getAbstractContainer_Attr25();

		/**
		 * The meta object literal for the '<em><b>Attr26</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR26 = eINSTANCE.getAbstractContainer_Attr26();

		/**
		 * The meta object literal for the '<em><b>Attr27</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR27 = eINSTANCE.getAbstractContainer_Attr27();

		/**
		 * The meta object literal for the '<em><b>Attr28</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR28 = eINSTANCE.getAbstractContainer_Attr28();

		/**
		 * The meta object literal for the '<em><b>Attr29</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR29 = eINSTANCE.getAbstractContainer_Attr29();

		/**
		 * The meta object literal for the '<em><b>Attr30</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR30 = eINSTANCE.getAbstractContainer_Attr30();

		/**
		 * The meta object literal for the '<em><b>Attr31</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR31 = eINSTANCE.getAbstractContainer_Attr31();

		/**
		 * The meta object literal for the '<em><b>Attr32</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR32 = eINSTANCE.getAbstractContainer_Attr32();

		/**
		 * The meta object literal for the '<em><b>Attr33</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR33 = eINSTANCE.getAbstractContainer_Attr33();

		/**
		 * The meta object literal for the '<em><b>Attr34</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR34 = eINSTANCE.getAbstractContainer_Attr34();

		/**
		 * The meta object literal for the '<em><b>Attr35</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR35 = eINSTANCE.getAbstractContainer_Attr35();

		/**
		 * The meta object literal for the '<em><b>Attr36</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR36 = eINSTANCE.getAbstractContainer_Attr36();

		/**
		 * The meta object literal for the '<em><b>Attr37</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR37 = eINSTANCE.getAbstractContainer_Attr37();

		/**
		 * The meta object literal for the '<em><b>Attr38</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR38 = eINSTANCE.getAbstractContainer_Attr38();

		/**
		 * The meta object literal for the '<em><b>Attr39</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR39 = eINSTANCE.getAbstractContainer_Attr39();

		/**
		 * The meta object literal for the '<em><b>Attr40</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR40 = eINSTANCE.getAbstractContainer_Attr40();

		/**
		 * The meta object literal for the '<em><b>Attr41</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR41 = eINSTANCE.getAbstractContainer_Attr41();

		/**
		 * The meta object literal for the '<em><b>Attr42</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR42 = eINSTANCE.getAbstractContainer_Attr42();

		/**
		 * The meta object literal for the '<em><b>Attr43</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR43 = eINSTANCE.getAbstractContainer_Attr43();

		/**
		 * The meta object literal for the '<em><b>Attr44</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR44 = eINSTANCE.getAbstractContainer_Attr44();

		/**
		 * The meta object literal for the '<em><b>Attr45</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR45 = eINSTANCE.getAbstractContainer_Attr45();

		/**
		 * The meta object literal for the '<em><b>Attr46</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR46 = eINSTANCE.getAbstractContainer_Attr46();

		/**
		 * The meta object literal for the '<em><b>Attr47</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR47 = eINSTANCE.getAbstractContainer_Attr47();

		/**
		 * The meta object literal for the '<em><b>Attr48</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR48 = eINSTANCE.getAbstractContainer_Attr48();

		/**
		 * The meta object literal for the '<em><b>Attr49</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR49 = eINSTANCE.getAbstractContainer_Attr49();

		/**
		 * The meta object literal for the '<em><b>Attr50</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTAINER__ATTR50 = eINSTANCE.getAbstractContainer_Attr50();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl <em>Read Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ReadPointImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getReadPoint()
		 * @generated
		 */
		EClass READ_POINT = eINSTANCE.getReadPoint();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ContainerImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl <em>Conveyor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ConveyorImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getConveyor()
		 * @generated
		 */
		EClass CONVEYOR = eINSTANCE.getConveyor();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl <em>Shelf</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.ShelfImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getShelf()
		 * @generated
		 */
		EClass SHELF = eINSTANCE.getShelf();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl <em>Gate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.GateImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getGate()
		 * @generated
		 */
		EClass GATE = eINSTANCE.getGate();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl <em>Pallet Truck</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PalletTruckImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPalletTruck()
		 * @generated
		 */
		EClass PALLET_TRUCK = eINSTANCE.getPalletTruck();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl <em>Push Arm</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.PushArmImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getPushArm()
		 * @generated
		 */
		EClass PUSH_ARM = eINSTANCE.getPushArm();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl <em>Hand Held Reader</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.HandHeldReaderImpl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getHandHeldReader()
		 * @generated
		 */
		EClass HAND_HELD_READER = eINSTANCE.getHandHeldReader();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl <em>Container1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container1Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer1()
		 * @generated
		 */
		EClass CONTAINER1 = eINSTANCE.getContainer1();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl <em>Container2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container2Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer2()
		 * @generated
		 */
		EClass CONTAINER2 = eINSTANCE.getContainer2();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl <em>Container3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container3Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer3()
		 * @generated
		 */
		EClass CONTAINER3 = eINSTANCE.getContainer3();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl <em>Container4</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container4Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer4()
		 * @generated
		 */
		EClass CONTAINER4 = eINSTANCE.getContainer4();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl <em>Container5</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container5Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer5()
		 * @generated
		 */
		EClass CONTAINER5 = eINSTANCE.getContainer5();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl <em>Container6</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container6Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer6()
		 * @generated
		 */
		EClass CONTAINER6 = eINSTANCE.getContainer6();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl <em>Container7</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container7Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer7()
		 * @generated
		 */
		EClass CONTAINER7 = eINSTANCE.getContainer7();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl <em>Container8</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container8Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer8()
		 * @generated
		 */
		EClass CONTAINER8 = eINSTANCE.getContainer8();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl <em>Container9</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container9Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer9()
		 * @generated
		 */
		EClass CONTAINER9 = eINSTANCE.getContainer9();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl <em>Container10</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.Container10Impl
		 * @see org.ow2.aspirerfid.ide.MasterDataEditorGMF.impl.MasterDataEditorGMFPackageImpl#getContainer10()
		 * @generated
		 */
		EClass CONTAINER10 = eINSTANCE.getContainer10();

	}

} //MasterDataEditorGMFPackage
