package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.service.ApplicationService;

public class AppTest {

	@Test
	public void test() {
		
//		ApplicationContext ctx = 
//				new FileSystemXmlApplicationContext("classpath:resources/config/spring/applicationContext.xml");
		
		ApplicationContext ctx = 
				new FileSystemXmlApplicationContext("classpath:applicationContext_test.xml");
		System.out.println("=========> ctx: "+ctx);
		
		ApplicationService s = (ApplicationService)ctx.getBean("ApplicationService");
		
		System.out.println(s);
		
		System.out.println(
		s.getApplicationsJDBC().getReturnObjects().get(0).getApplicationName()
				);
		
		System.out.println(
		s.getApplications(new Application()).getReturnObjects().get(0).getApplicationName());
		
//		EntityManagerFactory factory=Persistence.createEntityManagerFactory
//
//
//				("DB2PU");//这个字符串对应的是配置文件中的:<persistence-unit name="credream" 
//		
	}

}
