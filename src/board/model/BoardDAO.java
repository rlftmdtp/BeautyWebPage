package board.model;

import java.sql.*;
import java.util.ArrayList;


public class BoardDAO {
	public static final int WRITING_PER_PAGE = 10;
	
	public BoardDAO(){}
	
	public ArrayList<BoardDTO> boardList(String curPage){
		
        ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{
		Class.forName(jdbc_driver);
		
		System.out.println("�Խ��Ǳ��� �����ɴϴ�");
		Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
		
		String sql = "select num,name,subject,content,write_date,write_time,ref,step,lev,read_cnt,child_cnt from board order by ref desc,step asc limit ?,?";
		//sql += "from board order by ref desc,step asc";
		//sql += "limit ?,?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, WRITING_PER_PAGE*(Integer.parseInt(curPage)-1));
		pstmt.setInt(2, WRITING_PER_PAGE);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			int num = rs.getInt("num");
			String name = rs.getString("name");
		
			String subject = rs.getString("subject");
			String content = rs.getString("content");
			Date writeDate = rs.getDate("write_date");
			Time writeTime = rs.getTime("write_time");
			int ref = rs.getInt("ref");
			int step = rs.getInt("step");
			int lev = rs.getInt("lev");
			int readCnt = rs.getInt("read_cnt");
			int childCnt = rs.getInt("child_cnt");
			
			BoardDTO writing = new BoardDTO();
			writing.setNum(num);
			writing.setName(name);
			
			writing.setSubject(subject);
			writing.setContent(content);
			writing.setWriteDate(writeDate);
			writing.setWriteTime(writeTime);
			writing.setRef(ref);
			writing.setStep(step);
			writing.setLev(lev);
			writing.setReadCnt(readCnt);
			writing.setChildCnt(childCnt);
		
			
			list.add(writing);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
		
		public int boardPageCnt(){
			int pageCnt = 0;
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
 
			try{
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			
			String sql = "select count(*) as num from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				pageCnt = rs.getInt("num")/WRITING_PER_PAGE + 1;
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return pageCnt;
		}
		
		public void boardWrite(String name,String subject,String content,String password){
			int num = 1;
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try{
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			System.out.println("�Խ��� ��ȸ���� �����մϴ�");
			String sql = "select ifnull(max(num),0)+1 as num from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num=rs.getInt("num");
			}
			
			System.out.println("�Խ��� ���� ���ϴ�");
			sql = "insert into board (num,name,subject,content,write_date,write_time,ref,step,lev,read_cnt,child_cnt) values(?,?,?,?,curdate(),curtime(),?,0,0,0,0)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			pstmt.setString(2,name);
		
			pstmt.setString(3,subject);
			pstmt.setString(4,content);
			pstmt.setInt(5, num);
			
			pstmt.executeUpdate();
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		public BoardDTO boardRead(String inputNum){
			BoardDTO writing = new BoardDTO();
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try{
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			System.out.println("�Խ��� ��ȸ���� �����մϴ�");
			String sql = "update board set read_cnt=read_cnt+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			pstmt.executeUpdate();
			
			sql = "select num,name,subject,content,write_date,write_time,ref,step,lev,read_cnt,child_cnt from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inputNum));
			rs = pstmt.executeQuery();
			
			System.out.println("�Խ����� �����մϴ�");
			if(rs.next()){
				int num = rs.getInt("num");
				String name = rs.getString("name");
				
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("write_date");
				Time writeTime = rs.getTime("write_time");
				int ref = rs.getInt("ref");
				int step = rs.getInt("step");
				int lev = rs.getInt("lev");
				int readCnt = rs.getInt("read_cnt");
				int childCnt = rs.getInt("child_cnt");
				
				writing.setNum(num);
				writing.setName(name);
				
				writing.setSubject(subject);
				writing.setContent(content);
				writing.setWriteDate(writeDate);
				writing.setWriteTime(writeTime);
				writing.setRef(ref);
				writing.setStep(step);
				writing.setLev(lev);
				writing.setReadCnt(readCnt);
				writing.setChildCnt(childCnt);
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			return writing;
		}	
		
		public void boardUpdate(String inputNum,String inputSubject,String inputContent,String inputName){
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "UPDATE BOARD SET subject=?, content=?, name=?  WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, inputSubject);
				pstmt.setString(2, inputContent);
				pstmt.setString(3, inputName);
				
				pstmt.setInt(4, Integer.parseInt(inputNum));
				
				pstmt.executeUpdate();
				
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null) pstmt.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}	
		
		// �Խñ� ���� �� ������ ���� ��й�ȣ Ȯ�� ��� ��ȸ
		public boolean boardPasswordCheck(String inputNum){
			
			boolean passwordOk = false;
			int passwordCheck = 0;
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT COUNT(*) AS password_check FROM BOARD WHERE num=? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  Integer.parseInt(inputNum));
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) passwordCheck = rs.getInt("password_check"); 			
				if (passwordCheck > 0) passwordOk = true;
				
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null) pstmt.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			return passwordOk;
		}
		
		// �� ���� ȭ�鿡 �ʿ��� ���� ������ ��ȸ ���
		public BoardDTO boardUpdateForm(String inputNum){
			
			BoardDTO writing = new BoardDTO();
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BOARD WHERE num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  Integer.parseInt(inputNum));
				rs = pstmt.executeQuery();
				
				if (rs.next()){
					int num = rs.getInt("num");
					String name = rs.getString("name");
					
					String subject = rs.getString("subject");
					String content = rs.getString("content");
					Date writeDate = rs.getDate("write_date");
					Time writeTime = rs.getTime("write_time");
					int ref = rs.getInt("ref");
					int step = rs.getInt("step");
					int lev = rs.getInt("lev");
					int readCnt = rs.getInt("read_cnt");
					int childCnt = rs.getInt("child_cnt");
					
					writing.setNum(num);
					writing.setName(name);
				
					writing.setSubject(subject);
					writing.setContent(content);
					writing.setWriteDate(writeDate);
					writing.setWriteTime(writeTime);
					writing.setRef(ref);
					writing.setStep(step);
					writing.setLev(lev);
					writing.setReadCnt(readCnt);
					writing.setChildCnt(childCnt);
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}	
			return writing;
		}
		
		// �Խñ� ���� ��� ����
		public void boardDelete(String inputNum){
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT ref, lev, step FROM BOARD WHERE num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(inputNum));
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					int ref = rs.getInt(1);
					int lev = rs.getInt(2);
					int step = rs.getInt(3);
					boardDeleteChildCntUpdate(ref, lev, step);
				}			
				
