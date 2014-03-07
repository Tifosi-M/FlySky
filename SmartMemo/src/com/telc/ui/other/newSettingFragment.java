package com.telc.ui.other;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

import webservice.MemoWebPara;
import webservice.WebServiceDelegate;
import webservice.WebServiceUtils;

import com.telc.domain.Service.WeatherService;
import com.telc.smartmemo.R;
import com.telc.ui.main.SlidingActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class newSettingFragment extends Fragment implements WebServiceDelegate {

	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	private SharedPreferences sp;

	private ProgressDialog progressDialog;

	private Spinner sp_province;
	private Spinner sp_city;
	private Spinner sp_remindDistance;
	private Spinner sp_locationTime;
	private WebServiceUtils webService;
	
	private Button bt_setOk;
	private Button bt_upload;
	private Button bt_download;
	int flag = 0;
	boolean ffflag = false;

	boolean netFlag = false;

	private String mprovince;
	private int mprovinceId;

	private String mCity;
	private int mCityId;

	private int mRemindDistance;
	private int mRemindDistanceId;

	private int mLocationTime;
	private int mLocationTimeId;
	private boolean webflag = false;

	private static final String[] sp_remindDistanceSelect = { "50米", "100米",
			"200米", "250米" };
	private static final String[] sp_locationTimeSelect = { "10秒", "20秒",
			"25秒", "50秒", "60秒" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		webService = new WebServiceUtils(MemoWebPara.MM_NAMESPACE,
				MemoWebPara.MM_URL, this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.setlayout, null);

		netFlag = isConnect(getActivity());
		sp = getActivity().getSharedPreferences("Login",
				getActivity().MODE_PRIVATE);
		preferences = getActivity().getSharedPreferences("setInfo", 0);
		editor = preferences.edit();

		ffflag = true;

		sp_province = (Spinner) view.findViewById(R.id.set_province);
		sp_city = (Spinner) view.findViewById(R.id.set_city);
		sp_remindDistance = (Spinner) view
				.findViewById(R.id.set_reminddistance);
		sp_locationTime = (Spinner) view.findViewById(R.id.set_loctiontime);
		bt_upload = (Button) view.findViewById(R.id.btn_syn);
		bt_download = (Button) view.findViewById(R.id.btn_syn_to_here);
		bt_setOk = (Button) view.findViewById(R.id.bt_setOk);
		bt_download.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				webflag = false;
				String tel = sp.getString("user", null);
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("arg0", tel);
				webService.callWebService("downloadMemoDBFile", args,
						byte[].class);

			}
		});
		bt_upload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				webflag = true;
				uploadDbFile();

			}
		});
		
		
		bt_setOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("mprovince", mprovince);
				editor.putInt("mprovinceId", mprovinceId);

				editor.putString("mCity", mCity);
				editor.putInt("mCityId", mCityId);

				editor.putInt("mRemindDistance", mRemindDistance);
				editor.putInt("mRemindDistanceId", mRemindDistanceId);

				editor.putInt("mLocationTime", mLocationTime);
				editor.putInt("mLocationTimeId", mLocationTimeId);

				flag = 1;

				editor.putInt("flag", flag);
				editor.commit();
				Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT)
						.show();
				((SlidingActivity) getActivity()).toggle();
			}
		});

		// 设置提醒距离
		ArrayAdapter<String> adapter_remindDistance = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				sp_remindDistanceSelect);
		adapter_remindDistance
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_remindDistance.setAdapter(adapter_remindDistance);
		sp_remindDistance.setVisibility(View.VISIBLE);
		sp_remindDistance
				.setOnItemSelectedListener(new Spinner_remindDistance_SelectedListener());

		// 设置定位的时间
		ArrayAdapter<String> adapter_locationTime = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item,
				sp_locationTimeSelect);
		adapter_locationTime
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_locationTime.setAdapter(adapter_locationTime);
		sp_locationTime.setVisibility(View.VISIBLE);
		sp_locationTime
				.setOnItemSelectedListener(new Spinner_locationTime_SelectedListener());


		ArrayAdapter<String> adapter_province;
		
		String [] list_sp_province = new String []{"黑龙江","吉林","辽宁","内蒙古","河北","河北","河南","山东","山西","江苏",
				"安徽","陕西","宁夏","甘肃","青海","湖北","湖南","浙江","福建","贵州","四川","广东","广西","云南","海南"
				,"新疆","西藏","台湾","北京","上海","天津","重庆","香港","澳门","钓鱼岛"};
		

		if (!netFlag){

			new AlertDialog.Builder(getActivity())
					.setTitle("网络错误")
					.setMessage("网络连接失败，请确认网络连接")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									android.os.Process
											.killProcess(android.os.Process
													.myPid());
									System.exit(0);
								}
							}).show();
			
		}

		adapter_province = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, list_sp_province);
		
		
		adapter_province
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp_province.setAdapter(adapter_province);
		sp_province.setOnItemSelectedListener(new spinner_provinceListen());
		sp_province.setVisibility(View.VISIBLE);

