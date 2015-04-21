/**
 * 
 */
package com.amway.frm.logging.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.amway.frm.logging.util.LogConstant;

/**
 * 
 *
 * 2011-5-14 上午09:39:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface Monitor {
	String name() default LogConstant.EMPTY_STR;
}
