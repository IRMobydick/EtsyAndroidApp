package com.etsy.android.uikit.share;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ShareIntentListAdapter extends BaseRecyclerViewAdapter<ResolveInfo> {
    private int mLayoutId;
    private ShareIntentListAdapter mOnClickListener;
    private PackageManager mPkgManager;

    public class IntentItemHolder extends ViewHolder {
        public ImageView intentIcon;
        public TextView intentLabel;
        private ShareIntentListAdapter mListener;

        /* renamed from: com.etsy.android.uikit.share.ShareIntentListAdapter.IntentItemHolder.1 */
        class C09481 extends TrackingOnClickListener {
            final /* synthetic */ IntentItemHolder f4014a;

            C09481(IntentItemHolder intentItemHolder) {
                this.f4014a = intentItemHolder;
            }

            public void onViewClick(View view) {
                if (this.f4014a.mListener != null) {
                    this.f4014a.mListener.onIntentItemClick(this.f4014a.getAdapterPosition());
                }
            }
        }

        public IntentItemHolder(View view) {
            super(view);
            this.intentIcon = (ImageView) view.findViewById(R.item_image);
            this.intentLabel = (TextView) view.findViewById(R.item_label);
            view.setOnClickListener(new C09481(this));
        }

        public void setOnClickListener(ShareIntentListAdapter shareIntentListAdapter) {
            this.mListener = shareIntentListAdapter;
        }
    }

    public ShareIntentListAdapter(Activity activity, int i) {
        super(activity, null);
        this.mLayoutId = i;
        this.mPkgManager = activity.getPackageManager();
    }

    protected int getListItemViewType(int i) {
        return 0;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new IntentItemHolder(this.mInflater.inflate(this.mLayoutId, viewGroup, false));
    }

    protected void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder != null && (viewHolder instanceof IntentItemHolder) && getItem(i) != null) {
            bindIntentItemViewHolder((IntentItemHolder) viewHolder, i);
        }
    }

    private void bindIntentItemViewHolder(IntentItemHolder intentItemHolder, int i) {
        ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
        intentItemHolder.intentIcon.setImageDrawable(resolveInfo.loadIcon(this.mPkgManager));
        intentItemHolder.intentLabel.setText(resolveInfo.loadLabel(this.mPkgManager));
        intentItemHolder.setOnClickListener(this.mOnClickListener);
    }

    public void setOnClickListener(ShareIntentListAdapter shareIntentListAdapter) {
        this.mOnClickListener = shareIntentListAdapter;
    }
}
