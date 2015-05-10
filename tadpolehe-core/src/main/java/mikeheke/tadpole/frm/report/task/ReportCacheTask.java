/**
 * 
 */
package mikeheke.tadpole.frm.report.task;

import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.report.service.ReportShowService;

/**
 * 
 *
 * 2011-10-17 下午05:35:50
 */
public class ReportCacheTask {

	private ReportShowService reportShowService;

	public ReportCacheTask(){
		final String beanName = "ReportShowService";
		ReportShowService reportShowService = (ReportShowService) ContextFactory.getBean(beanName);
		this.reportShowService = reportShowService;
	}

	public void setReportShowService(ReportShowService reportShowService) {
		this.reportShowService = reportShowService;
	}

	private static Object lock = new Object();;
	
	private static boolean start = true;
	
	public void makeReportByAsynMode() {

		if(start){
			synchronized (lock) {
				try{
					start = false;
					reportShowService.makeCache();
				}finally{
					start = true;
				}
			}
		}
	}
}
