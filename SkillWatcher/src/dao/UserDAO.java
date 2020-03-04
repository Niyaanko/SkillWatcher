package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean;

public class UserDAO extends DBConnectUtil{

	private static final String TABLE = "";

	/**User新規登録メソッド
	 * @param not {@code null}
	 * @return INSERT文実行フラグ true/false
	 */
	public boolean addUser(UserBean ub) {

		try {
			//User情報INSERT SQL
			String sql = "INSERT INTO " + TABLE + " user_id, mail_address, password, authority, user_name "
						+ "VALUES(?,?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,ub.getId());
			pStmt.setString(2,ub.getMailAddress());
			pStmt.setString(3,ub.getPassword());
			pStmt.setInt(4,ub.getAuthority());
			pStmt.setString(5,ub.getName());
			return pStmt.execute();

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**User取得メソッド（メールアドレスから）
	 * @param not {@code null}
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUserByMailAddress(String mailAddress) {
		try {
			//User情報INSERT SQL
			String sql = "SELECT user_id, mail_address, password, authority, user_name FROM" + TABLE
						+ "WHERE mail_address = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,mailAddress);
			ResultSet rs = pStmt.executeQuery();
			UserBean ub = null;
			while(rs.next()) {
				ub = new UserBean();
				ub.setId(Integer.parseInt(rs.getString("user_id")));
				ub.setMailAddress(rs.getString("mail_address"));
				ub.setPassword(rs.getString("password"));
				ub.setAuthority(Integer.parseInt(rs.getString("authority")));
				ub.setName(rs.getString("user_name"));
			}
			return ub;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
