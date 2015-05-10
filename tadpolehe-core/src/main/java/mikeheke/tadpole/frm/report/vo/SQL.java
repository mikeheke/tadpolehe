/**
 * 
 */
package mikeheke.tadpole.frm.report.vo;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.report.util.ReportConstant;

/**
 * 
 *
 * 2011-9-19 下午02:12:30
 */
public class SQL {

	private StringBuffer sql = new StringBuffer(ReportConstant.EMPTY_ONE_STR);
	
	private List<Object> values = new ArrayList<Object>();

	public StringBuffer getSql() {
		return sql;
	}

	public void setSql(StringBuffer sql) {
		this.sql = sql;
	}
	
	public void addSql(String sql){
	
		this.sql.append(sql);
		this.sql.append(ReportConstant.EMPTY_ONE_STR);
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
	
	public void addValue(Object value){
		this.values.add(value);
	}
	
	public void addValues(List<Object> values){
		this.values.addAll(values);
	}
}
