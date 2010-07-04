/*
   Copyright 2005-2008, Aspire
   
   This library is free software; you can redistribute it and/or modify it 
   under the terms of the GNU Lesser General Public License as published by 
   the Free Software Foundation (the "LGPL"); either version 2.1 of the 
   License, or (at your option) any later version. If you do not alter this 
   notice, a recipient may use your version of this file under either the 
   LGPL version 2.1, or (at his option) any later version.
   
   You should have received a copy of the GNU Lesser General Public License 
   along with this library; if not, write to the Free Software Foundation, 
   Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
   
   This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
   KIND, either express or implied. See the GNU Lesser General Public 
   License for the specific language governing rights and limitations.
**/
package org.ow2.aspirerfid.vellemandvm1200.data;

import java.text.ParseException;

/**
 * format the date sent by the USB Multimeter
 * @author Julien Vey, Didier Donsez
 */
public class DVM1200MultimeterAcquisition {

    private boolean[][] segmentTable;
    private int[] digits;
    
    public DVM1200MultimeterAcquisition(byte[] bytes) throws ParseException {
        segmentTable = getSegmentTable(bytes);
        digits = getDigits();
    }

    public String toString() {
        String res = "";
        
        //res+=toBinaryStr()+"\n";        
        if(isOverRange()) return "Over Range";
        int dotPosition = getDotPosition();
        String unit = getUnitStr();
        
        res+=getValue();
        res+=getUnit();
        
        res+=" (error=+/-"+getError()+")";
        
        if(isAC()) res+=" AC";
        if(isDC()) res+=" DC";
        if(isAutoRangeMode()) res+=" AutoRange";
        if(isContinuityCheckMode()) res+=" ContinuityCheck";
        if(isDataHoldMode()) res+=" DataHold";
        // if(isDataTransmissionMode()) res+=" DataTransmission";
        if(isDiodeTestMode()) res+=" DiodeTest";
        if(isLowBattery()) res+=" LowBattery";
        if(isMin()) res+=" MIN";
        if(isMax()) res+=" MAX";
        
        return res;
    }

    private String toBinaryStr() {
    	String res="";
        for (int j = 0; j < 4; j++) {
        	for (int i = 0; i < 14 ; i++) {
                res+=(segmentTable[i][j])?"1":"0";
            }
        	res+="\n";
        }
        return res;
	}

	// TODO according to the manufacturer error range specification
    /*
# DC voltage: 0,6V / 6 / 60 / 600 / 1000V

    * basic accuracy: ±( 0,5% of reading + 8 digits) for 0.6V range , ±(0.8%+5digits) for 6/60/600V range , ±(1%+10digits) for 1000V range
    * input impedance: > 10Mohm
    * maximum input: 1000V

# AC voltage: 0,6V / 6 / 60 / 600 / 700V

    * basic accuracy: ±( 3% of reading + 3 digits) for 0.6V range , ±(1%+3digits) for 6/60/600V range , ±(1,5%+3digits) for 700V range
    * input impedance: > 10Mohm
    * frequency range: 40-400Hz
    * maximum input: 700V

# DC current: 600uA / 6m / 60m / 600m / 6A / 10A

    * basic accuracy: ±( 1,5% of reading + 3 digits) for 600u / 6mA range , ±(1,8%+5digits) for 60/600mA range , ±(2%+5digits) for 6/10A range
    * overload protection: 10A/600V-fuse for 10A-range , 750mA/600V fuse for other ranges

# AC current: 600uA / 6m / 60m / 600m / 6A / 10A

    * basic accuracy: ±( 1,8% of reading + 5 digits) for 600u / 6mA range , ±(2%+8digits) for 60/600mA range , ±(3%+8digits) for 6/10A range
    * overload protection: 10A/600V-fuse for 10A-range , 750mA/600V fuse for other ranges

# resistance: 600 / 6k / 60k / 600k / 6M / 60M

    * basic accuracy: ±( 0,5% of reading + 3 digits) for 600 Ohm range , ±(0,5%+2digits) for 6K...6M range , ±(1,5%+3digits) for 60Mohm range
    * overload protection: 250Vdc or 250Vrms

# capacitance: 60nF/ 600nF / 6uF / 60uF / 300uF

    * accuracy: ±( 3% of reading + 20 digits) for 60 nF range , ±(3%+10digits) for 600nF...60uF range , ±(5%+10digits) for 300 uF range

# inductance: -

    * accuracy: -

# frequency: 99.99Hz / 999.9Hz / 9.999kHz / 99.99 kHz / 999.9 kHz

    * basic accuracy: ±( 0,1% of reading + 3 digits)
    * overload protection: 250V dc or 250V ac rms

# temperature: -55°C to 1000°C

    * basic accuracy: -55°C to 0°C : ±( 5% of reading + 4 digits) . 0 to 400°C : ±(2%+3digits). 400 to 1000°C : ±(2% of reading)    
 */
    public double getError() {
    	return 0.0;
    }
    
