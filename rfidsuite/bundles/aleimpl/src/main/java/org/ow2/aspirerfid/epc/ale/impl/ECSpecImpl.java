/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.epc.ale.impl;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ow2.aspirerfid.epc.ale.api.ECReportSpec;
import org.ow2.aspirerfid.epc.ale.api.ECSpec;
import org.ow2.aspirerfid.epc.ale.pattern.api.BadPatternSyntaxException;
import org.ow2.aspirerfid.epc.ale.pattern.api.EPCGroupingPattern;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCFilteringPatternImpl;
import org.ow2.aspirerfid.epc.ale.pattern.impl.EPCGroupingPatternImpl;

/**
 * TODO Javadoc
 * 
 * @author Guillaume Surrel
 * @version 2007
 */
public class ECSpecImpl implements ECSpec {

	private Set logicalReaderNames;

	private URI startTrigger;

	private URI stopTrigger;

	private long repeatPeriod;

	private long duration;

	private long stableSetInterval;

	private Set reportsSpecs;

	private boolean includeSpecInReports;

	private String eventTopic;

	private String gatewayName;

	/**
	 * TODO Javadoc
	 */
	public ECSpecImpl() {
		this.logicalReaderNames = new HashSet();
		this.reportsSpecs = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#addLogicalReaderName(java.lang.String)
	 */
	public void addLogicalReaderName(final String name) {
		if (name != null) {
			this.logicalReaderNames.add(name);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#addLogicalReaderNames(java.util.Collection)
	 */
	public void addLogicalReaderNames(final Collection names) {
		this.logicalReaderNames.addAll(names);
		this.logicalReaderNames.remove(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#addReportSpec(org.ow2.aspirerfid.epc.ale.api.ECReportSpec)
	 */
	public void addReportSpec(final ECReportSpec spec) {
		if (spec != null) {
			this.reportsSpecs.remove(spec);
			this.reportsSpecs.add(spec);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#addReportSpecs(java.util.Set)
	 */
	public void addReportSpecs(final Set specs) {
		this.reportsSpecs.removeAll(specs);
		this.reportsSpecs.addAll(specs);
		this.reportsSpecs.remove(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getDuration()
	 */
	public long getDuration() {
		return this.duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getEventTopic()
	 */
	public String getEventTopic() {
		return this.eventTopic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getGatewayName()
	 */
	public String getGatewayName() {
		return this.gatewayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getIncludeSpecInReports()
	 */
	public boolean getIncludeSpecInReports() {
		return this.includeSpecInReports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getLogicalReaderNames()
	 */
	public Set getLogicalReaderNames() {
		return this.logicalReaderNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getRepeatPeriod()
	 */
	public long getRepeatPeriod() {
		return this.repeatPeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getReportSpec(java.lang.String)
	 */
	public ECReportSpec getReportSpec(final String name) {
		final Iterator it = this.reportsSpecs.iterator();
		while (it.hasNext()) {
			final ECReportSpec ecrs = (ECReportSpec) it.next();
			if (ecrs.getName().equals(name)) {
				return ecrs;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getReportSpecs()
	 */
	public Set getReportSpecs() {
		return this.reportsSpecs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getStableSetInterval()
	 */
	public long getStableSetInterval() {
		return this.stableSetInterval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getStartTrigger()
	 */
	public URI getStartTrigger() {
		return this.startTrigger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getStopTrigger()
	 */
	public URI getStopTrigger() {
		return this.stopTrigger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#getValidationMessage()
	 */
	public String getValidationMessage() {

		if ((this.getGatewayName() == null) || this.getGatewayName().equals("")) {
			return "You must specify a gateway name";
		}

		if ((this.logicalReaderNames == null)
				|| this.logicalReaderNames.isEmpty()) {
			return "You must specify one or more logical reader names";
		}

		if ((this.reportsSpecs == null) || this.reportsSpecs.isEmpty()) {
			return "You must define an event cycle specification (ECSpec)";
		}

		final Iterator it = this.reportsSpecs.iterator();
		while (it.hasNext()) {
			final ECReportSpec reportSpec = (ECReportSpec) it.next();
			if (!reportSpec.getIncludeCount() && !reportSpec.getIncludeEPC()
					&& !reportSpec.getIncludeRawDecimal()
					&& !reportSpec.getIncludeRawHex()
					&& !reportSpec.getIncludeTag()) {
				return "All datas are excluded";
			}

			try {
				Iterator filtersIterator = reportSpec.getIncludePatterns()
						.iterator();
				while (filtersIterator.hasNext()) {
					final String filter = (String) filtersIterator.next();
					new EPCFilteringPatternImpl(filter);
				}
				filtersIterator = reportSpec.getExcludePatterns().iterator();
				while (filtersIterator.hasNext()) {
					final String filter = (String) filtersIterator.next();
					new EPCFilteringPatternImpl(filter);
				}

				final List groupsList = reportSpec.getGroupSpec();
				for (int i = 0; i < groupsList.size(); i++) {
					final EPCGroupingPattern groupSpec = new EPCGroupingPatternImpl(
							(String) groupsList.get(i));
					for (int j = i + 1; j < groupsList.size(); j++) {
						final EPCGroupingPattern groupSpec2 = new EPCGroupingPatternImpl(
								(String) groupsList.get(i));
						if (!groupSpec.isDisjoint(groupSpec2)) {
							return "Groups are not disjoint";
						}
					}
				}

			} catch (final BadPatternSyntaxException e) {
				return "At least one pattern is not recognized";
			}
		}

		if (this.duration < 0) {
			return "Duration can't be negative";
		}
		if (this.repeatPeriod < 0) {
			return "RepeatPeriod can't be negative";
		}
		if (this.stableSetInterval < 0) {
			return "StableSetInterval can't be negative";
		}
		if (!((this.startTrigger == null) || this.startTrigger.toString()
				.equals(""))) {
			if (this.repeatPeriod != 0) {
				return "StartTrigger can't be defined while repeatPeriod is non-zero";
			}
		}
		if (((this.stopTrigger == null) || this.stopTrigger.toString().equals(
				""))
				&& (this.duration == 0) && (this.stableSetInterval == 0)) {
			return "The event cycle can't finish, you must specify a stopTrigger or a duration or a stableSetInterval";
		}

		if ((this.eventTopic == null) || this.eventTopic.equals("")) {
			return "EventTopic is not defined";
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setDuration(long)
	 */
	public void setDuration(final long duration) {
		this.duration = duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setEventTopic(java.lang.String)
	 */
	public void setEventTopic(final String eventTopic) {
		this.eventTopic = eventTopic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setGatewayName(java.lang.String)
	 */
	public void setGatewayName(final String gatewayName) {
		this.gatewayName = gatewayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setIncludeSpecInReports(boolean)
	 */
	public void setIncludeSpecInReports(final boolean includeSpec) {
		this.includeSpecInReports = includeSpec;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setLogicalReaderNames(java.util.Set)
	 */
	public void setLogicalReaderNames(final Set logicalReaderNames) {
		this.logicalReaderNames = logicalReaderNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setRepeatPeriod(long)
	 */
	public void setRepeatPeriod(final long repeatPeriod) {
		this.repeatPeriod = repeatPeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setReportSpecs(java.util.Set)
	 */
	public void setReportSpecs(final Set specs) {
		this.reportsSpecs = specs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setStableSetInterval(long)
	 */
	public void setStableSetInterval(final long stableSetInterval) {
		this.stableSetInterval = stableSetInterval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setStartTrigger(java.net.URI)
	 */
	public void setStartTrigger(final URI startTrigger) {
		this.startTrigger = startTrigger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.epc.ale.api.ECSpec#setStopTrigger(java.net.URI)
	 */
	public void setStopTrigger(final URI stopTrigger) {
		this.stopTrigger = stopTrigger;
	}

}
