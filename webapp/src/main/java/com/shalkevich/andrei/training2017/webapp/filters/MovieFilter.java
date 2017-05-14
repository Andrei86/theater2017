package com.shalkevich.andrei.training2017.webapp.filters;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shalkevich.andrei.training2017.datamodel.Customer;
import com.shalkevich.andrei.training2017.services.ICustomerService;
import com.shalkevich.andrei.training2017.services.IGenreService;
import com.shalkevich.andrei.training2017.services.IMovieService;

public class MovieFilter implements Filter{
	
	private ICustomerService customerService;

	private IMovieService service;
	
	private ApplicationContext appContext; // доступ к компонентам приложения
	
	@Override
	public void init(FilterConfig config) throws ServletException {

		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		service = context.getBean(IMovieService.class);
		customerService = context.getBean(ICustomerService.class);;
		appContext = context;
	}

	@Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws java.io.IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        if (!isAuthRequired(req)) {
	            chain.doFilter(request, response);
	        }
	        
	        String[] credentials = resolveCredentials(req); // это то что ввел юзер

	        boolean isCredentialsResolved = credentials != null && credentials.length == 2;
	        if (!isCredentialsResolved) {
	            res.sendError(401);
	            return;
	        }

	        String username = credentials[0]; // получили имя и пароль из хидера
	        String password = credentials[1];

	       /* // TODO query to DB instead of MAP
	        Integer userId = USERS_DB.get(username);
	        */
	        if (validateUserPassword(username, password)) { // если совпадает - пропускаем через цепочку

	            //userDataStorage.setId(userId); // хранилище данных юзера
	            chain.doFilter(request, response);
	        } else {
	            res.sendError(401);
	        }

	    }
	
	private boolean isAuthRequired(HttpServletRequest req) {
		if (req.getMethod().toUpperCase().equals("GET")) {
			return false;
		}
		// TODO other variants
		return true;
	}
	       /* UserAuthStorage userDataStorage = appContext.getBean(UserAuthStorage.class);*/
	
	private String[] resolveCredentials(HttpServletRequest req) {
        try {
            Enumeration<String> headers = req.getHeaders("Authorization");
            String nextElement = headers.nextElement();
            String base64Credentials = nextElement.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
            return credentials.split(":", 2);
        } catch (Exception e) {
            return null;
        }
    }
	
	 private boolean validateUserPassword(String username, String password) {
	        // TODO get user from DB by username and check password
		 
	        /*if (userId == null) {
	            return false;
	        }*/
		 
		 Customer userFromDB = customerService.getByLogin(username);
		 
		 System.out.println(userFromDB);
		 
		 if (userFromDB == null) {
	            return false;
	        }
	        String userLoginFromDB = userFromDB.getLogin();

	        return userFromDB.getPassword().equals(password);
	    }

    @Override
    public void destroy() {
    }

}
