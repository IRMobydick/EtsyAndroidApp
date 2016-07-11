package com.etsy.android.lib.models.homescreen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface LandingPageInfo extends Serializable {
    String getAPIPath();

    boolean getBooleanOption(String str);

    String getEventName();

    int getLayout();

    Map<String, String> getOptions();

    String getPageTitle();

    String getPageType();

    HashMap<String, String> getParams();

    int getRequestMethod();
}
