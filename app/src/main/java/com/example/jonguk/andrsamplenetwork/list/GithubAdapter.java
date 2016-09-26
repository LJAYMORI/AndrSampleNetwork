package com.example.jonguk.andrsamplenetwork.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonguk.andrsamplenetwork.R;
import com.example.jonguk.andrsamplenetwork.json.GithubJson;
import com.example.jonguk.andrsamplenetwork.json.ReposJson;
import com.example.jonguk.andrsamplenetwork.json.UserJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public class GithubAdapter extends RecyclerView.Adapter<AbsGithubViewHolder> {

    private List<GithubJson> mItems = new ArrayList<>();

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void addUser(UserJson user) {
        for (int i = 0; i < mItems.size();) {
            GithubJson githubJson = mItems.get(i);
            if (githubJson instanceof UserJson) {
                mItems.remove(i);
                continue;
            }
            i++;
        }
        mItems.add(0, user);
        notifyDataSetChanged();
    }

    public void addRepos(List<ReposJson> list) {
        Collections.sort(list, new Comparator<ReposJson>() {
            @Override
            public int compare(ReposJson o1, ReposJson o2) {
                return ((Integer) o2.stargazers_count).compareTo(o1.stargazers_count);
            }
        });
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        GithubJson json = mItems.get(position);
        if (json instanceof UserJson) {
            return GithubJson.Type.User.ordinal();
        } else if (json instanceof ReposJson) {
            return GithubJson.Type.Repos.ordinal();
        } else {
            return GithubJson.Type.Non.ordinal();
        }
    }

    @Override
    public AbsGithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (GithubJson.Type.values()[viewType]) {
            case Repos: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewholder_repos, parent, false);
                return new ReposViewHolder(v);
            }
            case User: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.viewholder_user, parent, false);
                return new UserViewHolder(v);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(AbsGithubViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
