package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.UserDAO;
import model.InputCheck;
import model.SessionManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher reqDispatcher = null;
		//セッション情報がセットされていれば、そのまま会員ページへ
		//セッション情報がセットされていない場合、ログインページへ
		if(SessionManager.isSession(request)) {
			reqDispatcher = request.getRequestDispatcher("/MemberServlet");
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF8");
		//入力値の取得
		String mailAddress = request.getParameter("mail_address");
		String password = request.getParameter("password");
		String errorMsg = null;
		RequestDispatcher reqDispatcher = null;
		if(!InputCheck.isInput(mailAddress) || !InputCheck.isInput(password)) {
			//入力されていない項目があるので、エラーメッセージをセット
			errorMsg = "入力されていない項目があります";
		}
		//メールアドレスチェック
		if(errorMsg == null && !InputCheck.isMailAddress(mailAddress)) {
			//不正なメールアドレスなので、エラーメッセージをセット
			errorMsg = "不正なメールアドレスです。";
		}
		//この時点でエラーメッセージがあった場合エラーメッセージをセットしログインページへ
		if(errorMsg != null) {
			request.setAttribute("ErrorMsg",errorMsg);
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/LoginPage.jsp");
			reqDispatcher.forward(request, response);
			return;
		}
		//ログイン情報があっているか判定
		UserDAO usDAO = new UserDAO();
		UserBean usBean = usDAO.getUserByMailAddressPassword(mailAddress, password);

		//あっていればセッションを発行し会員ページへ
		//あっていなければエラーメッセージをセットしログインページへ
		if(usBean != null) {
			reqDispatcher = request.getRequestDispatcher("/MemberServlet");
			SessionManager.addSession(request, response, usBean);
		}else {
			errorMsg = "メールアドレスかパスワードが違います。";
			request.setAttribute("ErrorMsg",errorMsg);
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/LoginPage.jsp");
		}
		reqDispatcher.forward(request, response);
		return;
	}


}
