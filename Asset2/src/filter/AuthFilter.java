package filter;

import java.io.IOException;
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

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
		//	if(ses==null)res.sendRedirect(req.getContextPath() + "/login.xhtml");
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
			if (utente != null && utente.getTipo() != null) {
				if (reqURI.indexOf("/admin/") !=-1  && !utente.getTipo().equalsIgnoreCase("A"))
					res.sendRedirect(req.getContextPath() + "/login.xhtml");
				else
					chain.doFilter(request, response);
			} else {
				if (ses != null)
					ses.invalidate();
				res.sendRedirect(req.getContextPath() + "/login.xhtml"); // Anonymous user. Redirect to login page

			}
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println(t.getMessage());
		}
	} // doFilter

	@Override
	public void destroy() {

	}
}