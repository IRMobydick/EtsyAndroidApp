package com.appboy.ui.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.appboy.Constants;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.configuration.XmlUIConfigurationProvider;
import com.appboy.ui.widget.BannerImageCardView;
import com.appboy.ui.widget.BaseCardView;
import com.appboy.ui.widget.CaptionedImageCardView;
import com.appboy.ui.widget.CrossPromotionSmallCardView;
import com.appboy.ui.widget.DefaultCardView;
import com.appboy.ui.widget.ShortNewsCardView;
import com.appboy.ui.widget.TextAnnouncementCardView;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppboyListAdapter extends ArrayAdapter<Card> {
    private static final String TAG;
    private final Set<String> mCardIdImpressions;
    private final Context mContext;
    private final XmlUIConfigurationProvider mUiConfigurationProvider;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyListAdapter.class.getName()});
    }

    public AppboyListAdapter(Context context, int i, List<Card> list) {
        super(context, i, list);
        this.mContext = context;
        this.mCardIdImpressions = new HashSet();
        this.mUiConfigurationProvider = new XmlUIConfigurationProvider(context);
    }

    public int getViewTypeCount() {
        return 8;
    }

    public int getItemViewType(int i) {
        Card card = (Card) getItem(i);
        if (card instanceof BannerImageCard) {
            return 1;
        }
        if (card instanceof CaptionedImageCard) {
            return 2;
        }
        if (card instanceof CrossPromotionSmallCard) {
            return 3;
        }
        if (card instanceof ShortNewsCard) {
            return 4;
        }
        if (card instanceof TextAnnouncementCard) {
            return 5;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Card card = (Card) getItem(i);
        if (view != null) {
            AppboyLogger.m662d(TAG, "Reusing convertView for rendering of item " + i);
            BaseCardView baseCardView = (BaseCardView) view;
        } else if (card instanceof BannerImageCard) {
            view = new BannerImageCardView(this.mContext);
        } else if (card instanceof CaptionedImageCard) {
            view = new CaptionedImageCardView(this.mContext);
        } else if (card instanceof CrossPromotionSmallCard) {
            view = new CrossPromotionSmallCardView(this.mContext);
        } else if (card instanceof ShortNewsCard) {
            view = new ShortNewsCardView(this.mContext);
        } else if (card instanceof TextAnnouncementCard) {
            view = new TextAnnouncementCardView(this.mContext);
        } else {
            view = new DefaultCardView(this.mContext);
        }
        AppboyLogger.m662d(TAG, String.format("Using view of type: %s for card at position %d: %s", new Object[]{view.getClass().getName(), Integer.valueOf(i), card.toString()}));
        view.setCard(card);
        logCardImpression(card);
        return view;
    }

    public synchronized void replaceFeed(List<Card> list) {
        int i = 0;
        synchronized (this) {
            setNotifyOnChange(false);
            if (list == null) {
                clear();
                notifyDataSetChanged();
            } else {
                AppboyLogger.m662d(TAG, String.format("Replacing existing feed of %d cards with new feed containing %d cards.", new Object[]{Integer.valueOf(getCount()), Integer.valueOf(list.size())}));
                int size = list.size();
                int i2 = 0;
                while (i2 < getCount()) {
                    Card card = (Card) getItem(i2);
                    Card card2 = null;
                    if (i < size) {
                        card2 = (Card) list.get(i);
                    }
                    if (card2 == null || !card2.isEqualToCard(card)) {
                        remove(card);
                    } else {
                        i++;
                        i2++;
                    }
                }
                if (VERSION.SDK_INT < 11) {
                    for (int i3 = i; i3 < size; i3++) {
                        add((Card) list.get(i3));
                    }
                } else {
                    addAllBatch(list.subList(i, size));
                }
                notifyDataSetChanged();
            }
        }
    }

    public synchronized void add(Card card) {
        super.add(card);
    }

    @TargetApi(11)
    private synchronized void addAllBatch(Collection<Card> collection) {
        super.addAll(collection);
    }

    public void resetCardImpressionTracker() {
        this.mCardIdImpressions.clear();
    }

    private void logCardImpression(Card card) {
        String id = card.getId();
        if (this.mCardIdImpressions.contains(id)) {
            AppboyLogger.m662d(TAG, String.format("Already counted impression for card %s", new Object[]{id}));
        } else {
            this.mCardIdImpressions.add(id);
            card.logImpression();
            AppboyLogger.m662d(TAG, String.format("Logged impression for card %s", new Object[]{id}));
        }
        if (!card.getViewed()) {
            card.setViewed(true);
        }
    }

    public void batchSetCardsToRead(int i, int i2) {
        if (getCount() == 0) {
            AppboyLogger.m662d(TAG, "mAdapter is empty in setting some cards to viewed.");
            return;
        }
        int max = Math.max(0, i);
        int min = Math.min(getCount(), i2);
        for (int i3 = max; i3 < min; i3++) {
            Card card = (Card) getItem(i3);
            if (card == null) {
                AppboyLogger.m662d(TAG, "Card was null in setting some cards to viewed.");
                return;
            }
            if (!card.isRead()) {
                card.setIsRead(true);
            }
        }
    }
}
