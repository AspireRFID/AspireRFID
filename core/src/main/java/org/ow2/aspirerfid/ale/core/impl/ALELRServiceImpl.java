package org.ow2.aspirerfid.ale.core.impl;

import org.ow2.aspirerfid.commons.ale.model.alelr.LRSpec;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ALELRServicePortType;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.AddReaders;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.AddReadersResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ArrayOfString;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.Define;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.DefineResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.DuplicateNameExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.EmptyParms;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.GetLRSpec;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.GetPropertyValue;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ImmutableReaderExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ImplementationExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.InUseExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.NoSuchNameExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.NonCompositeReaderExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ReaderLoopExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.RemoveReaders;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.RemoveReadersResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.SecurityExceptionResponse;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.SetProperties;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.SetPropertiesResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.SetReaders;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.SetReadersResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.Undefine;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.UndefineResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.Update;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.UpdateResult;
import org.ow2.aspirerfid.commons.ale.wsdl.alelr.ValidationExceptionResponse;

public class ALELRServiceImpl implements ALELRServicePortType{

	public LRSpec getLRSpec(GetLRSpec parms) throws SecurityExceptionResponse,
			ImplementationExceptionResponse, NoSuchNameExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStandardVersion(EmptyParms parms)
			throws ImplementationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public UndefineResult undefine(Undefine parms)
			throws SecurityExceptionResponse, InUseExceptionResponse,
			ImplementationExceptionResponse, ImmutableReaderExceptionResponse,
			NoSuchNameExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public DefineResult define(Define parms) throws SecurityExceptionResponse,
			ImplementationExceptionResponse, DuplicateNameExceptionResponse,
			ValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public SetPropertiesResult setProperties(SetProperties parms)
			throws SecurityExceptionResponse, InUseExceptionResponse,
			ImplementationExceptionResponse, ImmutableReaderExceptionResponse,
			NoSuchNameExceptionResponse, ValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public RemoveReadersResult removeReaders(RemoveReaders parms)
			throws NonCompositeReaderExceptionResponse,
			SecurityExceptionResponse, InUseExceptionResponse,
			ImplementationExceptionResponse, ImmutableReaderExceptionResponse,
			NoSuchNameExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public AddReadersResult addReaders(AddReaders parms)
			throws ReaderLoopExceptionResponse,
			NonCompositeReaderExceptionResponse, SecurityExceptionResponse,
			InUseExceptionResponse, ImplementationExceptionResponse,
			ImmutableReaderExceptionResponse, NoSuchNameExceptionResponse,
			ValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public UpdateResult update(Update parms)
			throws ReaderLoopExceptionResponse, SecurityExceptionResponse,
			InUseExceptionResponse, ImplementationExceptionResponse,
			ImmutableReaderExceptionResponse, NoSuchNameExceptionResponse,
			ValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public SetReadersResult setReaders(SetReaders parms)
			throws ReaderLoopExceptionResponse,
			NonCompositeReaderExceptionResponse, SecurityExceptionResponse,
			InUseExceptionResponse, ImplementationExceptionResponse,
			ImmutableReaderExceptionResponse, NoSuchNameExceptionResponse,
			ValidationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVendorVersion(EmptyParms parms)
			throws ImplementationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayOfString getLogicalReaderNames(EmptyParms parms)
			throws SecurityExceptionResponse, ImplementationExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPropertyValue(GetPropertyValue parms)
			throws SecurityExceptionResponse, ImplementationExceptionResponse,
			NoSuchNameExceptionResponse {
		// TODO Auto-generated method stub
		return null;
	}

}
