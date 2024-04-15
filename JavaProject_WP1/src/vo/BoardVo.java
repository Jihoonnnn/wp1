package vo;

import lombok.Data;

@Data
public class BoardVo {
    // 회원
    private int post_no;
    private int mem_no;
    private String mem_name;
    private String post_name;
    private String post_content;
    private String post_date;
    private String post_delyn;

    // 관리자
    private int cm_no;
    private int ad_no;
    private String cm_content;
    private String cm_date;
    private String cm_delyn;

    @Override
	public String toString() {
		return "문의 번호 : "+post_no+"\t\n"+
			   "제목 : "+post_name+"\t\n"+
			   "내용: "+post_content+"\t\n"+
			   "등록일자 : "+post_date+"\t\n"+
               "관리자 답변 : "+cm_content+"\t\n"+
               "답변일자 : "+cm_date;
	}

	public String toStr() {
		return "글 번호 : "+cm_no+"\t\n"+
			   "회원 아이디 : "+mem_name+"\t\n"+
			   "제목 : "+post_name+"\t\n"+
			   "내용: "+post_content+"\t\n"+
			   "등록일자 : "+post_date+"\t\n"+
               "관리자 번호 : "+ad_no+"\t\n"+
               "관리자 답변 : "+cm_content+"\t\n"+
               "답변일자 : "+cm_date;
	}
}
