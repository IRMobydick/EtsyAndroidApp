package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.datatypes.EtsyId;

public class Team$$PackageHelper {
    public static EtsyId accessTeam$FG$mId(Team team) {
        return team.mId;
    }

    public static String accessTeam$FG$mAvatarUrl(Team team) {
        return team.mAvatarUrl;
    }

    public static String accessTeam$FG$mName(Team team) {
        return team.mName;
    }

    public static String accessTeam$FG$mShortDescription(Team team) {
        return team.mShortDescription;
    }

    public static void accessTeam$FS$mId(Team team, EtsyId etsyId) {
        team.mId = etsyId;
    }

    public static void accessTeam$FS$mAvatarUrl(Team team, String str) {
        team.mAvatarUrl = str;
    }

    public static void accessTeam$FS$mName(Team team, String str) {
        team.mName = str;
    }

    public static void accessTeam$FS$mShortDescription(Team team, String str) {
        team.mShortDescription = str;
    }
}
