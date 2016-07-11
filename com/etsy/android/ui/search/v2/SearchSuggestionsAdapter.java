package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.adapter.BaseRecyclerViewCursorAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class SearchSuggestionsAdapter extends BaseRecyclerViewCursorAdapter<SuggestionsHolder> {
    private static final int INVALID_INDEX = -1;
    private static final String TAG;
    private ae mListener;
    private String mQuery;
    private int mTextCol;

    /* renamed from: com.etsy.android.ui.search.v2.SearchSuggestionsAdapter.1 */
    class C07911 extends TrackingOnClickListener {
        final /* synthetic */ String f3297a;
        final /* synthetic */ SearchSuggestionsAdapter f3298b;

        C07911(SearchSuggestionsAdapter searchSuggestionsAdapter, String str) {
            this.f3298b = searchSuggestionsAdapter;
            this.f3297a = str;
        }

        public void onViewClick(View view) {
            if (this.f3298b.mListener != null) {
                this.f3298b.mListener.onQuerySelected(this.f3297a);
            }
        }
    }

    public final class SuggestionsHolder extends ViewHolder {
        ImageView mIconRefine;
        TextView mText;

        public SuggestionsHolder(View view) {
            super(view);
            this.mText = (TextView) view.findViewById(16908308);
            this.mIconRefine = (ImageView) view.findViewById(R.edit_query);
        }
    }

    static {
        TAG = EtsyDebug.m1891a(SearchSuggestionsAdapter.class);
    }

    public SearchSuggestionsAdapter(Context context, Cursor cursor, String str, ae aeVar) {
        super(context, cursor);
        getColumnIndices(cursor);
        this.mQuery = str;
        this.mListener = aeVar;
    }

    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
        getColumnIndices(cursor);
    }

    protected SuggestionsHolder createViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new SuggestionsHolder(layoutInflater.inflate(2130903467, viewGroup, false));
    }

    protected void bindViewHolder(SuggestionsHolder suggestionsHolder, Cursor cursor) {
        suggestionsHolder.itemView.setBackgroundResource(R.list_selector_white);
        Object stringOrNull = getStringOrNull(cursor, this.mTextCol);
        suggestionsHolder.mText.setSingleLine(true);
        setViewText(suggestionsHolder.mText, stringOrNull);
        suggestionsHolder.mIconRefine.setVisibility(8);
        suggestionsHolder.itemView.setOnClickListener(new C07911(this, stringOrNull));
    }

    private void getColumnIndices(Cursor cursor) {
        if (cursor != null) {
            this.mTextCol = cursor.getColumnIndex("suggest_text_1");
        }
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private String getStringOrNull(Cursor cursor, int i) {
        String str = null;
        if (i != INVALID_INDEX) {
            try {
                str = cursor.getString(i);
            } catch (Throwable e) {
                EtsyDebug.m1917d(TAG, "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            }
        }
        return str;
    }
}
