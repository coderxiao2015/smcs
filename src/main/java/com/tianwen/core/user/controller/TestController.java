package com.tianwen.core.user.controller;
//package com.tianwen.twmall.controller;
//
//import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.lang.reflect.Method;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import com.sun.pdfview.PDFFile;
//import com.sun.pdfview.PDFPage;
//import com.tianwen.base.controller.BaseController;
//
//@Scope("prototype")
//@Controller
//@RequestMapping(value = "/test")
//public class TestController extends BaseController {
//
//	@GetMapping(value = "/pdf2pic")
//	public static int changePdfToImg() {
//		int countpage = 0;
//		try {
//			String instructiopath = "C:\\Users\\Administrator\\Desktop\\test1\\testfile.pdf";
//			String picturepath = "C:\\Users\\Administrator\\Desktop\\test1\\";
//
//			File file = new File(instructiopath);
//			RandomAccessFile raf = new RandomAccessFile(file, "r");
//			FileChannel channel = raf.getChannel();
//			MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
//			PDFFile pdffile = new PDFFile(buf);
//			// 创建图片文件夹
//			File dirfile = new File(picturepath);
//			if (!dirfile.exists()) {
//				dirfile.mkdirs();
//			}
//			// 获得图片页数
//			countpage = pdffile.getNumPages();
//			for (int i = 1; i <= pdffile.getNumPages(); i++) {
//				PDFPage page = pdffile.getPage(i);
//				Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox().getWidth()), ((int) page.getBBox().getHeight()));
//				int n = 2;
//				/** 图片清晰度（n>0且n<7）【pdf放大参数】 */
//				Image img = page.getImage(rect.width * n, rect.height * n,
//						rect, /** 放大pdf到n倍，创建图片。 */
//						null, /** null for the ImageObserver */
//						true, /** fill background with white */
//						true /** block until drawing is done */
//				);
//				BufferedImage tag = new BufferedImage(rect.width * n, rect.height * n, BufferedImage.TYPE_INT_RGB);
//				tag.getGraphics().drawImage(img, 0, 0, rect.width * n, rect.height * n, null);
//				/**
//				 * File imgfile = new File("D:\\work\\mybook\\FilesNew\\img\\" +
//				 * i + ".jpg"); if(imgfile.exists()){
//				 * if(imgfile.createNewFile()) { System.out.println("创建图片："+
//				 * "D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg"); } else {
//				 * System.out.println("创建图片失败！"); } }
//				 */
//				FileOutputStream out = new FileOutputStream(picturepath + "/" + i + ".png");
//				/** 输出到文件流 */
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
//				param2.setQuality(1f, true);
//				/** 1f~0.01f是提高生成的图片质量 */
//				encoder.setJPEGEncodeParam(param2);
//				encoder.encode(tag);
//				/** JPEG编码 */
//				out.close();
//			}
//			channel.close();
//			raf.close();
//			// unmap(buf);
//			/** 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法 */
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return countpage;
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public static void unmap(final Object buffer) {
//		AccessController.doPrivileged(new PrivilegedAction() {
//			public Object run() {
//				try {
//					Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
//					getCleanerMethod.setAccessible(true);
//					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
//					cleaner.clean();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//		});
//	}
//
//	public static void main(String[] args) {
//		// 长连接
//		String longUrl = "http://blog.csdn.net/everything1209/article/details/48468793";
//		// 转换成的短链接后6位码
//		String[] shortCodeArray = shortUrl(longUrl);
//
//		System.out.println(shortCodeArray);
//
//		for (int i = 0; i < shortCodeArray.length; i++) {
//			System.out.println(shortCodeArray[i]);// 任意一个都可以作为短链接码
//		}
//	}
//
//	public static String[] shortUrl(String url) {
//		// 可以自定义生成 MD5 加密字符传前的混合 KEY
//		String key = "";
//		// 要使用生成 URL 的字符
//		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
//				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
//
//		// 对传入网址进行 MD5 加密
//		String sMD5EncryptResult = DigestUtils.md5Hex(key + url);
//		String hex = sMD5EncryptResult;
//		String[] resUrl = new String[4];
//		for (int i = 0; i < 4; i++) {
//			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
//			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
//			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
//			// long ，则会越界
//			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
//			String outChars = "";
//			for (int j = 0; j < 6; j++) {
//				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
//				long index = 0x0000003D & lHexLong;
//				// 把取得的字符相加
//				outChars += chars[(int) index];
//				// 每次循环按位右移 5 位
//				lHexLong = lHexLong >> 5;
//			}
//
//			// 把字符串存入对应索引的输出数组
//			resUrl[i] = outChars;
//		}
//		return resUrl;
//	}
//
//}
