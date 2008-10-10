import java.util.HashMap;
import java.util.Map;

/**
 * This class provides an XSLT extension function that
 * may be utilized by Xalan-Java extension mechanism.
 */
public class StateVariableTable {

	private static Map stateVariableTable=new HashMap();
	
	final private static boolean trace=true;
	/**
	 */
	public static String clean() {
		if(trace) System.out.println("StateVariableTable clean");
		stateVariableTable.clear();
		return "";
	}

	public static String add(String varname,String datatype) {
		if(trace) System.out.println("StateVariableTable add("+varname+","+datatype+")");
		stateVariableTable.put(varname,datatype);
		return "";
	}
	
	public static String getPDataType(String varname) throws IllegalArgumentException {
		if(trace) System.out.println("StateVariableTable getDataType("+varname+")");
		String datatype=(String)stateVariableTable.get(varname);
		if(datatype==null) throw new IllegalArgumentException("No \""+varname+"\" statevariable");
		return datatype;
	}

	public static String getClassName(String varname) {
		if(trace) System.out.println("StateVariableTable getClassName("+varname+")");
		String datatype=(String)stateVariableTable.get(varname);
		if(datatype==null) throw new IllegalArgumentException("No \""+varname+"\" statevariable");
		return null; // UPnPDataTypeUtil.getClassName(datatype);
	}

	public static String getInputJavaType(String varname) {
		if(trace) System.out.println("StateVariableTable getInputJavaType("+varname+")");
		String datatype=(String)stateVariableTable.get(varname);
		if(datatype==null) throw new IllegalArgumentException("No \""+varname+"\" statevariable");
		return null; // UPnPDataTypeUtil.getInputJavaType(datatype);
	}

	public static String getOutputJavaType(String varname) {
		if(trace) System.out.println("StateVariableTable getOutputJavaType("+varname+")");
		String datatype=(String)stateVariableTable.get(varname);
		if(datatype==null) throw new IllegalArgumentException("No \""+varname+"\" statevariable");
		return null; // UPnPDataTypeUtil.getOutputJavaType(datatype);
	}
	
}