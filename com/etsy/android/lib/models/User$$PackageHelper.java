package com.etsy.android.lib.models;

import com.etsy.android.lib.models.User.PublicKey;
import com.etsy.android.lib.models.apiv3.Image;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.List;

public class User$$PackageHelper {
    public static PublicKey accessUser$FG$mPublicKey(User user) {
        return user.mPublicKey;
    }

    public static Image accessUser$FG$mAvatar(User user) {
        return user.mAvatar;
    }

    public static UserProfile accessUser$FG$mProfile(User user) {
        return user.mProfile;
    }

    public static int accessUser$FG$mAwaitingFeedbackCount(User user) {
        return user.mAwaitingFeedbackCount;
    }

    public static EtsyId accessUser$FG$mUserId(User user) {
        return user.mUserId;
    }

    public static String accessUser$FG$mLoginName(User user) {
        return user.mLoginName;
    }

    public static String accessUser$FG$mEmail(User user) {
        return user.mEmail;
    }

    public static List accessUser$FG$mShops(User user) {
        return user.mShops;
    }

    public static List accessUser$FG$mFavoriteListings(User user) {
        return user.mFavoriteListings;
    }

    public static int accessUser$FG$mFollowingCount(User user) {
        return user.mFollowingCount;
    }

    public static int accessUser$FG$mFollowerCount(User user) {
        return user.mFollowerCount;
    }

    public static List accessUser$FG$mUserAddresses(User user) {
        return user.mUserAddresses;
    }

    public static void accessUser$FS$mPublicKey(User user, PublicKey publicKey) {
        user.mPublicKey = publicKey;
    }

    public static void accessUser$FS$mAvatar(User user, Image image) {
        user.mAvatar = image;
    }

    public static void accessUser$FS$mProfile(User user, UserProfile userProfile) {
        user.mProfile = userProfile;
    }

    public static void accessUser$FS$mAwaitingFeedbackCount(User user, int i) {
        user.mAwaitingFeedbackCount = i;
    }

    public static void accessUser$FS$mUserId(User user, EtsyId etsyId) {
        user.mUserId = etsyId;
    }

    public static void accessUser$FS$mLoginName(User user, String str) {
        user.mLoginName = str;
    }

    public static void accessUser$FS$mEmail(User user, String str) {
        user.mEmail = str;
    }

    public static void accessUser$FS$mShops(User user, List list) {
        user.mShops = list;
    }

    public static void accessUser$FS$mFavoriteListings(User user, List list) {
        user.mFavoriteListings = list;
    }

    public static void accessUser$FS$mFollowingCount(User user, int i) {
        user.mFollowingCount = i;
    }

    public static void accessUser$FS$mFollowerCount(User user, int i) {
        user.mFollowerCount = i;
    }

    public static void accessUser$FS$mUserAddresses(User user, List list) {
        user.mUserAddresses = list;
    }
}
