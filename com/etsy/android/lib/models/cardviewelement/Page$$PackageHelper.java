package com.etsy.android.lib.models.cardviewelement;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.homescreen.MessageCard;
import java.util.List;

public class Page$$PackageHelper {
    public static MessageCard accessPage$FG$mMessageCard(Page page) {
        return page.mMessageCard;
    }

    public static String accessPage$FG$mTitle(Page page) {
        return page.mTitle;
    }

    public static BaseModel accessPage$FG$mMetadata(Page page) {
        return page.mMetadata;
    }

    public static List accessPage$FG$mListSections(Page page) {
        return page.mListSections;
    }

    public static void accessPage$FS$mMessageCard(Page page, MessageCard messageCard) {
        page.mMessageCard = messageCard;
    }

    public static void accessPage$FS$mTitle(Page page, String str) {
        page.mTitle = str;
    }

    public static void accessPage$FS$mMetadata(Page page, BaseModel baseModel) {
        page.mMetadata = baseModel;
    }

    public static void accessPage$FS$mListSections(Page page, List list) {
        page.mListSections = list;
    }
}