//		sp_city.setEnabled(false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		int fflag = preferences.getInt("flag", 0);

		if (fflag == 1) {

			sp_province.setSelection(preferences.getInt("mprovinceId", 0));
			int j = preferences.getInt("mprovinceId", 0);

			sp_remindDistance.setSelection(preferences.getInt(
					"mRemindDistanceId", 0));
			int k = preferences.getInt("mRemindDistanceId", 0);

			sp_locationTime.setSelection(preferences.getInt("mLocationTimeId",
					0));
			int m = preferences.getInt("mLocationTimeId", 0);

		}
	}

	class Spinner_locationTime_SelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				mLocationTime = 10;
				mLocationTimeId = 0;
				break;
			case 1:
				mLocationTime = 20;
				mLocationTimeId = 1;
				break;
			case 2:
				mLocationTime = 25;
				mLocationTimeId = 2;
				break;
			case 3:
				mLocationTime = 50;
				mLocationTimeId = 3;
				break;
			case 4:
				mLocationTime = 60;
				mLocationTimeId = 4;
				break;
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	class Spinner_remindDistance_SelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				mRemindDistance = 50;
				mRemindDistanceId = 0;
				break;
			case 1:
				mRemindDistance = 100;
				mRemindDistanceId = 1;

				break;
			case 2:
				mRemindDistance = 200;
				mRemindDistanceId = 2;

				break;
			case 3:
				mRemindDistance = 250;
				mRemindDistanceId = 3;

				break;
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// spinner 省份选择监听
	class spinner_provinceListen implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			mprovince = arg0.getItemAtPosition(arg2).toString();
			mprovinceId = arg2;

			sp_city.setEnabled(true);

			ArrayAdapter adapter_city;
			
			if (netFlag){
				List<String> list_city;
				
				list_city = WeatherService.getCityListByProvince(mprovince);
				
				adapter_city = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_spinner_item, list_city);
				}
			else{
				String [] list_sp_city = new String []{""};
				
				adapter_city = new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_spinner_item, list_sp_city);
			}

			adapter_city
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp_city.setAdapter(adapter_city);
			sp_city.setOnItemSelectedListener(new spinner_cityListen());

			if (preferences.getInt("flag", 0) == 1 && ffflag && netFlag) {
				sp_city.setSelection(preferences.getInt("mCityId", 0));
				ffflag = false;
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// spinner 城市选择监听
	class spinner_cityListen implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			mCity = arg0.getItemAtPosition(arg2).toString();
			mCityId = arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public byte[] getbyte() {

		byte[] tmp = new byte[1000];
		byte[] db = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
		int n;
		try {
			File file = new File(
					"/data/data/com.telc.smartmemo/databases/mydb.db3");
			FileInputStream is = new FileInputStream(file);
			while ((n = is.read(tmp)) != -1) {
				os.write(tmp, 0, n);
			}
			is.close();
			os.close();
			db = os.toByteArray();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}

	public void uploadDbFile() {
		byte[] db = getbyte();
		String tel = sp.getString("user", null);
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("tel", tel);
		args.put("db", db);
		webService.callWebService("uploadMemoDBFile", args, boolean.class);
	}

	@Override
	public void handleException(Object ex) {
		Toast toast = Toast.makeText(getActivity(), "请检查网络连接",
				Toast.LENGTH_SHORT);
		toast.show();

	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		if (webflag == true) {
			boolean flag = (Boolean) result;
			if (flag == true) {
				Toast toast = Toast.makeText(getActivity(), "同步成功",
						Toast.LENGTH_SHORT);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getActivity(), "同步失败",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		}else if(webflag == false){
			String tmp = result.toString();
			//转化成byte数组
			byte[] retByte = Base64.decode(tmp);
			createDatabase(retByte);
		}
	}
	public void createDatabase(byte[] db){
		String path = "/data/data/com.telc.smartmemo/databases/";
		File file=new File(path);
	    file.mkdir();
	    path=path+"mydb.db3";
	    file=new File(path);
	    try {
			file.createNewFile();
			 FileOutputStream os=new FileOutputStream(file);
			 os.write(db);
			 os.close();
			 System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
