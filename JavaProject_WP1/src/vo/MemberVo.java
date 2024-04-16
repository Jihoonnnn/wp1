package vo;

import lombok.Data;

@Data
public class MemberVo {
	private int mem_no;
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_nname;
	private String mem_regno;
	private String mem_cp;
	private int mem_point;
	private int mem_mileage;
	private String mem_delyn;
	
	@Override
	public String toString() {
		return "회원 번호 : "+mem_no+"\t\n"+
			   "아이디 : "+mem_id+"\t\n"+
			   "비밀번호 : "+mem_pass+"\t\n"+
			   "회원명: "+mem_name+"\t\n"+
			   "닉네임 : "+mem_nname+"\t\n"+
               "생년월일 : "+mem_regno+"\t\n"+
               "핸드폰 번호 : "+mem_cp+"\t\n"+
               "보유 포인트 : "+mem_point+"\t\n"+
               "보유 마일리지 : "+mem_mileage+"\t\n"+
               "탈퇴여부 : "+mem_delyn;
	}
	
	public String toStr() {
		return "회원 번호 : "+mem_no+"\t\n"+
			   "아이디 : "+mem_id+"\t\n"+
			   "회원명: "+mem_name+"\t\n"+
               "보유 쿠폰 : "+coupon_code+"\t\n"+
               "보유 쿠폰 가격 : "+coupon_unit+"\t\n"+
               "사용여부 : "+coupon_delyn;
	}
}
