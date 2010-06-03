/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ow2.aspirerfid.ide.bpwme.BpwmeFactory;
import org.ow2.aspirerfid.ide.bpwme.BpwmePackage;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.Condition;
import org.ow2.aspirerfid.ide.bpwme.Connection;
import org.ow2.aspirerfid.ide.bpwme.EBProc;
import org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute;
import org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes;
import org.ow2.aspirerfid.ide.bpwme.Node;
import org.ow2.aspirerfid.ide.bpwme.OLCBProc;
import org.ow2.aspirerfid.ide.bpwme.Transition;
import org.ow2.aspirerfid.ide.bpwme.Transitions;
import org.ow2.aspirerfid.ide.bpwme.WorkflowMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpwmePackageImpl extends EPackageImpl implements BpwmePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass olcbProcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clcbProcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ebProcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedAttributesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BpwmePackageImpl() {
		super(eNS_URI, BpwmeFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link BpwmePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BpwmePackage init() {
		if (isInited) return (BpwmePackage)EPackage.Registry.INSTANCE.getEPackage(BpwmePackage.eNS_URI);

		// Obtain or create and register package
		BpwmePackageImpl theBpwmePackage = (BpwmePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BpwmePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BpwmePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theBpwmePackage.createPackageContents();

		// Initialize created meta-data
		theBpwmePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBpwmePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BpwmePackage.eNS_URI, theBpwmePackage);
		return theBpwmePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkflowMap() {
		return workflowMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkflowMap_Name() {
		return (EAttribute)workflowMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowMap_Connections() {
		return (EReference)workflowMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkflowMap_Nodes() {
		return (EReference)workflowMapEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_IngoingConnections() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_OutgoingConnections() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_SourceNode() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_TargetNode() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOLCBProc() {
		return olcbProcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOLCBProc_Name() {
		return (EAttribute)olcbProcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOLCBProc_Id() {
		return (EAttribute)olcbProcEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOLCBProc_Transitions() {
		return (EReference)olcbProcEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOLCBProc_CLCBProc() {
		return (EReference)olcbProcEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCLCBProc() {
		return clcbProcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCLCBProc_Description() {
		return (EAttribute)clcbProcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCLCBProc_Name() {
		return (EAttribute)clcbProcEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCLCBProc_Id() {
		return (EAttribute)clcbProcEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCLCBProc_Transitions() {
		return (EReference)clcbProcEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCLCBProc_EBProc() {
		return (EReference)clcbProcEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEBProc() {
		return ebProcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEBProc_Description() {
		return (EAttribute)ebProcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEBProc_Name() {
		return (EAttribute)ebProcEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEBProc_Id() {
		return (EAttribute)ebProcEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEBProc_ExtendedAttributes() {
		return (EReference)ebProcEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitions() {
		return transitionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransitions_Transition() {
		return (EReference)transitionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_Condition() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Description() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransition_ExtendedAttributes() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_From() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Id() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_Name() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransition_To() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttribute() {
		return extendedAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Content() {
		return (EAttribute)extendedAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Name() {
		return (EAttribute)extendedAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedAttribute_Value() {
		return (EAttribute)extendedAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedAttributes() {
		return extendedAttributesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExtendedAttributes_ExtendedAttribute() {
		return (EReference)extendedAttributesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCondition() {
		return conditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCondition_Content() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCondition_Type() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpwmeFactory getBpwmeFactory() {
		return (BpwmeFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		workflowMapEClass = createEClass(WORKFLOW_MAP);
		createEAttribute(workflowMapEClass, WORKFLOW_MAP__NAME);
		createEReference(workflowMapEClass, WORKFLOW_MAP__CONNECTIONS);
		createEReference(workflowMapEClass, WORKFLOW_MAP__NODES);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__INGOING_CONNECTIONS);
		createEReference(nodeEClass, NODE__OUTGOING_CONNECTIONS);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__SOURCE_NODE);
		createEReference(connectionEClass, CONNECTION__TARGET_NODE);

		olcbProcEClass = createEClass(OLCB_PROC);
		createEAttribute(olcbProcEClass, OLCB_PROC__NAME);
		createEAttribute(olcbProcEClass, OLCB_PROC__ID);
		createEReference(olcbProcEClass, OLCB_PROC__TRANSITIONS);
		createEReference(olcbProcEClass, OLCB_PROC__CLCB_PROC);

		clcbProcEClass = createEClass(CLCB_PROC);
		createEAttribute(clcbProcEClass, CLCB_PROC__DESCRIPTION);
		createEAttribute(clcbProcEClass, CLCB_PROC__NAME);
		createEAttribute(clcbProcEClass, CLCB_PROC__ID);
		createEReference(clcbProcEClass, CLCB_PROC__TRANSITIONS);
		createEReference(clcbProcEClass, CLCB_PROC__EB_PROC);

		ebProcEClass = createEClass(EB_PROC);
		createEAttribute(ebProcEClass, EB_PROC__DESCRIPTION);
		createEAttribute(ebProcEClass, EB_PROC__NAME);
		createEAttribute(ebProcEClass, EB_PROC__ID);
		createEReference(ebProcEClass, EB_PROC__EXTENDED_ATTRIBUTES);

		transitionsEClass = createEClass(TRANSITIONS);
		createEReference(transitionsEClass, TRANSITIONS__TRANSITION);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__CONDITION);
		createEAttribute(transitionEClass, TRANSITION__DESCRIPTION);
		createEReference(transitionEClass, TRANSITION__EXTENDED_ATTRIBUTES);
		createEAttribute(transitionEClass, TRANSITION__FROM);
		createEAttribute(transitionEClass, TRANSITION__ID);
		createEAttribute(transitionEClass, TRANSITION__NAME);
		createEAttribute(transitionEClass, TRANSITION__TO);

		extendedAttributeEClass = createEClass(EXTENDED_ATTRIBUTE);
		createEAttribute(extendedAttributeEClass, EXTENDED_ATTRIBUTE__CONTENT);
		createEAttribute(extendedAttributeEClass, EXTENDED_ATTRIBUTE__NAME);
		createEAttribute(extendedAttributeEClass, EXTENDED_ATTRIBUTE__VALUE);

		extendedAttributesEClass = createEClass(EXTENDED_ATTRIBUTES);
		createEReference(extendedAttributesEClass, EXTENDED_ATTRIBUTES__EXTENDED_ATTRIBUTE);

		conditionEClass = createEClass(CONDITION);
		createEAttribute(conditionEClass, CONDITION__CONTENT);
		createEAttribute(conditionEClass, CONDITION__TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		olcbProcEClass.getESuperTypes().add(this.getNode());
		clcbProcEClass.getESuperTypes().add(this.getNode());
		ebProcEClass.getESuperTypes().add(this.getNode());

		// Initialize classes and features; add operations and parameters
		initEClass(workflowMapEClass, WorkflowMap.class, "WorkflowMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkflowMap_Name(), ecorePackage.getEString(), "name", null, 0, 1, WorkflowMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowMap_Connections(), this.getConnection(), null, "connections", null, 0, -1, WorkflowMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowMap_Nodes(), this.getNode(), null, "nodes", null, 0, -1, WorkflowMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_IngoingConnections(), this.getConnection(), this.getConnection_TargetNode(), "ingoingConnections", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_OutgoingConnections(), this.getConnection(), this.getConnection_SourceNode(), "outgoingConnections", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_SourceNode(), this.getNode(), this.getNode_OutgoingConnections(), "sourceNode", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnection_TargetNode(), this.getNode(), this.getNode_IngoingConnections(), "targetNode", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(olcbProcEClass, OLCBProc.class, "OLCBProc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOLCBProc_Name(), ecorePackage.getEString(), "name", "", 0, 1, OLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOLCBProc_Id(), ecorePackage.getEString(), "id", "", 0, 1, OLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOLCBProc_Transitions(), this.getTransitions(), null, "transitions", null, 0, 1, OLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOLCBProc_CLCBProc(), this.getCLCBProc(), null, "cLCBProc", null, 0, -1, OLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(clcbProcEClass, CLCBProc.class, "CLCBProc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCLCBProc_Description(), ecorePackage.getEString(), "description", "", 0, 1, CLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCLCBProc_Name(), ecorePackage.getEString(), "name", "", 0, 1, CLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCLCBProc_Id(), ecorePackage.getEString(), "id", "", 0, 1, CLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCLCBProc_Transitions(), this.getTransitions(), null, "transitions", null, 0, 1, CLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCLCBProc_EBProc(), this.getEBProc(), null, "eBProc", null, 0, -1, CLCBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ebProcEClass, EBProc.class, "EBProc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEBProc_Description(), ecorePackage.getEString(), "description", "", 0, 1, EBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEBProc_Name(), ecorePackage.getEString(), "name", "", 0, 1, EBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEBProc_Id(), ecorePackage.getEString(), "id", "", 0, 1, EBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEBProc_ExtendedAttributes(), this.getExtendedAttributes(), null, "extendedAttributes", null, 0, 1, EBProc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionsEClass, Transitions.class, "Transitions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitions_Transition(), this.getTransition(), null, "transition", null, 0, -1, Transitions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Condition(), this.getCondition(), null, "condition", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Description(), ecorePackage.getEString(), "description", "", 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_ExtendedAttributes(), this.getExtendedAttributes(), null, "extendedAttributes", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_From(), ecorePackage.getEString(), "from", "", 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Id(), ecorePackage.getEString(), "id", "", 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Name(), ecorePackage.getEString(), "name", "", 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_To(), ecorePackage.getEString(), "to", "", 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendedAttributeEClass, ExtendedAttribute.class, "ExtendedAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtendedAttribute_Content(), ecorePackage.getEJavaObject(), "content", null, 0, -1, ExtendedAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttribute_Name(), ecorePackage.getEString(), "name", "", 0, 1, ExtendedAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtendedAttribute_Value(), ecorePackage.getEString(), "value", "", 0, 1, ExtendedAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendedAttributesEClass, ExtendedAttributes.class, "ExtendedAttributes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtendedAttributes_ExtendedAttribute(), this.getExtendedAttribute(), null, "extendedAttribute", null, 0, -1, ExtendedAttributes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEClass, Condition.class, "Condition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCondition_Content(), ecorePackage.getEJavaObject(), "content", null, 0, -1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCondition_Type(), ecorePackage.getEString(), "type", "", 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //BpwmePackageImpl
