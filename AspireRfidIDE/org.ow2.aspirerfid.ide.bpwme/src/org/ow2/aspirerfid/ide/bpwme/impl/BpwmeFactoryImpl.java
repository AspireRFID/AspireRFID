/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.ow2.aspirerfid.ide.bpwme.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpwmeFactoryImpl extends EFactoryImpl implements BpwmeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BpwmeFactory init() {
		try {
			BpwmeFactory theBpwmeFactory = (BpwmeFactory)EPackage.Registry.INSTANCE.getEFactory("http://aspire.ow2.org/"); 
			if (theBpwmeFactory != null) {
				return theBpwmeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BpwmeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpwmeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BpwmePackage.WORKFLOW_MAP: return createWorkflowMap();
			case BpwmePackage.CONNECTION: return createConnection();
			case BpwmePackage.OLCB_PROC: return createOLCBProc();
			case BpwmePackage.CLCB_PROC: return createCLCBProc();
			case BpwmePackage.EB_PROC: return createEBProc();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowMap createWorkflowMap() {
		WorkflowMapImpl workflowMap = new WorkflowMapImpl();
		return workflowMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OLCBProc createOLCBProc() {
		OLCBProcImpl olcbProc = new OLCBProcImpl();
		return olcbProc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CLCBProc createCLCBProc() {
		CLCBProcImpl clcbProc = new CLCBProcImpl();
		return clcbProc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EBProc createEBProc() {
		EBProcImpl ebProc = new EBProcImpl();
		return ebProc;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpwmePackage getBpwmePackage() {
		return (BpwmePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BpwmePackage getPackage() {
		return BpwmePackage.eINSTANCE;
	}

} //BpwmeFactoryImpl
