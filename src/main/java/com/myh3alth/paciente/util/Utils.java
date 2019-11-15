package com.myh3alth.paciente.util;

/**
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 22/10/2019
 *
 */
public abstract class Utils {
    public static final Integer MAX_COUNT = 100;
    public static final String DEFAULT_REST_PAGE = "1";
    public static final String DEFAULT_REST_LIMIT = "20";
    public static final long DEFAULT_CIRCUIT_BRAKER_DELAY = 10000;
    public static final int DEFAULT_CIRCUIT_BRAKER_REQUEST_VOLUME_THRESHOLD = 5;
    public static final double DEFAULT_CIRCUIT_BRAKER_FAILURE_RATIO = 0.75;
    public static final int DEFAULT_CIRCUIT_BRAKER_SUCCESS_THRESHOLD = 10;
    public static final int DEFAULT_RETRY_MAX_RETRIES = 2;
    public static final long MEDIUM_TIMETOUT = 3000;
    public static final long SHORT_TIMETOUT = 2000;
    public static final long BIG_TIMETOUT = 5000;

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param qtde {@link Integer}
     * @return {@link Integer}
     */
    public static Integer checkLimit(final Integer qtde) {
        if (qtde > MAX_COUNT) {
            return MAX_COUNT;
        }
        return qtde;
    }

    /**
     * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
     * @since 29/10/2019
     *
     * @param enumeration {@link Enum}
     * @return {@link String}
     */
    public static String[] enumStringValues(final Enum<?>... enumeration) {
        String[] listString = new String[enumeration.length];
        for (int i = 0; i < enumeration.length; i++) {
            listString[i] = (enumeration[i].name());
        }
        return listString;
    }
}
