/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author egil
 */
class SimulatorClientStub {
	private final OutputStream outputStream;
	final String readerId;
	final TreeSet antennaIds;
	
	public SimulatorClientStub(OutputStream outputStream, String definition) {
		this.outputStream = outputStream;
		String[] fragments = definition.split(SimulatorServerTokens.EOH);
		if (fragments.length > 0) {
			readerId = fragments[0];
			if (fragments.length == 1) {
				antennaIds = null;
			} else {
				antennaIds = new TreeSet(Arrays.asList(fragments[1].split(SimulatorServerTokens.DELIMITER)));
			}
		} else {
			throw new IllegalArgumentException("Not a valid reader definition");
		}
	}
	
	public boolean checkConnection() {
		try {
			send(SimulatorServerTokens.PING + SimulatorServerTokens.EOL);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public String getReaderId() {
		return readerId;
	}
	
	public Set getAntennaIds() {
		return antennaIds;
	}
	
	public void add(String antennaId, String epc) throws IOException, SimulatorServerException {
		if (hasAntennaId(antennaId)) {
			send(SimulatorServerTokens.ADD + SimulatorServerTokens.EOH + antennaId + SimulatorServerTokens.DELIMITER + epc + SimulatorServerTokens.EOL);
		} else {
			throw new SimulatorServerException("No such antenna '" + antennaId + "' available at reader '" + readerId + "'");
		}
	}
	
	public void remove(String antennaId, String epc) throws IOException, SimulatorServerException {
		if (hasAntennaId(antennaId)) {
			send(SimulatorServerTokens.REMOVE + SimulatorServerTokens.EOH + antennaId + SimulatorServerTokens.DELIMITER + epc + SimulatorServerTokens.EOL);
		} else {
			throw new SimulatorServerException("No such antenna '" + antennaId + "' available at reader '" + readerId + "'");
		}
	}
	
	private boolean hasAntennaId(String antennaId) {
		return antennaIds.contains(antennaId);
	}
	
	private void send(String msg) throws IOException {
		outputStream.write(msg.getBytes());
		outputStream.flush();
	}
}