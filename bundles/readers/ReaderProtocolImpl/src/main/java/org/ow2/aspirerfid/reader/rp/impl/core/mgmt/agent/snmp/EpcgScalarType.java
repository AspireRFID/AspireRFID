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

package org.ow2.aspirerfid.reader.rp.impl.core.mgmt.agent.snmp;

/**
 * Denotes the scalar types.
 */
public enum EpcgScalarType {
	
	/********************
	 * epcgReaderDevice *
	 ********************/
	
	// epcgReaderDeviceInformation
	EPCG_RDR_DEV_DESCRIPTION,
	EPCG_RDR_DEV_ROLE,
	EPCG_RDR_DEV_EPC,
	EPCG_RDR_DEV_SERIAL_NUMBER,
	EPCG_RDR_DEV_TIME_UTC,
	EPCG_RDR_DEV_CURRENT_SOURCE,
	EPCG_RDR_DEV_REBOOT,
	EPCG_RDR_DEV_RESET_STATISTICS,
	EPCG_RDR_DEV_RESET_TIMESTAMP,
	EPCG_RDR_DEV_NORMALIZE_POWER_LEVEL,
	EPCG_RDR_DEV_NORMALIZE_NOISE_LEVEL,
	
	// epcgReaderDeviceOperation
	EPCG_RDR_DEV_OPER_STATUS,
	EPCG_RDR_DEV_OPER_STATUS_PRIOR,
	EPCG_RDR_DEV_OPER_STATE_ENABLE,
	EPCG_RDR_DEV_OPER_NOTIF_FROM_STATE,
	EPCG_RDR_DEV_OPER_NOTIF_TO_STATE,
	EPCG_RDR_DEV_OPER_NOTIF_STATE_LEVEL,
	EPCG_RDR_DEV_OPER_STATE_SUPPRESS_INTERVAL,
	EPCG_RDR_DEV_OPER_STATE_SUPPRESSIONS,
	
	// epcgReaderDeviceMemory
	EPCG_RDR_DEV_FREE_MEMORY,
	EPCG_RDR_DEV_FREE_MEMORY_NOTIF_ENABLE,
	EPCG_RDR_DEV_FREE_MEMORY_NOTIF_LEVEL,
	EPCG_RDR_DEV_FREE_MEMORY_ONSET_THRESHOLD,
	EPCG_RDR_DEV_FREE_MEMORY_ABATE_THRESHOLD,
	EPCG_RDR_DEV_FREE_MEMORY_STATUS,
	EPCG_RDR_DEV_MEM_STATE_SUPPRESS_INTERVAL,
	EPCG_RDR_DEV_MEM_STATE_SUPPRESSIONS,
	
	
	/******************
	 * epcgReadPoints *
	 ******************/
	
//	EPCG_READ_POINT_PRIOR_OPER_STATUS,
//	EPCG_READ_POINT_OPER_STATE_SUPPRESS_INTERVAL,
//	EPCG_READ_POINT_OPER_STATE_SUPPRESSIONS,
	
	
	/*************************
	 * epcgAntennaReadPoints *
	 *************************/
	
//	EPCG_ANT_RD_PNT_SUPPRESS_INTERVAL,
	
	
	/***************
	 * epcgIoPorts *
	 ***************/
	
//	EPCG_IO_PORT_OPER_STATUS_PRIOR,
//	EPCG_IO_PORT_OPER_STATE_SUPPRESS_INTERVAL,
//	EPCG_IO_PORT_OPER_STATE_SUPPRESSIONS,
	
	
	/***************
	 * epcgSources *
	 ***************/
	
//	EPCG_SRC_OPER_PRIOR_STATUS,
//	EPCG_SRC_OPER_STATE_SUPPRESS_INTERVAL,
//	EPCG_SRC_OPER_STATE_SUPPRESSIONS,
	
	
	/****************************
	 * epcgNotificationChannels *
	 ****************************/
	
//	EPCG_NOTIF_CHAN_OPER_STATUS_PRIOR,
//	EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESS_INTERVAL,
//	EPCG_NOTIF_CHAN_OPER_STATE_SUPPRESSIONS,
	
	/********
	 * IETF *
	 ********/
	
	// SNMPv2-MIB
	SYS_DESCR,
	SYS_LOCATION,
	SYS_CONTACT,
	SYS_UP_TIME,
	SYS_NAME
	
}
