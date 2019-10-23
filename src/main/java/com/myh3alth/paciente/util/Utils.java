package com.myh3alth.paciente.util;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
public abstract class Utils {
	public final static Integer MAX_COUNT = 100;
	public final static String DEFAULT_REST_PAGE = "1";
	public final static String DEFAULT_REST_LIMIT = "20";
	public final static long DEFAULT_REST_TIMEOUT = 2000L;
	
	/**
	 * 
	 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
	 * @since 22/10/2019
	 *
	 * @param qtde
	 * @return {@link Integer}
	 */
	public static Integer checkLimit(Integer qtde) {
		if(qtde > MAX_COUNT) {
			return MAX_COUNT;
		}
		return qtde;
	}
	
//	public static <T extends ResponseConverter<E>, E> T checkResponse(E value, Class<T> returnClass) {
//		if(value == null) {
//			return null;
//		}
//		
//		T ret = null;
//		try {
//			ret = returnClass.newInstance();
//			ret.converter(value);
//		} catch (InstantiationException | IllegalAccessException e) {
//			logger.error(e.getMessage());
//		}
//		
//		return ret;
//	}
	
	public static String[] enumStringValues(Enum<?>... enumeration) {
		String[] listString = new String[enumeration.length];
		for(int i=0; i < enumeration.length; i++) {
			listString[i] = (enumeration[i].name());
		}
		return listString;
	}
}
