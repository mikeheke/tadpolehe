package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ctx = 
				new FileSystemXmlApplicationContext("classpath:resources/config/spring/applicationContext.xml");

		
	}

}
