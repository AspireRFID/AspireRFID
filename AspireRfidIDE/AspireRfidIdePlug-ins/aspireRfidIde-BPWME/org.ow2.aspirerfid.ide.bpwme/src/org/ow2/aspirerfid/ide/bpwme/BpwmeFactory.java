/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage
 * @generated
 */
public interface BpwmeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpwmeFactory eINSTANCE = org.ow2.aspirerfid.ide.bpwme.impl.BpwmeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Workflow Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workflow Map</em>'.
	 * @generated
	 */
	WorkflowMap createWorkflowMap();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	Connection createConnection();

	/**
	 * Returns a new object of class '<em>OLCB Proc</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>OLCB Proc</em>'.
	 * @generated
	 */
	OLCBProc createOLCBProc();

	/**
	 * Returns a new object of class '<em>CLCB Proc</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CLCB Proc</em>'.
	 * @generated
	 */
	CLCBProc createCLCBProc();

	/**
	 * Returns a new object of class '<em>EB Proc</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EB Proc</em>'.
	 * @generated
	 */
	EBProc createEBProc();

	/**
	 * Returns a new object of class '<em>Transitions</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transitions</em>'.
	 * @generated
	 */
	Transitions createTransitions();

	/**
	 * Returns a new object of class '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition</em>'.
	 * @generated
	 */
	Transition createTransition();



	/**
	 * Returns a new object of class '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition</em>'.
	 * @generated
	 */
	Condition createCondition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BpwmePackage getBpwmePackage();

} //BpwmeFactory
