package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.DismissType;
import java.util.Map;
import org.json.JSONObject;

public interface IInAppMessage {
    JSONObject forJsonPut();

    boolean getAnimateIn();

    boolean getAnimateOut();

    int getBackgroundColor();

    Bitmap getBitmap();

    ClickAction getClickAction();

    DismissType getDismissType();

    int getDurationInMilliseconds();

    Map<String, String> getExtras();

    String getIcon();

    int getIconBackgroundColor();

    int getIconColor();

    boolean getImageDownloadSuccessful();

    String getImageUrl();

    String getMessage();

    int getMessageTextColor();

    Uri getUri();

    boolean logClick();

    boolean logImpression();

    void setAnimateIn(boolean z);

    void setAnimateOut(boolean z);

    void setBackgroundColor(int i);

    void setBitmap(Bitmap bitmap);

    boolean setClickAction(ClickAction clickAction);

    boolean setClickAction(ClickAction clickAction, Uri uri);

    void setDismissType(DismissType dismissType);

    void setDurationInMilliseconds(int i);

    void setIcon(String str);

    void setIconBackgroundColor(int i);

    void setIconColor(int i);

    void setImageDownloadSuccessful(boolean z);

    void setImageUrl(String str);

    void setMessage(String str);

    void setMessageTextColor(int i);
}
