package com.telc.domain.IService;

import java.util.List;

import com.telc.domain.Emtity.Timing;

/**
 * 定时提醒接口
 * @author evil
 *
 */

public interface ITimingService {
	public List<Timing> getTimingByUserID(String user_id);
	public boolean addTiming(Timing timing);
	public boolean removeTiming(String timing_id);
	public Timing findTimingByStart(String start_time);
	public boolean updateTiming(Timing timing);
	public boolean updateIsfinish(String timing_id);
}
