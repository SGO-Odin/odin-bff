package com.odin.odinbff.controller.commons;

import com.odin.odinbff.model.Phone;

public final class PhoneResponse {

    private final Boolean isMain;

    private final Phone phone;

    public PhoneResponse(final Phone phone, final Boolean isMain) {
        this.isMain = isMain;
        this.phone = phone;
    }

    public String getPhone() {
        return phone.getPrettyNumber();
    }

    public Boolean isMain() {
        return isMain;
    }
}
