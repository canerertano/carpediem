package com.carpediem.skeleton.model.enumaration;

import java.util.Locale;

public enum StatusEnum {
    ACTIVE, PASSIVE;

    public static StatusEnum fromValue(String v) {
        StatusEnum enumValue = null;
        try {
            enumValue = valueOf(v.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return null;
        }
        return enumValue;
    }

    public String value() {
        return name();
    }
}
