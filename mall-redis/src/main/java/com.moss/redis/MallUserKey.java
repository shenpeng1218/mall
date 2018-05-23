package com.moss.redis;

public class MallUserKey extends BasePrefix{

    private static final int TOKEN_EXPIRE = 1800;

    public MallUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MallUserKey token = new MallUserKey(TOKEN_EXPIRE, "token");
}
