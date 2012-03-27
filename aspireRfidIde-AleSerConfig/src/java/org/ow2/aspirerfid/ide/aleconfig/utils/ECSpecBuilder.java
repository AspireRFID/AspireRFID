package org.ow2.aspirerfid.ide.aleconfig.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec.LogicalReaders;
import org.ow2.aspirerfid.commons.ale.model.ale.ECSpec.ReportSpecs;
import org.ow2.aspirerfid.commons.ale.model.ale.*;
import org.ow2.aspirerfid.commons.ale.utils.DeserializerUtil;
import org.ow2.aspirerfid.commons.ale.utils.SerializerUtil;

/**
 * 
 *
 * @author Vasso Koletti e-mail: vkol@ait.edu.gr
 * @author Nikos Kefalakis (nkef) e-mail: nkef@ait.edu.gr
 *
 */
public class ECSpecBuilder {

	private ECSpec spec;

	public ECSpecBuilder() {
		spec = new ECSpec();
		spec.setLogicalReaders(new LogicalReaders());
		spec.setReportSpecs(new ReportSpecs());
		spec.setBoundarySpec(new ECBoundarySpec());
		spec.setExtension(new ECSpecExtension());
	}

	public boolean addLogicalReader(String id) {
		return spec.getLogicalReaders().getLogicalReader().add(id);
	}

	public void setLogicalReaders(List<String> list) {
		spec.getLogicalReaders().getLogicalReader().clear();
		spec.getLogicalReaders().getLogicalReader().addAll(list);
	}

	public List<String> getLogicalReaders() {
		return spec.getLogicalReaders().getLogicalReader();
	}

	public boolean removeLogicalReader(String id) {
		return spec.getLogicalReaders().getLogicalReader().remove(id);
	}

	public boolean addECReportSpec(ECReportSpec reportSpec) {
		if (spec.getReportSpecs() == null) {
			ReportSpecs rep = new ReportSpecs();
			spec.setReportSpecs(rep);
		}
		return spec.getReportSpecs().getReportSpec().add(reportSpec);
	}

	public List<ECReportSpec> getECReportSpecs() {
		return spec.getReportSpecs().getReportSpec();
	}

	public boolean removeECReportSpec(ECReportSpec reportSpec) {
		return spec.getReportSpecs().getReportSpec().remove(reportSpec);
	}

	public ECBoundarySpecBuilder getECBoundarySpec() {
		return new ECBoundarySpecBuilder(spec.getBoundarySpec());
	}

	public void setECBoundarySpec(ECBoundarySpecBuilder spec) {
		this.spec.setBoundarySpec(spec.getECBoundarySpec());
	}

	public boolean addPrimaryKeyFields(String value) {
		ECSpecExtension extention = spec.getExtension();
		return extention.getPrimaryKeyFields().getPrimaryKeyField().add(value);
	}

	public void setPrimaryKeyFields(List<String> list) {
		ECSpecExtension extension = spec.getExtension();
		try {
			extension.getPrimaryKeyFields().getPrimaryKeyField().clear();
		} catch (Exception e) {
		}

		extension.getPrimaryKeyFields().getPrimaryKeyField().addAll(list);
	}

	public List<String> getPrimaryKeyFields() {
		ECSpecExtension extention = spec.getExtension();
		return extention.getPrimaryKeyFields().getPrimaryKeyField();
	}

	public boolean removePrimaryKeyField(String value) {
		ECSpecExtension extention = spec.getExtension();
		return extention.getPrimaryKeyFields().getPrimaryKeyField().remove(
				value);
	}

	public boolean getIncudeSpecInReports() {
		return spec.isIncludeSpecInReports();
	}

	public void setIncludeSpecInreports(boolean value) {
		spec.setIncludeSpecInReports(value);

	}

	public void generateXml(String filename) throws IOException {
		FileWriter file = new FileWriter(new File(filename));
		SerializerUtil.serializeECSpec(spec, file);

		file.close();
	}

	/**
	 * This method loads the ec specification from a file.
	 * 
	 * @param filename
	 *            of ec specification file
	 * @return ec specification
	 * @throws Exception
	 *             if specification could not be loaded
	 */
	public ECSpec getECSpecFromFile(String filename) throws Exception {
		FileInputStream inputStream = new FileInputStream(filename);
		return DeserializerUtil.deserializeECSpec(inputStream);

	}
}
