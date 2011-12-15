package org.mashup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Description:
 *Date:2007-8-13 
 *
 *@author Hong Jun
 *email:hongjunred@163.com
 */
public class DataProcess extends HttpServlet {

	/**
	 * @param args
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----do get:");
		perform(request, response);
	}

	/**
	 * 处理Post命令,该方法调用perform()。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----do post:");
		perform(request, response);
	}

	public void perform(HttpServletRequest request, HttpServletResponse response) {
		try {
			String latitute = request.getParameter("latitute");
			String longitute = request.getParameter("longitute");
			String urlStr = "http://A5338764333.api.wxbug.net/getLiveWeatherRSS.aspx?"
					+ "ACode=A5338764333&citytype=1&unittype=1"
					+ "&lat=" + latitute + "&long=" + longitute;
			String dataStr = new RSSReader().getWeatherData(urlStr);
			// System.out.println("------restult:"+result);
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().print(dataStr);
			System.out.println("----rss data:"+dataStr);
		} catch (Exception e) {
			System.out.println("-----perform error:");
			e.printStackTrace();
		}
	}
}
