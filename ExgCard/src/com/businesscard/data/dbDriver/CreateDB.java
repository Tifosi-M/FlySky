package com.businesscard.data.dbDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.businesscard.ui.main.systemManagement.BusinessCardApplication;

public class CreateDB {
	/**
	 * ������ݿ⣬����һ��������assets�еĿ���ݿ⵼��
	 * 
	 * @param path
	 * @param memoApplication
	 */
	public CreateDB(String path,
			BusinessCardApplication businessCardApplication) {
		try {
			InputStream is = businessCardApplication.getResources().getAssets()
					.open("BusinessCard.db3");
			File file = new File(path);
			file.mkdir();
			path = path + "BusinessCard.db3";
			file = new File(path);
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			byte temp[] = new byte[1024];
			while (is.read(temp) != -1) {
				os.write(temp);
			}
			System.out.println("��ݿⴴ���ɹ�");
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
