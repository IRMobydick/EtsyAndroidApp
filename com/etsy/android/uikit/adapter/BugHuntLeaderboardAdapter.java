package com.etsy.android.uikit.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.models.apiv3.bughunt.Leader;
import java.util.List;

public class BugHuntLeaderboardAdapter extends TypedViewHolderRecyclerViewAdapter<Leader, LeaderViewHolder> {
    int mAvatarSize;
    int mDefaultColor;
    String mSignedInUser;
    int mUserColor;

    public class LeaderViewHolder extends ViewHolder {
        public ImageView avatar;
        public TextView name;
        public TextView score;

        public LeaderViewHolder(View view) {
            super(view);
            this.avatar = (ImageView) view.findViewById(R.bughunt_leaderboard_avatar);
            this.name = (TextView) view.findViewById(R.bughunt_leaderboard_primary);
            this.score = (TextView) view.findViewById(R.bughunt_leaderboard_secondary);
        }
    }

    public BugHuntLeaderboardAdapter(Context context, String str, List<Leader> list, ImageBatch imageBatch) {
        super(context, imageBatch);
        this.mSignedInUser = str;
        Resources resources = context.getResources();
        this.mAvatarSize = (int) resources.getDimension(R.conversation_avatar);
        this.mUserColor = resources.getColor(R.bughunt_primary_color);
        this.mDefaultColor = resources.getColor(R.text_dark_grey);
    }

    protected LeaderViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new LeaderViewHolder(this.mInflater.inflate(R.list_item_bughunt_leaderboard, viewGroup, false));
    }

    protected void onBindTypedViewHolder(LeaderViewHolder leaderViewHolder, int i) {
        Leader leader = (Leader) getItem(i);
        leaderViewHolder.name.setText(leader.getName());
        leaderViewHolder.score.setText(Integer.toString(leader.getScore()));
        if (leader.getUsername().equals(this.mSignedInUser)) {
            leaderViewHolder.name.setTextColor(this.mUserColor);
            leaderViewHolder.score.setTextColor(this.mUserColor);
        } else {
            leaderViewHolder.name.setTextColor(this.mDefaultColor);
            leaderViewHolder.score.setTextColor(this.mDefaultColor);
        }
        leaderViewHolder.avatar.setVisibility(8);
    }

    protected int getListItemViewType(int i) {
        return 0;
    }
}
