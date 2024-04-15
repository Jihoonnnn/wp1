package dao;

import java.util.List;

import util.JDBCUtil;
import vo.AdminVo;
import vo.BoardVo;
import vo.MemberVo;

public class AdminDao {
	private static AdminDao instance;

	private AdminDao() {

	}

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	// 0415
	public AdminVo checkAdmin(List<Object> param) {
		String sql = " SELECT *\r\n " + 
				" FROM ADMIN\r\n " + 
				" WHERE AD_NAME = ? \r\n " + 
				" AND AD_CP = ? \r\n " + 
				" AND AD_DELYN = 'N' ";
		
		return jdbc.selectOne(sql, param, AdminVo.class);
	}
	
	public void adUpdatePassword(List<Object> param) {
		String sql = " UPDATE ADMIN\r\n " + 
				" SET AD_PASS = ?\r\n " + 
				" WHERE AD_NAME = ? \r\n " + 
				" AND AD_CP = ? \r\n " + 
				" AND AD_DELYN = 'N' ";
		jdbc.update(sql, param);
	}
	
	public AdminVo adFindId(List<Object> param) {
		String sql = " SELECT AD_ID\r\n " + 
				" FROM ADMIN\r\n " + 
				" WHERE AD_NAME = ? \r\n " + 
				" AND AD_CP = ? \r\n " + 
				" AND AD_DELYN = 'N' ";
		
		return jdbc.selectOne(sql, param, AdminVo.class);
	}
//	0415
	public AdminVo adminLogin(List<Object> param) {
		String sql = " SELECT *\r\n " + 
					 " FROM ADMIN\r\n " + 
					 " WHERE AD_ID = ? \r\n " + 
					 " AND AD_PASS = ? \r\n " + 
					 " AND AD_DELYN = 'N' ";
		return jdbc.selectOne(sql, param, AdminVo.class);
	}
	
	public void adminSign(List<Object> param) {
		String sql = " INSERT INTO ADMIN\r\n " + 
				 " VALUES ((SELECT NVL(MAX(AD_NO), 0)+1 FROM ADMIN)\r\n " + 
				 "       , ? , ? , ? , ? , ? , ? , 'N'\r\n " + 
				 "       )";
		
		jdbc.update(sql, param);
	}
	
	public List<BoardVo> adiminBoard() {
        String sql = " SELECT A.CM_NO,\n" + //
					"       C.MEM_NAME,\n" + //
					"       B.POST_NAME,\n" + //
					"       B.POST_CONTENT,\n" + //
					"       TO_CHAR(B.POST_DATE,'YYYY.MM.DD') AS POST_DATE, \n" + //
					"       D.AD_NO,\n" + //
					"       NVL(A.CM_CONTENT,'미등록') AS CM_CONTENT,\n" + //
					"       NVL(TO_CHAR(A.CM_DATE, 'YYYY.MM.DD'),'미등록') AS CM_DATE\n" + //
					" FROM BOARD_COMMENT A\n" + //
					" LEFT JOIN BOARD_POST B ON A.POST_NO = B.POST_NO\n" + //
					" LEFT JOIN MEMBER C ON B.MEM_NO = C.MEM_NO\n" + //
					" LEFT JOIN ADMIN D ON D.AD_NO = A.AD_NO\n" + //
					" WHERE CM_DELYN = 'N' "+
					" ORDER BY A.CM_NO";
		return jdbc.selectList(sql, BoardVo.class);
    }

	public void commentInsert(List<Object> param) {
		String sql = "INSERT INTO BOARD_COMMENT\n" + //
					" VALUES(\n" + //
					"    (SELECT NVL(MAX(CM_NO)+1,1) FROM BOARD_COMMENT),\n" + //
					"    ?,\n" + //
					"    ?,\n" + //
					"    ?,\n" + //
					"    SYSDATE,\n" + //
					"    'N'\n" + //
					")";
		jdbc.update(sql, param);
	}

    public void commentUpdate(List<Object> param) {
		String sql = "UPDATE BOARD_COMMENT\n" +
					 " SET \n" +
					 "    AD_NO = ?,\n" +
					 "    POST_NO = ?,\n" +
					 "    CM_CONTENT = ?,\n" +
					 "    CM_DATE = SYSDATE,\n" +
					 "    CM_DELYN = 'N'\n" +
					 " WHERE POST_NO = ?";
		jdbc.update(sql, param);
	}

    public void commentDelete(List<Object> param) {
        String sql = " UPDATE BOARD_COMMENT\n" +
					 " SET \n" +
					 "    AD_NO = ?,\n" +
					 "    POST_NO = ?,\n" +
					 "    CM_DELYN = 'Y'\n" +
					 " WHERE POST_NO = ?";
		jdbc.update(sql, param);
    }

	public List<MemberVo> signList() {
		String sql = " SELECT MEM_NO,\r\n" + 
					"       MEM_ID,\r\n" + 
					"       MEM_PASS,\r\n" + 
					"       MEM_NAME,\r\n" + 
					"       MEM_NNAME,\r\n" + 
					"       MEM_REGNO,\r\n" + 
					"       MEM_CP,\r\n" + 
					"       MEM_POINT,\r\n" + 
					"       MEM_MILEAGE,\r\n" + 
					"       MEM_DELYN\r\n" + 
					" FROM MEMBER";
		return jdbc.selectList(sql, MemberVo.class);
	}
}
