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
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqDispatcher = null;
		//セッション情報がセットされていれば、そのまま会員ページへ
		//セッション情報がセットされていない場合、会員登録ページへ
		if(SessionManager.isSession(request)) {
			reqDispatcher = request.getRequestDispatcher("/MemberServlet");
			System.out.println("jajaja");
		}else {
			reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/CreateAccountPage.jsp");
			System.out.println("ajajaj");
		}
		reqDispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF8");
			//入力値の取得
			String createMailAddress = request.getParameter("create_mail_address");
			String createPassword = request.getParameter("create_password");

			String errorMsg = null;
			//入力値チェック
			if(!InputCheck.isInput(createMailAddress) || !InputCheck.isInput(createPassword)) {
				//入力されていない項目があるので、エラーメッセージをセット
				errorMsg = "入力されていない項目があります";
			}
			//メールアドレスチェック
			if(errorMsg == null && !InputCheck.isMailAddress(createMailAddress)) {
				//不正なメールアドレスなので、エラーメッセージをセット
				errorMsg = "不正なメールアドレスです。";
			}
			UserDAO usDAO = new UserDAO();
			//メールアドレスが登録済でないかのチェック
			if(errorMsg == null && usDAO.getUserByMailAddress(createMailAddress) != null) {
				//登録済のメールアドレスなので、エラーメッセージをセット
				errorMsg = "既に登録されているメールアドレスです。";
			}
			//エラーメッセージがない場合は登録完了 セッションをセットし、会員ページへ
			//エラーメッセージがある場合は新規登録ページへ
			RequestDispatcher reqDispatcher = null;
			if(errorMsg == null) {
				//ユーザー新規登録
				UserBean usBean = new UserBean();
				usBean.setMailAddress(createMailAddress);
				usBean.setName(createMailAddress);
				usBean.setPassword(createPassword);
				usBean.setAuthority(1);
				usDAO.addUser(usBean);

				//登録後のユーザー情報取得
				usBean = usDAO.getUserByMailAddress(createMailAddress);
				System.out.println( usBean.getId());
				System.out.println( usBean.getMailAddress());
				System.out.println( usBean.getPassword());
				System.out.println( usBean.getAuthority());
				System.out.println( usBean.getName());
				//セッション情報登録
				SessionManager.addSession(request, response, usBean);
				reqDispatcher = request.getRequestDispatcher("/MemberServlet");
			}else {
				request.setAttribute("ErrorMsg",errorMsg);
				reqDispatcher = request.getRequestDispatcher("/WEB-INF/JSP/CreateAccountPage.jsp");
			}
			reqDispatcher.forward(request, response);
			return;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
