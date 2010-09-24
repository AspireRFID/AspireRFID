package org.ow2.aspirerfid.ide.bpwme.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.diagram.ui.render.util.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditor;

public class MasterDataFileUtil {
	public static DiagramEditPart getDiagramEditPart(String fileName) {
		URI diagramModelFilename = URI.createFileURI(fileName);
		GMFResource locationDiagram = new GMFResource(diagramModelFilename);
		System.out.println(locationDiagram);
//		CopyToImageUtil copyToImageUtil = new CopyToImageUtil();
//		copyToImageUtil.createDiagramEditPart((Diagram) locationDiagram, null, null);
		return null;
	}
	
	
	public static void openMasterDataFile(String fileName) {
		if(!fileName.endsWith(".masterdataeditorgmf_diagram")) {
			System.out.println("Wrong file extention");
			return;
		}

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		URI fileURI = URI.createFileURI(fileName);
		try {
			page.openEditor(new URIEditorInput(fileURI), MasterDataEditorGMFDiagramEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createMasterDataFile(String fileName) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		URI fileURI = URI.createFileURI(fileName);
		Resource diagram = org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
		.createDiagram(fileURI, null);
		try {
			org.ow2.aspirerfid.ide.MasterDataEditorGMF.diagram.part.MasterDataEditorGMFDiagramEditorUtil
			.openDiagram(diagram);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
}