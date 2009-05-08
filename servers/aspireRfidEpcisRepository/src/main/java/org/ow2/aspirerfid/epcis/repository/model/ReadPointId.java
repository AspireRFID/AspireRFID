/*
 * Copyright © 2008-2010, Aspire 
 *
 * This file contains the source code of the Accada library by ETH Zurich (www.accada.org),
 * licensed under the terms of the GNU Lesser General Public License version 2.1 in 2007
 * and modified for the needs of the Aspire project.
 *
 * Aspire is free software; you can redistribute it and/or 
 * modify it under  the terms of the GNU Lesser General Public 
 * License version 2.1 as published by the Free Software Foundation (the 
 * "LGPL"). 
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library in the file COPYING-LGPL-2.1; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA. 
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY 
 * OF ANY KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations. 
 */

package org.ow2.aspirerfid.epcis.repository.model;

import org.ow2.aspirerfid.epcis.repository.EpcisConstants;

/**
 * A vocabulary type for representing read point identifiers, per section 7.2.3
 * of the spec.
 * 
 * @author Sean Wellington
 * @author Nikos Kefalakis (nkef)
 */
public class ReadPointId extends VocabularyElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8794325182911414868L;


	@Override
    public String getVocabularyType() {
        return EpcisConstants.READ_POINT_ID;
    }

}
