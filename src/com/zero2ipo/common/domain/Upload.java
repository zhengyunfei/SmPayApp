package com.zero2ipo.common.domain;

import com.sun.istack.logging.Logger;
import com.zero2ipo.common.domain.exception.DomainFileUploadException;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.mobile.utils.SignUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.ContextLoader;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * 跨域文件上传
 * @author zhengyunfei
 *
 */
public class Upload {

	Logger logger = Logger.getLogger(Upload.class);

	private String fileMax = "4194304";
	private String domainUrl = "";
	private String fileLocationTempDirectory = "";
    private long sizeMax = -1;

	private Map<String, Object> rJsonMap = new HashMap<String, Object>();

	private String[] fileType = new String[]{".jpg",".gif",".bmp",".png", ".jpeg"};

	public Upload (String fileMax, String domainUrl, String fileLocationTempDirectory)
	{
		this.fileMax = fileMax;
		this.domainUrl = domainUrl.trim();
		this.fileLocationTempDirectory = fileLocationTempDirectory;
	}

	/*
	 * 获取返回数据
	 */
	public String getStringValue(String field) {
		String rStr = "";
		Object o = rJsonMap.get(field);
		if(o != null && !"".equals(o))
		{
			rStr = o.toString();
		}
		return rStr;
	}

	/*
	 * 跨域请求
	 * @return
	 */
	public String requestDomainUpload (HttpServletRequest request, String path) throws DomainFileUploadException
	{
        Map<String, String> textMap = new HashMap<String, String>();
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("file", path);
		String str = formUpload(domainUrl, textMap, fileMap);
		if(str != null && !"".equals(str))
		{
			rJsonMap = StringUtil.JsonToMap(JSONObject.fromObject(str));
		}
		return str;
	}


