package com.sbelei.viberbot.request.buttons;

public class Keyboard {
    private String Type;
    private boolean DefaultHeight;
    private Button[] Buttons;

    public Keyboard() {
        Type = "keyboard";
        DefaultHeight = false;
        Buttons = new Button[]{};
    }

    public Keyboard(Button... buttons) {
        super();
        Buttons = buttons;
    }

    public String getType() {
        return Type;
    }

    public boolean isDefaultHeight() {
        return DefaultHeight;
    }

    public Button[] getButtons() {
        return Buttons;
    }

    public void setButtons(Button[] buttons) {
        Buttons = buttons;
    }
}
