package util;

public enum View {
	HOME,					// 기본 화면
	LOGIN,					// 로그인 기본 화면
	SIGN,					// 회원가입 기본 화면
	ACCOUNT,				// 계정찾기 기본 화면
	MEMBER,					// 회원 기본 화면
	ADMIN,					// 관리자 기본 화면
	
	FIND_ID,				// 아이디 찾기
	FIND_PASSWORD,			// 비밀번호 찾기
	
	MEMBER_LOGIN,			// 회원 로그인
	MEMBER_SIGN,			// 회원 가입
	
	ADMIN_LOGIN,			// 관리자 로그인
	ADMIN_SIGN,				// 관리자 회원 가입
	
	MEMBER_BOARD,			// 회원 게시판 기본 화면
	POST_INSERT,			// 문의글 등록
	POST_DELETE,			// 문의글 삭제
	ADMIN_BOARD,			// 회원 게시판 기본 화면
	COMMENT_INSERT,			// 답변글 등록
	COMMENT_UPDATE,			// 답변글 수정
	COMMENT_DELETE,			// 답변글 삭제
	
	SIGN_LIST				// 가입 회원 리스트
}
