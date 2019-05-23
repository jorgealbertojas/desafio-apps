package com.example.jorge.infoglobo.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GenericViewHolder extends RecyclerView.ViewHolder {

    public GenericViewHolder(View itemView) {
        super(itemView);
    }

    public abstract  void setDataOnView(int position);
}
