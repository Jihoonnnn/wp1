package dao;

import java.util.List;

import util.JDBCUtil;
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
	
	public MemberVo findId(List<Object> param) {
		String sql = " SELECT SUBSTR(MEM_ID, 1, LENGTH(MEM_ID) - 3) || '***'\r\n " + 
					 " FROM MEMBER\r\n " + 
					 " WHERE MEM_NAME = ? \r\n " + 
					 " AND MEM_CP = ? \r\n " + 
					 " AND MEM_DELYN = 'N' ";
		
		return jdbc.selectOne(sql, param, MemberVo.class);
	}
	
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
}
