package com.example.scrappost.configs;

import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
@Configuration
public class ValidationDirectiveConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        ValidationRules validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .build();

        ValidationSchemaWiring schemaWiring = new ValidationSchemaWiring(validationRules);
        return builder -> builder.directiveWiring(schemaWiring).build();
    }
}