/**
 * 
 */
package com.amway.frm.job.util;

import com.amway.frm.base.util.AppConstant;

/**
 * @author huangweijin
 *
 * 2011-9-28 上午09:23:17
 */
public interface JobConstant extends AppConstant {

	public static final Integer DEFAULT_FAIL_NUM = 3;
	public static final String DEFAULT_RUN_METHOD = "run";
	public static final String WINDOW_CLOSE_JS = "window.opener=null;window.open('','_self');window.close();";
	public static final String JOB_INFO_EXP_0001 = "0001";
	public static final String JOB_INFO_EXP_0002 = "0002";
	public static final String JOB_INFO_EXP_0003 = "0003";
	
	public static final String START_TIME_KEY = "startTimeMsg";
	public static final String START_TIME_MSG = "启动时间格式不正确";
	public static final String END_TIME_KEY = "endTimeMsg";
	public static final String END_TIME_MSG = "结束时间格式不正确";
	public static final String CYCLE_KEY = "cycleMsg";
	public static final String CYCLE_MSG = "周期格式不正确";
	public static final String CYCLE_UNIT_KEY = "cycleUnitMsg";
	public static final String CYCLE_UNIT_MSG = "周期单位格式不正确";
	public static final String FAIL_NUM_KEY = "failNumMsg";
	public static final String FAIL_NUM_MSG = "连续失败阀值格式不正确";
}
