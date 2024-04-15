package print;

import java.util.List;

import vo.BoardVo;
import vo.MemberVo;

public class Print {
	public void printVar() {
		System.out.println("=====================================================================================================");
	}
	
	public void printMemberBoard(List<BoardVo> boardList){
		for(BoardVo list : boardList){
			System.out.println(list);
			printVar();
		}
	}

	public void printAdminBoard(List<BoardVo> boardList){
		for(BoardVo list : boardList){
			System.out.println(list.toStr());
			printVar();
		}
	}
	
	public void printSignList(List<MemberVo> memList){
		for(MemberVo list : memList){
			System.out.println(list);
			printVar();
		}
	}
}
