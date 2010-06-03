package org.ow2.aspirerfid.ide.bpwme.diagram.providers;

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
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.ConnectionEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.EBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.OLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.WorkflowMapEditPart;
import org.ow2.aspirerfid.ide.bpwme.diagram.part.BpwmeDiagramEditorPlugin;


/**
 * @generated
 */
public class BpwmeElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private BpwmeElementTypes() {
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
	public static final IElementType WorkflowMap_1000 = getElementType("org.ow2.aspirerfid.ide.bpwme.diagram.WorkflowMap_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OLCBProc_2001 = getElementType("org.ow2.aspirerfid.ide.bpwme.diagram.OLCBProc_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType CLCBProc_3001 = getElementType("org.ow2.aspirerfid.ide.bpwme.diagram.CLCBProc_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EBProc_3002 = getElementType("org.ow2.aspirerfid.ide.bpwme.diagram.EBProc_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Connection_4001 = getElementType("org.ow2.aspirerfid.ide.bpwme.diagram.Connection_4001"); //$NON-NLS-1$

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
				return BpwmeDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
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

			elements.put(WorkflowMap_1000, BpwmePackage.eINSTANCE
					.getWorkflowMap());

			elements.put(OLCBProc_2001, BpwmePackage.eINSTANCE.getOLCBProc());

			elements.put(CLCBProc_3001, BpwmePackage.eINSTANCE.getCLCBProc());

			elements.put(EBProc_3002, BpwmePackage.eINSTANCE.getEBProc());

			elements.put(Connection_4001, BpwmePackage.eINSTANCE
					.getConnection());
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
			KNOWN_ELEMENT_TYPES.add(WorkflowMap_1000);
			KNOWN_ELEMENT_TYPES.add(OLCBProc_2001);
			KNOWN_ELEMENT_TYPES.add(CLCBProc_3001);
			KNOWN_ELEMENT_TYPES.add(EBProc_3002);
			KNOWN_ELEMENT_TYPES.add(Connection_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case WorkflowMapEditPart.VISUAL_ID:
			return WorkflowMap_1000;
		case OLCBProcEditPart.VISUAL_ID:
			return OLCBProc_2001;
		case CLCBProcEditPart.VISUAL_ID:
			return CLCBProc_3001;
		case EBProcEditPart.VISUAL_ID:
			return EBProc_3002;
		case ConnectionEditPart.VISUAL_ID:
			return Connection_4001;
		}
		return null;
	}

}
