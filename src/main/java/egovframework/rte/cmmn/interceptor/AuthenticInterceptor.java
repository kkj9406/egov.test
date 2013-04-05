package egovframework.rte.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import egovframework.rte.cmmn.vo.Account;



public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 세션에 계정정보(Account)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(Account)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {		
		// HandlerInterceptorAdapter는 preHandle, postHandle, afterCompletion을 지원하고
		// 계정이 세션에 있는지 검사하여 리다이렉트 시키는 작업은 요청 전에 해야하므로
		// preHandle 클래스를 오버라이드하여 사용한다.
		
		Account account = (Account) WebUtils.getSessionAttribute(request, "UserAccount");
		//account형의 세션을 받아서
		
		if(account!=null){//세션에 계정이 있으면 true를 반납하고 
			return true;
		}else{//계정이 없다면 로그인화면으로 리다이렉트 한다.
			ModelAndView modelAndView = new ModelAndView("redirect:/sample/login.do");
			/* ModelAndView 클래스는 컨트롤러의 처리 결과를 보여줄 view와
			 * view에서 사용할 값을 전달하는 클래스이다. 여기서는 String형의
			 * viewName을 매개변수로 전달하므로 dispatcher-servlet에 등록된
			 * viewResolver에 의해 view 객체를 찾아간다. 
			 * ModelAndView로 포워드 뿐만 아니라 리다이렉트도 사용할 수 있는데
			 * 이런 경우에는 setViewName() 메소드 파라미터에 "redirect:"를 
			 * prefix로 붙여주면 된다. /main.jsp로 보내고 싶다면 아래와 같이 사용하면 된다
			 * return ModelAndView("redirect:/main.jsp");
			 * 혹시나 다른 URL로 포워드 하고 싶으면 "forward:"를 prefix로 붙이면 된다
			 * forward와 redirect 차이는 클라이언트와 통신하느냐, 
			 * request가 유지되느냐, 최종 URL이 변경되느냐 정도이다
			 * */
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

}
