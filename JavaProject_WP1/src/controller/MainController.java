package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.AdminService;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.BoardVo;
import vo.MemberVo;

public class MainController extends Print{
	static public Map<String, Object> sessionStorage = new HashMap<>(); // 데이터를 저장할 수 있게 만들어놓은 공간
	MemberService memberService = MemberService.getInstance();
	AdminService adminService = AdminService.getInstance();
	
	public static void main(String[] args) {
		new MainController().start();
	}
	
	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case LOGIN:
				view = login();
				break;
			case SIGN:
				view = sign();
				break;
			case ACCOUNT:
				view = account();
				break;
//				0415
			case MEMBER_ACCOUNT:
				view = memberAccount();
				break;
			case MEMBER_FIND_ID:
				view = memFindId();
				break;
			case MEMBER_UPDATE_PASSWORD:
				view = memUpdatePassword();
				break;
				
			case ADMIN_ACCOUNT:
				view = adminAccount();
				break;
			case ADMIN_FIND_ID:
				view = 	adFindId();
				break;
			case ADMIN_UPDATE_PASSWORD:
				view = adUpdatePassword();
				break;
//				0415
				
			case MEMBER_LOGIN:
				view = memberLogin();
				break;
			case MEMBER_SIGN:
				view = memberSign();
				break;
				
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case ADMIN_SIGN:
				view = adminSign();
				break;
				
			case MEMBER:
				view = member();
				break;
			case ADMIN:
				view = admin();
				break;
			case MEMBER_BOARD:
				view = memberBoard();
				break;
			case ADMIN_BOARD:
				view = adminBoard();
				break;
			case POST_INSERT:
				view = postInsert();
				break;		
			case POST_DELETE:
				view = postDelete();
				break;		
			case COMMENT_INSERT:
				view = commentInsert();
				break;		
			case COMMENT_UPDATE:
				view = commentUpdate();
				break;		
			case COMMENT_DELETE:
				view = commentDelete();
				break;		
			case SIGN_LIST:
				view = signList();
				break;
				
			default:
				break;
			}
		}
	}
	
	private View signList() {
		List<MemberVo> memList = adminService.signList();
		printSignList(memList);
		return View.ADMIN;
	}

	private View commentDelete() {
		AdminVo admin = (AdminVo) sessionStorage.get("admin");
		int adNo = admin.getAd_no();
		int no = ScanUtil.nextInt("삭제 할 글 번호 입력 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(adNo);
		param.add(no);
		param.add(no);
		adminService.commentDelete(param);
		return View.ADMIN_BOARD;
	}

	private View commentUpdate() {
		AdminVo admin = (AdminVo) sessionStorage.get("admin");
		int adNo = admin.getAd_no();
		int no = ScanUtil.nextInt("수정 할 글 번호 입력 : ");
		String cont = ScanUtil.nextLine("답변 내용 입력 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(adNo);
		param.add(no);
		param.add(cont);
		param.add(no);
		adminService.commentUpdate(param);
		return View.ADMIN_BOARD;
	}
	
	private View commentInsert() {
		AdminVo admin = (AdminVo) sessionStorage.get("admin");
		int adNo = admin.getAd_no();
		int no = ScanUtil.nextInt("등록 할 문의 번호 입력 : ");
		String cont = ScanUtil.nextLine("답변 내용 입력 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(adNo);
		param.add(no);
		param.add(cont);
		adminService.commentInsert(param);
		return View.ADMIN_BOARD;
	}

	private View adminBoard() {
		List<BoardVo> boardList = adminService.adiminBoard();
		printAdminBoard(boardList);
		
		System.out.println("1. 문의글 등록");
		System.out.println("2. 문의글 수정");
		System.out.println("3. 문의글 삭제");
		System.out.println("4. 홈");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.COMMENT_INSERT;
		case 2: return View.COMMENT_UPDATE;
		case 3: return View.COMMENT_DELETE;
		case 4: return View.ADMIN;
		default: return View.ADMIN_BOARD;
		}
	}

	private View postDelete() {
		int no = ScanUtil.nextInt("삭제 할 문의 번호 입력 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(no);
		memberService.postDelete(param);
		return View.ADMIN_BOARD;
	}

	private View postInsert() {

		System.out.println("\n 문의글 등록 \n");
		
		MemberVo member = (MemberVo) sessionStorage.get("member");
		int memNo = member.getMem_no();
		String title = ScanUtil.nextLine("제목 입력 : ");
		String cont = ScanUtil.nextLine("내용 입력 : ");
		List<Object> param = new ArrayList<Object>();
		param.add(memNo);
		param.add(title);
		param.add(cont);
		
		memberService.postInsert(param);
		
		return View.MEMBER;
	}

	private View memberBoard() {
		MemberVo member = (MemberVo) sessionStorage.get("member");
		int memNo = member.getMem_no();
		List<Object> param = new ArrayList<Object>();
		param.add(memNo);
		memberService.memberBoard(param);
		List<BoardVo> boardList = memberService.memberBoard(param);
		printMemberBoard(boardList);

		System.out.println("1. 문의글 등록");
		System.out.println("2. 문의글 삭제");
		System.out.println("3. 홈");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.POST_INSERT;
		case 2: return View.POST_DELETE;
		case 3: return View.MEMBER;
		default: return View.MEMBER;
		}
	}
	
	private View member() {
		System.out.println("1. 좌석/룸 예약");
		System.out.println("2. 예약 현황");
		System.out.println("3. 포인트 충전");
		System.out.println("4. 문의글");
		System.out.println("5. 마이페이지");
		System.out.println("6. 로그아웃");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.HOME;
		case 2: return View.HOME;
		case 3: return View.HOME;
		case 4: return View.MEMBER_BOARD;
		case 5: return View.HOME;
		case 6: return View.HOME;
		default: return View.HOME;
		}
	}
	
	private View admin() {
		System.out.println("1. 예약 현황");
		System.out.println("2. 가입 회원 조회");
		System.out.println("3. 문의글");
		System.out.println("4. 로그아웃");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.HOME;
		case 2: return View.SIGN_LIST;
		case 3: return View.ADMIN_BOARD;
		case 4: return View.HOME;
		default: return View.HOME;
		}
	}
