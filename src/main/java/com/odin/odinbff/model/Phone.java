package com.odin.odinbff.model;

import com.odin.odinbff.util.MaskFormatter;
import jakarta.persistence.Embeddable;

@Embeddable
public final class Phone {
    private final String ddd;
    private final String number;

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private Phone() {
        this(null, null);
    }

    public Phone(final String ddd, final String number) {
        this.ddd = ddd;
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public String getPrettyNumber() {
        return (new MaskFormatter("(##) #####-####").format(getFullNumber()));
    }

    public String getFullNumber(){
        return ddd + number;
    }

    public String getNumber() {
        return number;
    }
}