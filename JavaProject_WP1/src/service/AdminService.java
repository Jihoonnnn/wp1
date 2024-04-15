package service;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import dao.AdminDao;
import vo.AdminVo;
import vo.BoardVo;
import vo.MemberVo;

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
//	0415
	public boolean checkAdmin(String name, String cp) {
	    List<Object> param = new ArrayList<>();
	    param.add(name);
	    param.add(cp);
	    AdminVo admin = adminDao.checkAdmin(param);
	    if(admin == null) {
	        return false;
	    }
	    MainController.sessionStorage.put("admin", admin);
	    return true;
	}
	
	public void adUpdatePassword(String name, String cp, String newPassword) {
	    List<Object> param = new ArrayList<>();
	    param.add(newPassword);
	    param.add(name);
	    param.add(cp);
	    adminDao.adUpdatePassword(param);
	}

	public AdminVo adFindId(List<Object> param) {
		AdminVo admin = adminDao.adFindId(param);
		if(admin != null) {
			MainController.sessionStorage.put("admin", admin);
		}
		return admin;
	}
//	0415
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

	public List<BoardVo> adiminBoard() {
		return adminDao.adiminBoard();
	}

	public void commentInsert(List<Object> param) {
		adminDao.commentInsert(param);
	}

	public void commentUpdate(List<Object> param) {
		adminDao.commentUpdate(param);
	}

	public void commentDelete(List<Object> param) {
		adminDao.commentDelete(param);
	}

	public List<MemberVo> signList() {
		return adminDao.signList();
	}

}
