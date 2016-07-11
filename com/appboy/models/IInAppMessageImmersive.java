package com.appboy.models;

import java.util.List;

public interface IInAppMessageImmersive extends IInAppMessage {
    int getCloseButtonColor();

    String getHeader();

    int getHeaderTextColor();

    List<MessageButton> getMessageButtons();

    boolean logButtonClick(MessageButton messageButton);

    void setCloseButtonColor(int i);

    void setHeader(String str);

    void setHeaderTextColor(int i);

    void setMessageButtons(List<MessageButton> list);
}
