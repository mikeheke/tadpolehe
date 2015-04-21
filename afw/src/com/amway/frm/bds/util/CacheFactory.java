/**
 * 
 */
package com.amway.frm.bds.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * 
 *
 * 2011-8-30 下午05:51:14
 */
public class CacheFactory {

	private final static String CACHE_CONF_PATH = "/resources/config/cache/ehcache.xml";
	private final static String CACHE_DEFAULT_NAME = "systemCache"; 
	
	private static CacheManager cacheManager = new CacheManager(
			CacheFactory.class.getResourceAsStream(CACHE_CONF_PATH));
	
	public static Cache getCache(String name){
		
		//新建缓存
		Cache cache = createCache(name);

		return cache;
	}
	
	private static Cache createCache(String name){
		
		// 代码中创建缓存
		Cache cache = cacheManager.getCache(name);
		
		return cache;
	}
	
	public static Cache getCache(){
		
		return getCache(CACHE_DEFAULT_NAME);
	}
	
	public static boolean clearCache(String key){
		return clearCache(CACHE_DEFAULT_NAME, key);
	}
	
	public static boolean clearCache(String name, String key){
		
		if(cacheManager.cacheExists(name)){
			return getCache(name).remove(key);
		}
		
		return false;
	}
}
