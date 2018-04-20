/**
 *
 */
package com.spz.webcrawler;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @描述 webcrawler
 * @作者 施鹏振
 * @创建日期 2018年1月23日
 * @创建时间 上午11:35:24
 */
public class Test {

	/**
	 * TODO
	 * 
	 * @param args
	 *            void
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		new Test().csdn();
	}

	public void waduanzi() throws IOException {
		// 请求Dom文档
		Document document = Jsoup.connect("http://www.waduanzi.com/").get();
		// 解析
		String selecttor = "div[class=panel panel20 post-item post-box]";
		Elements elements = document.select(selecttor);
		for (Element element : elements) {

			System.out.println("作者=>" + element.child(0).child(1).text());
			Elements select = element.select("div[class=item-detail]>h2>a");
			Elements title = select;
			System.out.println("标题=>" + title.text());
			Elements content = element.select("div[class=item-detail]>div");
			System.out.println("内容=>" + content.text());
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");

		}
	}

	public void news163() throws IOException {
		// 请求Dom文档
		Document document = Jsoup.connect("http://news.163.com").get();
		// 解析
		String select = "div[class=mod_top_news2]>h2>a";
		Elements titles = document.select(select);
		for (Element title : titles) {
			System.out.println("标题=>" + title.text());
			// 再次请求url获取,内容
			String url = title.absUrl("href");
			Document doc = Jsoup.connect(url).get();
			Elements content = doc.select("div[class=post_text]>p");
			System.out.println("内容=>" + content.text());
			System.out.println("----------------------------------------------------------------------------------");
		}
	}

	public void csdn() throws IOException {

		String url = "http://blog.csdn.net/index.html?page=1";
		// 请求Dom文档
		Connection connection = Jsoup.connect(url);
		Document document = connection.get();
		Elements titles = document.select("h3");
		for (Element title : titles) {
			System.out.println(title.text());
		}
	}

	public void csdn403() throws IOException {
		// String url="http://blog.csdn.net";
		// String url="https://www.csdn.net/";
		for (int i = 1; i < 100; i++) {

			String url = "http://blog.csdn.net/index.html?page=" + i;
			// 请求Dom文档
			Connection connection = Jsoup.connect(url);
			// connection.header("Accept",
			// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			// connection.header("Accept-Encoding","gzip, deflate, br");
			// connection.header("Accept-Language","zh-CN,zh;q=0.9");
			// connection.header("Connection","keep-alive");
			// connection.header("Cookie","BAIDUID=89800C1A2F480731E7D552C2C41CB082:FG=1;
			// BIDUPSID=89800C1A2F480731E7D552C2C41CB082; PSTM=1503316238;
			// FP_UID=1e08bbd079f9538a9f44deaccc558694;
			// FP_UID=764d42e8299841ce83f1d677e3fdf074;
			// BDUSS=ZndW5XRVNCWFU5ZGZoOWFPQ2p6ejhWeThEcWltN1c1LVBKUm05a3BjQmlPTVpaSVFBQUFBJCQAAAAAAAAAAAEAAACzhMKqyqnF9NXxAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGKrnlliq55ZVV");
			// connection.header("Host","blog.csdn.net");
			// connection.header("Upgrade-Insecure-Requests","1");
			// connection.header("User-Agent","Mozilla/5.0 (Linux; Android 6.0;
			// Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko)
			// Chrome/65.0.3298.4 Mobile Safari/537.36");
			Document document = connection.get();
			System.out.println(document);
		}
	}
}