// 0415
	private View memFindId() {
		String name = ScanUtil.nextLine("\n회원 이름 : ");
		String cp = ScanUtil.nextLine("\n회원 휴대폰 번호 : ");
		
		List<Object> param = new ArrayList<>();
		param.add(name);
		param.add(cp);
		
		MemberVo member = memberService.memFindId(param);
		if(member == null) {
			System.out.println("\n등록되지 않은 정보입니다.\n");
			return View.MEMBER_FIND_ID;
		}
		
		System.out.println("\n회원 아이디 : " + member.getMem_id());
		return View.LOGIN;
	}

	private View memUpdatePassword() {
	    String name = ScanUtil.nextLine("\n회원 이름 : ");
	    String cp = ScanUtil.nextLine("\n회원 휴대폰 번호 : ");

	    boolean checkMember = memberService.checkMember(name, cp);
	    if (!checkMember) {
	        printVar();
	        System.out.println("\n등록되지 않은 정보입니다.\n");
	        printVar();
	        return View.MEMBER_UPDATE_PASSWORD;
	    }

	    String newPassword = ScanUtil.nextLine("\n새 비밀번호를 입력해주세요 : ");
	    System.out.println("\n비밀번호가 성공적으로 변경되었습니다.");
	    List<Object> param = new ArrayList<>();
	    param.add(newPassword);
	    memberService.memUpdatePassword(name, cp, newPassword);
	    return View.MEMBER_LOGIN;
	}
	
	private View adFindId() {
		String name = ScanUtil.nextLine("\n관리자 이름 : ");
		String cp = ScanUtil.nextLine("\n관리자 휴대폰 번호 : ");
		
		List<Object> param = new ArrayList<>();
		param.add(name);
		param.add(cp);
		
		AdminVo admin = adminService.adFindId(param);
		if(admin == null) {
			System.out.println("\n등록되지 않은 정보입니다.\n");
			return View.ADMIN_FIND_ID;
		}
		
		System.out.println("\n회원 아이디 : " + admin.getAd_id());
		return View.LOGIN;
	}
	
	private View adUpdatePassword() {
		String name = ScanUtil.nextLine("\n회원 이름 : ");
		String cp = ScanUtil.nextLine("\n회원 휴대폰 번호 : ");
		
		boolean checkAdmin = adminService.checkAdmin(name, cp);
		if (!checkAdmin) {
			printVar();
			System.out.println("\n등록되지 않은 정보입니다.\n");
			printVar();
			return View.ADMIN_UPDATE_PASSWORD;
		}
		
		String newPassword = ScanUtil.nextLine("\n새 비밀번호를 입력해주세요 : ");
		System.out.println("\n비밀번호가 성공적으로 변경되었습니다.");
		List<Object> param = new ArrayList<>();
		param.add(newPassword);
		adminService.adUpdatePassword(name, cp, newPassword);
		return View.ADMIN_LOGIN;
	}

	private View account() {
		System.out.println("1. 회원 계정 찾기");
		System.out.println("2. 관리자 계정 찾기");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.MEMBER_ACCOUNT;
		case 2: return View.ADMIN_ACCOUNT;
		default: return View.ACCOUNT;
		}
	}
	
	private View adminAccount() {
		System.out.println("1. 아이디 찾기");
		System.out.println("2. 비밀번호 찾기");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.ADMIN_FIND_ID;
		case 2: return View.ADMIN_UPDATE_PASSWORD;
		default: return View.ADMIN_ACCOUNT;
		}
	}
	
	private View memberAccount() {
		System.out.println("1. 아이디 찾기");
		System.out.println("2. 비밀번호 찾기");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.MEMBER_FIND_ID;
		case 2: return View.MEMBER_UPDATE_PASSWORD;
		default: return View.MEMBER_ACCOUNT;
		}
	}
