/**
 * 
 */
package com.amway.frm.query.vo;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.query.entity.Where;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：表
 */
public class Table implements java.io.Serializable 

{ 


	//用户条件结果集
	private List<Where> wheres = new ArrayList<Where>();
	
	//查询列结果集
	private List<Column> columns = new ArrayList<Column>();
	
	//查询数据
	private List<Row> datas = new ArrayList<Row>();
	
	//总行数
	private int totalRow;
	
	private int rowStart;
	
	private int rowEnd;

	private int pageNum;
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Row> getDatas() {
		return datas;
	}

	public void setDatas(List<Row> datas) {
		this.datas = datas;
	}

	public List<Where> getWheres() {
		return wheres;
	}

	public void setWheres(List<Where> wheres) {
		this.wheres = wheres;
	}
	
	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
