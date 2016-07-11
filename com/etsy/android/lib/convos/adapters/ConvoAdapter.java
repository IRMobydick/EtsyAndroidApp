package com.etsy.android.lib.convos.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.convos.ConvoMessageCallbacks;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ad;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.bl;
import com.etsy.android.uikit.adapter.BaseCursorAdapter;
import com.etsy.android.uikit.util.GraphikUtil;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.FullImageView;
import com.etsy.android.uikit.view.ReadStateRelativeLayout;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConvoAdapter extends BaseCursorAdapter {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int STATE_HAS_HEADER = 1;
    private static final int STATE_NO_HEADER = 2;
    private static final int STATE_UNKNOWN = 0;
    private final int mAvatarWidth;
    private int mBorderMargin;
    private int mBorderMarginDimenId;
    private StringBuilder mBuffer;
    private final Calendar mCalendar;
    private ConvoMessageCallbacks mCallbacks;
    private int mCurrentYear;
    private int[] mHeaderCache;
    private final boolean mIsCustomShopsEnabled;
    private Map<Long, Integer> mPositions;
    private final Resources mRes;

    /* renamed from: com.etsy.android.lib.convos.adapters.ConvoAdapter.1 */
    class C04401 extends TrackingOnClickListener {
        final /* synthetic */ EtsyId f1310a;
        final /* synthetic */ ConvoAdapter f1311b;

        C04401(ConvoAdapter convoAdapter, AnalyticsLogAttribute analyticsLogAttribute, EtsyId etsyId, EtsyId etsyId2) {
            this.f1311b = convoAdapter;
            this.f1310a = etsyId2;
            super(analyticsLogAttribute, etsyId);
        }

        public void onViewClick(View view) {
            if (this.f1311b.mCallbacks != null) {
                this.f1311b.mCallbacks.m998a(this.f1310a);
            }
        }
    }

    static {
        $assertionsDisabled = !ConvoAdapter.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public ConvoAdapter(FragmentActivity fragmentActivity, int i, int i2, ImageBatch imageBatch, @NonNull ad adVar, ConvoMessageCallbacks convoMessageCallbacks) {
        super(fragmentActivity, i, imageBatch);
        this.mHeaderCache = new int[0];
        this.mCallbacks = convoMessageCallbacks;
        this.mRes = fragmentActivity.getResources();
        this.mAvatarWidth = this.mRes.getDimensionPixelSize(R.conversation_avatar);
        this.mBorderMarginDimenId = i2;
        this.mBorderMargin = this.mRes.getDimensionPixelSize(this.mBorderMarginDimenId);
        this.mCalendar = Calendar.getInstance(Locale.getDefault());
        this.mCalendar.setTimeInMillis(System.currentTimeMillis());
        this.mCurrentYear = this.mCalendar.get(STATE_HAS_HEADER);
        this.mPositions = new ConcurrentHashMap();
        this.mBuffer = new StringBuilder();
        this.mIsCustomShopsEnabled = adVar.m1864f().m885c(EtsyConfigKeys.cp);
    }

    public ConvoAdapter(FragmentActivity fragmentActivity, ImageBatch imageBatch, @NonNull ad adVar, ConvoMessageCallbacks convoMessageCallbacks) {
        this(fragmentActivity, R.list_item_conversation, R.convo_list_border_margin, imageBatch, adVar, convoMessageCallbacks);
    }

    protected void onContentChanged() {
        super.onContentChanged();
        this.mHeaderCache = new int[getCount()];
        cacheIdPositions($assertionsDisabled);
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor swapCursor = super.swapCursor(cursor);
        if (cursor != null) {
            this.mHeaderCache = new int[cursor.getCount()];
            cacheIdPositions(true);
        }
        return swapCursor;
    }

    private void cacheIdPositions(boolean z) {
        if (z) {
            this.mPositions.clear();
        }
        Cursor cursor = getCursor();
        if (cursor != null && !cursor.isClosed()) {
            int position = cursor.getPosition();
            if (cursor.moveToFirst()) {
                do {
                    this.mPositions.put(Long.valueOf(cursor.getLong(STATE_HAS_HEADER)), Integer.valueOf(cursor.getPosition()));
                } while (cursor.moveToNext());
            }
            cursor.moveToPosition(position);
        }
    }

    public Cursor getItem(int i) {
        return (Cursor) super.getItem(i);
    }

    public int getPositionForConvoId(long j) {
        Integer num = (Integer) this.mPositions.get(Long.valueOf(j));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public void refreshListView(Resources resources) {
        this.mBorderMargin = resources.getDimensionPixelSize(this.mBorderMarginDimenId);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(getLayoutId(), null);
        ConvoAdapter createConversationHolder = createConversationHolder(inflate);
        if ($assertionsDisabled || inflate != null) {
            inflate.setTag(createConversationHolder);
            return inflate;
        }
        throw new AssertionError();
    }

    public void bindView(View view, Context context, Cursor cursor) {
        if ($assertionsDisabled || view != null) {
            ConvoAdapter convoAdapter = (ConvoAdapter) view.getTag();
            if (convoAdapter == null) {
                convoAdapter = createConversationHolder(view);
            }
            if ($assertionsDisabled || convoAdapter != null) {
                int position = cursor.getPosition();
                convoAdapter.f1314b.setText(cursor.getString(10));
                convoAdapter.f1315c.setText(cursor.getString(7));
                convoAdapter.f1316d.setText(DateUtils.getRelativeTimeSpanString(cursor.getLong(3)));
                convoAdapter.f1319g.setText(cursor.getString(4));
                getImageBatch().m1574b(cursor.getString(11), convoAdapter.f1318f, this.mAvatarWidth);
                EtsyId etsyId = new EtsyId(cursor.getString(8));
                convoAdapter.f1318f.setOnClickListener(new C04401(this, AnalyticsLogAttribute.TARGET_USER_ID, etsyId, etsyId));
                boolean z = cursor.getInt(STATE_NO_HEADER) == STATE_HAS_HEADER ? true : $assertionsDisabled;
                if (convoAdapter.f1326n != null) {
                    setupNewStyle(z, convoAdapter, position, cursor);
                } else {
                    convoAdapter.f1313a.setRead(z);
                }
                if (cursor.getInt(6) == STATE_HAS_HEADER) {
                    convoAdapter.f1320h.setVisibility(0);
                } else {
                    convoAdapter.f1320h.setVisibility(4);
                }
                if (this.mIsCustomShopsEnabled && convoAdapter.f1325m != null) {
                    if (cursor.getInt(12) == STATE_HAS_HEADER) {
                        if (!GraphikUtil.m5547a(convoAdapter.f1325m, R.typeface_bold)) {
                            convoAdapter.f1325m.setTypeface(Typeface.DEFAULT);
                        }
                        convoAdapter.f1325m.setVisibility(0);
                    } else {
                        convoAdapter.f1325m.setVisibility(8);
                    }
                }
                int i = cursor.getInt(5);
                if (i > STATE_HAS_HEADER) {
                    convoAdapter.f1317e.setVisibility(0);
                    convoAdapter.f1317e.setText(String.valueOf(i));
                } else {
                    convoAdapter.f1317e.setVisibility(4);
                }
                if (shouldShowHeader(position, cursor)) {
                    showHeader(cursor, convoAdapter);
                } else {
                    hideHeader(convoAdapter);
                }
                if (cursor.isLast() || cursor.isAfterLast()) {
                    convoAdapter.f1324l.setVisibility(0);
                    return;
                } else {
                    convoAdapter.f1324l.setVisibility(8);
                    return;
                }
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private void setupNewStyle(boolean z, ConvoAdapter convoAdapter, int i, Cursor cursor) {
        if (z) {
            convoAdapter.f1318f.setAlpha(0.6f);
            convoAdapter.f1326n.setVisibility(4);
            convoAdapter.f1319g.setTextAppearance(getActivityContext(), R.TextLightGrey_Small);
            convoAdapter.f1314b.setTextAppearance(getActivityContext(), R.TextLightGrey);
            convoAdapter.f1315c.setTextAppearance(getActivityContext(), R.TextLightGrey_Small);
            return;
        }
        convoAdapter.f1318f.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
        convoAdapter.f1326n.setVisibility(0);
        convoAdapter.f1319g.setTextAppearance(getActivityContext(), R.TextDarkGrey_Small);
        convoAdapter.f1314b.setTextAppearance(getActivityContext(), bl.m3352a(getActivityContext(), R.textBlackBold));
        convoAdapter.f1315c.setTextAppearance(getActivityContext(), bl.m3352a(getActivityContext(), R.textBlueSmallBold));
    }

    private void showHeader(Cursor cursor, ConvoAdapter convoAdapter) {
        this.mBuffer.setLength(0);
        this.mCalendar.setTimeInMillis(cursor.getLong(3));
        this.mBuffer.append(this.mCalendar.getDisplayName(STATE_NO_HEADER, STATE_NO_HEADER, Locale.getDefault()).toUpperCase(Locale.getDefault()));
        if (this.mCalendar.get(STATE_HAS_HEADER) != this.mCurrentYear) {
            this.mBuffer.append(" ").append(this.mCalendar.get(STATE_HAS_HEADER));
        }
        convoAdapter.f1322j.setText(this.mBuffer.toString());
        convoAdapter.f1321i.setVisibility(0);
        LayoutParams layoutParams = (LayoutParams) convoAdapter.f1323k.getLayoutParams();
        if ($assertionsDisabled || layoutParams != null) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            convoAdapter.f1323k.setLayoutParams(layoutParams);
            return;
        }
        throw new AssertionError();
    }

    private void hideHeader(ConvoAdapter convoAdapter) {
        convoAdapter.f1321i.setVisibility(8);
        LayoutParams layoutParams = (LayoutParams) convoAdapter.f1323k.getLayoutParams();
        layoutParams.leftMargin = this.mBorderMargin;
        layoutParams.rightMargin = this.mBorderMargin;
        convoAdapter.f1323k.setLayoutParams(layoutParams);
    }

    private boolean isNewMonth(long j, long j2) {
        this.mCalendar.setTimeInMillis(j);
        int i = this.mCalendar.get(STATE_NO_HEADER);
        this.mCalendar.setTimeInMillis(j2);
        return i != this.mCalendar.get(STATE_NO_HEADER) ? true : $assertionsDisabled;
    }

    private boolean shouldShowHeader(int i, Cursor cursor) {
        boolean z = $assertionsDisabled;
        int i2 = STATE_HAS_HEADER;
        switch (this.mHeaderCache[i]) {
            case STATE_HAS_HEADER /*1*/:
                return true;
            case STATE_NO_HEADER /*2*/:
                return $assertionsDisabled;
            default:
                if (cursor.isFirst()) {
                    z = true;
                } else if (!(cursor.isAfterLast() || cursor.isBeforeFirst())) {
                    cursor.moveToPrevious();
                    long j = cursor.getLong(3);
                    cursor.moveToNext();
                    if (isNewMonth(cursor.getLong(3), j)) {
                        z = true;
                    }
                }
                int[] iArr = this.mHeaderCache;
                if (!z) {
                    i2 = STATE_NO_HEADER;
                }
                iArr[i] = i2;
                return z;
        }
    }

    private ConvoAdapter createConversationHolder(View view) {
        ConvoAdapter convoAdapter = new ConvoAdapter();
        convoAdapter.f1313a = (ReadStateRelativeLayout) view.findViewById(R.convo_layout);
        convoAdapter.f1314b = (TextView) view.findViewById(R.convo_username);
        convoAdapter.f1315c = (TextView) view.findViewById(R.convo_title);
        convoAdapter.f1316d = (TextView) view.findViewById(R.convo_time);
        convoAdapter.f1319g = (TextView) view.findViewById(R.convo_message_preview);
        convoAdapter.f1317e = (TextView) view.findViewById(R.convo_msg_count);
        convoAdapter.f1320h = view.findViewById(R.convo_has_attachment);
        convoAdapter.f1318f = (ImageView) view.findViewById(R.convo_user_img);
        convoAdapter.f1321i = view.findViewById(R.convo_header);
        convoAdapter.f1322j = (TextView) view.findViewById(R.convo_header_text);
        convoAdapter.f1323k = view.findViewById(R.conversation_divider);
        convoAdapter.f1324l = view.findViewById(R.conversation_divider_bottom);
        convoAdapter.f1325m = (TextView) view.findViewById(R.convo_badge);
        convoAdapter.f1321i.setOnClickListener(null);
        convoAdapter.f1326n = view.findViewById(R.new_message_indicator);
        return convoAdapter;
    }
}
