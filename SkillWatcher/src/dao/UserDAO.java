package dao;

import java.sql.PreparedStatement;

import bean.UserBean;

public class UserDAO extends DBConnectUtil{

	private static final String TABLE = "";

	/**
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

}
