/**
 * 
 */
package com.amway.frm.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 文件操作工具类
 * 
 * @author fenghanhao 
 * CreateTime: 2011-3-28 下午04:22:20
 */
public class FileHelper {

	private static final int BUFFER_SIZE = AppConstant._16*AppConstant._1024;

	/**
	 * 获得文件后缀
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(AppConstant.DOT_SIGN);
		return fileName.substring(pos);
	}

	/**
	 * 拷贝文件内容
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
	}

	/**
	 * 压缩并拷贝文件内容
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	public static void compressAndCopy(File src, File dst) {
		String dstFileName = DataConverter.valueOf(dst.getName());
		try {
			InputStream in = null;
			OutputStream out = null;
			ZipOutputStream zipOut = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);

				// 创建ZIP数据输出流对象
				zipOut = new ZipOutputStream(new FileOutputStream(dst));
				// 创建指向压缩原始文件的入口
				ZipEntry entry = new ZipEntry(dstFileName.substring(dstFileName
						.lastIndexOf('/') + 1, (int) dst.length())); // 0
				zipOut.putNextEntry(entry);

				out = new BufferedOutputStream(zipOut, BUFFER_SIZE);

				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
				if (null != zipOut) {
					zipOut.close();
				}

			}
		} catch (Exception e) {
			throw new AmwaySysException(e);
		}
	}

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]
	 * </p>
	 * 
	 * @param file
	 * @return fileType
	 */
	public final static String getFileTypeByFile(File file) {
		String filetype = null;
		final Integer len = 50;
		byte[] b = new byte[len];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			is.read(b);
			filetype = getFileTypeByStream(b);
		} catch (FileNotFoundException e) {
			throw new AmwaySysException(e);
		} catch (IOException e) {
			throw new AmwaySysException(e);
		}finally{
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
		return filetype;
	}

	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getAllFileType,常见文件头信息]
	 * </p>
	 * 
	 * @author:[shixing_11@sina.com]
	 */
	private static void getAllFileType() {
		FILE_TYPE_MAP.put(AppConstant.JPG, AppConstant.JPG_VALUE); // JPEG (jpg)
		FILE_TYPE_MAP.put(AppConstant.PNG, AppConstant.PNG_VALUE); // PNG (png)
		FILE_TYPE_MAP.put(AppConstant.GIF, AppConstant.GIF_VALUE); // GIF (gif)
		FILE_TYPE_MAP.put(AppConstant.TIF, AppConstant.TIF_VALUE); // TIFF (tif)
		FILE_TYPE_MAP.put(AppConstant.BMP, AppConstant.BMP_VALUE); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put(AppConstant.DWG, AppConstant.DWG_VALUE); // CAD (dwg)
		FILE_TYPE_MAP.put(AppConstant.HTML, AppConstant.HTML_VALUE); // HTML (html)
		FILE_TYPE_MAP.put(AppConstant.RTF, AppConstant.RTF_VALUE); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put(AppConstant.XML, AppConstant.XML_VALUE);
		FILE_TYPE_MAP.put(AppConstant.ZIP, AppConstant.ZIP_VALUE);
		FILE_TYPE_MAP.put(AppConstant.RAR, AppConstant.RAR_VALUE);
		FILE_TYPE_MAP.put(AppConstant.PSD, AppConstant.PSD_VALUE); // Photoshop (psd)
		FILE_TYPE_MAP.put(AppConstant.EML, AppConstant.EML_VALUE); // Email

		FILE_TYPE_MAP.put(AppConstant.DBX, AppConstant.DBX_VALUE); // Outlook Express (dbx)
		FILE_TYPE_MAP.put(AppConstant.PST, AppConstant.PST_VALUE); // Outlook (pst)
		FILE_TYPE_MAP.put(AppConstant.XLS, AppConstant.XLS_VALUE); // MS Word
		FILE_TYPE_MAP.put(AppConstant.DOC, AppConstant.DOC_VALUE); // MS Excel 注意：word 和 excel的文件头一样
		FILE_TYPE_MAP.put(AppConstant.MDB, AppConstant.MDB_VALUE); // MS Access (mdb)
		FILE_TYPE_MAP.put(AppConstant.WPD, AppConstant.WPD_VALUE); // WordPerfect (wpd)
		FILE_TYPE_MAP.put(AppConstant.EPS, AppConstant.EPS_VALUE);
		FILE_TYPE_MAP.put(AppConstant.PS, AppConstant.PS_VALUE);
		FILE_TYPE_MAP.put(AppConstant.PDF, AppConstant.PDF_VALUE); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put(AppConstant.QDF, AppConstant.QDF_VALUE); // Quicken (qdf)
		FILE_TYPE_MAP.put(AppConstant.PWL, AppConstant.PWL_VALUE); // Windows Password (pwl)
		FILE_TYPE_MAP.put(AppConstant.WAV, AppConstant.WAV_VALUE); // Wave (wav)
		FILE_TYPE_MAP.put(AppConstant.AVI, AppConstant.AVI_VALUE);
		FILE_TYPE_MAP.put(AppConstant.RAM, AppConstant.RAM_VALUE); // Real Audio (ram)
		FILE_TYPE_MAP.put(AppConstant.RM, AppConstant.RM_VALUE); // Real Media (rm)
		FILE_TYPE_MAP.put(AppConstant.MPG, AppConstant.MPG_VALUE); //     
		FILE_TYPE_MAP.put(AppConstant.MOV, AppConstant.MOV_VALUE); // Quicktime (mov)
		FILE_TYPE_MAP.put(AppConstant.ASF, AppConstant.ASF_VALUE); // Windows Media (asf)
		FILE_TYPE_MAP.put(AppConstant.MID, AppConstant.MID_VALUE); // MIDI (mid)
	}

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileTypeByStream]
	 * </p>
	 * 
	 * @param b
	 * @return fileType
	 */
	public final static String getFileTypeByStream(byte[] b) {
		getAllFileType();
		String filetypeHex = String.valueOf(getFileHexString(b));
		Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP
				.entrySet().iterator();
		while (entryiterator.hasNext()) {
			Entry<String, String> entry = entryiterator.next();
			String fileTypeHexValue = entry.getValue();
			if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileHexString]
	 * </p>
	 * 
	 * @param b
	 * @return fileTypeHex
	 * @author:[shixing_11@sina.com]
	 */
	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		final int B_FF = 0xFF;
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & B_FF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
