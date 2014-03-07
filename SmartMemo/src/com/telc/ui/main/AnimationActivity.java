package com.telc.ui.main;



import com.telc.smartmemo.R;
import com.telc.ui.systemManagement.LoginAndRegisterActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AnimationActivity extends Activity {
	
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		sp=getSharedPreferences("guidFlag",MODE_PRIVATE );

		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(2000); // 

		this.findViewById(R.id.imageView1).startAnimation(aa);// 
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if(sp.getBoolean("isFirst", true)){
					Editor editor = sp.edit();
					editor.putBoolean("isFirst", false);
					editor.commit();
				Intent intent = new Intent(AnimationActivity.this,
						ViewPagerActivity.class);
				startActivity(intent);
				AnimationActivity.this.finish();
				}else{
					Intent intent = new Intent(AnimationActivity.this,
							LoginAndRegisterActivity.class);
					startActivity(intent);
					AnimationActivity.this.finish();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
			}
		});
	}
}
