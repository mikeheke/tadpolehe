package test;

import java.util.HashMap;
import java.util.Map;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.logging.util.LogFactory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AfwTest {

	@Test
	public void test() {
		
//		ApplicationContext ctx = 
//				new FileSystemXmlApplicationContext("classpath:resources/config/spring/applicationContext.xml");
		
//		ApplicationContext ctx = 
//				new FileSystemXmlApplicationContext("classpath:applicationContext_test.xml");
		
		ApplicationContext ctx = 
				new FileSystemXmlApplicationContext("classpath:applicationContext_test_mysql.xml");

		
		LogFactory.getLogger(this.getClass()).info("=========> ctx: "+ctx);
		
		BaseDataSourceService s = (BaseDataSourceService)ctx.getBean("BaseDataSourceService");
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put("column1", new String[]{"aaa"});
		filterMap.put("column2", new String[]{"dd"});
		filterMap.put("displayname", new String[]{"33"});
		filterMap.put("column1", new String[]{"不错哦"});
		
		ReturnMessage<BaseDataSourceVo> ret = s.getBdsVoData("bds1", filterMap, AppConstant.SQL_OR);
		
		BaseDataSourceVo rv = ret.getReturnObject();
		
		System.out.println(rv.getBdsDatas());
		
	}

}
