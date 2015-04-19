package service;




import java.sql.SQLException;
import java.util.Date;

import mikeheke.kgem.entity.Knowledge;
import mikeheke.kgem.service.KnowledgeService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class KnowledgeServiceTest {
	
	private static ApplicationContext ctx = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = new FileSystemXmlApplicationContext("classpath:applicationContext_test.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		KnowledgeService knowledgeService = (KnowledgeService)ctx.getBean("knowledgeService");
		
		
//		Knowledge query = new Knowledge();
//		query.setKnowledgeId(1L);
//		
//		Knowledge k = knowledgeService.querySingle(query);
//		
//		Assert.notNull(k);
		
//		KnowledgeDao knowledgeDao = (KnowledgeDao)ctx.getBean("knowledgeDao");
//		Knowledge query = new Knowledge();
//		query.setKnowledgeId(1L);
//		
//		Knowledge k = knowledgeDao.getEntityById(1L);
//		
//		Assert.notNull(k);
		
		Knowledge k = new Knowledge();
		k.setTitle("aaaaa");
		k.setContent("bbbb");
		k.setCreatedTime(new Date());
		
		//knowledgeService.add(k);
		knowledgeService.addOrUpdate(k);
		
		
	}

}
