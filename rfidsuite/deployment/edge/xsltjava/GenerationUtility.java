/**
 * This class provides an XSLT extension function that
 * may be utilized by Xalan-Java extension mechanism.
 */
public class GenerationUtility {

	// to understand XSLT java method invocations
	private static int cpt=0;
	private final static boolean trace=false;
	
	/**
	 * This method variabilize the first character in the provided string.
	 * @return resulted string
	 */
	public static String variabilize(String classname) {
	    if(trace) System.out.println((++cpt)+"\tVariabilize "+classname);
		return Character.toLowerCase(classname.charAt(0)) + classname.substring(1);
	}

	/**
	 * This method capitalizes the first character in the provided string.
	 * @return resulted string
	 */
	public static String capitalize(String membername) {
		if(trace) System.out.println((++cpt)+"\tCapitalize "+membername);
		return Character.toUpperCase(membername.charAt(0)) + membername.substring(1);
	}

	/**
	 * This method capitalizes all characters in the provided string.
	 * @return resulted string
	 */
	public static String finalstaticOf(String membername) {
		if(trace) System.out.println((++cpt)+"\tFinalstaticof "+  membername);	
		int len=membername.length();
		StringBuffer sb=new StringBuffer(len+2);
		for(int i=0; i<len; i++){
			char c=membername.charAt(i);
			if(Character.isLowerCase(c) ) {
				sb.append(Character.toUpperCase(c));
			} else if(Character.isUpperCase(c) ) {
				sb.append('_').append(c);
			} else {
				
				sb.append(c);				
			}
		} 
		return sb.toString();
	}
	
	/**
	 * This method returns the package name in a full class name
	 * @return resulted string
	 */
	public static String packageOf(String fullclassname) {
		if(trace) System.out.println((++cpt)+"\tPackageof " + fullclassname);
		int index=fullclassname.lastIndexOf(".");
		if(index>0) {
			return fullclassname.substring(0,index);
		} else {
			return "";	
		}
	}

	/**
	 * This method returns the package name in a full class name
	 * @return resulted string
	 */
	public static String classOf(String fullclassname) {
		if(trace) System.out.println((++cpt)+"\tClassof " + fullclassname);
		int index=fullclassname.lastIndexOf(".");
		if(index>0) {
			return fullclassname.substring(index+1);
		} else {
			return fullclassname;	
		}
	}

}