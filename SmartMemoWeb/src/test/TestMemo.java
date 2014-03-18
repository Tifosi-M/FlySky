package test;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sun.org.apache.regexp.internal.recompile;

import domain.Helper.RTMemoHelper;
import domain.Helper.TMMemoHelper;
import domain.Memo.Realtime;
import domain.Memo.Timing;
import domain.Service.Memo.MemoService;

public class TestMemo extends MemoService {
	private RTMemoHelper rt;
	private TMMemoHelper tm;
	@Before
	public void init(){
		rt = new RTMemoHelper();
		rt.setId("1");
		rt.setUserId("123");
		rt.setAging("24");
		rt.setPriority("8");
		rt.setLocation("123");
		rt.setStartTime("21");
		rt.setContent("test");
		tm = new TMMemoHelper();
		tm.setUserId("2");
		tm.setTimingId("1234");
		tm.setPriority("1");
	}
	@Ignore
	public void testSaveRealTimeMemo() {
		saveRealTimeMemo(rt);
	}

	@Ignore
	public void testSaveTimingMemo() {
		saveTimingMemo(tm);
	}
	@Ignore
	public void testGetRealTimeMemo(){
		List<RTMemoHelper> list = getRealTimeMemoByTel("123");
	}
	
	@Ignore
	public void testGetTimingMemo(){
		List<TMMemoHelper> list = getTimingMemoByTel("123");
		System.out.println(list.get(0).getUserId());
		System.out.println(list.get(0).getContent());
	}
	@Ignore
	public void testUpdateRealtimeMemo(){
		updateRealTimeMemo(rt);
	}
	@Test
	public void testUplodDB(){
		BufferedInputStream in = null;
		File file = new File("e:/著作权登记说明.pdf");
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int length = (int) file.length();
			System.out.println();
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);      
		    byte[] temp = new byte[1024];        
		    int size = 0;
		    while ((size = in.read(temp)) != -1) {        
		    	out.write(temp, 0, size);        
			}
		    byte[] content = out.toByteArray();
		    uploadMemoDBFile("123", content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
