package org.mashup;

import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.Namespace;
import org.json.JSONObject;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


/**
 *Description:
 *Date:2007-8-13 
 *
 *@author Hong Jun
 *email:hongjunred@163.com
 */
public class RSSReader {

	public RSSReader() {
	}

	public String getWeatherData(String urlStr) throws Exception {
		URLConnection feedUrl = new URL(urlStr).openConnection();
		feedUrl.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedUrl));
		List list = (List) feed.getForeignMarkup();
		Iterator itr = (Iterator) list.iterator();
		JSONObject root = new JSONObject();
		JSONObject jsonObj = null;
		jsonObj = new JSONObject();
		Element ele = (Element) itr.next();
		Namespace ns = ele.getNamespace();
		Element ob = ele.getChild("ob", ns);
		if (ob != null) {			
			jsonObj.put("current_condition", ob.getChild(
					"current-condition", ns).getText());
			jsonObj.put("icon", ob.getChild("current-condition", ns)
					.getAttributeValue("icon"));
			jsonObj.put("rain_today", ob.getChild("rain-today", ns)
					.getValue());
			jsonObj.put("wind_speed", ob.getChild("wind-speed", ns)
					.getValue());
			jsonObj.put("city_state", ob.getChild("city-state", ns)
					.getText());			
			jsonObj.put("temp", ob.getChild("temp", ns).getValue());
			jsonObj.put("temp_high", ob.getChild("temp-high", ns).getValue());
			jsonObj.put("temp_low", ob.getChild("temp-low", ns).getValue());
			jsonObj.put("humidity", ob.getChild("humidity", ns).getValue());
			jsonObj.put("humidity_high", ob.getChild("humidity-high", ns)
					.getValue());
			jsonObj.put("humidity_low", ob.getChild("humidity-low", ns)
					.getValue());
			jsonObj.put("pressure", ob.getChild("pressure", ns).getValue());
			jsonObj.put("pressure_high", ob.getChild("pressure-high", ns)
					.getValue());
			jsonObj.put("pressure_low", ob.getChild("pressure-low", ns)
					.getValue());			
		} else {
			System.out.println("aws:ob not exist!");
		}
		root.append("weather", jsonObj);		
		return root.toString();
	}

	/*public static void main(String[] args) throws Exception {
		try {
			String urlStr = "http://A5338764333.api.wxbug.net/getLiveWeatherRSS.aspx?ACode=A5338764333&citytype=1&unittype=1&lat=39.89945&long=116.40969";
			URLConnection feedUrl = new URL(urlStr).openConnection();
			feedUrl.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			String feedType = feed.getFeedType();// 获取原feed属于哪种格式
			System.out.println(feed.getTitle());
			System.out.println("----foreign:"
					+ feed.getForeignMarkup().toString());
			List list = (List) feed.getForeignMarkup();
			Iterator itr = (Iterator) list.iterator();			
			Element ele = (Element) itr.next();
			Namespace ns = ele.getNamespace();
			Element ob = ele.getChild("ob", ns);
			JSONObject root = new JSONObject();
			if (ob != null) {
				 root.put("temp",Float.parseFloat(ob.getChild("temp", ns).getValue()));
				 String str= ob.getChild("temp-high", ns).getValue();
				 System.out.println("---temp high:"+str);
				 root.put("temp_high",Float.parseFloat(str));
				 ob.getChild("temp-low", ns).getValue();				
			} else {
				System.out.println("aws:ob not exist!");
			}		
			System.out.println(root.toString());
		} catch (Exception e) {
			System.out.println("-----Error:");
			e.printStackTrace();
		}
	}*/

}
