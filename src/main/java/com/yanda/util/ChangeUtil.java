package com.yanda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUtil {

	public static final Pattern A_MAILTO_PATTERN = Pattern.compile("<a([^>]*)mailTo:([^>]*)>(.+?)</a>",
			Pattern.CASE_INSENSITIVE);

	public static final Pattern A_URL_PATTERN = Pattern
			.compile("<a([^>]*)href\\s*=[\"|']([^>].+?)[\"|']([^>].+?)>(.+?)</a>", Pattern.CASE_INSENSITIVE);

	public static final Pattern KJJ_IMG_PATTERN = Pattern.compile("\\[IMG\\](.+?)\\[/IMG\\]", Pattern.CASE_INSENSITIVE);

	public static final Pattern KJJ_URL_PATTERN = Pattern.compile("\\[URL=(.+?)\\](.+?)\\[/URL\\]",
			Pattern.CASE_INSENSITIVE);

	public static final Pattern KJJ_PATTERN = Pattern.compile("\\[(.+?)\\]", Pattern.CASE_INSENSITIVE);

	/**
	 * 测试连接是否有效
	 * 
	 * @param urlStr
	 * @return
	 */
	public static int isConnect(String urlStr) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			return connection.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("测试链接[" + urlStr + "]发生错误:" + e);
		} catch (IOException e) {
			System.err.println("测试链接[" + urlStr + "]发生错误:" + e);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("测试链接[" + urlStr + "]发生错误:" + e);
		} finally {
			connection.disconnect();
		}
		return 500;
	}

	/**
	 * 过滤带有mailTo的<a>标签，只保留内容 例： <a href='mailTo:12313'>13213123</a> -> 13213123
	 * 
	 * @param content
	 * @return
	 */
	public static String aTagReplace(String content) {
		Matcher matcher = A_MAILTO_PATTERN.matcher(content);
		if (matcher.find()) {
			return aTagReplace(matcher.replaceFirst("$3"));
		} else {
			return content;
		}
	}

	/**
	 * 匹配所有<a>标签，校验属性href的url是否能正常访问，如果不能则去掉该标签，只保留文本
	 * 
	 * @param content
	 * @return
	 */
	public static String aTagUrlCheck(String content, String domain) {
		Matcher matcher = A_URL_PATTERN.matcher(content);
		if (matcher.find()) {
			String hrefUrl = matcher.group(2);
			if (hrefUrl.startsWith("http://")) {
				if (ChangeUtil.isConnect(hrefUrl) != 200) {
					return aTagUrlCheck(matcher.replaceFirst("$4"), domain);
				}
			} else if (hrefUrl.startsWith("/")) {
				if (ChangeUtil.isConnect(domain + hrefUrl) != 200) {
					return aTagUrlCheck(matcher.replaceFirst("$4"), domain);
				}
			}
			return aTagUrlCheck(matcher.replaceFirst("<a$1href@='$2'$3>$4</a>"), domain);
		} else {
			return content.replaceAll("href@", "href");
		}
	}

	/**
	 * 科技局匹配所有[IMG]标签，替换成<img>
	 * 
	 * @param content
	 * @return
	 */
	public static String KjjImgCheck(String content) {
		Matcher matcher = KJJ_IMG_PATTERN.matcher(content);
		if (matcher.find()) {
			String newImg = "";
			String src = matcher.group(1);
			newImg = "<div align='center'><img src=\"" + src + "\"/></div><BR>";
			return KjjImgCheck(matcher.replaceFirst(newImg));

		}
		return content;

	}

	/**
	 * 科技局匹配所有[IMG]标签，替换成<img>
	 * 
	 * @param content
	 * @return
	 */
	public static String KjjHrefCheck(String content) {
		Matcher matcher = KJJ_URL_PATTERN.matcher(content);
		if (matcher.find()) {
			String newHref = "";
			String href = matcher.group(1);
			String text = matcher.group(2);
			newHref = "<a href=\"" + href + "\" target=\"_blank\">" + text + "</a>";
			return KjjHrefCheck(matcher.replaceFirst(newHref));

		}
		return content;

	}

	/**
	 * 科技局匹配所有[]标签，替换成<>
	 * 
	 * @param content
	 * @return
	 */
	public static String KjjCheck(String content) {
		Matcher matcher = KJJ_PATTERN.matcher(content);
		if (matcher.find()) {
			String newHtml = "";
			String text = matcher.group(1);
			newHtml = "<" + text + ">";
			return KjjCheck(matcher.replaceFirst(newHtml));

		}
		return content;

	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String readTxt(String path) {
		File file = new File(path);
		StringBuilder result = new StringBuilder();
		if (file.isFile() && file.exists()) {// 判断文件是否存在
			try {
				// BufferedReader br = new BufferedReader(new
				// FileReader(file));//构造一个BufferedReader类来读取文件

				InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
				BufferedReader br = new BufferedReader(isr);

				String s = null;

				while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
					result.append(s + "\n");
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("找不到指定的文件");
		}

		return result.toString();
	}

	public static boolean isUrlResponseContainStr(String url, String str) {
		try {
			String responseStr = "";
			if (!StringUtil.isEmpty(responseStr) && responseStr.contains(str))
				return true;
			return false;
		} catch (Exception e) {
			System.out.println("测试连接[" + url + "]出错:" + e);
			return false;
		}

	}

	public static void main(String[] args) throws IOException {

		String result = readTxt("D:\\article_content\\3\\2708_1.txt");
		System.out.println(result);

		isUrlResponseContainStr("http://xfrb.hj.cn/Read.asp?NewsID=1362500", "NoPaper.asp");

	}
}
