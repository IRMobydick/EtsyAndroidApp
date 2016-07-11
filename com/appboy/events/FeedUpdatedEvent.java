package com.appboy.events;

import com.appboy.Constants;
import com.appboy.enums.CardCategory;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public final class FeedUpdatedEvent {
    private static final String f889a;
    private final List<Card> f890b;
    private final String f891c;
    private final boolean f892d;
    private final long f893e;

    static {
        f889a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, FeedUpdatedEvent.class.getName()});
    }

    public FeedUpdatedEvent(List<Card> list, String str, boolean z, long j) {
        this.f891c = str;
        this.f892d = z;
        if (list == null) {
            throw new NullPointerException();
        }
        this.f890b = list;
        this.f893e = j;
    }

    public final boolean isFromOfflineStorage() {
        return this.f892d;
    }

    public final List<Card> getFeedCards() {
        return getFeedCards(CardCategory.ALL_CATEGORIES);
    }

    public final List<Card> getFeedCards(CardCategory cardCategory) {
        return getFeedCards(EnumSet.of(cardCategory));
    }

    public final List<Card> getFeedCards(EnumSet<CardCategory> enumSet) {
        try {
            if (this.f890b == null) {
                AppboyLogger.m666i(f889a, "There are no cards targeted to this user in the Appboy News Feed, you can call Appboy.requestFeedRefresh(),and try to call getFeedCards(categories) again when you receive a new feed updated event, or check your Appboy dashboard and make sure there are cards in the segment.");
                return new ArrayList();
            }
            if (enumSet == null) {
                AppboyLogger.m666i(f889a, "The categories passed to getFeedCards are null, FeedUpdatedEvent is going to return all the cards in cache.");
                enumSet = CardCategory.ALL_CATEGORIES;
            }
            if (enumSet.isEmpty()) {
                AppboyLogger.m670w(f889a, "The parameter passed into categories is not valid, Appboy is returning an empty card list.Please pass in a non-empty EnumSet of CardCategory for getFeedCards().");
                return new ArrayList();
            }
            List<Card> arrayList = new ArrayList();
            for (Card card : this.f890b) {
                if (card.isInCategorySet(enumSet) && !card.isExpired()) {
                    arrayList.add(card);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            AppboyLogger.m671w(f889a, String.format("Unable to get cards with categories[%s]. Ignoring.", new Object[]{enumSet}), e);
            return null;
        }
    }

    public final String getUserId() {
        return this.f891c;
    }

    public final int getCardCount() {
        return getCardCount(CardCategory.ALL_CATEGORIES);
    }

    public final int getCardCount(CardCategory cardCategory) {
        return getCardCount(EnumSet.of(cardCategory));
    }

    public final int getCardCount(EnumSet<CardCategory> enumSet) {
        if (enumSet == null) {
            AppboyLogger.m666i(f889a, "The categories passed into getCardCount are null, FeedUpdatedEvent is going to return the count of all the cards in cache.");
            return this.f890b.size();
        } else if (!enumSet.isEmpty()) {
            return getFeedCards((EnumSet) enumSet).size();
        } else {
            AppboyLogger.m670w(f889a, "The parameters passed into categories are not valid, Appboy is returning 0 in getCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
    }

    public final int getUnreadCardCount() {
        return getUnreadCardCount(CardCategory.ALL_CATEGORIES);
    }

    public final int getUnreadCardCount(CardCategory cardCategory) {
        return getUnreadCardCount(EnumSet.of(cardCategory));
    }

    public final int getUnreadCardCount(EnumSet<CardCategory> enumSet) {
        while (enumSet == null) {
            AppboyLogger.m670w(f889a, "The categories passed to getUnreadCardCount are null, FeedUpdatedEvent is going to return the count of all the unread cards in cache.");
            enumSet = CardCategory.ALL_CATEGORIES;
        }
        if (enumSet.isEmpty()) {
            AppboyLogger.m670w(f889a, "The parameters passed into categories are Empty, Appboy is returning 0 in getUnreadCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
        int i = 0;
        for (Card card : this.f890b) {
            int i2;
            if (!card.isInCategorySet(enumSet) || card.getViewed() || card.isExpired()) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        return i;
    }

    public final long lastUpdatedInSecondsFromEpoch() {
        return this.f893e;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("FeedUpdatedEvent{");
        stringBuilder.append("mFeedCards=").append(this.f890b);
        stringBuilder.append(", mUserId='").append(this.f891c).append('\'');
        stringBuilder.append(", mFromOfflineStorage=").append(this.f892d);
        stringBuilder.append(", mTimestamp=").append(this.f893e);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
