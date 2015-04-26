package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.logging.util.LogFactory;

public class AfwTest {

	@Test
	public void test() {
		
//		ApplicationContext ctx = 
//				new FileSystemXmlApplicationContext("classpath:resources/config/spring/applicationContext.xml");
		
		ApplicationContext ctx = 
				new FileSystemXmlApplicationContext("classpath:applicationContext_test.xml");
		
		LogFactory.getLogger(this.getClass()).info("=========> ctx: "+ctx);
		
		BaseDataSourceService s = (BaseDataSourceService)ctx.getBean("BaseDataSourceService");
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put("column1", new String[]{"aaa"});
		filterMap.put("column2", new String[]{"dd"});
		
		ReturnMessage<BaseDataSourceVo> ret = s.getBdsVoData("bds1", filterMap, AppConstant.SQL_AND);
		
		BaseDataSourceVo rv = ret.getReturnObject();
		
		System.out.println(rv.getBdsDatas());
		
	}

}
