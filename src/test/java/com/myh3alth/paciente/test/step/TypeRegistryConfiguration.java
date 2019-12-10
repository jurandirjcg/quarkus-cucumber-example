package com.myh3alth.paciente.test.step;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;

import br.com.jgon.canary.util.DateUtil;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

/**
 * 
 * @author Jurandir C. Gonçalves <jurandir> - Zion Mountain
 * @since 26/10/2019
 *
 */
public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
	//TODO colocar estes patterns no dateutils do canary
	private static final String regexPatternDate = "(((0?[1-9]|[12][0-9]|3[01])[/-](0[1-9]|1[0-2])[/-]((19|20)\\d\\d))|((19|20)\\d\\d[-/](0[1-9]|1[012])[-/](0[1-9]|[12][0-9]|3[01])))";
	private static final String regexTime = "((0\\d|1\\d|2[0-3]):[0-5]\\d)?(:[0-5]\\d)?(.\\d\\d\\d)?(Z)?(\\+[0-2][0-4]:[0-5]\\d)?";
	@SuppressWarnings("unused")
    private static final String regexPatternDateTime =  regexPatternDate +"(([\\s]|T|'T')?" + regexTime + ")?";

	@Override
	public Locale locale() {
		return LocaleUtils.toLocale("pt");
	}
	
    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
            "date",
            "\\d{4}\\-\\d{2}\\-\\d{2}",//regexPatternDateTime,
            Date.class,
            (String s) -> DateUtil.parseDate(s))
        );
        
        typeRegistry.defineParameterType(new ParameterType<>(
            "localDate",
            "\\d{4}\\-\\d{2}\\-\\d{2}",
            LocalDate.class,
            (String s) -> LocalDate.parse(s))
        );
        
//        typeRegistry.defineDataTableType(new DataTableType(
//            Date.class,
//            (String s) -> new Date())
//        );
    }
}