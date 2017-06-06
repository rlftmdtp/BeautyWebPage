package cosmetic.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ch11.logon.CosmeticDataBean;

public class CosmeticDAO {
public static final int WRITING_PER_PAGE = 10;
	
	public CosmeticDAO(){}
	
	public ArrayList<CosmeticDTO> cosmeticList(String curPage){ // image ����� �������� �޼ҵ�
	    ArrayList<CosmeticDTO> list = new ArrayList<CosmeticDTO>();

		String jdbc_driver = "com.mysql.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{
		Class.forName(jdbc_driver);
		
		Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
		
		String sql="select * from cosmetic limit ?,?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,WRITING_PER_PAGE*(Integer.parseInt(curPage)-1));
		pstmt.setInt(2,WRITING_PER_PAGE);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			CosmeticDTO writing = new CosmeticDTO();
			writing.setTitle(rs.getString("title"));
			writing.setBeautytype(rs.getString("beautytype"));
			writing.setAge(rs.getString("age"));
			writing.setGrade(rs.getString("grade"));
			writing.setContents(rs.getString("contents"));
			writing.setProductname(rs.getString("productname"));
			writing.setImage(rs.getString("image"));
			
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

public int cosmeticPageCnt(){ // �����Ϳ������ ��ü ��� ���� �������� �޼ҵ�
	int pageCnt = 0;
	
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	try{		
		Class.forName(jdbc_driver);
		
		Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
		
		String sql="select count(*) as num from cosmetic";
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

public List<CosmeticDataBean> TonergetArticles(int start,int end){ // image ����� �������� �޼ҵ�
	List<CosmeticDataBean> articleList = null;

		String jdbc_driver = "com.mysql.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		try{
		Class.forName(jdbc_driver);
		
		Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
		
		String sql="select * from cosmetic where productname like '%토너%' or productname like '%에멀젼%' limit ?,?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,start-1);
		pstmt.setInt(2,end);
		rs = pstmt.executeQuery();
		
		
		if(rs.next()){
			articleList = new ArrayList<CosmeticDataBean>(end);
			do{
				CosmeticDataBean article = new CosmeticDataBean();
				article.setTitle(rs.getString("title"));
				article.setBeautytype(rs.getString("beautytype"));
				article.setAge(rs.getString("age"));
				article.setGrade(rs.getString("grade"));
				article.setContents(rs.getString("contents"));
				article.setProductname(rs.getString("productname"));
				article.setImage(rs.getString("image"));
				
				articleList.add(article);
			  }while(rs.next());
			}
		pstmt.close(); 
		conn.close();
		}
	catch(Exception e){
		System.out.println(e);
		System.out.println("�Ƕ�");}
	System.out.println("zz:"+articleList);
	return articleList;
		}



	
	
	

}
