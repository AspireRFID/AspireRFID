/**
 * Copyright (c) 2008-2010, AspireRFID
 *
 * This library is free software; you can redistribute it and/or
 * modify it either under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation
 * (the "LGPL"). If you do not alter this
 * notice, a recipient may use your version of this file under the LGPL.
 *
 * You should have received a copy of the LGPL along with this library
 * in the file COPYING-LGPL-2.1; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY
 * OF ANY KIND, either express or implied. See the LGPL  for
 * the specific language governing rights and limitations.
 *
 * Contact: AspireRFID mailto:aspirerfid@ow2.org
 */

package org.ow2.aspirerfid.ale.engine.com;

import pops.lang.AnObject;
//import pops.lang.HashCodeUtil;

/**
 *
 * @author pops
 * @author Guche Grégory aka Sceez Melting-Pot.
 */
public class SGLNAggregationKey extends AnObject {
    private long sglnAggregationKeyCompanyPrefix;
    private int  sglnAggregationKeyReference;

    public SGLNAggregationKey(long aCompanyPrefix, int aReference) {
        sglnAggregationKeyCompanyPrefix = aCompanyPrefix;
        sglnAggregationKeyReference     = aReference;
    }

    public long getCompanyPrefix() {
        return sglnAggregationKeyCompanyPrefix;
    }

    public int getReference() {
        return sglnAggregationKeyReference;
    }   

    protected boolean onEquals(Object anObject) {
        SGLNAggregationKey key = (SGLNAggregationKey)anObject;
        if(key.getCompanyPrefix() == getCompanyPrefix())
            return key.getReference() == getReference();
        return false;
    }

    public int hashCode() {
		/**
        int hash = HashCodeUtil.SEED;
        hash = HashCodeUtil.hash(hash, sgtinAggregationKeyCompanyPrefix);
        return HashCodeUtil.hash(hash, sgtinAggregationKeyReference);*/
		return (int)((31L * ((long)sglnAggregationKeyReference) + sglnAggregationKeyCompanyPrefix) % Integer.MAX_VALUE);
    }
}
