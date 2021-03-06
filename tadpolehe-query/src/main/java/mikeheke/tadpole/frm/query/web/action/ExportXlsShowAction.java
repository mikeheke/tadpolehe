/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Select;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.Row;

import org.apache.struts2.ServletActionContext;

/**
 * 
 *
 * 2011-6-17 下午01:19:24
 */
public class ExportXlsShowAction extends ShowAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020812277115649588L;

	private WritableWorkbook writableWorkbook;
	
	private WritableSheet writableSheet;
	
	boolean firstQuery = true;
	
	public String export() throws Exception{
		
		this.firstQuery = true;
		
		super.execute();
		Query query = getQueryFromContext();
		
		OutputStream outputStream = genExportOs(query.getQueryName());
		
		exportBefore(query.getQueryName(), outputStream);
		exportHead(query.getSelects(), outputStream);
		
		exportDatas(query, outputStream);
		
		int totalRow = query.getTable().getTotalRow();
		int rowEnd = query.getTable().getRowEnd();
		
		for( ; rowEnd <= totalRow ; ){
			
			super.execute();
			query = getQueryFromContext();
			
			exportDatas(query, outputStream);
			
			rowEnd = query.getTable().getRowEnd();
		}
		
		this.exportAfter();
		
		return NONE;
	}
	
	protected OutputStream genExportOs(String fileName) 
		throws IOException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(QueryConstant.EXPORT_CONTENT_TYPE);
		response.setContentType(getMimeType());
		response.setHeader(QueryConstant.EXPORT_CONTENT_DISPOSITION, QueryConstant.EXPORT_ATTACHMENT
				+QueryConstant.QUO_D_SIGN+DataConverter.strGbkToIso(fileName)+QueryConstant.QUO_D_SIGN);
		response.setHeader(QueryConstant.EXPORT_CACHE_CONTROL, QueryConstant.EXPORT_MUST_POST_PRE);
		response.setHeader(QueryConstant.EXPORT_PRAGMA, QueryConstant.EXPORT_PUBLIC);
		final Integer EXPORT_EXPIRES = 1000;
		response.setDateHeader(QueryConstant.EXPORT_EXPIRES, (System.currentTimeMillis() + EXPORT_EXPIRES));
		
		return response.getOutputStream();
	}
	
	protected String getMimeType(){
		return QueryConstant.MIME_VND_MS_EXCEL;
	}
	
	protected void exportBefore(String sheetName, OutputStream outputStream) 
		throws Exception {
		//Runtime.getRuntime().freeMemory();
		writableWorkbook = Workbook.createWorkbook(outputStream);
		writableSheet = writableWorkbook.createSheet(sheetName, 0); 
	}
	
	protected void exportHead(List<Select> selects, OutputStream outputStream) 
		throws Exception{
		
		WritableCellFormat wcfFC = getHeaderCellFormat();
		int size = selects.size();
		for(int i = 0; i < size; i++){
			Select select = selects.get(i);
			Label label = new Label(i, 0, select.getDes(), wcfFC);
			writableSheet.addCell(label);
		}
	}
	
	protected WritableCellFormat getHeaderCellFormat(){
		//列头单元格字体格式
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);
		
		return wcfFC;
	}
	
	protected void exportDatas(Query query, OutputStream outputStream) 
		throws Exception {
		//数据单元格字体格式
		List<Select> selects = query.getSelects();
		List<Row> datas = query.getTable().getDatas();
		WritableCellFormat wcfFC = getDataCellFormat();
		int rowSize = datas.size();
		int colSize = selects.size();
		for(int i=0; i<rowSize; i++){
			int rows = writableSheet.getRows();
			Row row = datas.get(i);
			for(int j=0; j<colSize; j++){
				Select select = selects.get(j);
				StringBuffer colName =  new StringBuffer();
				colName.append(select.getTableName());
				colName.append(select.getFieldName());
				colName.append(select.getFieldAlias());
				
				Object data = row.get(colName.toString());
				WritableCell writableCell = new Label(j, rows, 
						DataConverter.NoHTML(data.toString()), wcfFC);
				writableSheet.addCell(writableCell);
			}
			rows++;
		}
	}
	
	protected WritableCellFormat getDataCellFormat(){
		
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, 
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK); 
			WritableCellFormat wcfFC = new WritableCellFormat(wfc);
		
		return wcfFC;
	}
	
	protected void exportAfter() throws Exception {
		
		//Runtime.getRuntime().freeMemory();
		
		writableWorkbook.write();
		writableWorkbook.close();
	}
	
	/*
	 根据数据类型，创建单元格
	 
	private Object getWritableCell(Select select, WritableCellFormat wcf, 
			int col, int row, Object obj) throws Exception {
		if (obj == null) {
			obj = "";
		}
		WritableCell writableCell = null;
		if (obj instanceof java.lang.Double) {
			//添加Double对象 
			double value = Double.valueOf(obj.toString()).doubleValue();		
			DecimalFormat decimalFormat = null;
			decimalFormat = new DecimalFormat("###,##0.00");
			String strValue = decimalFormat.format(value);
			writableCell = new Label(col, row, strValue , wcf);
		} else if (obj instanceof Date) {
			//添加带有formatting的DateFormat对象
			java.util.Date value = java.sql.Date.valueOf(obj.toString());
			jxl.write.DateFormat df = new jxl.write.DateFormat("yyyy-MM-dd");
			WritableCellFormat wcfDF = new WritableCellFormat(df);
			writableCell = new jxl.write.DateTime(col, row, value, wcfDF);
		} else {
			//添加Label对象 ,并将文本中的HTML标签去除
			writableCell = new Label(col, row, DataConverter.NoHTML(obj.toString()),wcf);
		}
		return writableCell;
	}*/
	
	@Override
	protected void setPageAttributes(Query query, int rowStart, int rowEnd,
			int pageNum) {
		
		if(firstQuery){
			super.setPageAttributes(query, 0, 5, 5);
			firstQuery = false;
		}else{
			super.setPageAttributes(query, 
					query.getTable().getRowStart()+query.getTable().getPageNum(), 
					query.getTable().getRowEnd()+query.getTable().getPageNum(), 
					query.getTable().getPageNum());
		}
	}
}
