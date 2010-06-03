/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

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
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmeFactory
 * @model kind="package"
 * @generated
 */
public interface BpwmePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bpwme";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://aspire.ow2.org/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.ow2.aspirerfid.ide.bpwme";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpwmePackage eINSTANCE = org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl <em>Workflow Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getWorkflowMap()
	 * @generated
	 */
	int WORKFLOW_MAP = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MAP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MAP__CONNECTIONS = 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MAP__NODES = 2;

	/**
	 * The number of structural features of the '<em>Workflow Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_MAP_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.NodeImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Ingoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__INGOING_CONNECTIONS = 0;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__OUTGOING_CONNECTIONS = 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.ConnectionImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 2;

	/**
	 * The feature id for the '<em><b>Source Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__SOURCE_NODE = 0;

	/**
	 * The feature id for the '<em><b>Target Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TARGET_NODE = 1;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl <em>OLCB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getOLCBProc()
	 * @generated
	 */
	int OLCB_PROC = 3;

	/**
	 * The feature id for the '<em><b>Ingoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__INGOING_CONNECTIONS = NODE__INGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__NAME = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__ID = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__TRANSITIONS = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>CLCB Proc</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__CLCB_PROC = NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>OLCB Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC_FEATURE_COUNT = NODE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl <em>CLCB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getCLCBProc()
	 * @generated
	 */
	int CLCB_PROC = 4;

	/**
	 * The feature id for the '<em><b>Ingoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__INGOING_CONNECTIONS = NODE__INGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__DESCRIPTION = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__ID = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__TRANSITIONS = NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>EB Proc</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__EB_PROC = NODE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>CLCB Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC_FEATURE_COUNT = NODE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl <em>EB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getEBProc()
	 * @generated
	 */
	int EB_PROC = 5;

	/**
	 * The feature id for the '<em><b>Ingoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__INGOING_CONNECTIONS = NODE__INGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__DESCRIPTION = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__ID = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__EXTENDED_ATTRIBUTES = NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>EB Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC_FEATURE_COUNT = NODE_FEATURE_COUNT + 4;

	/*
	 * the fake feature id for ebproc to use.
	 */
	int EB_PROC_FAKE_ATTR = NODE_FEATURE_COUNT + 5;
	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionsImpl <em>Transitions</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.TransitionsImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getTransitions()
	 * @generated
	 */
	int TRANSITIONS = 6;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONS__TRANSITION = 0;

	/**
	 * The number of structural features of the '<em>Transitions</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 7;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONDITION = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Extended Attributes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EXTENDED_ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__FROM = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = 5;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TO = 6;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link bpwme.impl.ExtendedAttributeImpl <em>Extended Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.ExtendedAttributeImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getExtendedAttribute()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTE = 8;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE__VALUE = 2;

	/**
	 * The number of structural features of the '<em>Extended Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link bpwme.impl.ExtendedAttributesImpl <em>Extended Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.ExtendedAttributesImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getExtendedAttributes()
	 * @generated
	 */
	int EXTENDED_ATTRIBUTES = 9;

	/**
	 * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES__EXTENDED_ATTRIBUTE = 0;

	/**
	 * The number of structural features of the '<em>Extended Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_ATTRIBUTES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.ConditionImpl
	 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 10;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.WorkflowMap <em>Workflow Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Map</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.WorkflowMap
	 * @generated
	 */
	EClass getWorkflowMap();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getName()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EAttribute getWorkflowMap_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getConnections()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EReference getWorkflowMap_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.WorkflowMap#getNodes()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EReference getWorkflowMap_Nodes();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the reference list '{@link org.ow2.aspirerfid.ide.bpwme.Node#getIngoingConnections <em>Ingoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ingoing Connections</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Node#getIngoingConnections()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_IngoingConnections();

	/**
	 * Returns the meta object for the reference list '{@link org.ow2.aspirerfid.ide.bpwme.Node#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Connections</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Node#getOutgoingConnections()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_OutgoingConnections();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.Connection#getSourceNode <em>Source Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Node</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Connection#getSourceNode()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_SourceNode();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.Connection#getTargetNode <em>Target Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Node</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Connection#getTargetNode()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_TargetNode();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc <em>OLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OLCB Proc</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.OLCBProc
	 * @generated
	 */
	EClass getOLCBProc();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.OLCBProc#getName()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EAttribute getOLCBProc_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.OLCBProc#getId()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EAttribute getOLCBProc_Id();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transitions</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.OLCBProc#getTransitions()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EReference getOLCBProc_Transitions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.bpwme.OLCBProc#getCLCBProc <em>CLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>CLCB Proc</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.OLCBProc#getCLCBProc()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EReference getOLCBProc_CLCBProc();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc <em>CLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CLCB Proc</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc
	 * @generated
	 */
	EClass getCLCBProc();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc#getDescription()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc#getName()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc#getId()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Id();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transitions</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc#getTransitions()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EReference getCLCBProc_Transitions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.bpwme.CLCBProc#getEBProc <em>EB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EB Proc</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.CLCBProc#getEBProc()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EReference getCLCBProc_EBProc();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.EBProc <em>EB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EB Proc</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.EBProc
	 * @generated
	 */
	EClass getEBProc();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.EBProc#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.EBProc#getDescription()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.EBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.EBProc#getName()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.EBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.EBProc#getId()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Id();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.EBProc#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extended Attributes</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.EBProc#getExtendedAttributes()
	 * @see #getEBProc()
	 * @generated
	 */
	EReference getEBProc_ExtendedAttributes();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.Transitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transitions</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transitions
	 * @generated
	 */
	EClass getTransitions();

	/**
	 * Returns the meta object for the reference list '{@link org.ow2.aspirerfid.ide.bpwme.Transitions#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Transition</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transitions#getTransition()
	 * @see #getTransitions()
	 * @generated
	 */
	EReference getTransitions_Transition();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Condition</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getCondition()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Condition();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getDescription()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Description();

	/**
	 * Returns the meta object for the reference '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extended Attributes</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getExtendedAttributes()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_ExtendedAttributes();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getFrom()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_From();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getId()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getName()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Transition#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Transition#getTo()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_To();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attribute</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute
	 * @generated
	 */
	EClass getExtendedAttribute();

	/**
	 * Returns the meta object for the attribute list '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Content</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getContent()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getName()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttribute#getValue()
	 * @see #getExtendedAttribute()
	 * @generated
	 */
	EAttribute getExtendedAttribute_Value();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes <em>Extended Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Attributes</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes
	 * @generated
	 */
	EClass getExtendedAttributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes#getExtendedAttribute <em>Extended Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.ExtendedAttributes#getExtendedAttribute()
	 * @see #getExtendedAttributes()
	 * @generated
	 */
	EReference getExtendedAttributes_ExtendedAttribute();

	/**
	 * Returns the meta object for class '{@link org.ow2.aspirerfid.ide.bpwme.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute list '{@link org.ow2.aspirerfid.ide.bpwme.Condition#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Content</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Condition#getContent()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.ow2.aspirerfid.ide.bpwme.Condition#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ow2.aspirerfid.ide.bpwme.Condition#getType()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Type();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BpwmeFactory getBpwmeFactory();

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
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl <em>Workflow Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.WorkflowMapImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getWorkflowMap()
		 * @generated
		 */
		EClass WORKFLOW_MAP = eINSTANCE.getWorkflowMap();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW_MAP__NAME = eINSTANCE.getWorkflowMap_Name();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_MAP__CONNECTIONS = eINSTANCE.getWorkflowMap_Connections();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_MAP__NODES = eINSTANCE.getWorkflowMap_Nodes();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.NodeImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Ingoing Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__INGOING_CONNECTIONS = eINSTANCE.getNode_IngoingConnections();

		/**
		 * The meta object literal for the '<em><b>Outgoing Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__OUTGOING_CONNECTIONS = eINSTANCE.getNode_OutgoingConnections();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.ConnectionImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Source Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__SOURCE_NODE = eINSTANCE.getConnection_SourceNode();

		/**
		 * The meta object literal for the '<em><b>Target Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__TARGET_NODE = eINSTANCE.getConnection_TargetNode();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl <em>OLCB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.OLCBProcImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getOLCBProc()
		 * @generated
		 */
		EClass OLCB_PROC = eINSTANCE.getOLCBProc();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OLCB_PROC__NAME = eINSTANCE.getOLCBProc_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OLCB_PROC__ID = eINSTANCE.getOLCBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OLCB_PROC__TRANSITIONS = eINSTANCE.getOLCBProc_Transitions();

		/**
		 * The meta object literal for the '<em><b>CLCB Proc</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OLCB_PROC__CLCB_PROC = eINSTANCE.getOLCBProc_CLCBProc();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl <em>CLCB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.CLCBProcImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getCLCBProc()
		 * @generated
		 */
		EClass CLCB_PROC = eINSTANCE.getCLCBProc();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__DESCRIPTION = eINSTANCE.getCLCBProc_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__NAME = eINSTANCE.getCLCBProc_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__ID = eINSTANCE.getCLCBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLCB_PROC__TRANSITIONS = eINSTANCE.getCLCBProc_Transitions();

		/**
		 * The meta object literal for the '<em><b>EB Proc</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLCB_PROC__EB_PROC = eINSTANCE.getCLCBProc_EBProc();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl <em>EB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.EBProcImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getEBProc()
		 * @generated
		 */
		EClass EB_PROC = eINSTANCE.getEBProc();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__DESCRIPTION = eINSTANCE.getEBProc_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__NAME = eINSTANCE.getEBProc_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__ID = eINSTANCE.getEBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EB_PROC__EXTENDED_ATTRIBUTES = eINSTANCE.getEBProc_ExtendedAttributes();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionsImpl <em>Transitions</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.TransitionsImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getTransitions()
		 * @generated
		 */
		EClass TRANSITIONS = eINSTANCE.getTransitions();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITIONS__TRANSITION = eINSTANCE.getTransitions_Transition();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.TransitionImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__CONDITION = eINSTANCE.getTransition_Condition();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__DESCRIPTION = eINSTANCE.getTransition_Description();

		/**
		 * The meta object literal for the '<em><b>Extended Attributes</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__EXTENDED_ATTRIBUTES = eINSTANCE.getTransition_ExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__FROM = eINSTANCE.getTransition_From();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__ID = eINSTANCE.getTransition_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__NAME = eINSTANCE.getTransition_Name();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__TO = eINSTANCE.getTransition_To();

		/**
		 * The meta object literal for the '{@link bpwme.impl.ExtendedAttributeImpl <em>Extended Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.ExtendedAttributeImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getExtendedAttribute()
		 * @generated
		 */
		EClass EXTENDED_ATTRIBUTE = eINSTANCE.getExtendedAttribute();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE__CONTENT = eINSTANCE.getExtendedAttribute_Content();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE__NAME = eINSTANCE.getExtendedAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_ATTRIBUTE__VALUE = eINSTANCE.getExtendedAttribute_Value();

		/**
		 * The meta object literal for the '{@link bpwme.impl.ExtendedAttributesImpl <em>Extended Attributes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.ExtendedAttributesImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getExtendedAttributes()
		 * @generated
		 */
		EClass EXTENDED_ATTRIBUTES = eINSTANCE.getExtendedAttributes();

		/**
		 * The meta object literal for the '<em><b>Extended Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDED_ATTRIBUTES__EXTENDED_ATTRIBUTE = eINSTANCE.getExtendedAttributes_ExtendedAttribute();

		/**
		 * The meta object literal for the '{@link org.ow2.aspirerfid.ide.bpwme.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.ConditionImpl
		 * @see org.ow2.aspirerfid.ide.bpwme.impl.BpwmePackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__CONTENT = eINSTANCE.getCondition_Content();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__TYPE = eINSTANCE.getCondition_Type();

	}

} //BpwmePackage
