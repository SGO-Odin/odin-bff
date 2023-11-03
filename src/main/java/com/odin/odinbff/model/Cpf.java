package com.odin.odinbff.model;

import com.odin.odinbff.util.MaskFormatter;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Embeddable
public final class Cpf {
    @NotBlank
    @CPF
    private final String cpf;

    public Cpf(final String cpf) {
        this.cpf = cpf;
    }

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private Cpf(){
        this(null);
    }

    public String getNumbers() {
        return cpf;
    }

    private boolean isValid() {
       // TODO: implement validation to cpf
        return true;
    }

    private String getPrettyFormat() {
        return new MaskFormatter("###.***.***-**", '*').format(getNumbers());
    }
}
