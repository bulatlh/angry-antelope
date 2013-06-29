package com.me.mygdxgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.me.mygdxgame.controller.TitleCreater;

public class MainActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		Constants.theGreatCreater = new TitleCreater();
		
		initialize(Constants.theGreatCreater, config);
    }
}
