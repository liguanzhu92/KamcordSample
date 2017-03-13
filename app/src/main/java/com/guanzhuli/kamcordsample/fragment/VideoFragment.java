package com.guanzhuli.kamcordsample.fragment;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;

import android.widget.TextView;
import com.guanzhuli.kamcordsample.R;

import java.io.IOException;

import static com.guanzhuli.kamcordsample.utils.Constant.HEART;
import static com.guanzhuli.kamcordsample.utils.Constant.VIDEO_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private String mVideoURL, mHeart;
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private TextView mTextHeart;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideoURL = getArguments().getString(VIDEO_URL);
        mHeart = getArguments().getString(HEART);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        mTextHeart = (TextView) view.findViewById(R.id.video_heart);
        mTextHeart.setText(mHeart);
        mSurfaceView = (SurfaceView)view.findViewById(R.id.video_surface);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDisplay(mSurfaceHolder);
                try {
                    mMediaPlayer.setDataSource(mVideoURL);
                    mMediaPlayer.prepare();
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mMediaPlayer.setLooping(true);
                        }
                    });
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
