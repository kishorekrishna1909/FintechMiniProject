package com.example.CardService.Model;

import jakarta.validation.constraints.Size;

public class PinUpdateRequest {
    @Size(min = 4, max = 4, message = "PIN must be exactly 4 digits")
    private String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
