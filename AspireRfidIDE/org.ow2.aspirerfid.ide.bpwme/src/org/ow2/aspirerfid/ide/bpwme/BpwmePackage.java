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
 * @see bpwme.BpwmeFactory
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
	 * The meta object id for the '{@link bpwme.impl.WorkflowMapImpl <em>Workflow Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.WorkflowMapImpl
	 * @see bpwme.impl.BpwmePackageImpl#getWorkflowMap()
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
	 * The meta object id for the '{@link bpwme.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.NodeImpl
	 * @see bpwme.impl.BpwmePackageImpl#getNode()
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
	 * The meta object id for the '{@link bpwme.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.ConnectionImpl
	 * @see bpwme.impl.BpwmePackageImpl#getConnection()
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
	 * The meta object id for the '{@link bpwme.impl.OLCBProcImpl <em>OLCB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.OLCBProcImpl
	 * @see bpwme.impl.BpwmePackageImpl#getOLCBProc()
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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__ID = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>CLCB Proc</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC__CLCB_PROC = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>OLCB Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OLCB_PROC_FAKE_FEATURE = NODE_FEATURE_COUNT + 3;
	
	int OLCB_PROC_FEATURE_COUNT = NODE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link bpwme.impl.CLCBProcImpl <em>CLCB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.CLCBProcImpl
	 * @see bpwme.impl.BpwmePackageImpl#getCLCBProc()
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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__ID = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__DESCRIPTION = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Biz Location File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLCB_PROC__BIZ_LOCATION_FILE = NODE_FEATURE_COUNT + 3;

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
	 * The meta object id for the '{@link bpwme.impl.EBProcImpl <em>EB Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see bpwme.impl.EBProcImpl
	 * @see bpwme.impl.BpwmePackageImpl#getEBProc()
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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__ID = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC__DESCRIPTION = NODE_FEATURE_COUNT + 2;

	int EB_PROC_FAKE_FEATURE = NODE_FEATURE_COUNT + 3;
	
	/**
	 * The number of structural features of the '<em>EB Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EB_PROC_FEATURE_COUNT = NODE_FEATURE_COUNT + 4;

	
	

	/**
	 * Returns the meta object for class '{@link bpwme.WorkflowMap <em>Workflow Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Map</em>'.
	 * @see bpwme.WorkflowMap
	 * @generated
	 */
	EClass getWorkflowMap();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.WorkflowMap#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see bpwme.WorkflowMap#getName()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EAttribute getWorkflowMap_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link bpwme.WorkflowMap#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see bpwme.WorkflowMap#getConnections()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EReference getWorkflowMap_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link bpwme.WorkflowMap#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see bpwme.WorkflowMap#getNodes()
	 * @see #getWorkflowMap()
	 * @generated
	 */
	EReference getWorkflowMap_Nodes();

	/**
	 * Returns the meta object for class '{@link bpwme.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see bpwme.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the reference list '{@link bpwme.Node#getIngoingConnections <em>Ingoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ingoing Connections</em>'.
	 * @see bpwme.Node#getIngoingConnections()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_IngoingConnections();

	/**
	 * Returns the meta object for the reference list '{@link bpwme.Node#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Connections</em>'.
	 * @see bpwme.Node#getOutgoingConnections()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_OutgoingConnections();

	/**
	 * Returns the meta object for class '{@link bpwme.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see bpwme.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link bpwme.Connection#getSourceNode <em>Source Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Node</em>'.
	 * @see bpwme.Connection#getSourceNode()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_SourceNode();

	/**
	 * Returns the meta object for the reference '{@link bpwme.Connection#getTargetNode <em>Target Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Node</em>'.
	 * @see bpwme.Connection#getTargetNode()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_TargetNode();

	/**
	 * Returns the meta object for class '{@link bpwme.OLCBProc <em>OLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OLCB Proc</em>'.
	 * @see bpwme.OLCBProc
	 * @generated
	 */
	EClass getOLCBProc();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.OLCBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see bpwme.OLCBProc#getId()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EAttribute getOLCBProc_Id();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.OLCBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see bpwme.OLCBProc#getName()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EAttribute getOLCBProc_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link bpwme.OLCBProc#getCLCBProc <em>CLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>CLCB Proc</em>'.
	 * @see bpwme.OLCBProc#getCLCBProc()
	 * @see #getOLCBProc()
	 * @generated
	 */
	EReference getOLCBProc_CLCBProc();

	/**
	 * Returns the meta object for class '{@link bpwme.CLCBProc <em>CLCB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CLCB Proc</em>'.
	 * @see bpwme.CLCBProc
	 * @generated
	 */
	EClass getCLCBProc();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.CLCBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see bpwme.CLCBProc#getId()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Id();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.CLCBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see bpwme.CLCBProc#getName()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Name();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.CLCBProc#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see bpwme.CLCBProc#getDescription()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_Description();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.CLCBProc#getBizLocationFile <em>Biz Location File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Biz Location File</em>'.
	 * @see bpwme.CLCBProc#getBizLocationFile()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EAttribute getCLCBProc_BizLocationFile();

	/**
	 * Returns the meta object for the containment reference list '{@link bpwme.CLCBProc#getEBProc <em>EB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EB Proc</em>'.
	 * @see bpwme.CLCBProc#getEBProc()
	 * @see #getCLCBProc()
	 * @generated
	 */
	EReference getCLCBProc_EBProc();

	/**
	 * Returns the meta object for class '{@link bpwme.EBProc <em>EB Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EB Proc</em>'.
	 * @see bpwme.EBProc
	 * @generated
	 */
	EClass getEBProc();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.EBProc#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see bpwme.EBProc#getId()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Id();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.EBProc#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see bpwme.EBProc#getName()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Name();

	/**
	 * Returns the meta object for the attribute '{@link bpwme.EBProc#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see bpwme.EBProc#getDescription()
	 * @see #getEBProc()
	 * @generated
	 */
	EAttribute getEBProc_Description();

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
		 * The meta object literal for the '{@link bpwme.impl.WorkflowMapImpl <em>Workflow Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.WorkflowMapImpl
		 * @see bpwme.impl.BpwmePackageImpl#getWorkflowMap()
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
		 * The meta object literal for the '{@link bpwme.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.NodeImpl
		 * @see bpwme.impl.BpwmePackageImpl#getNode()
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
		 * The meta object literal for the '{@link bpwme.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.ConnectionImpl
		 * @see bpwme.impl.BpwmePackageImpl#getConnection()
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
		 * The meta object literal for the '{@link bpwme.impl.OLCBProcImpl <em>OLCB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.OLCBProcImpl
		 * @see bpwme.impl.BpwmePackageImpl#getOLCBProc()
		 * @generated
		 */
		EClass OLCB_PROC = eINSTANCE.getOLCBProc();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OLCB_PROC__ID = eINSTANCE.getOLCBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OLCB_PROC__NAME = eINSTANCE.getOLCBProc_Name();

		/**
		 * The meta object literal for the '<em><b>CLCB Proc</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OLCB_PROC__CLCB_PROC = eINSTANCE.getOLCBProc_CLCBProc();

		/**
		 * The meta object literal for the '{@link bpwme.impl.CLCBProcImpl <em>CLCB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.CLCBProcImpl
		 * @see bpwme.impl.BpwmePackageImpl#getCLCBProc()
		 * @generated
		 */
		EClass CLCB_PROC = eINSTANCE.getCLCBProc();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__ID = eINSTANCE.getCLCBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__NAME = eINSTANCE.getCLCBProc_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__DESCRIPTION = eINSTANCE.getCLCBProc_Description();

		/**
		 * The meta object literal for the '<em><b>Biz Location File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLCB_PROC__BIZ_LOCATION_FILE = eINSTANCE.getCLCBProc_BizLocationFile();

		/**
		 * The meta object literal for the '<em><b>EB Proc</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLCB_PROC__EB_PROC = eINSTANCE.getCLCBProc_EBProc();

		/**
		 * The meta object literal for the '{@link bpwme.impl.EBProcImpl <em>EB Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see bpwme.impl.EBProcImpl
		 * @see bpwme.impl.BpwmePackageImpl#getEBProc()
		 * @generated
		 */
		EClass EB_PROC = eINSTANCE.getEBProc();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__ID = eINSTANCE.getEBProc_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__NAME = eINSTANCE.getEBProc_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EB_PROC__DESCRIPTION = eINSTANCE.getEBProc_Description();

	}

} //BpwmePackage
