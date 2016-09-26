package com.example.jonguk.andrsamplenetwork.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jonguk.andrsamplenetwork.json.GithubJson;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public abstract class AbsGithubViewHolder extends RecyclerView.ViewHolder {
    public AbsGithubViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(GithubJson json);
}