    public double getValue() {
        if(isOverRange()) return Double.NaN;

        double value=digits[0]*1000+digits[1]*100+digits[2]*10+digits[3];

        int dotPosition = getDotPosition();
        if(dotPosition==1) value /=1000;
        else if(dotPosition==2) value /=100;
        else if(dotPosition==3) value /=10;
     
        value*=getScale();
                
        if(isNegative()) value*=-1;
        
        return value;
    }

    private boolean isNegative() {
         return segmentTable[1][3];
    }

    public boolean isAC() {
        if (segmentTable[0][3]) {
            return true;
        }
        return false;
    }

    public boolean isDC() {
        if (segmentTable[0][2]) {
            return true;
        }
        return false;
    }


    public boolean isMin() {
        if (segmentTable[13][3]) {
            return true;
        }
        return false;
    }

    public boolean isMax() {
        if (segmentTable[13][0]) {
            return true;
        }
        return false;
    }

    // Scales

    public boolean isOverRange() {
    	// Test OL
    	return digits[0]==' ' && digits[0]==0 && digits[0]=='L' && digits[0]==' ' ;
    }

    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private double getScale() {
    	double scale=1.0;
	    if(isKilo()) scale =1000;
	    else if(isMega()) scale =1000000;
	    else if(isMilli()) scale /=1000;
	    else if(isMicro()) scale /=1000000;
	    else if(isNano()) scale /=1000000000;	
	    else if(isPercentage()) scale /=100;
	    return scale;
    }
    
    
    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isMega() {
    	return segmentTable[10][1];
    }

    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isKilo() {
    	return segmentTable[9][1];
    }

    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isMilli() {
    	return segmentTable[10][3];
    }

    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isMicro() {
    	return segmentTable[9][3];
    }

    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isNano() {
    	return segmentTable[9][2];
    }
    
    /**
     * Extract the scale from the LCD segments
     * @return
     */
    private boolean isPercentage() {
    	return segmentTable[10][2];
    }
    
    // Modes

    public boolean isAutoRangeMode() {
    	return segmentTable[9][0];
    }

    public boolean isDataTransmissionMode() {
        return segmentTable[0][0];
    }

    public boolean isDiodeTestMode() {
    	return segmentTable[9][0];
    }

    public boolean isContinuityCheckMode() {
    	return segmentTable[10][0];
    }
    
    public boolean isDataHoldMode() {
    	return segmentTable[11][0];
    }

    public boolean isLowBattery() {
    	return segmentTable[12][0];
    }

    private String getUnitStr() {
        String unit = " ";
        if (segmentTable[13][1]) {
            unit += "C"; // ADP1
        }
        if (segmentTable[9][1]) {
            unit += "k";
        }
        if (segmentTable[9][2]) {
            unit += "n";
        }
        if (segmentTable[9][3]) {
            unit += "u";
        }
        
        if (segmentTable[10][1]) {
            unit += "M";
        }
        if (segmentTable[10][2]) {
            unit += "%";
        }
        if (segmentTable[10][3]) {
            unit += "m";
        }
        
        if (segmentTable[11][2]) {
            unit += "Ohm";
        }
        if (segmentTable[11][3]) {
            unit += "F";
        }
        
        if (segmentTable[12][1]) {
            unit += "Hz";
        }
        if (segmentTable[12][2]) {
            unit += "V";
        }
        if (segmentTable[12][3]) {
            unit += "A";
        }
        return unit;
    }

