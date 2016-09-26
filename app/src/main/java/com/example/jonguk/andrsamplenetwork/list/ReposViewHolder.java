package com.example.jonguk.andrsamplenetwork.list;

import android.view.View;
import android.widget.TextView;

import com.example.jonguk.andrsamplenetwork.R;
import com.example.jonguk.andrsamplenetwork.json.GithubJson;
import com.example.jonguk.andrsamplenetwork.json.ReposJson;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public class ReposViewHolder extends AbsGithubViewHolder {
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvStarCount;

    public ReposViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        tvStarCount = (TextView) itemView.findViewById(R.id.tv_star_count);
    }

    public void bind(GithubJson json) {
        if (json instanceof ReposJson) {
            ReposJson repos = (ReposJson) json;
            tvTitle.setText(repos.name);
            tvDescription.setText(repos.description);
            tvStarCount.setText(String.valueOf(repos.stargazers_count));
        }
    }
}
