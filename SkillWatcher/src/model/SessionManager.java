package model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

public class SessionManager {

	/**
	 * セッション・クッキーの登録を行うメソッド
	 * @param HttpServletRequest HttpServletResponse UserBean
	 * @return セッション登録失敗:false セッション登録成功:true
	 */
	public static boolean addSession(HttpServletRequest request,HttpServletResponse response,UserBean usBean) {
		//ユーザー情報がnullだった場合false
		if(usBean == null) {
			return false;
		}
		//セッション・クッキーの登録
		HttpSession session = request.getSession(true);
		session.setAttribute("user", usBean);
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(cookies != null) {
			for(Cookie c : cookies){
				if(c.getName().equals("id")) {
					cookie = c;
					cookie.setValue(Integer.toString(usBean.getId()));
				}
			}
		}
		//Cookieに「id」があれば取得
		if(cookie == null) {
			cookie = new Cookie("id",Integer.toString(usBean.getId()));
		}
		response.addCookie(cookie);
		return true;
	}

	/**
	 * セッション有無の判定を行うメソッド
	 * @param HttpServletRequest
	 * @return セッション無:false セッション有:true
	 */
	public static boolean isSession(HttpServletRequest request) {
		//セッションの取得
		HttpSession session = request.getSession(false);
		//セッションがない場合false
		if(session == null) {
			return false;
		}
		//セッションのユーザーがない場合false
		if(session.getAttribute("user") == null) {
			return false;
		}
		//クッキーの取得
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(cookies == null) {
			return false;
		}
		//Cookieに「id」があれば取得
		for(Cookie c : cookies){
			if(c.getName().equals("id")) {
				cookie = c;
			}
		}
		//クッキーのidが取得できなければfalse
		if(cookie == null) {
			return false;
		}
		UserBean usBean = (UserBean)session.getAttribute("user");
		//セッションのUserBean id とクッキーのidが照合できるか
		if(!cookie.getValue().equals(Integer.toString(usBean.getId()))) {
			return false;
		}
		return true;
	}

	/**
	 * セッション・クッキーの破棄を行うメソッド
	 * @param HttpServletRequest HttpServletResponse
	 */
	public static void disposeSession(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		//セッションがnullでなければ破棄
		if(session != null) {
			session.invalidate();
		}
		//クッキーのidを空の文字列で上書き（破棄）
		Cookie cookie = new Cookie("id","");
		response.addCookie(cookie);
	}



}
