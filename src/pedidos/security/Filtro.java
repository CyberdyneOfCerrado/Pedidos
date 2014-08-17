package pedidos.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Security
 */
@WebFilter("/Filtro")
public class Filtro implements Filter {
		private FilterConfig filterConfig;

	  public void init(FilterConfig filterConfig) throws ServletException {
	    this.filterConfig = filterConfig;
	  }

	  public void destroy() {
	    this.filterConfig = null;
	  }
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Security security = new Security();
		HttpServletRequest re = (HttpServletRequest) request;
		HttpSession session = re.getSession();
		
		boolean acess = security.permissao((String) session.getAttribute("login"), (String) request.getParameter("useCase"),(String) request.getParameter("action"));
		
		if(!acess){
			request.setAttribute("security","true");
		}else{
			request.setAttribute("security","false");
		}
		chain.doFilter(request, response);
	}

}
