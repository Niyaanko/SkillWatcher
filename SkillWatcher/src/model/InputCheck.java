package model;

public class InputCheck {

	/*
	 *入力チェックメソッド
	 *null と 空の文字列はfalse
	 */
	public static boolean isInput(String str) {
		if(str == null) {
			return false;
		}else if(str.length() == 0){
			return false;
		}
		return true;
	}

	/*
	 *メールアドレスチェックメソッド
	 */
	public static boolean isMailAddress(String str) {
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+"
				+ "(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+"
				+ "(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		if(!str.matches(mailFormat)) {
			return false;
		}
		return true;
	}
}
