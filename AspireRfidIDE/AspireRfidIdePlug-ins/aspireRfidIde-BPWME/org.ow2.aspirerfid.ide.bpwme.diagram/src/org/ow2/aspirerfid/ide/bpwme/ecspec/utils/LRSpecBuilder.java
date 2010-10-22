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

package org.ow2.aspirerfid.ide.bpwme.ecspec.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec.LogicalReaders;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRProperty;
import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;
import org.ow2.aspirerfid.commons.apdl.model.ApdlDataField;
import org.ow2.aspirerfid.commons.apdl.model.EBProc;
import org.ow2.aspirerfid.ide.bpwme.CLCBProc;
import org.ow2.aspirerfid.ide.bpwme.diagram.edit.parts.CLCBProcEditPart;
import org.ow2.aspirerfid.ide.bpwme.ecspec.model.*;
import org.ow2.aspirerfid.ide.bpwme.utils.MainControl;
import org.ow2.aspirerfid.ide.bpwme.utils.MainUtil;

/**
 * Hold lr specs and notify changes to observers.
 * @author Yongming Luo <yluo@ait.edu.gr>
 *
 */
public class LRSpecBuilder {


	ArrayList<Viewer> listeners;
	ArrayList<Spec> lspecList;
	ArrayList<Spec> rspecList;
	EBProc ebp;

	/*for test use*/
	public LRSpecBuilder() {
		listeners = new ArrayList<Viewer>();
		lspecList = new ArrayList<Spec>();
		rspecList = new ArrayList<Spec>();
	}
	public void test() {
		lspecList.add(new LLRPSpec("123"));
		lspecList.add(new LLRPSpec("456"));
		lspecList.add(new LLRPSpec("789"));
	}


	public LRSpecBuilder(ApdlDataField adf) {
		//lrsbList = new ArrayList<ApdlDataField>();
		listeners = new ArrayList<Viewer>();
		//		if((lrsb = adf.getLRSpec()) == null) {
		//			
		//		}		
	}

	public LRSpecBuilder(EBProc ebp) {
		//get all lr spec in clcb level
		//add them to the candidates
		//add the ones appear in select list to select view
		this.ebp = ebp;

		listeners = new ArrayList<Viewer>();
		lspecList = new ArrayList<Spec>();
		rspecList = new ArrayList<Spec>();	

		List<String> selectLRList = getSelectedLR(ebp);


		boolean haveLR = haveCandidateLRSpec(ebp);

		LRSpec tempLR;

		//candidates are from the clcb level
		//search all the available ones
		MainControl mc = MainControl.getMainControl();
		CLCBProcEditPart clcbPart = MainUtil.getCLCBPartSelection();

		if(clcbPart != null) {
			CLCBProc clcbp = (CLCBProc)((View)clcbPart.getModel()).getElement();
			for(org.ow2.aspirerfid.ide.bpwme.EBProc ebproc :clcbp.getEBProc()) {
				EBProc ebpm = (EBProc)mc.getMapObject(ebproc.hashCode());
				if(ebpm != null) {
					addReaderToList(ebpm,lspecList,rspecList,selectLRList);
				}
			}
		}
	}
	
	public void setEBProc(EBProc ebp) {
		this.ebp = ebp;
		
		lspecList.clear();
		rspecList.clear();

		List<String> selectLRList = getSelectedLR(ebp);

		//candidates are from the clcb level
		//search all the available ones
		MainControl mc = MainControl.getMainControl();
		CLCBProcEditPart clcbPart = MainUtil.getCLCBPartSelection();

		if(clcbPart != null) {
			CLCBProc clcbp = (CLCBProc)((View)clcbPart.getModel()).getElement();
			for(org.ow2.aspirerfid.ide.bpwme.EBProc ebproc :clcbp.getEBProc()) {
				EBProc ebpm = (EBProc)mc.getMapObject(ebproc.hashCode());
				if(ebpm != null) {
					addReaderToList(ebpm,lspecList,rspecList,selectLRList);
				}
			}
		}

		notifyListeners();
		
	}


