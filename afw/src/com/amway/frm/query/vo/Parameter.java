/**
 * 
 */
package com.amway.frm.query.vo;


/**
 * 
 *
 * 2011-4-30 下午12:04:48
 */
public class Parameter {

	private String type;
	
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param type
	 * @param value
	 * @throws ClassNotFoundException 
	 */
	public Parameter(String type, String value){
		super();
		this.type = type;
		this.value = value;
	}

	@Override
	public String toString() {
		final String TYPE = "[type=";
		final String VALUE = ", value=";
		final String END = "]";
		StringBuffer msg = new StringBuffer();
		msg.append(TYPE).append(type);
		msg.append(VALUE).append(value);
		msg.append(END);
		return msg.toString();
	}
}
