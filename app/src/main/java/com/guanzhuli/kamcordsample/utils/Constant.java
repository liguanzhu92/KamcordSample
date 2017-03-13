package com.guanzhuli.kamcordsample.utils;

/**
 * Created by Guanzhu Li on 3/12/2017.
 */
public class Constant {
    public static final String SCREEN_SMALL = "small";
    public static final String SCREEN_MEDIUM = "medium";
    public static final String SCREEN_LARGE = "large";

    public static final String VIDEO_URL = "video";
    public static final String HEART = "heart";

    public static final String BASE_URL = "https://api.kamcord.com/v1/feed/set/featuredShots?count=20";

    public static final String FIELD_GROUP = "groups";
    public static final String FIELD_CARDS = "cards";
    public static final String FIELD_SHOT = "shotCardData";
    public static final String FIELD_HEART = "heartCount";
    public static final String FIELD_THUMBNAIL = "shotThumbnail";
    public static final String FIELD_PLAY = "play";
    public static final String FIELD_MP4 = "mp4";
    static class Connection {
        public static final String METHOD = "GET";
        public static final String HEADER_ACCEPT = "Accept";
        public static final String HEADER_ACCEPT_FIELD = "application/json";
        public static final String HEADER_LANGUAGE = "accept-language";
        public static final String HEADER_LANGUAGE_FIELD = "en";
        public static final String HEADER_TOKEN = "device-token";
        public static final String HEADER_TOKEN_FIELD = "abc123";
        public static final String HEADER_CLIENT = "client-name";
        public static final String HEADER_CLIENT_FIELD = "android";
    }
}
