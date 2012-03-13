package org.ow2.aspirerfid.ale.core.impl;

import org.ow2.aspirerfid.commons.ale.model.ale.ECReports;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ALEServicePortType;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ArrayOfString;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Define;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.DuplicateNameExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.DuplicateSubscriptionExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ECSpecValidationExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.EmptyParms;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.GetECSpec;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.GetSubscribers;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Immediate;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.ImplementationExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.InvalidURIExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.NoSuchNameExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.NoSuchSubscriberExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Poll;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.SecurityExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Subscribe;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Undefine;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.Unsubscribe;
import org.ow2.aspirerfid.commons.ale.wsdl.ale.VoidHolder;

public class ALEServicePortTypeImpl implements ALEServicePortType{

	public VoidHolder undefine(Undefine parms)
			throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse, SecurityExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayOfString getECSpecNames(EmptyParms parms)
			throws ImplementationExceptionResponse, SecurityExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public ECReports immediate(Immediate parms)
			throws ImplementationExceptionResponse, SecurityExceptionResponse,
			ECSpecValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public VoidHolder define(Define parms)
			throws ImplementationExceptionResponse, SecurityExceptionResponse,
			ECSpecValidationExceptionResponse, DuplicateNameExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public VoidHolder unsubscribe(Unsubscribe parms)
			throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse, NoSuchSubscriberExceptionResponse,
			SecurityExceptionResponse, InvalidURIExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public ECSpec getECSpec(GetECSpec parms)
			throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse, SecurityExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVendorVersion(EmptyParms parms)
			throws ImplementationExceptionResponse {
		// TODO Auto-generated method stub
		return "AspireRFID-ALE-OSGi-V0.6";
	}

	public ArrayOfString getSubscribers(GetSubscribers parms)
			throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse, SecurityExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public ECReports poll(Poll parms) throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse, SecurityExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStandardVersion(EmptyParms parms)
			throws ImplementationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public VoidHolder subscribe(Subscribe parms)
			throws ImplementationExceptionResponse,
			NoSuchNameExceptionResponse,
			DuplicateSubscriptionExceptionResponse, SecurityExceptionResponse,
			InvalidURIExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

}
