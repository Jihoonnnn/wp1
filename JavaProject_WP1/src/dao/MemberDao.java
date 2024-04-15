package dao;

import java.util.List;

import util.JDBCUtil;
import vo.BoardVo;
import vo.MemberVo;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
// 0415
	public MemberVo checkMember(List<Object> param) {
		String sql = " SELECT *\r\n " + 
				" FROM MEMBER\r\n " + 
				" WHERE MEM_NAME = ? \r\n " + 
				" AND MEM_CP = ? \r\n " + 
				" AND MEM_DELYN = 'N' ";
		
		return jdbc.selectOne(sql, param, MemberVo.class);
	}
	
	public void memUpdatePassword(List<Object> param) {
		String sql = " UPDATE MEMBER\r\n " + 
				" SET MEM_PASS = ?\r\n " + 
				" WHERE MEM_NAME = ? \r\n " + 
				" AND MEM_CP = ? \r\n " + 
				" AND MEM_DELYN = 'N' ";
		jdbc.update(sql, param);
	}
	
	public MemberVo memFindId(List<Object> param) {
		String sql = " SELECT MEM_ID\r\n " + 
				" FROM MEMBER\r\n " + 
				" WHERE MEM_NAME = ? \r\n " + 
				" AND MEM_CP = ? \r\n " + 
				" AND MEM_DELYN = 'N' ";
		
		return jdbc.selectOne(sql, param, MemberVo.class);
	}
//	0415	
	public MemberVo memberLogin(List<Object> param) {
		String sql = " SELECT *\r\n " + 
					 " FROM MEMBER\r\n " + 
					 " WHERE MEM_ID = ? \r\n " + 
					 " AND MEM_PASS = ? \r\n " + 
					 " AND MEM_DELYN = 'N' ";
		return jdbc.selectOne(sql, param, MemberVo.class);
	}
	
	public void memberSign(List<Object> param) {
		String sql = " INSERT INTO MEMBER\r\n " + 
					 " VALUES ((SELECT NVL(MAX(MEM_NO), 0)+1 FROM MEMBER)\r\n " + 
					 "       , ? , ? , ? , ? , ? , ? , 0 , 2000 , 'N'\r\n " + 
					 "       )";
		
		jdbc.update(sql, param);
	}
	
	public List<BoardVo> memberBoard(List<Object> param) {
		String sql = " SELECT A.POST_NO,\n" +
					"       A.POST_NAME,\n" +
					"       A.POST_CONTENT,\n" +
					"       TO_CHAR(A.POST_DATE,'YYYY.MM.DD') AS POST_DATE, \n" + //
					"       NVL(B.CM_CONTENT,'미등록') AS CM_CONTENT,\n" + //
					"       NVL(TO_CHAR(B.CM_DATE, 'YYYY.MM.DD'),'미등록') AS CM_DATE\n" + //
					" FROM BOARD_POST A\n" +
					" LEFT JOIN BOARD_COMMENT B ON A.POST_NO = B.POST_NO\n" + //
					" WHERE A.MEM_NO = ?\n" +
					" AND A.POST_DELYN = 'N'\n" +
					" ORDER BY A.POST_NO";
		return jdbc.selectList(sql, param, BoardVo.class);
	}
	
		public void postInsert(List<Object> param) {
			String sql = "INSERT INTO BOARD_POST\n" + //
						" VALUES(\n" + //
						"    (SELECT NVL(MAX(POST_NO)+1,1) FROM BOARD_POST),\n" + //
						"    ?,\n" + //
						"    ?,\n" + //
						"    ?,\n" + //
						"    SYSDATE,\n" + //
						"    'N'\n" + //
						")";
			jdbc.update(sql, param);
		}

        public void postUpdate(List<Object> param) {
            String sql = "UPDATE BOARD_POST\n" +
					 " SET \n" +
					 "    POST_CONTENT = ?,\n" +
					 "    POST_DATE = SYSDATE\n" +
					 " WHERE POST_NO = ?";
		jdbc.update(sql, param);
        }

        public void postDelete(List<Object> param) {
            String sql = "UPDATE BOARD_POST\n" +
					 " SET \n" +
					 "    POST_DELYN = 'Y'\n" +
					 " WHERE POST_NO = ?";
		jdbc.update(sql, param);
        }
}
