package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import common.Log;
import database.dao.ParameterDAO;

public class MyServletContextListener implements ServletContextListener {
	private static boolean stopAll=false;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		Log.getLogger().info("ServletContextListener destroyed");
		
		try {
			  System.out.println("***************************************** call quartz Scheduler.shutdown()");
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.shutdown();
			
			
			
			// Thread.sleep(10 * 1000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		ServletContext context = event.getServletContext();
//        StdSchedulerFactory sch = (StdSchedulerFactory) context.getAttribute("org.quartz.impl.StdSchedulerFactory.KEY");
//
//        if(sch!=null){
//            try {
//               System.out.println("call quartz Scheduler.shutdown()");
//                Collection<Scheduler> col = sch.getAllSchedulers();
//                for(Scheduler s:col){ 
//                    s.shutdown();
//                }
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//        }
        
		
		
		setStopAll(true);
	}

	// Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	
		Log.getLogger().info("ServletContextListener started");
		
		System.out.println("************* Checking System Parameters... **********************");
		ParameterDAO.initDatabase();
		ParameterDAO.checkValues();
		System.out.println("************* Checking System Parameters complete **********************");
	}

	public synchronized static boolean isStopAll() {
		return stopAll;
	}

	public synchronized static void setStopAll(boolean s) {
		MyServletContextListener.stopAll = s;
	}
}