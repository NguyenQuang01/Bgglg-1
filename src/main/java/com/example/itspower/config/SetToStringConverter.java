package com.example.itspower.config;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetToStringConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        return strings == null ? null : String.join(",",strings);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return dbData == null ? Collections.emptySet() : new HashSet(Arrays.asList(dbData.split(",")));
    }
}
