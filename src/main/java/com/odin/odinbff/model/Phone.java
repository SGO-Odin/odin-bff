package com.odin.odinbff.model;

import com.odin.odinbff.util.MaskFormatter;
import jakarta.persistence.Embeddable;

@Embeddable
public class Phone {
    private final String ddd;
    private final String number;

    private Phone(String ddd, String onlyNumber, String number) {
        this.ddd = ddd;
        this.number = onlyNumber;
    }

    public String getDdd() {
        return ddd;
    }

    public String getPrettyNumber() {
        return new MaskFormatter("(##) #####-####").format(this.number);
    }

    public String getNumber() {
        return number;
    }
}