//	0415
	private View adminSign() {
		String id = ScanUtil.nextLine("\n아이디 (영문 5자 이상 15자 이하) : ");
		String pw = ScanUtil.nextLine("비밀번호 (5자 이상 15자 이하) : ");
		String name = ScanUtil.nextLine("이름 : ");
		String nname = ScanUtil.nextLine("닉네임 : ");
		String regno = ScanUtil.nextLine("생년월일 (EX:970403) : ");
		String cp = ScanUtil.nextLine("휴대폰번호 (EX:01077242177) : ");
		
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(nname);
		param.add(regno);
		param.add(cp);
		adminService.adminSign(param);
		printVar();
		System.out.println("\n가입이 완료되었습니다.\n");
		printVar();
		
		return View.ADMIN_LOGIN;
	}

	private View sign() {
		System.out.println("1. 회원 가입");
		System.out.println("2. 관리자 회원 가입");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.MEMBER_SIGN;
		case 2: return View.ADMIN_SIGN;
		default: return View.SIGN;
		}
	}

	private View login() {
		System.out.println("1. 회원 로그인");
		System.out.println("2. 관리자 로그인");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.MEMBER_LOGIN;
		case 2: return View.ADMIN_LOGIN;
		default: return View.LOGIN;
		}
	}

	private View memberSign() {
		String id = ScanUtil.nextLine("\n아이디 (영문 5자 이상 15자 이하) : ");
		String pw = ScanUtil.nextLine("비밀번호 (5자 이상 15자 이하) : ");
		String name = ScanUtil.nextLine("이름 : ");
		String nname = ScanUtil.nextLine("닉네임 : ");
		String regno = ScanUtil.nextLine("생년월일 (EX:970403) : ");
		String cp = ScanUtil.nextLine("휴대폰번호 (EX:01077242177) : ");
		
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(nname);
		param.add(regno);
		param.add(cp);
		memberService.memberSign(param);
		printVar();
		System.out.println("\n가입이 완료되었습니다.\n");
		printVar();
		
		return View.MEMBER_LOGIN;
	}

	private View adminLogin() {
		String id = ScanUtil.nextLine("\n관리자 ID : ");
		String pw = ScanUtil.nextLine("관리자 PW : ");
		
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		
		boolean login = adminService.adminLogin(param);
		if(!login) {
			printVar();
			System.out.println("\n아이디 또는 비밀번호가 일치하지 않습니다.\n");
			printVar();
			System.out.println("1. 로그인");
			System.out.println("2. 회원 가입");
			System.out.println("3. 계정 찾기");
			System.out.println("4. 홈");
			
			int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
			printVar();
			if(sel == 1) return View.ADMIN_LOGIN;
			else if(sel == 2) return View.ADMIN_SIGN;
			else if(sel == 3) return View.HOME;
			else if(sel == 4) return View.HOME;
			else return View.ADMIN_LOGIN;
		}
		View view = (View) sessionStorage.get("view");
		printVar();
		System.out.println("\n로그인이 완료되었습니다.\n");
		printVar();
		if(view == null) return View.ADMIN;
		return view;
	}
	
	private View memberLogin() {
		String id = ScanUtil.nextLine("\n회원 ID : ");
		String pw = ScanUtil.nextLine("회원 PW : ");
		
		List<Object> param = new ArrayList<>();
		param.add(id);
		param.add(pw);
		
		boolean login = memberService.memberLogin(param);
		if(!login) {
			printVar();
			System.out.println("\n아이디 또는 비밀번호가 일치하지 않습니다.\n");
			printVar();
			System.out.println("1. 로그인");
			System.out.println("2. 회원 가입");
			System.out.println("3. 계정 찾기");
			System.out.println("4. 홈");
			
			int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
			printVar();
			if(sel == 1) return View.MEMBER_LOGIN;
			else if(sel == 2) return View.MEMBER_SIGN;
			else if(sel == 3) return View.HOME;
			else if(sel == 4) return View.HOME;
			else return View.MEMBER_LOGIN;
		}
		View view = (View) sessionStorage.get("view");
		printVar();
		System.out.println("\n로그인이 완료되었습니다.\n");
		printVar();
		if(view == null) return View.MEMBER;
		return view;
	}

	private View home() {
		System.out.println("*스터디 카페 소개를 작성해주세요*");
		System.out.println("*요금표를 작성해주세요*\n");
		System.out.println("1. 로그인");
		System.out.println("2. 회원 가입");
		System.out.println("3. 계정찾기");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.LOGIN;
		case 2: return View.SIGN;
		case 3: return View.ACCOUNT;
		default: return View.HOME;
		}
	}
}
