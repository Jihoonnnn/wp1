package service;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import dao.MemberDao;
import vo.BoardVo;
import vo.MemberVo;

public class MemberService {
	private static MemberService instance;

	private MemberService() {

	}

	public static MemberService getInstance() {
		if(instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	
	MemberDao memberDao = MemberDao.getInstance();
//	0415
	public boolean checkMember(String name, String cp) {
	    List<Object> param = new ArrayList<>();
	    param.add(name);
	    param.add(cp);
	    MemberVo member = memberDao.checkMember(param);
	    if(member == null) {
	        return false;
	    }
	    MainController.sessionStorage.put("member", member);
	    return true;
	}
	
	public void memUpdatePassword(String name, String cp, String newPassword) {
	    List<Object> param = new ArrayList<>();
	    param.add(newPassword);
	    param.add(name);
	    param.add(cp);
	    memberDao.memUpdatePassword(param);
	}

	public MemberVo memFindId(List<Object> param) {
		MemberVo member = memberDao.memFindId(param);
		if(member != null) {
			MainController.sessionStorage.put("member", member);
		}
		return member;
	}
// 	0415
	public boolean memberLogin(List<Object> param) {
		MemberVo member = memberDao.memberLogin(param);
		if(member == null) {
			return false;
		}
		MainController.sessionStorage.put("member", member);
		
		return true;
	}
	
	public void memberSign(List<Object> param) {
		memberDao.memberSign(param);
	}
	
	public List<BoardVo> memberBoard(List<Object> param) {
		return memberDao.memberBoard(param);
	}

    public void postInsert(List<Object> param) {
		memberDao.postInsert(param);
	}

	public void postUpdate(List<Object> param) {
		memberDao.postUpdate(param);
	}

    public void postDelete(List<Object> param) {
        memberDao.postDelete(param);
    }
}
