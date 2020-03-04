package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
DB接続クラス
継承して使用すること。
*/
public class DBConnectUtil {

	//接続DBの宣言
	private static final String DB = "SkillWatcher";
	//接続USERの宣言
	private static final String USER = "sa";
	//接続PWの宣言
	private static final String PW = "sa";

	static Connection conn = null;

	public DBConnectUtil() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//DBへの接続情報

			conn = DriverManager.getConnection("jdbc:sqlserver:"
					+ "//localhost\\SQLEXPRESS;database=" + DB
					+ "integratedSecurity=false;user=" + USER + ";password=sa" + PW);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}