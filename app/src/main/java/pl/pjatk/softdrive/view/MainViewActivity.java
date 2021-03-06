package pl.pjatk.softdrive.view;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import pl.pjatk.softdrive.R;

/**
 * Set main view activity
 * @author Dominik Stec
 * @see AppCompatActivity
 */
public class MainViewActivity extends AppCompatActivity {

    /**
     * Run view activity in main thread loop
     * @param savedInstanceState Android application Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenOn();

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(1);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                setContentView(R.layout.activity_main_view);

                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        };

        thread.start();

    }

    /**
     * Screen always on with maximum brightness when application is active
     */
    private void setScreenOn() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 1;
        getWindow().setAttributes(params);
    }


}