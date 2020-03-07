package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SessionManager;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher = null;
		//セッション情報がセットされていれば、そのまま会員ページへ
		//セッション情報がセットされていない場合、ログインページへ
		if(SessionManager.isSession(request)) {
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/MemberPage.jsp");
		}else {
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/LoginPage.jsp");
		}
		reqDispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher = null;
		//セッション情報がセットされていれば、そのまま会員ページへ
		//セッション情報がセットされていない場合、ログインページへ
		if(SessionManager.isSession(request)) {
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/MemberPage.jsp");
		}else {
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/LoginPage.jsp");
		}
		reqDispatcher.forward(request, response);
		return;
	}

}
