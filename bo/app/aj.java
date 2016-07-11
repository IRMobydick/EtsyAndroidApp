package bo.app;

import com.appboy.models.IPutIntoJson;

public enum aj implements IPutIntoJson<String> {
    FEED,
    INAPP,
    CONFIG
}
