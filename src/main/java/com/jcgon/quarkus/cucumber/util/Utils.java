package com.jcgon.quarkus.cucumber.util;

import java.util.Arrays;
import java.util.List;

import com.jcgon.quarkus.cucumber.config.ConfigController;

public abstract class Utils {
    /**
     * Check if "limit" parameter exceed the maximun limit value for the
     * application, if yes return the maximun value otherwise return the parameter
     * value
     * 
     * @param qtde
     * @return
     */
    public static Integer checkLimit(final Integer limit) {
        if (limit > ConfigController.REST_MAX_RESULT_LIST) {
            return ConfigController.REST_MAX_RESULT_LIST;
        }
        return limit;
    }

    /**
     * Check if fields parameter has value, if has no value return defaultFields
     * parameter
     * 
     * @param fields
     * @param defaultFields
     * @return
     */
    public static List<String> checkFields(final List<String> fields, String... defaultFields) {
        if (fields == null || fields.isEmpty()) {
            return Arrays.asList(defaultFields);
        }
        return fields;
    }
}
