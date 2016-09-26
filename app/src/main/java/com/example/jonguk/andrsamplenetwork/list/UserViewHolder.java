package com.example.jonguk.andrsamplenetwork.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jonguk.andrsamplenetwork.R;
import com.example.jonguk.andrsamplenetwork.json.GithubJson;
import com.example.jonguk.andrsamplenetwork.json.UserJson;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public class UserViewHolder extends AbsGithubViewHolder {

    private ImageView ivProfile;
    private TextView tvUserName;

    public UserViewHolder(View itemView) {
        super(itemView);
        ivProfile = (ImageView) itemView.findViewById(R.id.iv_profile);
        tvUserName = (TextView) itemView.findViewById(R.id.tv_username);
    }

    @Override
    public void bind(GithubJson json) {
        if (json instanceof UserJson) {
            UserJson user = (UserJson) json;
            Glide.with(itemView.getContext())
                    .load(user.avatar_url)
                    .into(ivProfile);

            tvUserName.setText(user.name);
        }
    }
}
