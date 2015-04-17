/**
 * 
 */
package com.amway.frm.tag.filter;

/**
 * @author huangweijin
 *
 * 2011-10-26 上午10:00:08
 */
public interface TagDataFilter {

	Object matches(Object data, String pattern);
}
