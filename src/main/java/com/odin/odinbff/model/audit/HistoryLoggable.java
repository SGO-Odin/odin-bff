package com.odin.odinbff.model.audit;


import com.odin.odinbff.model.HasLongId;
import jakarta.persistence.Transient;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class HistoryLoggable<T> {
    Map<String, Object> update(T newer) {
        final Map<String, Object> data = new HashMap<>();

        attrToUpdateLog().forEach(fieldName -> {
            try {

                final Field thisField = this.getClass().getDeclaredField(fieldName);
                final Field newerField = newer.getClass().getDeclaredField(fieldName);

                newerField.setAccessible(true);
                thisField.setAccessible(true);

                final Object thisFieldValue = thisField.get(this);
                final Object newerFieldValue = newerField.get(newer);

                if (!thisFieldValue.equals(newerFieldValue)) {
                    if (thisFieldValue instanceof HasLongId) {
                        data.put(fieldName, ((HasLongId) thisFieldValue).getId());
                    } else if (thisFieldValue instanceof LocalDateTime) {
                        data.put(fieldName, ((LocalDateTime) thisFieldValue).format(DateTimeFormatter.ISO_DATE_TIME));
                    } else {
                        data.put(fieldName, thisFieldValue);
                    }
                    thisField.set(this, newerFieldValue);
                }

            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return Collections.unmodifiableMap(data);
    }

    @Transient
    Map<String, Object> logOfAllData() throws IllegalAccessException {

        Field[] declaredFields = this.getClass().getDeclaredFields();

        Map<String, Object> data = new HashMap<>();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            final Object fieldValue = field.get(this);
            if (fieldValue instanceof HasLongId) {
                data.put(field.getName(), ((HasLongId) field.get(this)).getId());
            } else if (fieldValue instanceof LocalDateTime) {
                data.put(field.getName(), ((LocalDateTime) fieldValue).format(DateTimeFormatter.ISO_DATE_TIME));
            } else {
                data.put(field.getName(), fieldValue);
            }
        }
        return Collections.unmodifiableMap(data);
    }

    @Transient
    protected abstract Set<String> attrToUpdateLog();
}
