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

package org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class MasterDataEditorGMFElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private MasterDataEditorGMFElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Company_79 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Company_79"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BizLoc_1001 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.BizLoc_1001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Warehouse_1002 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Warehouse_1002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Room_1003 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Room_1003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Section_1004 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Section_1004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BizLoc_2001 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.BizLoc_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Warehouse_2002 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Warehouse_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Room_2003 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Room_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Section_2004 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Section_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ReadPoint_2005 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.ReadPoint_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Container_2006 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Container_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Conveyor_2007 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Conveyor_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Shelf_2008 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Shelf_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Gate_2009 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.Gate_2009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType PalletTruck_2010 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.PalletTruck_2010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType PushArm_2011 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.PushArm_2011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HandHeldReader_2012 = getElementType("aspireRfidIde-MasterDataEditorGMF.diagram.HandHeldReader_2012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorPlugin
						.getInstance().getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements
					.put(
							Company_79,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getCompany());

			elements
					.put(
							BizLoc_1001,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getBizLoc());

			elements
					.put(
							Warehouse_1002,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getWarehouse());

			elements
					.put(
							Room_1003,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getRoom());

			elements
					.put(
							Section_1004,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getSection());

			elements
					.put(
							BizLoc_2001,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getBizLoc());

			elements
					.put(
							Warehouse_2002,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getWarehouse());

			elements
					.put(
							Room_2003,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getRoom());

			elements
					.put(
							Section_2004,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getSection());

			elements
					.put(
							ReadPoint_2005,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getReadPoint());

			elements
					.put(
							Container_2006,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getContainer());

			elements
					.put(
							Conveyor_2007,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getConveyor());

			elements
					.put(
							Shelf_2008,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getShelf());

			elements
					.put(
							Gate_2009,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getGate());

			elements
					.put(
							PalletTruck_2010,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getPalletTruck());

			elements
					.put(
							PushArm_2011,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getPushArm());

			elements
					.put(
							HandHeldReader_2012,
							org.ow2.aspirerfid.ide.MasterDataEditorGMF.MasterDataEditorGMFPackage.eINSTANCE
									.getHandHeldReader());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(Company_79);
			KNOWN_ELEMENT_TYPES.add(BizLoc_1001);
			KNOWN_ELEMENT_TYPES.add(Warehouse_1002);
			KNOWN_ELEMENT_TYPES.add(Room_1003);
			KNOWN_ELEMENT_TYPES.add(Section_1004);
			KNOWN_ELEMENT_TYPES.add(BizLoc_2001);
			KNOWN_ELEMENT_TYPES.add(Warehouse_2002);
			KNOWN_ELEMENT_TYPES.add(Room_2003);
			KNOWN_ELEMENT_TYPES.add(Section_2004);
			KNOWN_ELEMENT_TYPES.add(ReadPoint_2005);
			KNOWN_ELEMENT_TYPES.add(Container_2006);
			KNOWN_ELEMENT_TYPES.add(Conveyor_2007);
			KNOWN_ELEMENT_TYPES.add(Shelf_2008);
			KNOWN_ELEMENT_TYPES.add(Gate_2009);
			KNOWN_ELEMENT_TYPES.add(PalletTruck_2010);
			KNOWN_ELEMENT_TYPES.add(PushArm_2011);
			KNOWN_ELEMENT_TYPES.add(HandHeldReader_2012);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
