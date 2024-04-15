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
				
			case FIND_ID:
				view = findId();
				break;
			case FIND_PASSWORD:
				view = findPassword();
				break;
				
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
			default:
				break;
			}
		}
	}
	

	private View findId() {
		String name = ScanUtil.nextLine("\n회원 이름 : ");
		String cp = ScanUtil.nextLine("\n회원 휴대폰 번호 : ");
		
		List<Object> param = new ArrayList<>();
		param.add(name);
		param.add(cp);
		
		boolean findId = memberService.findId(param);
		if(!findId) {
			System.out.println("\n등록되지 않은 정보입니다.\n");
			return View.FIND_ID;
		}
		
		return View.LOGIN;
	}

	private View findPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private View account() {
		System.out.println("1. 아이디 찾기");
		System.out.println("2. 비밀번호 찾기");
		
		int sel = ScanUtil.nextInt("\n메뉴 선택 : ");
		printVar();
		switch (sel) {
		case 1: return View.FIND_ID;
		case 2: return View.FIND_PASSWORD;
		default: return View.ACCOUNT;
		}
	}

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