package peter.vargovcik.ataspcompanionv2;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

import yjkim.mjpegviewer.MjpegView;

public class MainActivity extends AppCompatActivity {
    MjpegView mv;
    ImageView shutter;
    Animation shutterSlideOut,shutterSlideIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        shutter = (ImageView) findViewById(R.id.shutter_image_view);

        shutterSlideIn	= AnimationUtils.loadAnimation(this, R.anim.shutter_slide_in);
        shutterSlideOut	= AnimationUtils.loadAnimation(this, R.anim.shutter_slide_out);
        shutterSlideOut	= AnimationUtils.loadAnimation(this, R.anim.shutter_slide_out);
        Animation dashboardSlideInAnimation =  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_slide_in);

        mv = (MjpegView) findViewById(R.id.videwView);
        // By the time you do this, the mjpeg stream url below may not work..
        //mv.Start("http://69.59.215.51:80/-wvhttp-01-/GetOneShot?image_size=640x480&frame_count=1000000000",MjpegViewHandler);
//        mv.Start("http://192.168.1.17:9000/?action=stream",MjpegViewHandler);// Works
//
//        mv.SetDisplayMode(mv.SIZE_FIT);
    }
    public void toggleBtnAction(View view){
        Switch swich = (Switch) view;

        if(swich.isChecked()){
            mv.Start("http://192.168.1.13:9000/?action=stream",MjpegViewHandler);// Works
            mv.SetDisplayMode(mv.SIZE_FIT);

            // animate
            shutter.startAnimation(shutterSlideOut);
            shutter.setVisibility(View.INVISIBLE);
        }else{
            mv.Stop();

            // animate
            shutter.startAnimation(shutterSlideIn);
            shutter.setVisibility(View.VISIBLE);
        }
    }

    final Handler MjpegViewHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Log.d("State : ", msg.obj.toString());

            switch (msg.obj.toString()){
                case "DISCONNECTED" :
                    // TODO : When video stream disconnected
                    break;
                case "CONNECTION_PROGRESS" :
                    // TODO : When connection progress
                    break;
                case "CONNECTED" :
                    // TODO : When video streaming connected
                    break;
                case "CONNECTION_ERROR" :
                    // TODO : When connection error
                    break;
                case "STOPPING_PROGRESS" :
                    // TODO : When MjpegViewer is in stopping progress
                    break;
            }

        }
    };
}
