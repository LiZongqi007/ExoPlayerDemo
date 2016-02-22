package com.exo.exoplayerdemo;



import java.util.ArrayList;
import java.util.List;

import com.tencent.qcload.playersdk.ui.VideoRootFrame;
import com.tencent.qcload.playersdk.util.PlayerListener;
import com.tencent.qcload.playersdk.util.VideoInfo;
import com.tencent.qcload.playersdk.ui.*;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VideoRootFrame player=(VideoRootFrame) findViewById(R.id.player);
        List<TitleMenu> videoTitleMenus=new ArrayList<TitleMenu>();
        TitleMenu icon1=new TitleMenu();
        icon1.iconId=R.drawable.ic_share;
        icon1.action=new PlayerActionInterface(){
            @Override
            public void action() {
                Toast.makeText(MainActivity.this,"share icon taped",Toast.LENGTH_SHORT).show();
            }
        };
        videoTitleMenus.add(icon1);
        TitleMenu icon2=new TitleMenu();
        icon2.iconId=R.drawable.ic_favorite;
        videoTitleMenus.add(icon2);
        TitleMenu icon3=new TitleMenu();
        icon3.iconId=R.drawable.ic_perm_identity;
        videoTitleMenus.add(icon3);
        player.setMenu(videoTitleMenus);
        List<VideoInfo> videos=new ArrayList<VideoInfo>();
        VideoInfo v1=new VideoInfo();
        v1.description="标清";
        v1.type=VideoInfo.VideoType.MP4;
        v1.url="http://200005887.vod.myqcloud.com/200005887_12a1c04ad55211e5a91f5d250d829fc2.f0.mp4";
        videos.add(v1);
        VideoInfo v2=new VideoInfo();
        v2.description="高清";
        v2.type=VideoInfo.VideoType.MP4;
        v2.url="http://200005887.vod.myqcloud.com/200005887_12a1c04ad55211e5a91f5d250d829fc2.f0.mp4";
        videos.add(v2);   
        player.setListener(new PlayerListener(){

			@Override
			public void onError(Exception arg0) {
				arg0.printStackTrace();
				
			}

			@Override
			public void onStateChanged(int arg0) {
				Log.d(TAG, "player states:"+arg0);
				
			}
        	
        });
        player.play(videos);
        player.setToggleFullScreenHandler(new UiChangeInterface() {
            @Override
            public void OnChange() {                
                if (player.isFullScreen()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
