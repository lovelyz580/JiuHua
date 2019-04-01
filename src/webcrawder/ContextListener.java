package webcrawder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

/**
 * 定时器
 * 
 * @author WJF on 2018/09/25
 */

public class ContextListener extends HttpServlet implements ServletContextListener {

	private Timer timer = null; // 定时器

	/**
	 * 构造函数
	 */
	public ContextListener() {

	}

	/**
	 * 初始化
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			timer = new Timer(true); // 创建定时器

			// 新闻更新
			event.getServletContext().log("自动验收定时器--已启动");

			// 从配置文件中获取相关数据
			Properties properties = new Properties();
			properties.load(ContextListener.class.getClassLoader().getResourceAsStream("config.properties"));
			// 新闻更新的时间
			String NEWS_START_TIME = properties.getProperty("config.NEWS_START_TIME");
			// 新闻更新的时间间隔
			long NEWS_TIME_INTERVAL = Long.valueOf(properties.getProperty("config.NEWS_TIME_INTERVAL"));

			// 计算第一次触发的时间
			Date NEWS_START_DATE = getStartDate(NEWS_START_TIME);

			// timer.schedule(定时任务, 第一次触发的时间, 下一次触发的时间)
			timer.scheduleAtFixedRate(new ApplyCheckWebCrawder(event.getServletContext()), NEWS_START_DATE, NEWS_TIME_INTERVAL);

			event.getServletContext().log("自动验收定时器--已经将任务添加到任务调度表");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel(); // 销毁

		event.getServletContext().log("自动验收定时器--已销毁");
	}

	/**
	 * 获取第一次触发的时间
	 * 
	 * @param StartTime
	 * @return
	 */
	private Date getStartDate(String startTime) {
		Date startDate = null;

		SimpleDateFormat sdfOfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			// 如果第一次触发的时间为当前日期规定的时间
			Calendar calendar = Calendar.getInstance();
			String nowDate = sdfOfDate.format(calendar.getTime());
			startDate = sdfOfTime.parse(nowDate + " " + startTime);

			// 如果部署项目时的时间大于规定的时间，则第一次触发的时间则为下一天规定的时间
			if (new Date().getTime() > startDate.getTime()) {
				calendar.add(Calendar.DATE, +1);
				String date = sdfOfDate.format(calendar.getTime());
				startDate = sdfOfTime.parse(date + " " + startTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return startDate;
	}

}
