package com.odin.odinbff.model.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.Serializable;
import java.util.Map;

@Converter
public class LogDiffConverter
        implements AttributeConverter<Map<String, Object>, String>, Serializable {



        @Override public String convertToDatabaseColumn (Map<String, Object> attribute){
            if (attribute == null) {
                return null;
            }
            try {
                return new ObjectMapper().writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                return null;
            }
        }

        @Override public Map<String, Object> convertToEntityAttribute (String dbData){
            if (dbData == null) {
                return null;
            }
            try {
                return new ObjectMapper().readValue(dbData, new TypeReference<>() {} );
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }