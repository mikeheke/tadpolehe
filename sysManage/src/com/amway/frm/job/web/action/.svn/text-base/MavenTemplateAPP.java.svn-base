package com.amway.frm.job.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.exception.exception.AmwayBizException;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;
import com.amway.frm.query.service.ConfigService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * <p>
 * 当开发人员用浏览器在系统框架内配置了一个新的应用后,
 * 可以点击应用编码下载一个小应用maven模板工程,下载的模板工程将会以应用上下文根作为工程名,
 * 也就是下载的文件夹名称和压缩包名称.
 * </p>
 * 下载后即可导入到开发工具内进行编译
 * @author huangbo
 * 
 */
@SuppressWarnings("rawtypes")
public class MavenTemplateAPP extends BaseAction {

	private static final long serialVersionUID = 6706974786066578050L;
														// 配置在基础数据服务的模板工程路径CODE
	public static final String BDS_CODE = "templateAppPath";
	public static final String TMP_CODE = "tmp_path"; 	// 配置在基础数据服务的模板工程路径CODE
	public static final String MVN_CODE = "mvn_path"; 	// 配置在基础数据服务的模板工程路径CODE
														// 默认需要处理的文件，当这些文件里
	public static final String[] PRO_SUFFIX_FILE = { "java", "xml", "project",
			"classpath" };

	private static boolean isCreateSrcDir = true; 		// 是否创建源目录

	private String appCode = "mvnAppTemplate"; 			// 新建应用的应用编码,用来替换模板工程的应用名称
	private String appCode2 = "MvnAppTemplate";

	private String oldName = "smallAppTemplate"; 		// 模板工程的应用编码名称
	private String oldName2 = "SmallAppTemplate";
	private String downLoadFileName; 					// 下载文件名，提供给struts2文件下载模块

	private InputStream inputStream;
	private ConfigService configService;
	private static LogService logger = LogFactory
			.getLogger(MavenTemplateAPP.class);

