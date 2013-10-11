package rentmanage.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

public class LoginInterceptor implements HandlerInterceptor  {
	final static Logger logger = (Logger) LoggerFactory.getLogger(LoginInterceptor.class); 


	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		logger.info("开始拦截--"+req.getRequestURI());
		if("/rent/login".equals(req.getRequestURI())){
			logger.info("登录--不用拦截");
			return true;
		}
		String loginUser=(String) req.getSession().getAttribute("loginUser");
		if(loginUser==null){
			req.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp").forward(req, resp);
		}
		return false;
	}

}
