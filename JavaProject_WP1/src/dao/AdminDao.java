package dao;

import java.util.List;

import util.JDBCUtil;
import vo.AdminVo;

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
}
