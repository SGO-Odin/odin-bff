package com.odin.odinbff.util;

import jakarta.annotation.Nullable;

public class MaskFormatter {

    private final String pattern;

    @Nullable
    private final Character splitter;

   public MaskFormatter(final String pattern) {
       this(pattern, null);
   }

    public MaskFormatter(final String pattern, @Nullable Character splitter) {
        this.pattern = pattern;
        this.splitter = splitter;
    }

   public String format(final String text) {
        final char[] patternArr = pattern.toCharArray();
        final char[] textArr = text.toCharArray();

        short textI = 0;

        for(short patternI = 0; patternI < patternArr.length; patternI++) {
            if (splitter == null || patternArr[patternI] != splitter) {
                if (patternArr[patternI] == '#' && textI < textArr.length) {
                    patternArr[patternI] = textArr[textI];
                    textI++;
                }
            }
        }

        return new String(patternArr);
    }
}