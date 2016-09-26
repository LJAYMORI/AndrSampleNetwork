package com.example.jonguk.andrsamplenetwork.json;

/**
 * Created by Jonguk on 2016. 9. 26..
 */

public class GithubJson {
    public enum Type {Non, User, Repos}

    public int id;
    public Type mType;

    public int getId() {
        return id;
    }

    public Type getType() {
        return mType;
    }
}
