package com.liskovsoft.leanbackassistant.channels;

import android.content.Context;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.liskovsoft.leanbackassistant.R;
import com.liskovsoft.myvideotubeapi.Video;
import com.liskovsoft.youtubeapi.adapters.YouTubeVideoService;

import java.util.ArrayList;
import java.util.List;

public class MySampleClipApi {
    private static final int SUBSCRIPTIONS_ID = 1;
    private static final int HISTORY_ID = 2;
    private static final int RECOMMENDED_ID = 3;
    private static final String SUBS_CHANNEL_ID = "subs_channel_id";
    private static final String SUBS_PROGRAMS_IDS = "subs_clips_ids";
    private static final String RECOMMENDED_CHANNEL_ID = "recommended_channel_id";
    private static final String RECOMMENDED_PROGRAMS_IDS = "recommended_programs_ids";
    private static final String HISTORY_CHANNEL_ID = "history_channel_id";
    private static final String HISTORY_PROGRAMS_IDS = "history_programs_ids";
    private static final String SUBSCRIPTIONS_URL = "https://www.youtube.com/tv#/zylon-surface?c=FEsubscriptions&resume";
    private static final String HISTORY_URL = "https://www.youtube.com/tv#/zylon-surface?c=FEmy_youtube&resume";
    private static final String RECOMMENDED_URL = "https://www.youtube.com/tv#/zylon-surface?c=default&resume";

    public static Playlist getSubscriptionsPlaylist(Context context) {
        YouTubeVideoService service = YouTubeVideoService.instance();
        List<Video> subscriptions = service.getSubscriptions();

        Playlist playlist = new Playlist(
                context.getResources().getString(R.string.subscriptions_playlist_name),
                Integer.toString(SUBSCRIPTIONS_ID));
        playlist.setChannelKey(SUBS_CHANNEL_ID);
        playlist.setProgramsKey(SUBS_PROGRAMS_IDS);
        playlist.setPlaylistUrl(SUBSCRIPTIONS_URL);
        playlist.setLogoResId(R.drawable.generic_channels);

        if (subscriptions != null && !subscriptions.isEmpty()) {
            if (subscriptions.size() < 20) {
                subscriptions.addAll(service.getNextSubscriptions());
                subscriptions.addAll(service.getNextSubscriptions());
                subscriptions.addAll(service.getNextSubscriptions());
            }

            List<Clip> clips = convertToClips(subscriptions);
            playlist.setClips(clips);
        }

        return playlist;
    }

    public static Playlist getHistoryPlaylist(Context context) {
        YouTubeVideoService service = YouTubeVideoService.instance();
        List<Video> history = service.getHistory();

        Playlist playlist = new Playlist(
                context.getResources().getString(R.string.history_playlist_name),
                Integer.toString(HISTORY_ID));
        playlist.setChannelKey(HISTORY_CHANNEL_ID);
        playlist.setProgramsKey(HISTORY_PROGRAMS_IDS);
        playlist.setPlaylistUrl(HISTORY_URL);
        playlist.setLogoResId(R.drawable.generic_channels);

        if (history != null && !history.isEmpty()) {
            if (history.size() < 20) {
                history.addAll(service.getNextHistory());
                history.addAll(service.getNextHistory());
                history.addAll(service.getNextHistory());
            }

            List<Clip> clips = convertToClips(history);
            playlist.setClips(clips);
        }

        return playlist;
    }

    public static Playlist getRecommendedPlaylist(Context context) {
        YouTubeVideoService service = YouTubeVideoService.instance();
        List<Video> recommended = service.getRecommended();

        Playlist playlist = new Playlist(
                context.getResources().getString(R.string.recommended_playlist_name),
                Integer.toString(RECOMMENDED_ID));
        playlist.setChannelKey(RECOMMENDED_CHANNEL_ID);
        playlist.setProgramsKey(RECOMMENDED_PROGRAMS_IDS);
        playlist.setPlaylistUrl(RECOMMENDED_URL);
        playlist.setLogoResId(R.drawable.generic_channels);

        if (recommended != null && !recommended.isEmpty()) {
            if (recommended.size() < 20) {
                recommended.addAll(service.getNextRecommended());
                recommended.addAll(service.getNextRecommended());
                recommended.addAll(service.getNextRecommended());
            }

            List<Clip> clips = convertToClips(recommended);
            playlist.setClips(clips);
        }

        return playlist;
    }

    private static List<Clip> convertToClips(List<Video> videos) {
        if (videos != null) {
            List<Clip> clips = new ArrayList<>();

            for (Video v : videos) {
                clips.add(new Clip(
                        v.getTitle(),
                        v.getDescription(),
                        v.getBackgroundImageUrl(),
                        v.getCardImageUrl(),
                        v.getVideoUrl(),
                        null,
                        false,
                        null,
                        Integer.toString(v.getId()),
                        null,
                        TvContractCompat.PreviewProgramColumns.ASPECT_RATIO_16_9));
            }

            return clips;
        }

        return null;
    }
}
