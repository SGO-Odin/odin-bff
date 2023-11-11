package com.odin.odinbff.model.audit;


import com.odin.odinbff.model.HasLongId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class HistoryLoggable<T> {
    @Transient
    public Map<String, Object> logUpdates(T newer, final boolean applyUpdate) {
        final Map<String, Object> data = new HashMap<>();

        attrToUpdateLog().forEach(fieldName -> {
            try {

                final Field field = this.getClass().getDeclaredField(fieldName);

                field.setAccessible(true);

                final Object thisFieldValue = field.get(this);
                final Object newerFieldValue = field.get(newer);

                if (!thisFieldValue.equals(newerFieldValue)) {
                    setLogDataValue(field, thisFieldValue, data);
                    if(applyUpdate)
                        field.set(this, newerFieldValue);
                }

            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return Collections.unmodifiableMap(data);
    }

    private static void setLogDataValue(final Field field, final Object value, final Map<String, Object> data) {
        if(field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(Transient.class)){
            return;
        }
        if (value instanceof HasLongId) {
            data.put(field.getName(), ((HasLongId) value).getId());
        } else if (value instanceof LocalDateTime) {
            data.put(field.getName(), ((LocalDateTime) value).format(DateTimeFormatter.ISO_DATE_TIME));
        } else if(value instanceof LocalDate) {
            data.put(field.getName(), ((LocalDate) value).format(DateTimeFormatter.ISO_DATE));
        }
        else {
            data.put(field.getName(), value);
        }
    }

    @Transient
    public Map<String, Object> logOfAllData() throws IllegalAccessException {

        Field[] declaredFields = this.getClass().getDeclaredFields();

        Map<String, Object> data = new HashMap<>();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            setLogDataValue(field, field.get(this), data);
        }
        return Collections.unmodifiableMap(data);
    }

    @Transient
    protected Set<String> attrToUpdateLog() {
        return Set.of();
    }
}
