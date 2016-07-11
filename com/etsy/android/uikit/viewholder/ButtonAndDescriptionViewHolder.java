package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

public class ButtonAndDescriptionViewHolder extends ButtonViewHolder<a> {
    private final TextView mDescription;

    public ButtonAndDescriptionViewHolder(View view) {
        super(view);
        TextView textView = (TextView) view.findViewById(16908308);
        textView.setVisibility(0);
        this.mDescription = textView;
    }

    public void bind(@NonNull a aVar) {
        if ((aVar.b & 1) == 1) {
            super.bind((Object) aVar);
            this.mButton.setVisibility(0);
        } else {
            this.mButton.setVisibility(8);
        }
        if ((aVar.b & 2) == 2) {
            this.mDescription.setText(aVar.a);
            this.mDescription.setVisibility(0);
        } else {
            this.mDescription.setVisibility(8);
        }
        this.itemView.setBackgroundColor(aVar.c);
    }
}
