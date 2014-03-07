package com.telc.resource.remind;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Handler;
import android.os.Looper;

public class connentNet extends Thread {

	static final String DISPLAYNBR = "8659522945606"; // 这里输入您自己的号码点数
	static final String KEY = "69589032"; // 这里输入您自己的号码点数Key
	String contentString;

	Handler handler;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Looper.prepare();
		try {
			contentString = URLEncoder.encode(remindContent.Content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String urlString = "http://open.fjii.com:8088/httpIntf/dealIntf?postData=<Request><Head><MethodName>VoiceNotice</MethodName><Spid>666888</Spid><Appid>888</Appid><Passwd>601f1889667efaebb33b8c12572835da3f027f78</Passwd></Head><Body><DisplayNbr>"
				+ DISPLAYNBR
				+ "</DisplayNbr><Key>"
				+ KEY
				+ "</Key><CalleeNbr>"
				+ remindContent.useId
				+ "</CalleeNbr><VoiceName></VoiceName><TTSContent>"
				+ contentString + "</TTSContent></Body></Request>";
		Read read = new Read();
		try {
			String result = read.request(urlString);

			// if (result != null && result.indexOf("<Result>0</Result>") > 0)
			// Toast.makeText(getApplicationContext(), "����ɹ�",
			// Toast.LENGTH_SHORT).show();
			// else {
			// Toast.makeText(getApplicationContext(), "����ʧ�ܣ�������",
			// Toast.LENGTH_SHORT).show();
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), "����ʧ�ܣ�������",
			// Toast.LENGTH_SHORT).show();
		} finally {
			// closeProgress();
		}
		Looper.loop();

	}

}
