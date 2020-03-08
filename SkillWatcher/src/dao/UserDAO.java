package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean;

public class UserDAO extends DBConnectUtil{

	private static final String TABLE = "Users";

	/**User新規登録メソッド
	 * @param not {@code null}
	 * @return INSERT文実行フラグ true/false
	 */
	public boolean addUser(UserBean ub) {

		try {
			//User情報INSERT SQL
			String sql = "INSERT INTO " + TABLE + " (mail_address, password, authority, user_name) "
						+ "VALUES (?,?,?,?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,ub.getMailAddress());
			pStmt.setString(2,ub.getPassword());
			pStmt.setInt(3,ub.getAuthority());
			pStmt.setString(4,ub.getName());
			return pStmt.execute();

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**User取得メソッド（複数列指定）
	 * 指定する列のインデックス番号と値のインデックス番号を同期すること。
	 * @param not {@code null} String[] columns,String[] values
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUser(String[] columns, String[] values) {
		try {
			//User情報INSERT SQL
			String sql = "SELECT user_id, mail_address, password, authority, user_name FROM " + TABLE + " WHERE";
			if(columns.length != values.length) {
				return null;
			}else {
				for(int i = 0;i < columns.length;i++) {
					//最後のみANDを文に入れない
					if(i != columns.length - 1) {
						sql += " " + columns[i] + " = ? AND";
					}else {
						sql += " " + columns[i] + " = ?";
					}
				}
			}
			//値をセット
			PreparedStatement pStmt = conn.prepareStatement(sql);
			for(int i = 0;i < values.length;i++) {
				pStmt.setString(i + 1,values[i]);
			}
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
	/**User取得メソッド（単独列指定）
	 * @param not {@code null} String[] columns,String[] values
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUser(String column, String val) {
		try {
			//User情報INSERT SQL
			String sql = "SELECT user_id, mail_address, password, authority, user_name FROM " + TABLE
						+ " WHERE " + column + " = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,val);
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
	/**User取得メソッド（メールアドレスから）
	 * @param not {@code null}
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUserByMailAddress(String mailAddress) {
		return getUser("mail_address", mailAddress);
	}
	/**User取得メソッド（ユーザーIDから）
	 * @param not {@code null}
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUserByUserId(int userId) {
			return getUser("user_id", Integer.toString(userId));
	}
	/**User取得メソッド（ユーザーネームから）
	 * @param not {@code null}
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUserByUserName(String userName) {
			return getUser("user_name", userName);
	}
	/**User情報UPDATEメソッド（列、条件単独指定）
	 * @param not {@code null} String updColumn, String updVal, String whrColumn, String whrVal
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public boolean updateUser(String updColumn, String updVal, String whrColumn, String whrVal) {
		try {
			//User情報UPDATE SQL
			String sql = "UPDATE " + TABLE + " SET " + updColumn + " = ? WHERE " + whrColumn + " = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,updVal);
			pStmt.setString(2,whrVal);
			pStmt.executeUpdate();

			return true;

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**User取得メソッド（メールアドレス、パスワードから）
	 * このメソッドを実行してnullで無ければログイン完了
	 * @param not {@code null}
	 * @return UserBean User情報 取得できなかった場合 null
	 */
	public UserBean getUserByMailAddressPassword(String userName, String password) {
		String[] columns = {"mail_address","password"};
		String[] values = {userName,password};
			return getUser(columns, values);
	}
}
