package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import com.etsy.android.lib.R;

public class ButtonViewHolder<T extends b> extends BaseViewHolder<T> {
    protected final Button mButton;

    public ButtonViewHolder(View view) {
        super(view);
        this.mButton = (Button) view.findViewById(R.button);
    }

    public void bind(@NonNull b bVar) {
        this.mButton.setText(bVar.d);
        if (bVar.e != null) {
            this.mButton.setOnClickListener(bVar.e);
        }
    }
}
