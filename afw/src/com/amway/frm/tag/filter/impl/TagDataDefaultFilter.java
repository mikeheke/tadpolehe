/**
 * 
 */
package com.amway.frm.tag.filter.impl;

import com.amway.frm.tag.filter.TagDataFilter;

/**
 * 
 *
 * 2011-10-26 上午10:03:56
 */
public class TagDataDefaultFilter implements TagDataFilter {

	/* (non-Javadoc)
	 * @see com.amway.frm.tag.filter.TagDataFilter#match(java.lang.Object)
	 */
	@Override
	public Object matches(Object data, String pattern) {
		
		boolean result = false;
		
		if(null == data){
			return false;
		}
		if(null == pattern){
			return false;
		}
		
		if(data instanceof String){
			result = ((String) data).matches(pattern);
		}
		
		return result;
	}

}
