package kgem;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppTest {

	@Test
	public void test() {
		
		ApplicationContext ctx = 
				new FileSystemXmlApplicationContext("classpath:resources/config/spring/applicationContext.xml");
		
//		EntityManagerFactory factory=Persistence.createEntityManagerFactory
//
//
//				("DB2PU");//这个字符串对应的是配置文件中的:<persistence-unit name="credream" 
//		
	}

}
