package service;

import java.util.List;

import controller.MainController;
import dao.AdminDao;
import vo.AdminVo;

public class AdminService {
	private static AdminService instance;

	private AdminService() {

	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	AdminDao adminDao = AdminDao.getInstance();
	
	public boolean adminLogin(List<Object> param) {
		AdminVo admin = adminDao.adminLogin(param);
		if(admin == null) {
			return false;
		}
		MainController.sessionStorage.put("admin", admin);
		
		return true;
	}
	
	public void adminSign(List<Object> param) {
		adminDao.adminSign(param);
	}
}
