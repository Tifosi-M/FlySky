package com.businesscard.ui.main;

import java.util.Hashtable;

import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Service.NormalCardInfoService;
import com.businesscard.mobile.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QRCodeActivity extends Activity {
	NormalCardInfoService normalCardInfoService;
	NormalCardInfo normalCardInfo;
	String normalCardCode;
	SQLiteDatabase db;
	private String get_userPhone;
    //private ImageView  iv_qrcode_card;
	private RelativeLayout rl_qrcode_card;
    private TextView tv_qrcode_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_create_qrcode);
		SharedPreferences sharedPreferences = getSharedPreferences(
				"current_user", MODE_PRIVATE);
		get_userPhone = sharedPreferences.getString("userPhone", "");
		db = openOrCreateDatabase(get_userPhone + ".db3", MODE_PRIVATE, null);
		normalCardInfoService = new NormalCardInfoService(db);
		normalCardInfo = new NormalCardInfo();
		//iv_qrcode_card=(ImageView) findViewById(R.id.iv_qrcode_card);
		rl_qrcode_card= (RelativeLayout) findViewById(R.id.rl_qrcode_card);
		tv_qrcode_name=(TextView) findViewById(R.id.tv_qrcode_name);
		createImage();
	}

	private void createImage() {
		QRCodeWriter writer = new QRCodeWriter();
		normalCardInfo = normalCardInfoService
				.findMyCardByUserPhone(get_userPhone);
		tv_qrcode_name.setText(normalCardInfo.getUserName());
	normalCardCode = "normalCardInfo;"+normalCardInfoService.CardInfoToString(normalCardInfo);
		 Log.i("TAG" ,"生成的文本：" + normalCardCode);
		if (normalCardCode == null || "".equals(normalCardCode)
				|| normalCardCode.length() < 1) {
			return;
		}
		// 把输入的文本转为二维码
		Point size = new Point();
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		display.getSize(size);

		try {
			BitMatrix martix = writer.encode(normalCardCode,
					BarcodeFormat.QR_CODE, size.x, size.x);
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			 BitMatrix bitMatrix = new QRCodeWriter().encode(normalCardCode,
	                    BarcodeFormat.QR_CODE, size.x, size.x, hints);
			 int[] pixels = new int[size.x * size.x];
	            for (int y = 0; y < size.x; y++) {
	                for (int x = 0; x < size.x; x++) {
	                    if (bitMatrix.get(x, y)) {
	                        pixels[y * size.x + x] = 0xff000000;
	                    } else {
	                        pixels[y * size.x + x] = 0xffffffff;
	                    }

	                }
	            }

	            Bitmap bitmap = Bitmap.createBitmap(size.x, size.x,
	                    Bitmap.Config.ARGB_8888);
	            bitmap.setPixels(pixels, 0, size.x, 0, 0, size.x, size.x);
	           // iv_qrcode_card.setImageBitmap(bitmap);
	            if(bitmap!=null){
	            	BitmapDrawable bd=new BitmapDrawable(bitmap);
	            	rl_qrcode_card.setBackgroundDrawable(bd);
	            }
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
