package com.example.AccountService.Model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

@Converter
public class StringListConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        return set != null ? String.join(",", set) : "";
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        return (joined != null && !joined.isEmpty())
            ? new HashSet<>(Arrays.asList(joined.split(",")))
            : new HashSet<>();
    }
}
        