package com.liskovsoft.youtubeapi.content_old.models;

import com.liskovsoft.youtubeapi.common.models.videos.ChannelItem;
import com.liskovsoft.youtubeapi.common.models.videos.MusicItem;
import com.liskovsoft.youtubeapi.common.models.videos.VideoItem;
import com.liskovsoft.youtubeapi.support.converters.jsonpath.JsonPath;

import java.util.List;

public class ContentTabSection {
    @JsonPath("$.title.runs[0].text")
    private String title;
    @JsonPath("$.content.horizontalListRenderer.items[*].gridVideoRenderer")
    private List<VideoItem> videoItems;
    @JsonPath("$.content.horizontalListRenderer.items[*].tvMusicVideoRenderer")
    private List<MusicItem> musicItems;
    @JsonPath("$.content.horizontalListRenderer.items[*].gridChannelRenderer")
    private List<ChannelItem> channelItems;

    public String getTitle() {
        return title;
    }

    public List<VideoItem> getVideoItems() {
        return videoItems;
    }

    public List<MusicItem> getMusicItems() {
        return musicItems;
    }

    public List<ChannelItem> getChannelItems() {
        return channelItems;
    }
}