    /**
     * Extract the unit from the LCD segments
     * @param i
     * @return
     */
    public String getUnit() {
        String unit=null;
        if (segmentTable[13][1]) {
            unit = "C"; // ADP1
        }
        if (segmentTable[11][2]) {
            unit = "Ohm";
        }
        if (segmentTable[11][3]) {
            unit = "F";
        }        
        if (segmentTable[12][1]) {
            unit = "Hz";
        }
        if (segmentTable[12][2]) {
            unit = "V";
        }
        if (segmentTable[12][3]) {
            unit = "A";
        }
        return unit;
    }
    
    
    /**
     * Extract the position of the dot from the LCD segments
     * @param i
     * @return
     */
    private int getDotPosition() {
        if (segmentTable[3][3]) {
            return 1;
        }
        if (segmentTable[5][3]) {
            return 2;
        }
        if (segmentTable[7][3]) {
            return 3;
        }
        return 0;
    }

    /**
     * Extract the digit from the LCD segments
     * @param i
     * @return
     */
    private int[] getDigits() {
        int[] res = new int[4];
        for (int i = 0; i < 4; i++) {
            res[i] = getDigit(i);
        }
        return res;
    }

    /**
     * Extract the digit from the LCD segments
     * @param i
     * @return
     */
    private int getDigit(int i) {
        int index = i * 2 + 1;
        int res = -1;
        boolean[] digitSegments = new boolean[7];
        // [0] for segment 1, etc.
        digitSegments[0] = segmentTable[index][0];
        digitSegments[1] = segmentTable[index + 1][0];
        digitSegments[2] = segmentTable[index + 1][2];
        digitSegments[3] = segmentTable[index + 1][3];
        digitSegments[4] = segmentTable[index][2];
        digitSegments[5] = segmentTable[index][1];
        digitSegments[6] = segmentTable[index + 1][1];
        if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] == false // 7
        ) {
            res = 0;
        }
        else if (digitSegments[0] == false && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] == false // 7
        ) {
            res = 1;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] == false && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] == false && // 6
                digitSegments[6]// 7
        ) {
            res = 2;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] // 7
        ) {
            res = 3;
        }
        else if (digitSegments[0] == false && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 4;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 5;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 6;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] == false // 7
        ) {
            res = 7;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 8;
        }
        else if (digitSegments[0] && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 9;
        }
        else if (digitSegments[0]  && // 1
                digitSegments[1]  && // 2
                digitSegments[2]  && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 10; // A
        }

        else if (digitSegments[0] == false && // 1
                digitSegments[1] == false && // 2
                digitSegments[2]  && // 3
                digitSegments[3]  && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 11; // b;
        }
        
        else if (digitSegments[0] && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] == false  && // 3
                digitSegments[3]  && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] == false // 7
        ) {
            res = 12; // C
        }

        else if (digitSegments[0] && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] && // 3
                digitSegments[3]  && // 4
                digitSegments[4] && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] // 7
        ) {
            res = 13; // d
        }

        else if (digitSegments[0] && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] == false && // 3
                digitSegments[3]  && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 14; // E
        }

        else if (digitSegments[0] && // 1
                digitSegments[1]  && // 2
                digitSegments[2] == false && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 15; // F
        }
        else if (digitSegments[0] == false && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] == false && // 3
                digitSegments[3] == false && // 4
                digitSegments[4] == false && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] == false // 7
        ) {
            res = ' '; // 0x20;
        }
        else if (digitSegments[0] == false && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] == false && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] == false // 7
        ) {
            res = 'L';
        }
        else if (digitSegments[0] == false && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] // 7
        ) {
            res = 'o';
        } else if (digitSegments[0] == false && // 1
                digitSegments[1] && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] && // 6
                digitSegments[6] // 7
        ) {
            res = 'U';
        } else if (digitSegments[0] == false && // 1
                digitSegments[1] == false && // 2
                digitSegments[2] && // 3
                digitSegments[3] && // 4
                digitSegments[4] && // 5
                digitSegments[5] == false && // 6
                digitSegments[6] // 7
        ) {
            res = 'u';
        } else {

        	res=Integer.MAX_VALUE;
        }
        return res;
    }

    /**
     * extract the LCD segments from the bytes
     * @param bytes
     * @return
     */
    private boolean[][] getSegmentTable(byte[] bytes) {
        boolean[][] table = new boolean[14][4];
        boolean res;
        byte temp;
        for (int i = 0; i < 14 ; i++) {
            temp = bytes[i];
            for (int j = 0; j < 4; j++) {
                res = (temp >> j & 0x0001)==1;
                table[i][j] = res;
            }
        }
        return table;
    }
}
