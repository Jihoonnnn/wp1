package controller;

import java.util.HashMap;
import java.util.Map;

import util.ScanUtil;
import util.View;

public class MainController  {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.MAIN;
		while (true) {
			switch (view) {
			case MAIN:
				view = home();
				break;
			case USER_LOGIN:
				view = userLogin();
				break;
			default:
				break;
			}
		}
	}

	private View userLogin() {
		return null;
	}

	private View home() {
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		switch (sel) {
		case 1:
			return View.ADMIN_LOGIN;
		case 2:
			return View.USER_HOME;
		case 3: 
			return View.USER_JOIN;
		case 4:
			return View.SYSOUT;
		default:
			return View.MAIN;
		}
	}
}
