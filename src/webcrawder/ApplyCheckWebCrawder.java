package webcrawder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

import javax.servlet.ServletContext;

/**
 * 自动验收
 * 
 * @author WJF on 2018/09/25
 */

public class ApplyCheckWebCrawder extends TimerTask {

	// 判断是否正在同步中
	private static boolean isRunning = false;

	private ServletContext context = null;

	// 格式化时间
	private SimpleDateFormat sdfOfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 加载驱动
	private Properties properties;
	private String URL;
	private String userName;
	private String password;

	public ApplyCheckWebCrawder() {

	}

	public ApplyCheckWebCrawder(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		if (!isRunning) {
			context.log("自动验收定时器--任务执行开始");
			isRunning = true;

			// 加载驱动
			try {
				properties = new Properties();
				properties.load(ContextListener.class.getClassLoader().getResourceAsStream("config.properties"));
				URL = properties.getProperty("jdbc.url");
				userName = properties.getProperty("jdbc.username");
				password = properties.getProperty("jdbc.password");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 自动验收
			applyCheck();
			
			isRunning = false;
			context.log("自动验收定时器--任务执行结束");
		} else {
			// 上一次的更新任务还没有结束
			context.log("自动验收定时器--上一次任务还没有结束");
		}
	}

	/**
	 * 自动验收
	 */
	private void applyCheck() {
		context.log("自动验收定时器--自动验收--任务执行开始");

		try {
			// 连接数据库
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			Connection conn = null;
			CallableStatement updateStatement = null;
			conn = DriverManager.getConnection(URL, userName, password);

			// 更新apply_check表
			String currentTime = sdfOfTime.format(new Date()); // 格式化当前时间

			// 更新语句
			String updateSql = "UPDATE apply_check " + 
					"SET ApplyCheckState = 'ZDYS', ApplyCheckTime = '" + currentTime + "' " + 
					"WHERE ApplyCheckState = 'SQYS' AND ApplyCheckApplyTime < '" + currentTime + "'";

			// 更新
			updateStatement = conn.prepareCall(updateSql);
			int updateNum = updateStatement.executeUpdate();
			
			// 更新结果
			context.log("自动验收定时器--自动验收--任务执行结束--更新成功--更新条数：" + updateNum);
		} catch (Exception e) {
			e.printStackTrace();
			context.log("自动验收定时器--自动验收--任务执行结束--任务失败--失败原因:" + e);
		}
	}

}
