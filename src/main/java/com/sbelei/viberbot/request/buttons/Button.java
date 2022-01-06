package com.sbelei.viberbot.request.buttons;

public class Button {
    private String ActionType;
    private String ActionBody;
    private String Text;
    private String TextSize;

    public String getActionType() {
        return ActionType;
    }

    public void setActionType(String actionType) {
        ActionType = actionType;
    }

    public String getActionBody() {
        return ActionBody;
    }

    public void setActionBody(String actionBody) {
        ActionBody = actionBody;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTextSize() {
        return TextSize;
    }

    public void setTextSize(String textSize) {
        TextSize = textSize;
    }
}
