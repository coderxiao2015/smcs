package com.tianwen.common.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tianwen.common.log.LogUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class Freemarker {
	private static Configuration config;
	
	static Map<String, Object> initMap;

	static {
		initMap = new LinkedHashMap<>();
		
		config = new Configuration();
		config.setLocale(Locale.getDefault());
		config.setEncoding(Locale.getDefault(), "UTF-8");
	}
	
	/**
	 * 
	 * @param path 模版路径
	 * @param inFile 模版文件
	 * @param outPath 输出html路径
	 * @param outFile 输出html NAME+后缀
	 * @param map 只是一个传值的对象，可以为空
	 * @throws IOException 
	 * @throws TemplateException 
	 * @throws Exception
	 */
	public  void outHtml(String path, String inFile,String outPath,String outFile,Map<String,Object> outMap) throws IOException, TemplateException{
		FileOutputStream fos  = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		try{
			
			//3、加载模板目录
			File filex = new File(path);
			config.setDirectoryForTemplateLoading(filex) ;
			//4、加载模板文件
			Template temp = config.getTemplate(inFile) ;
			//设置编码
			temp.setEncoding("UTF-8") ;
			//5、构建一个File对象输出 
			File file = new File(outPath + outFile) ;
			fos = new FileOutputStream(file) ;
			osw = new OutputStreamWriter(fos,"UTF-8") ;
			bw  = new BufferedWriter(osw) ;
			//6、准备数据模型
			// 模版方法模式,子类实现
			Map<String, Object> resultMap =  doOutMap(outMap);
			
			resultMap.putAll(initMap);
			
			//7、调用Template对象的process方法来输出文件
			temp.process(resultMap, bw) ;
		}finally{
			try {
				if(bw!=null) bw.flush();
				if(fos!=null)fos.close();
				if(osw!=null)osw.close();
				if(bw!=null) bw.close();
			} catch (IOException e) {
				LogUtils.error(this.getClass(), "创建 ["+ outFile +"] . io close exception", e);
			}
		}
		
	}
	
	Map<String,Object> doOutMap(Map<String, Object> outMap){
		return new HashMap<String, Object>();
	};
}
