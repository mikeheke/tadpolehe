package com.amway.frm.base.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 
 * @author lenovo
 *
 */
public class TemplateUtil {

    //替换模板变量
    public static String replaceTemplateVars(String content,
    		Map<String, Object> variableMap) throws IOException{
    	
    	VelocityContext ctx = new VelocityContext(variableMap);
		
		VelocityEngine engine = new VelocityEngine(); 
		engine.init(); 
		
		StringWriter writer = new StringWriter();
		final String MV = "mv";
		engine.evaluate(ctx, writer, MV, content);
		String contentRet = writer.toString();
		writer.close();
		
		return contentRet;
    }
}
