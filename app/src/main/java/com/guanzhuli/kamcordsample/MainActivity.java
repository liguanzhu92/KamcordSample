package com.guanzhuli.kamcordsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.guanzhuli.kamcordsample.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new MainFragment()).commit();
    }
}
