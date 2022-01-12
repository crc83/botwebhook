package com.sbelei.botapi.viber.request.buttons;

public class Keyboard {
    public String Type;
    public boolean DefaultHeight;
    public Button[] Buttons;

    public Keyboard() {
        Type = "keyboard";
        DefaultHeight = false;
        Buttons = new Button[]{};
    }

    public Keyboard(Button... buttons) {
        this();
        Buttons = buttons;
    }
}