	/**
	 * 下载小应用maven工程模板.
	 * @return
	 * @throws AmwayBizException
	 */
	public String downLoadTemplateApp() throws AmwayBizException {

		
		String tmpPath = configService.getDisplayNameByCode(BDS_CODE, TMP_CODE);
		String mvnPath = configService.getDisplayNameByCode(BDS_CODE, MVN_CODE);

		if (StringUtils.isEmpty(tmpPath)) {
			throw new AmwayBizException("在服务器上无法确定模板工程路径.");
		}

		long time = System.currentTimeMillis();
		String downLoadFilePath = null;
		String path = tmpPath.substring(0, tmpPath.lastIndexOf(File.separator));

		final String inputDirPath = mvnPath + File.separator + time
				+ File.separator + "src";
		final String outputDirPath = mvnPath + File.separator + time
				+ File.separator + "des";

		try {
			try {
				generateAppFile(path, inputDirPath, outputDirPath);

				if (!StringUtils.isEmpty(outputDirPath)) {
					downLoadFilePath = outputDirPath + File.separator + appCode
							+ ".zip";
					writeByApacheZipOutputStream(outputDirPath + File.separator
							+ appCode, downLoadFilePath,
							"zip dbdatas for amway small application framework");

					inputStream = getInputStream(downLoadFilePath);
					downLoadFileName = appCode + ".zip";
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("下载模板工程" + (appCode) + "失败：" + e.getMessage());
				throw new AmwayBizException("下载模板工程" + (appCode) + "失败");
			}

		} finally {
			try {
				FileUtils.deleteDirectory(new File(inputDirPath));
				FileUtils.deleteDirectory(new File(outputDirPath
						+ File.separator + appCode));
			} catch (IOException e) {
				logger.debug("删除" + (appCode) + "模板工程临时文件夹失败，请手动删除："
						+ e.getMessage());
			}
		}
		return "downLoadFile";
	}

	private InputStream getInputStream(String fileName)
			throws FileNotFoundException {

		InputStream fileIn = new FileInputStream(fileName);

		return fileIn;
	}

	
	public static void writeByApacheZipOutputStream(String src, String archive,
			String comment) throws FileNotFoundException, IOException {

		FileOutputStream f = null;
		CheckedOutputStream csum = null;
		ZipOutputStream zos = null;
		BufferedOutputStream out = null;

		try {
			// ----压缩文件：
			f = new FileOutputStream(archive);
			// 使用指定校验和创建输出流
			csum = new CheckedOutputStream(f, new CRC32());

			zos = new ZipOutputStream(csum);
			// 支持中文
			zos.setEncoding("UTF-8");

			out = new BufferedOutputStream(zos);
			// 设置压缩包注释
			zos.setComment(comment);
			// 启用压缩
			zos.setMethod(ZipOutputStream.DEFLATED);
			// 压缩级别为最强压缩，但时间要花得多一点
			zos.setLevel(Deflater.BEST_COMPRESSION);

			File srcFile = new File(src);

			if (!srcFile.exists()
					|| (srcFile.isDirectory() && srcFile.list().length == 0)) {
				throw new FileNotFoundException(
						"File must exist and  ZIP file must have at least one entry.");
			}
			// 获取压缩源所在父目录
			src = src.replaceAll("\\\\", "/");
			String prefixDir = null;
			if (srcFile.isFile()) {
				prefixDir = src.substring(0, src.lastIndexOf("/") + 1);
			} else {
				prefixDir = (src.replaceAll("/$", "") + "/");
			}

			// 如果不是根目录
			if (prefixDir.indexOf("/") != (prefixDir.length() - 1)
					&& isCreateSrcDir) {
				prefixDir = prefixDir.replaceAll("[^/]+/$", "");
			}

			// 开始压缩
			writeRecursive(zos, out, srcFile, prefixDir);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 使用 org.apache.tools.zip.ZipFile 解压文件，它与 java 类库中的 java.util.zip.ZipFile
	 * 使用方式是一新的，只不过多了设置编码方式的 接口。
	 * 
	 * 注，apache 没有提供 ZipInputStream 类，所以只能使用它提供的ZipFile 来读取压缩文件。
	 * 
	 * @param archive
	 *            压缩包路径
	 * @param decompressDir
	 *            解压路径
	 * @throws Exception
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static void readByApacheZipFile(File archive, String decompressDir)
			throws Exception {

		BufferedInputStream bi = null;
		BufferedOutputStream bos = null;
		ZipFile zf = null;
		List<File> files = new ArrayList<File>();

		zf = new ZipFile(archive, "UTF-8");
		Enumeration e = zf.getEntries();

		try {
			try {
				while (e.hasMoreElements()) {

					ZipEntry ze2 = (ZipEntry) e.nextElement();
					String entryName = ze2.getName();
					String path = decompressDir + File.separator + entryName;

					if (ze2.isDirectory()) {

						File decompressDirFile = new File(path);
						if (!decompressDirFile.exists()) {
							decompressDirFile.mkdirs();
							files.add(decompressDirFile);
						}
					} else {
						String fileDir = null;
						int i = path.lastIndexOf("/");
						int j = path.lastIndexOf("\\");
						if (i > j) {
							fileDir = path.substring(0, i);
						} else {
							fileDir = path.substring(0, j);
						}

						File fileDirFile = new File(fileDir);
						if (!fileDirFile.exists()) {
							fileDirFile.mkdirs();
						}

						File file = null;
						try {

							file = new File(decompressDir + File.separator
									+ entryName);
							bi = new BufferedInputStream(zf.getInputStream(ze2));
							bos = new BufferedOutputStream(
									new FileOutputStream(file));

							byte[] readContent = new byte[1024];
							int readCount = bi.read(readContent);

							while (readCount != -1) {
								bos.write(readContent, 0, readCount);
								readCount = bi.read(readContent);
								bos.flush();
							}
						} finally {
							if (bos != null) {
								bos.close();
							}
							if (bi != null) {
								bi.close();
							}
							if (file != null) {
								files.add(file);
							}
						}
					}
				}
			} finally {
				zf.close();
			}
		} catch (Exception ex) {
			if (!files.isEmpty()) {
				for (File file : files) {
					file.delete();
				}
			}
			throw ex;
		}

	}

	/**
	 * 使用 java api 中的 ZipInputStream 类解压文件，但如果压缩时采用了
	 * org.apache.tools.zip.ZipOutputStream时，而不是 java 类库中的
	 * java.util.zip.ZipOutputStream时，该方法不能使用，原因就是编码方 式不一致导致，运行时会抛如下异常：
	 * java.lang.IllegalArgumentException at
	 * java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:290)
	 * 
	 * 当然，如果压缩包使用的是java类库的java.util.zip.ZipOutputStream 压缩而成是不会有问题的，但它不支持中文
	 * 
	 * @param archive
	 *            压缩包路径
	 * @param decompressDir
	 *            解压路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readByZipInputStream(String archive, String decompressDir)
			throws FileNotFoundException, IOException {
		BufferedInputStream bi;
		// ----解压文件(ZIP文件的解压缩实质上就是从输入流中读取数据):

		FileInputStream fi = new FileInputStream(archive);
		CheckedInputStream csumi = new CheckedInputStream(fi, new CRC32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		bi = new BufferedInputStream(in2);
		java.util.zip.ZipEntry ze;// 压缩文件条目
		// 遍历压缩包中的文件条目
		while ((ze = in2.getNextEntry()) != null) {
			String entryName = ze.getName();
			if (ze.isDirectory()) {
				File decompressDirFile = new File(decompressDir + "/"
						+ entryName);
				if (!decompressDirFile.exists()) {
					decompressDirFile.mkdirs();
				}
			} else {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(decompressDir + "/" + entryName));
				byte[] buffer = new byte[1024];
				int readCount = bi.read(buffer);

				while (readCount != -1) {
					bos.write(buffer, 0, readCount);
					readCount = bi.read(buffer);
				}
				bos.close();
			}
		}
		bi.close();
	}

	/**
	 * 递归压缩
	 * 
	 * 使用 org.apache.tools.zip.ZipOutputStream 类进行压缩，它的好处就是支持中文路径， 而Java类库中的
	 * java.util.zip.ZipOutputStream 压缩中文文件名时压缩包会出现乱码。 使用 apache 中的这个类与 java
	 * 类库中的用法是一新的，只是能设置编码方式了。
	 * 
	 * @param zos
	 * @param bo
	 * @param srcFile
	 * @param prefixDir
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void writeRecursive(ZipOutputStream zos,
			BufferedOutputStream bo, File srcFile, String prefixDir)
			throws IOException, FileNotFoundException {
		ZipEntry zipEntry;

		String filePath = srcFile.getAbsolutePath().replaceAll("\\\\", "/")
				.replaceAll("//", "/");
		if (srcFile.isDirectory()) {
			filePath = filePath.replaceAll("/$", "") + "/";
		}
		String entryName = filePath.replace(prefixDir, "").replaceAll("/$", "");
		if (srcFile.isDirectory()) {
			if (!"".equals(entryName)) {
				// 如果是目录，则需要在写目录后面加上 /
				zipEntry = new ZipEntry(entryName + "/");
				zos.putNextEntry(zipEntry);
			}

			File srcFiles[] = srcFile.listFiles();
			for (int i = 0; i < srcFiles.length; i++) {
				writeRecursive(zos, bo, srcFiles[i], prefixDir);
			}
		} else {
			BufferedInputStream bi = null;
			try {
				bi = new BufferedInputStream(new FileInputStream(srcFile));

				// 开始写入新的ZIP文件条目并将流定位到条目数据的开始处
				zipEntry = new ZipEntry(entryName);
				zos.putNextEntry(zipEntry);
				byte[] buffer = new byte[1024];
				int readCount = bi.read(buffer);

				while (readCount != -1) {
					bo.write(buffer, 0, readCount);
					readCount = bi.read(buffer);
				}
				// 注，在使用缓冲流写压缩文件时，一个条件完后一定要刷新一把，不
				// 然可能有的内容就会存入到后面条目中去了
				bo.flush();
			} finally {
				// 文件读完后关闭
				if (bi != null) {
					bi.close();
				}
			}
		}
	}

	/**
	 * 生成应用文件夹
	 * @param path
	 * @param inputDirPath
	 * @param outputDirPath
	 * @throws IOException
	 */
	private void generateAppFile(String path, String inputDirPath,
			String outputDirPath) throws IOException {

		File src = new File(inputDirPath);
		File des = new File(outputDirPath);
		if (!src.exists()) {
			src.mkdirs();
		}
		if (!des.exists()) {
			des.mkdirs();
		}
		FileUtils.copyDirectory(new File(path), src);
		List<String> needFilterSuffix = Arrays.asList(PRO_SUFFIX_FILE);
		rename(needFilterSuffix, inputDirPath, outputDirPath);
	}

	/**
	 * 对模板文件夹进行更名,以上下文根的名称来替换模板名称。
	 * 包括所有的文件夹名，文件名，文件内容的模板名称
	 * @param needFilterSuffix
	 * @param inputDirPath
	 * @param outputDirPath
	 * @throws IOException
	 */
	public void rename(List<String> needFilterSuffix, String inputDirPath,
			String outputDirPath) throws IOException {
		File dir = new File(inputDirPath);

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				rename(file, outputDirPath, needFilterSuffix);
			}
		} else {
			rename(dir, outputDirPath, needFilterSuffix);
		}
	}

	private void rename(File file, String outputDirPath,
			List<String> needFilterSuffix) throws IOException {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files == null || files.length == 0) {
				String oldParent = file.getParent();
				String destParentPath = outputDirPath
						+ File.separator
						+ oldParent.substring(oldParent.indexOf(oldName))
								.replaceAll(oldName, appCode);
				file.renameTo(new File(destParentPath + File.separator
						+ file.getName()));
			} else {
				for (File f : files) {
					rename(f, outputDirPath, needFilterSuffix);
				}
			}
		} else {

			String oldFileName = file.getName();

			String name = appCode;
			String oldParent = file.getParent();
			String destParentPath = outputDirPath
					+ File.separator
					+ oldParent.substring(oldParent.indexOf(oldName))
							.replaceAll(oldName, appCode);

			String fileSuffix = "";
			int index = oldFileName.lastIndexOf(".");
			if (index > -1) {
				fileSuffix = oldFileName.substring(index);
			}

			File t = null;
			if (oldFileName.toLowerCase().indexOf(oldName.toLowerCase()) > -1) {

				t = new File(destParentPath + File.separator + name
						+ fileSuffix);
			} else {
				t = new File(destParentPath + File.separator + oldFileName);
			}

			if (!t.getParentFile().exists()) {
				t.getParentFile().mkdirs();
			}

			if (needFilterSuffix
					.contains(fileSuffix.substring(1).toLowerCase())) {
				String content = FileUtils.readFileToString(file, "UTF-8");
				content = content.replaceAll(oldName, appCode);
				content = content.replaceAll(oldName2, appCode2);
				FileUtils.writeStringToFile(file, content);
				
				String ap = t.getAbsolutePath();
				if (ap.endsWith("java") || ap.endsWith("class")) {
					String ac = ap.substring(ap.lastIndexOf(File.separator));
					if (ac.toLowerCase().indexOf(appCode.toLowerCase()) > -1) {
						ap = ap.substring(0, ap.lastIndexOf(File.separator) + 1)
								+ appCode2
								+ oldFileName.substring(0 + oldName2.length(),
										oldFileName.lastIndexOf("."))
								+ ac.substring(ac.lastIndexOf("."));
						t = new File(ap);
					}
				}
			}

			file.renameTo(t);
		}
	}

	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		if(!StringUtils.isEmpty(oldName)){
			this.oldName = oldName;
		}
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		if(!StringUtils.isEmpty(appCode)){
			this.appCode = appCode;
			this.appCode2 = this.appCode.substring(0,1).toUpperCase() + this.appCode.substring(1);
		}
	}

	public String getDownLoadFileName() {
		return downLoadFileName;
	}

	public void setDownLoadFileName(String downLoadFileName) {
		this.downLoadFileName = downLoadFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
