package com.telc.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.telc.smartmemo.R;
import com.telc.ui.Memos.PeriodicMemoActivity;
import com.telc.ui.systemManagement.LoginAndRegisterActivity;

public class ViewPagerActivity extends Activity {
	Animation animation = null;
	private ArrayList<View> pageViews;
	Button btn_enter;
	ViewPagerActivity viewPagerActivity;
	private ImageView[] dots=new ImageView[4];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);
		viewPagerActivity=this;
		LayoutInflater inflater = getLayoutInflater();
		dots[0]=(ImageView) findViewById(R.id.imageDot1);
		dots[1]=(ImageView) findViewById(R.id.imageDot2);
		dots[2]=(ImageView) findViewById(R.id.imageDot3);
		dots[3]=(ImageView) findViewById(R.id.imageDot4);
		
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.welcome0,null));
		pageViews.add(inflater.inflate(R.layout.welcome1,null));
		pageViews.add(inflater.inflate(R.layout.welcome2,null));
		pageViews.add(inflater.inflate(R.layout.welcome3, null));
		
		ViewPager vp =(ViewPager) findViewById(R.id.pager);
		
		vp.setAdapter(new ViewPagerAdapter(pageViews));
		vp.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	@Override
	public void setContentView(int id) {
		// TODO Auto-generated method stub
		super.setContentView(id);
	}

	
	
	public class ViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;
		
		public ViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{	
			container.removeView(mListViews.get(position));
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {	
			//这个方法用来实例化页卡		
			 container.addView(mListViews.get(position));//添加页卡
			 //判断是否为最后一页，实例化“开始体验”Button。
			 switch (position){
			 case 3:
			 btn_enter=(Button) mListViews.get(position).findViewById(R.id.btn_enter);
			 btn_enter.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(ViewPagerActivity.this,LoginAndRegisterActivity.class);
					startActivity(intent);

					viewPagerActivity.finish();
				}
			});
			 break;
			}
			 return mListViews.get(position);//返回页卡列表
		}

		@Override
		public int getCount() {			
			return  mListViews.size();//返回页卡的数量
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {			
			return arg0==arg1;//官方提示这样写
		}
		
	}
	
	
	public class MyOnPageChangeListener implements OnPageChangeListener {  
		  
        public void onPageSelected(int position) {//参数arg0为选中的View  
        	int tempPosition=0;
            Animation animation = null;//声明动画对象  
            switch (position) {  
            case 0: //页面一             
                dots[0].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_selected));//进入第一个导航页面，小圆点为选中状态，下一个页面的小圆点是未选中状态。  
                dots[1].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
                dots[2].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
                dots[3].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
                if (tempPosition == position+1){
                    animation = new TranslateAnimation(position+1, position, 0, 0);//圆点移动效果动画，从当前View移动到下一个View  
                }
                else{
                	animation = new TranslateAnimation(position-1, position, 0, 0);
                }
                break;  
            case 1: //页面二  
            	dots[0].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));//上一个View
                dots[1].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_selected));//当前View  
                dots[2].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));//下一个View  
                dots[3].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
                if (tempPosition == position-1){ //如果滑动到上一个View  
                    animation = new TranslateAnimation(position-1, position, 0, 0); //圆点移动效果动画，从当前View移动到下一个View      
                } else { //圆点移动效果动画，从当前View移动到下一个View，下同。  
                    animation = new TranslateAnimation(position+1, position, 0, 0);  
                }
                break;  
            case 2: //页面三  
            	dots[0].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
            	dots[1].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));  
                dots[2].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_selected));  
                dots[3].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));  
                if (tempPosition == position-1) {  
                    animation = new TranslateAnimation(position-1, position, 0, 0);  
                } else  {  
                    animation = new TranslateAnimation(position+1, position, 0, 0);  
                }  
                break;  
            case 3:  
            	dots[0].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
            	dots[1].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));
                dots[2].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_unselect));  
                dots[3].setImageDrawable(getResources().getDrawable(R.drawable.img_dot_selected));  
                if (tempPosition == position-1) {  
                    animation = new TranslateAnimation(position-1, position, 0, 0);  
                } else  {  
                    animation = new TranslateAnimation(position+1, position, 0, 0);  
                }  
                break;  
            }
            tempPosition = position;//设置当前View  
            animation.setFillAfter(true);// True:设置图片停在动画结束位置  
            animation.setDuration(300);//设置动画持续时间  
        }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}  
	}
}
