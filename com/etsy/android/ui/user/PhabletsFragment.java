package com.etsy.android.ui.user;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.core.img.ImageBatch;

/* renamed from: com.etsy.android.ui.user.e */
class PhabletsFragment extends ViewHolder {
    public ImageView f3594a;
    public TextView f3595b;

    /* renamed from: com.etsy.android.ui.user.e.1 */
    class PhabletsFragment implements OnClickListener {
        final /* synthetic */ PhabletsFragment f3591a;
        final /* synthetic */ PhabletsFragment f3592b;
        final /* synthetic */ PhabletsFragment f3593c;

        PhabletsFragment(PhabletsFragment phabletsFragment, PhabletsFragment phabletsFragment2, PhabletsFragment phabletsFragment3) {
            this.f3593c = phabletsFragment;
            this.f3591a = phabletsFragment2;
            this.f3592b = phabletsFragment3;
        }

        public void onClick(View view) {
            if (this.f3591a != null) {
                this.f3591a.m5009a(this.f3592b);
            }
        }
    }

    public PhabletsFragment(View view) {
        super(view);
        this.f3594a = (ImageView) view.findViewById(2131756081);
        this.f3595b = (TextView) view.findViewById(2131756082);
    }

    public void m5056a(PhabletsFragment phabletsFragment, ImageBatch imageBatch, int i, @Nullable PhabletsFragment phabletsFragment2) {
        this.f3595b.setText(phabletsFragment.m5054b());
        imageBatch.m1574b(phabletsFragment.m5053a(), this.f3594a, i);
        this.itemView.setOnClickListener(new PhabletsFragment(this, phabletsFragment2, phabletsFragment));
    }
}
