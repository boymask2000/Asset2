package servlets;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import batchjob.system.SystemJob;
import common.Log;

/**
 * Servlet implementation class BatchMonitor
 */
@WebServlet("/BatchMonitor")
public class BatchMonitor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BatchMonitor() {
		super();

		Log.getLogger().info("BatchMonitor start");

		List<SystemJob> lista = SystemJob.getAllSystemJobs();
		for (SystemJob job : lista) {
		
			Log.getLogger().info("Trovato job " + job.getClassName());
			try {
				job.setPeriodo(SystemJob.MINUTO);
				job.go();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
