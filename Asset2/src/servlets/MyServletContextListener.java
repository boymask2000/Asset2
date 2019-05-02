package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import common.ApplicationConfig;
import common.Log;

public class MyServletContextListener implements ServletContextListener {
	private static boolean stopAll=false;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Log.getLogger().info("ServletContextListener destroyed");
		setStopAll(true);
	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationConfig.getDocumentdir();
		Log.getLogger().info("ServletContextListener started");
	
		
		
	}

	public synchronized static boolean isStopAll() {
		return stopAll;
	}

	public synchronized static void setStopAll(boolean s) {
		MyServletContextListener.stopAll = s;
	}
}