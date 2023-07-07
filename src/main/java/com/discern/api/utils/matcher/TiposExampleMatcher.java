package com.discern.api.utils.matcher;

import org.springframework.data.domain.ExampleMatcher;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

/**
 * @author Davi
 * @created 21/jun./2023
 */

public class TiposExampleMatcher {

    /**
     * Faz a busca por matchingAny campo or campo or campo
     * <p>
     * Fusca por StringMatcher CONTAINING que seria o like
     * <p>
     * Ignora se está maiúsculo ou minúsculo
     * <p>
     * Não trata os valores nulos
     */
    public static ExampleMatcher exampleMatcherMatchingAny() {
        return ExampleMatcher
                .matchingAny() // campo or campo or campo
                // .matchingAll() // campo and campo
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // faz a consulta com o like
                .withIgnoreCase() // ignora se está maiúsculo ou minúsculo
                .withIgnoreNullValues();

    }

    public static ExampleMatcher exampleMatcherMatching() {
        return ExampleMatcher
                 .matching() // campo and campo and campo
                // .matchingAll() // campo and campo
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // faz a consulta com o like
                .withIgnoreCase() // ignora se está maiúsculo ou minúsculo
                .withIgnoreNullValues();

    }

    public static ExampleMatcher exampleMatcherEquals() {
        return ExampleMatcher
                //.matchingAny() // campo or campo or campo
                .matching() // campo and campo and campo
                // .matchingAll() // campo and campo
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT) // faz a consulta com o like
                .withIgnoreCase() // ignora se está maiúsculo ou minúsculo
                .withIgnoreNullValues();
    }

    public static ExampleMatcher exampleMatcherSelctCampo(String Campo) {
        return ExampleMatcher
                .matching()
                .withIgnorePaths("id")
                .withMatcher(Campo, ignoreCase());
    }
}
