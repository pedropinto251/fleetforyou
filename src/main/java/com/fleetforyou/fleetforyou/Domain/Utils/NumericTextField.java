package com.fleetforyou.fleetforyou.Domain.Utils;

import javafx.scene.control.TextField;

public class NumericTextField extends TextField {

    public NumericTextField() {
        super();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        // If the replaced text would not match the desired format, then ignore this call
        if (text.matches("[0-9]*")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        // If the replaced text would not match the desired format, then ignore this call
        if (text.matches("[0-9]*")) {
            super.replaceSelection(text);
        }
    }
}
