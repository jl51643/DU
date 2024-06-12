package com.fer.hr.du.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                ObjectMapper.DefaultTyping.NON_FINAL
        );
        return objectMapper;
    }
}*/

