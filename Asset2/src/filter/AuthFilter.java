package filter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utente;
import common.ApplicationConfig;
import managed.Faces;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		copyImages();
	}

	private void copyImages() {
		String uploadDir = Faces.HOMEDIR + "resources" + File.separator + "images" + File.separator;
		String backupDir = ApplicationConfig.getDocumentdir();
		if (!backupDir.endsWith(File.separator))
			backupDir += File.separator;
		String dir = backupDir + "images";
		File fDir = new File(dir);
		String[] lista = fDir.list();
		for (int i = 0; i < lista.length; i++) {
			String file = dir + File.separator + lista[i];
			Path copied = Paths.get(uploadDir + lista[i]);
			Path originalPath = Paths.get(file);
			System.out.println("origin: " + originalPath);
			System.out.println("copied: " + copied);
			try {
				Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			// if(ses==null)res.sendRedirect(req.getContextPath() + "/login.xhtml");
			// allow user to proccede if url is login.xhtml or user logged in or user is
			// accessing any page in //public folder
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0 || ses == null) {
				if (ses != null)
					ses.invalidate();
				chain.doFilter(request, response);
				return;
			}
			Utente utente = (Utente) ses.getAttribute("utente");
			if (utente == null || utente.getUsername() == null) {
				res.sendRedirect(req.getContextPath() + "/login.xhtml");
			}
			if (utente != null && utente.getTipo() != null) {
				if (reqURI.indexOf("/admin/") != -1 && !utente.getTipo().equalsIgnoreCase("A"))
					res.sendRedirect(req.getContextPath() + "/login.xhtml");
				else
					chain.doFilter(request, response);
			} else {

				ses.invalidate();
				res.sendRedirect(req.getContextPath() + "/login.xhtml"); // Anonymous user. Redirect to login page

			}
		} catch (Throwable t) {
			t.printStackTrace();

		}
	} // doFilter

	@Override
	public void destroy() {

	}
}