package com.tianwen.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * 工具集合类.<br/>
 * 
 * @author Feiqiumin
 * @date 2011-5-26
 */
public class SysUtils {

	public static String append(String str, String tmp) {
		if (SysUtils.isEmpty(str)) {
			str += tmp;
		} else {
			str += "," + tmp;
		}
		return str;
	}

	/**
	 * 判断一个对象是否为空
	 * 
	 * @param obj
	 *            任意对象
	 * @return 是/否
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj instanceof String) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			} else if (obj instanceof StringBuffer) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 描述 :通过 java.net.URL 类访问一个页面，并且返回结果<br/>
	 * 参考：http://java.sun.com
	 * 
	 * @param strUrl
	 *            ：访问路径
	 * @return：返回字符串，错误则返回"error open url"
	 */
	public static String getContent(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s);
				// sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url" + strUrl;
		}
	}

	/**
	 * MD5加密方法。
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String retMd5Pwd(String pwd) throws Exception {
		String s = pwd;
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		StringBuffer bufHs = new StringBuffer();

		String stmp = "";
		for (int n = 0; n < md.length; n++) {
			stmp = (Integer.toHexString(md[n] & 0XFF));
			if (stmp.length() == 1) {
				bufHs.append("0").append(stmp);
			} else {
				bufHs.append(stmp);
			}
		}
		return bufHs.toString().toUpperCase();

	}

	public static String MD5(String pwd) {
		if (pwd == null)
			return null;
		if (pwd.trim().length() == 0)
			return pwd;
		Md5 md5 = new Md5();
		return md5.getMD5ofStr(pwd);
	}

	public static String format(Object temp) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (temp instanceof String) {
			return temp.toString();
		} else if (temp instanceof Date) {
			return sf.format(temp);
		} else if (temp instanceof java.sql.Date) {
			return sf.format(temp);
		} else {
			if (temp == null) {
				return "";
			}
			return temp.toString();
		}
	}

	public static String format(Date obj, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(obj);
	}

	public static Date parseDate(String str) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String str, String format) {

		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据时间字符串把天数加number天 把1改成-1就为最近1天的。
	 * 
	 * @param number
	 *            为正整数，则为现在时间加是几天。为负整数则为最近几天。
	 * @return
	 */
	public static String addNumberDay(String time, int number) {
		String add = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date timeNow = df.parse(time);
			Calendar begin = Calendar.getInstance();
			begin.setTime(timeNow);
			begin.add(Calendar.DAY_OF_MONTH, number);
			add = df.format(begin.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return add;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}

	// 处理空字符串
	public static String getStringValue(String str) {
		if ("".equals(str) || str == null) {
			return "";
		}
		return str.trim();
	}
	
	/**
	 * 截取字符串
	 * 
	 **/
	public static String getSubStr(String str, int num) {
		String result = "";
		int i = 0;
		while (i < num) {
			int lastFirst = str.lastIndexOf('\\');
			result = str.substring(lastFirst) + result;
			str = str.substring(0, lastFirst);
			i++;
		}
		return result.substring(1);
	}

	public static String postHttpReq(String url, HashMap<String, Object> map) {
		HttpClient httpClient = new HttpClient();
		EntityEnclosingMethod postMethod = new PostMethod(url);
		String appendData = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			appendData += entry.getKey() + "=" + entry.getValue() + "&";
		}
		if (appendData.length() > 0)
			postMethod.setQueryString(appendData.substring(0, appendData.length() - 1));
		postMethod.setRequestHeader("Content-Type", "application/octet-stream");

		// RequestEntity request = new InputStreamRequestEntity(in);
		// postMethod.setRequestEntity(request);

		String responseMsg = "";
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);// 发送请求
			responseMsg = postMethod.getResponseBodyAsString();// 获取返回值
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}

		return responseMsg;
	}

	public static String postHttpReq(String url, String jsonData) {
		HttpClient httpClient = new HttpClient();
		EntityEnclosingMethod postMethod = new PostMethod(url);
		postMethod.setQueryString(jsonData);
		postMethod.setRequestHeader("Content-Type", "application/octet-stream");

		// RequestEntity request = new InputStreamRequestEntity(in);
		// postMethod.setRequestEntity(request);

		String responseMsg = "";
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);// 发送请求
			responseMsg = postMethod.getResponseBodyAsString();// 获取返回值
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}

		return responseMsg;
	}

	public static String sendPost(String param, String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			// out = new PrintWriter(conn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
//	public static String sendFilePost(String path, File file) throws IOException{
//		URL urlObj = new URL(path);
//	    //连接
//	    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
//	    String result = null;
//	    con.setDoInput(true);
//	    con.setDoOutput(true);
//	    con.setUseCaches(false); // post方式不能使用缓存
//
//	    // 设置请求头信息
//	    con.setRequestProperty("Connection", "Keep-Alive");
//	    con.setRequestProperty("Charset", "UTF-8");
//	    // 设置边界
//	    String BOUNDARY = "----------" + System.currentTimeMillis();
//	    con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//
//	    // 请求正文信息
//	    // 第一部分：
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("--"); // 必须多两道线
//	    sb.append(BOUNDARY);
//	    sb.append("\r\n");
//	    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\"" + file.getName() + "\"\r\n");
//	    sb.append("Content-Type:application/octet-stream\r\n\r\n");
//	    byte[] head = sb.toString().getBytes("utf-8");
//	    // 获得输出流
//	    OutputStream out = new DataOutputStream(con.getOutputStream());
//	    // 输出表头
//	    out.write(head);
//
//	    // 文件正文部分
//	    // 把文件已流文件的方式 推入到url中
//	    DataInputStream in = new DataInputStream(new FileInputStream(file));
//	    int bytes = 0;
//	    byte[] bufferOut = new byte[1024];
//	    while ((bytes = in.read(bufferOut)) != -1) {
//	        out.write(bufferOut, 0, bytes);
//	    }
//	    in.close();
//	    // 结尾部分
//	    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
//	    out.write(foot);
//	    out.flush();
//	    out.close();
//	    StringBuffer buffer = new StringBuffer();
//	    BufferedReader reader = null;
//	    try {
//	        // 定义BufferedReader输入流来读取URL的响应
//	        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	        String line = null;
//	        while ((line = reader.readLine()) != null) {
//	            buffer.append(line);
//	        }
//	        if (result == null) {
//	            result = buffer.toString();
//	        }
//	    } catch (IOException e) {
//	        System.out.println("发送POST请求出现异常！" + e);
//	        e.printStackTrace();
//	    } finally {
//	        if (reader != null) {
//	            reader.close();
//	        }
//	    }
//	    return result;
//	}
}