	private void addReaderToList(EBProc ebproc, ArrayList<Spec> candidateList, ArrayList<Spec> selectList, List<String> selectLR) {
		for(ApdlDataField adf:ebproc.getApdlDataFields().getApdlDataField()) {
			LRSpec tempLR;
			if((tempLR = adf.getLRSpec()) != null) {
				//get the type from readerType field
				String type = getType(tempLR);
				if(type == null) {
					//TODO for now there is no type information for HAL, we should have it later
					HALSpec hal = Spec.createHAL(adf);
					candidateList.add(hal);
					if(inSelect(hal.getName(), selectLR)) {
						selectList.add(hal);
					}
				}else if(type.equals("org.ow2.aspirerfid.ale.server.readers.llrp.LLRPAdaptor")) {
					LLRPSpec llrp = Spec.createLLRP(adf);
					candidateList.add(llrp);
					if(inSelect(llrp.getName(), selectLR)) {
						selectList.add(llrp);
					}

				}else if(type.equals("org.ow2.aspirerfid.ale.server.readers.rp.RPAdaptor")) {
					RPSpec rp = Spec.createRP(adf);
					candidateList.add(rp);
					if(inSelect(rp.getName(), selectLR)) {
						selectList.add(rp);
					}

				}else {
					System.out.println("This should not happen in LRSpecBuilder()");
				}
			}
		}
	}

	private boolean inSelect(String reader, List<String> selectList) {
		if(selectList == null) {
			return false;
		}
		for(String lr:selectList) {
			if(lr.equals(reader)) {
				return true;
			}
		}
		return false;
	}

	private String getType(LRSpec tempLR) {
		LRSpec.Properties properties;
		properties = tempLR.getProperties();
		for(LRProperty lrp : properties.getProperty()) {
			if(lrp.getName().equals("ReaderType")) {
				return lrp.getValue();
			}
		}
		return null;
	}

	private List<String> getSelectedLR(EBProc ebp) {
		boolean newECSB = true;
		ECSpec tempECSpec = null;
		for(ApdlDataField adf:ebp.getApdlDataFields().getApdlDataField()) {
			//if there exists one ECSpec definition
			if(adf.getECSpec() != null) {
				tempECSpec = adf.getECSpec();
				newECSB = false;
				break;
			}
		}
		if(newECSB) {
			return null;
		}else {
			LogicalReaders lr = tempECSpec.getLogicalReaders();
			if(lr.getLogicalReader().size() == 0) {
				return null;
			}else {
				return lr.getLogicalReader();
			}
		}
	}

	private boolean haveCandidateLRSpec(EBProc ebp) {
		for(ApdlDataField adf:ebp.getApdlDataFields().getApdlDataField()) {
			if(adf.getLRSpec() != null) {
				return true;
			}
		}
		return false;
	}


	public void addLRSpec(ApdlDataField adf) {
		//		LRSpec lrsb;
		//		if((lrsb = adf.getLRSpec()) != null) {
		//			//lrsbList.add(adf);
		//		} else {
		//			System.err.println("This is not a LRSpec ApdlDataField" + adf);
		//		}
		ebp.getApdlDataFields().getApdlDataField().add(adf);
	}

	public void removeLRSpec(ApdlDataField adf) {
		ebp.getApdlDataFields().getApdlDataField().remove(adf);
	}

	public void addListener(Viewer listener) {
		listeners.add(listener);
	}

	public void notifyListeners() {
		for(Viewer v : listeners) {
			//System.out.println(v);
			v.refresh();
		}
	}

	public void addProperty(){}
	public void changeProperty(){}
	public void removeProperty() {}
	public ArrayList<Spec> getLeftSpecList() {
		return lspecList;
	}
	public ArrayList<Spec> getRightSpecList() {
		return rspecList;
	}

}
