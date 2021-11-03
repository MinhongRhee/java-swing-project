package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import view.NewMemberSearchPasswd;

public class NewMemberDao {

	// Fields
	private String driver = "oracle.jdbc.OracleDriver";
	private String url    = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private String dbuid  = "test";
	private String dbpwd  = "1234";
	
	private Connection         conn;
	private PreparedStatement  pstmt;
	private ResultSet          rs;
	
	// Constructor
	public NewMemberDao() {
		open();
	}

	private void open() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection( url, dbuid, dbpwd );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if( !conn.isClosed() ) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원추가
//	INSERT INTO NEWMEMBER ( USERID, PASSWD, USERNAME, NATID, EMAIL, TEL, JOB, GENDER, INTRO )
//	VALUES ( 'tiger', '1234', '호랑이', '900101-1111111', 'tiger@naver.com', '010-1990-1111', '학생', '남', '어흥' );
	public int insertMember(NewMemberVo vo) {
		int aftcnt = 0;
		try {
			String sql = "INSERT INTO NEWMEMBER ";
			sql	+= " ( USERID, PASSWD, USERNAME, NATID, EMAIL, TEL, JOB, GENDER, INTRO )";
			sql += " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getNatid());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getJob());
			pstmt.setString(8, vo.getGender());
			pstmt.setString(9, vo.getIntro());
			
			aftcnt = pstmt.executeUpdate();
			if ( aftcnt != 0 ) {
			String message = "회원 가입이 완료되었습니다";
			JOptionPane.showMessageDialog(null,
						message, "회원 가입 완료",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				String msg = "가입에 실패하였습니다";
				JOptionPane.showMessageDialog(null,
						msg, "회원가입 불가",
						JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			if(e.getErrorCode() == 1) {
				String message = "이미 사용중인 아이디, 이메일, 휴대폰번호 입니다\n다시 입력하세요";
				System.out.println(message);
				JOptionPane.showMessageDialog(null,
						message, "중복된 아이디",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return aftcnt;
	}
	
	// 로그인 시 아이디 가져오기
	public String getId(String uid) {
		String id = null;
		try {
			String sql = "SELECT USERID";
			sql += " FROM    NEWMEMBER"; 
			sql += " WHERE   USERID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			rs = pstmt.executeQuery();
			System.out.println(rs);
			if ( rs.next() ) {
				id   = rs.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( !rs.isClosed() ) rs.close();
				if( !pstmt.isClosed() ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	// 로그인시 비밀번호 가져오기
	public String getPwd(String uid, String pwd) {
		String passwd = null;
		try {
			String sql = "SELECT PASSWD";
			sql += " FROM    NEWMEMBER"; 
			sql += " WHERE   USERID = ?";
			sql += " AND     PASSWD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);		
			pstmt.setString(2, pwd);		
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				passwd   = rs.getString("passwd");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( !rs.isClosed() ) rs.close();
				if( !pstmt.isClosed() ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return passwd;
	}

	// 아이디찾기
	public String getSId(String username, String natid, String email) {
		String id = null;
		try {
			String sql = "SELECT USERID";
			sql += " FROM    NEWMEMBER"; 
			sql += " WHERE   USERNAME = ?";
			sql += " AND     NATID = ?";
			sql += " AND     EMAIL = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, natid);
			pstmt.setString(3, email);
			
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				id   = rs.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( !rs.isClosed() ) rs.close();
				if( !pstmt.isClosed() ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	// 비밀번호 찾기
	public NewMemberVo checkPwd(String userid, String username, String natid, String tel) {
		NewMemberVo vo = null;
		try {
			String sql = "SELECT USERID, USERNAME, NATID, TEL, PASSWD";
			sql += " FROM    NEWMEMBER"; 
			sql += " WHERE   USERID = ?";
			sql += "  AND   USERNAME = ?";
			sql += "  AND   NATID = ?";
			sql += "  AND   TEL = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, natid);
			pstmt.setString(4, tel);
			
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				String uid   = rs.getString("userid");
				String uname = rs.getString("username");
				String nid   = rs.getString("natid");
				String hp    = rs.getString("tel");
				String pwd   = rs.getString("PASSWD");
				vo = new NewMemberVo(uid, uname, nid, hp, pwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if( !rs.isClosed() ) rs.close();
				if( !pstmt.isClosed() ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
}