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
package org.ow2.aspirerfid.management.model;

import java.util.Map;

/**
 *
 * @author Kiev
 */
public class SensorCacheData {

    private double value;
    private String unit;
    private long timestamp;

    public SensorCacheData(double value, String unit, long timestamp) {
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    static SensorCacheData fromMap(Map<String, String> data) {
        double value = Double.parseDouble((String) data.get("value"));
        long timestamp = Long.parseLong((String) data.get("timestamp"));
        String unit = data.get("unit");

        return new SensorCacheData(value, unit, timestamp);
    }
}