				sql = "DELETE FROM BOARD WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(inputNum));
				pstmt.executeUpdate();
				
				sql = "DELETE FROM reply WHERE num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(inputNum));
				pstmt.executeUpdate();

				
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		// ���� ����� �Խñۿ� ����� ���� ������ �˻�
		public boolean boardReplyCheck(String inputNum){
			boolean replyCheck = false;
			int replyCnt = 0;
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT child_cnt AS reply_check FROM BOARD WHERE num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  Integer.parseInt(inputNum));
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) replyCnt = rs.getInt("reply_check");
				if (replyCnt == 0) replyCheck = true;
				
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(pstmt != null) pstmt.close();
				
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			
			return replyCheck;
		}	
		
		// �Խñ��� ����� ���, ���۵��� ��� ������ �ٿ��ִ� ��� ����
		public void boardDeleteChildCntUpdate(int ref, int lev, int step){
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				for (int updateLev = lev-1 ; updateLev>= 0 ; updateLev--){
					sql = "SELECT MAX(step) FROM BOARD WHERE ref = ? and lev = ? and step < ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, updateLev);
					pstmt.setInt(3, step);
					
					rs = pstmt.executeQuery();
					int maxStep = 0;
					
					if (rs.next()) maxStep = rs.getInt(1);
					sql = "UPDATE BOARD SET child_cnt = child_cnt - 1 where ref = ? and lev = ? and step = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, updateLev);
					pstmt.setInt(3, maxStep);
					pstmt.executeUpdate();
				}

			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		
		// �˻� ��� ����
		public ArrayList<BoardDTO> boardSearch(String searchOption, String searchWord){
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				String sql = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BOARD";
				
				if(searchOption.equals("subject")){
					sql += " WHERE subject LIKE ?";
					sql += " ORDER BY ref desc, step asc";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchWord + "%");

				}else if(searchOption.equals("content")){
					sql += " WHERE content LIKE ?";
					sql += " ORDER BY ref desc, step asc";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchWord + "%");
					
				}else if(searchOption.equals("name")){
					sql += " WHERE name LIKE ?";
					sql += " ORDER BY ref desc, step asc";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchWord + "%");
					
				}else if(searchOption.equals("both")){
					sql += " WHERE subject LIKE ? OR content LIKE ? ";
					sql += " ORDER BY ref desc, step asc";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchWord + "%");
					pstmt.setString(2, "%" + searchWord + "%");
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
				
					int num = rs.getInt("num");
					String name = rs.getString("name");
					
					String subject = rs.getString("subject");
					String content = rs.getString("content");
					Date writeDate = rs.getDate("write_date");
					Time writeTime = rs.getTime("write_time");
					int ref = rs.getInt("ref");
					int step = rs.getInt("step");
					int lev = rs.getInt("lev");
					int readCnt = rs.getInt("read_cnt");
					int childCnt = rs.getInt("child_cnt");
					
					BoardDTO writing = new BoardDTO();
					writing.setNum(num);
					writing.setName(name);
					
					writing.setSubject(subject);
					writing.setContent(content);
					writing.setWriteDate(writeDate);
					writing.setWriteTime(writeTime);
					writing.setRef(ref);
					writing.setStep(step);
					writing.setLev(lev);
					writing.setReadCnt(readCnt);
					writing.setChildCnt(childCnt);
					
					list.add(writing);
				}
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			
			return list;
		}
		
		// ��� �ۼ��� �ʿ��� ���� ������ ��ȸ ���
		public BoardDTO boardReplyForm(String inputNum){
			BoardDTO writing = new BoardDTO();
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt FROM BOARD WHERE NUM = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  Integer.parseInt(inputNum));
				rs = pstmt.executeQuery();
				
				if (rs.next()){
					int num = rs.getInt("num");
					String name = rs.getString("name");
					
					String subject = "RE:" + rs.getString("subject");
					Date writeDate = rs.getDate("write_date");
					Time writeTime = rs.getTime("write_time");
					String content = "[����:" + writeDate + " " + writeTime +" �ۼ���]\n" + rs.getString("content");
					int ref = rs.getInt("ref");
					int step = rs.getInt("step");
					int lev = rs.getInt("lev");
					int readCnt = rs.getInt("read_cnt");
					int childCnt = rs.getInt("child_cnt");
					
					writing.setNum(num);
					writing.setName(name);
					
					writing.setSubject(subject);
					writing.setContent(content);
					writing.setWriteDate(writeDate);
					writing.setWriteTime(writeTime);
					writing.setRef(ref);
					writing.setStep(step);
					writing.setLev(lev);
					writing.setReadCnt(readCnt);
					writing.setChildCnt(childCnt);
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}	
			
			return writing;
		}
		
		// ��� ��� ��� ����
		public void boardReply(String num, String name, String subject, String content, String ref, String step, String lev){
		
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			int replyNum = 0;
			int replyStep = 0;
			String sql = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				replyStep = boardReplySearchStep(ref, lev, step); // ����� ��ġ�� step ���� ������
				
				if (replyStep > 0){
					sql = "UPDATE BOARD SET step = step + 1 where ref = ? and step >= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(ref));
					pstmt.setInt(2, replyStep);
					pstmt.executeUpdate();
				} else {
					sql = "SELECT MAX(STEP) FROM BOARD WHERE ref = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(ref));
					rs = pstmt.executeQuery();
					if (rs.next()) replyStep = rs.getInt(1) + 1;
				}
				
				sql = "SELECT MAX(num)+1 AS NUM FROM BOARD";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();			
				if (rs.next()) replyNum = rs.getInt("num"); 
				
				sql = "INSERT INTO BOARD";
				sql += " (num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt)";
				sql += " values(?, ?, ?, ?, curdate(), curtime(), ?, ?, ?, 0, 0)"; 
		
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1, replyNum);
				pstmt.setString(2, name);
				
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setInt(5, Integer.parseInt(ref));
				pstmt.setInt(6, replyStep);
				pstmt.setInt(7, Integer.parseInt(lev)+1);
				
				pstmt.executeUpdate();
				boardReplyChildCntUpdate(ref, lev, replyStep);
				
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				
				}catch (SQLException e){
					e.printStackTrace();
				}
			}	
		}
		
		// ����� ��� ��ġ (step) ���� ��� ����
		public int boardReplySearchStep(String ref, String lev, String step){
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			int replyStep=0;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				String sql = "SELECT IFNULL(MIN(step), 0) from BOARD WHERE ref = ? and lev <= ? and step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(ref));
				pstmt.setInt(2, Integer.parseInt(lev));
				pstmt.setInt(3, Integer.parseInt(step));
				rs = pstmt.executeQuery();

				if (rs.next()) replyStep = rs.getInt(1); 

			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
			return replyStep;
		}
		
		// ��� �ۼ� ��, ���۵��� ��� ������ �÷��ִ� ��� ����
		public void boardReplyChildCntUpdate(String ref, String lev, int replyStep){
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			String sql = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				for (int updateLev = Integer.parseInt(lev) ; updateLev>= 0 ; updateLev--){
					sql = "SELECT MAX(step) FROM BOARD WHERE ref = ? and lev = ? and step < ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(ref));
					pstmt.setInt(2,  updateLev);
					pstmt.setInt(3, replyStep);
					
					rs = pstmt.executeQuery();
					int maxStep = 0;
					
					if (rs.next()) maxStep = rs.getInt(1);
					sql = "UPDATE BOARD SET child_cnt = child_cnt + 1 where ref = ? and lev = ? and step = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(ref));
					pstmt.setInt(2, updateLev);
					pstmt.setInt(3, maxStep);
					pstmt.executeUpdate();
				}

			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		
		// ��¼��� ���� ��� �ֱ�
		public void boardReply2(String num, String content,String userid){
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
		
			String sql = null;
			
			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				
				
				sql = "INSERT INTO reply";
				sql += " (num,content,userid,write_date,write_time)";
				sql += " values(?,?,?,curdate(),curtime())"; 
		
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(num));
				pstmt.setString(2, content);
				pstmt.setString(3, userid);
				
				pstmt.executeUpdate();
			
				
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
				
				}catch (SQLException e){
					e.printStackTrace();
				}
			}	
		}
		
		// ��¼��� ���� ��۲�������
		public ArrayList<BoardDTO> boardReply2Form(String inputNum){
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try{
				Class.forName(jdbc_driver);
				Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
				
				String sql = "SELECT num, content,userid, write_date, write_time FROM reply WHERE NUM = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  Integer.parseInt(inputNum));
				rs = pstmt.executeQuery();
				
				while (rs.next()){
					int num = rs.getInt("num");
					String userid = rs.getString("userid");
					String content = rs.getString("content");
					Date writeDate = rs.getDate("write_date");
					Time writeTime = rs.getTime("write_time");
					
					System.out.println(userid);
					BoardDTO writing = new BoardDTO();
					writing.setNum(num);
					writing.setUserid(userid);
					writing.setContent(content);
					writing.setWriteDate(writeDate);
					writing.setWriteTime(writeTime);
					
					list.add(writing);
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					
				}catch (SQLException e){
					e.printStackTrace();
				}
			}	
			
			return list;
		}
}
	
	