	/*
     * 上传图片
     */
    private String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap) throws DomainFileUploadException {
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, String>> iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    MagicMatch match = Magic.getMagicMatch(file, false, true);
                    String contentType = match.getMimeType();

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            res = strBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
        	e.printStackTrace();
        	//logger.info("发送POST请求出错。" + urlStr);
           // throw new DomainFileUploadException();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    /*
     * 获取上传文件目录
     */
    public String getUploadFileDirectory() {
    	return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(fileLocationTempDirectory) + "/";
    }

    /*
     * 上传文件到本地临时目录
     */
	@SuppressWarnings("unchecked")
	public Map<String, String> fileLocationTempUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String relaPath = getUploadFileDirectory();
        String x=request.getParameter("length");
        String y = request.getParameter("wide");
        String temp = relaPath + "temp";

        //创建该目录
        createDirectory(relaPath);
        createDirectory(temp);

        //配置
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(temp));
        factory.setSizeThreshold(1024*1024);//10M

        ServletFileUpload upload = new ServletFileUpload(factory);
        //将页面请求传递信息最大值设置为50M
       // upload.setSizeMax(1024*1024*50);
       // upload.setFileSizeMax(10*1024*1024);//设置上传文件大小3M
        upload.setSizeMax(-1);//设置请求总文件大小6M
        //将单个上传文件信息最大值设置为25M
       // upload.setFileSizeMax(1024*1024*12);
        //处理请求中的文件
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String filePath = "";
        String urlPath = "";
        String fName = "";
        for (FileItem item : items)
        {
        	if(!item.isFormField())
        	{
        		String fileName = item.getName().toLowerCase();
                System.out.println("你上传的图片的大小====================================="+item.getSize());
                if(item.getSize()>1024){
                    fileName = thumbnailatorImage(filePath, fName, "", Integer.parseInt(x), Integer.parseInt(y));
                }
                if(validateFileFmt(fileName)){
            		String dsign = SignUtils.generateDateSign("yyyyMMddHHmmss");
            		String random4 = SignUtils.generateRandom(4);
            		fName = dsign + random4 + fileName.substring(fileName.lastIndexOf("."));
                    filePath = relaPath + fName;
                    urlPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath() + "/upload/" + fName;
                    try {
                        item.write(new File(filePath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        	}
        	else
        	{
        		String formField = item.getFieldName();
        		if("tempFileName".equals(formField))
        		{
        			//删除临时文件
        			removeLocationUploadFile(relaPath + item.getString());
        		}
        	}
        }
        //压缩图片
        if(x!=null&&!"".equals(x) && y!=null&&!"".equals(y)) {
            fName = thumbnailatorImage(filePath, fName, "", Integer.parseInt(x), Integer.parseInt(y));
        }
        Map<String, String> path = new HashMap<String, String>();
        path.put("filePath", filePath);
        path.put("urlPath", urlPath);
        path.put("fileName", fName);

		return path;
	}

	/*
	 * 目录不存在创建目录
	 */
	private void createDirectory(String directory) {
		if(!new File(directory).isDirectory())
		{
			new File(directory).mkdirs();
		}
	}

	/*
	 * 校验上传文件格式
	 */
	private boolean validateFileFmt(String fileName) {
		for(String ftype : fileType)
		{
			if(fileName.endsWith(ftype))
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除本地上传文件
	 */
	public void removeLocationUploadFile(String filePath) {
		if(filePath != null && !"".equals(filePath))
		{
			File file = new File(filePath);
			if(file.exists())
			{
				if(file.isFile())
				{
					file.delete();
				}
			}
		}
	}


    /**
     * 下载压缩压缩并保存图片
     * @param oldSavePath 原文件路径
     * @param oldFileName 原文件名称
     * @param fix 文件类型
     * @param x 需要压缩的宽度
     * @param y 需要压缩的长度
     * @return
     * @throws IOException
     */
    public static String downloadThumbnailatorImage(String servicePath,String uri,int x,int y) throws IOException {
        //校验图片是否存在
        String uriSubPath = uri.substring(0, uri.lastIndexOf("/")+1);//文件名以前，服务器以后
        String fileName = uri.substring(uri.lastIndexOf("/")+1,uri.length());//文件名
        String getThumbnailatorPath = servicePath + uriSubPath+x+"-"+y+"/";
        String saveFileName = getThumbnailatorPath+fileName;
        File downFilePath = new File(getThumbnailatorPath);//压缩以后的文件夹
        File downFile = new File(saveFileName);//压缩以后的文件
        if (downFilePath.isDirectory()&&downFile.exists()) {
            return saveFileName;
        } else {
            //Thumbnail读取并压缩图片
            //log.error(servicePath+uri);
            BufferedImage waterMarkBufferedImage = Thumbnails.of(servicePath + uri)
                    //Thumbnail的方法,压缩图片
                    .size(x, y)
                            //读取成BufferedImage对象
                    .asBufferedImage();
            if (!downFilePath.isDirectory()) {
                downFilePath.mkdirs();
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 4);
            factory.setRepository(downFilePath);
            ServletFileUpload uploader = new ServletFileUpload(factory);
            uploader.setSizeMax(20 * 1024 * 1024);
            File fileOutPut = new File(saveFileName);
            ImageIO.write(waterMarkBufferedImage, "jpg", fileOutPut);
        }
        return saveFileName;
    }
    /**
     * 上传压缩压缩并保存图片
     * @param oldSavePath 原文件路径
     * @param oldFileName 原文件名称
     * @param fix 文件类型
     * @param x 需要压缩的宽度
     * @param y 需要压缩的长度
     * @return
     * @throws IOException
     */
    public static String thumbnailatorImage(String oldSavePath,String oldFileName,String fix,int x,int y) throws IOException {
        //Thumbnail读取并压缩图片
        BufferedImage waterMarkBufferedImage = Thumbnails.of(oldSavePath+oldFileName)
                //Thumbnail的方法,压缩图片
                .size(x, y)
                        //读取成BufferedImage对象
                .asBufferedImage();
        //把内存中的图片写入到指定的文件中
        String savePath = oldSavePath+x+"-"+y+"/";
        File saveFile = new File(savePath);
        if (!saveFile.isDirectory())
            saveFile.mkdirs();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 4);
        factory.setRepository(saveFile);
        ServletFileUpload uploader = new ServletFileUpload(factory);
        uploader.setSizeMax(20 * 1024 * 1024);
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString();
        fileName += "." + fix;
        String saveFileName = savePath+fileName;
        File fileOutPut = new File(saveFileName);
        ImageIO.write(waterMarkBufferedImage, fix, fileOutPut);
        return saveFileName;
    }
}
