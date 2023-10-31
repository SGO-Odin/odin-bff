package com.odin.odinbff.util;

public class MaskFormatter {

    private final String pattern;


    private final char splitter;

   public MaskFormatter(final String pattern) {
       this(pattern, null);
   }

    public MaskFormatter(final String pattern, Character splitter) {
        this.pattern = pattern;
        this.splitter = splitter;
    }

   public String format(final String text) {
        final char[] patternArr = pattern.toCharArray();
        final char[] textArr = text.toCharArray();

        short textI = 0;

        for(short patternI = 0; patternI < patternArr.length; patternI++) {
            if (patternArr[patternI] != splitter) {
                if (patternArr[patternI] == '#' && textI < textArr.length) {
                    patternArr[patternI] = textArr[textI];
                }
                textI++;
            }
        }

        return new String(patternArr);
    }
}