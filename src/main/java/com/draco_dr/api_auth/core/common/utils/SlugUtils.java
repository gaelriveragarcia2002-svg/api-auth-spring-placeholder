package com.draco_dr.api_auth.core.common.utils;

public final class SlugUtils {

    private SlugUtils() {}

    public static String toSlug(String value) {
        return value.toLowerCase().trim().replaceAll("\\s+", "_");
    }
}