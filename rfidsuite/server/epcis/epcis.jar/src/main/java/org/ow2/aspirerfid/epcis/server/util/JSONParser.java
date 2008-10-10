/*
 * Copyright 2005-2008, Aspire This library is free software; you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation (the "LGPL");
 * either version 2.1 of the License, or (at your option) any later version. If
 * you do not alter this notice, a recipient may use your version of this file
 * under either the LGPL version 2.1, or (at his option) any later version. You
 * should have received a copy of the GNU Lesser General Public License along
 * with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA. This software is
 * distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express
 * or implied. See the GNU Lesser General Public License for the specific
 * language governing rights and limitations.
 */
package org.ow2.aspirerfid.epcis.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * TODO Javadoc
 * 
 * @author Unknown
 */
public class JSONParser {
    /**
     * Converts a JSON Object to a list of tags with mapped properties.
     * 
     * @param object
     *            The JSON Object
     * @return The list of tags
     * @throws JSONException
     *             Thrown if the parsing fails
     */
    public static List<Map<String, String>> jsonToList(JSONObject object)
            throws JSONException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        
        if (object.length() > 0) {
            JSONArray array = object.getJSONArray("tags");
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);
                Iterator it = data.keys();
                
                Map<String, String> map = new HashMap<String, String>();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    map.put(key, ((JSONArray) data.get(key)).get(0).toString());
                }
                list.add(map);
            }
        }
        
        return list;
    }
}
