package domain.Service.Memo;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebEndpoint;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RtMethodGenerator;

import domain.Helper.RTMemoHelper;
import domain.Helper.TMMemoHelper;

@WebService
public interface IMemoService {
	public boolean saveRealTimeMemo(
			@WebParam(name = "RealTimeMemo") RTMemoHelper rtHelper);

	public boolean saveTimingMemo(
			@WebParam(name = "TimingMemo") TMMemoHelper tmHelper);

	public List<RTMemoHelper> getRealTimeMemoByTel(
			@WebParam(name = "Tel") String tel);

	public List<TMMemoHelper> getTimingMemoByTel(
			@WebParam(name = "Tel") String tel);

	public boolean updateRealTimeMemo(
			@WebParam(name = "RealTimeMemo") RTMemoHelper rtHelper);

	//
	// /**
	// * Android web调用待测试
	// *
	// * @param list_rt
	// * @param tel
	// * @return
	// */
	// public boolean saveAllRealTimeMemo(
	// @WebParam(name = "List<RTMemoHelper") List<RTMemoHelper> list_rt,
	// @WebParam(name = "Tel") String tel);
	//
	// /**
	// * Android web调用待测试
	// *
	// * @param list_tm
	// * @param tel
	// * @return
	// */
	// public boolean saveAllTimingMemo(
	// @WebParam(name = "List<TMMemoHelper") List<TMMemoHelper> list_tm,
	// @WebParam(name = "Tel") String tel);
	//
	public boolean uploadMemoDBFile(@WebParam(name = "tel") String tel,
			@WebParam(name = "db") byte[] db);

	public byte[] downloadMemoDBFile(String tel);

	public boolean deleteMemoDBFile(String tel);

}
