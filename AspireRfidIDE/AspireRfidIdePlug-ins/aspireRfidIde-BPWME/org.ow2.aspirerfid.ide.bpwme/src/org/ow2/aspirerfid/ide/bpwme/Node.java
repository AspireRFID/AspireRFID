/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ow2.aspirerfid.ide.bpwme;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Node#getIngoingConnections <em>Ingoing Connections</em>}</li>
 *   <li>{@link org.ow2.aspirerfid.ide.bpwme.Node#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getNode()
 * @model abstract="true"
 * @generated
 */
public interface Node extends EObject {
	/**
	 * Returns the value of the '<em><b>Ingoing Connections</b></em>' reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.bpwme.Connection}.
	 * It is bidirectional and its opposite is '{@link org.ow2.aspirerfid.ide.bpwme.Connection#getTargetNode <em>Target Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ingoing Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ingoing Connections</em>' reference list.
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getNode_IngoingConnections()
	 * @see org.ow2.aspirerfid.ide.bpwme.Connection#getTargetNode
	 * @model opposite="targetNode"
	 * @generated
	 */
	EList<Connection> getIngoingConnections();

	/**
	 * Returns the value of the '<em><b>Outgoing Connections</b></em>' reference list.
	 * The list contents are of type {@link org.ow2.aspirerfid.ide.bpwme.Connection}.
	 * It is bidirectional and its opposite is '{@link org.ow2.aspirerfid.ide.bpwme.Connection#getSourceNode <em>Source Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Connections</em>' reference list.
	 * @see org.ow2.aspirerfid.ide.bpwme.BpwmePackage#getNode_OutgoingConnections()
	 * @see org.ow2.aspirerfid.ide.bpwme.Connection#getSourceNode
	 * @model opposite="sourceNode"
	 * @generated
	 */
	EList<Connection> getOutgoingConnections();

} // Node
