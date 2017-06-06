package ch11.logon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CosmeticDBBean {
	private static CosmeticDBBean instance = new CosmeticDBBean();
	
	public static CosmeticDBBean getInstace(){
		return instance;
	}
	private CosmeticDBBean(){}
	
	public List<CosmeticDataBean> getArticles(int start,int end){ // image ����� �������� �޼ҵ�
		    List<CosmeticDataBean> articleList = null;
	
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;

			try{
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			
			String sql="select * from cosmetic limit ?,?";
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
	
	public int getArticleCount(){ // �����Ϳ������ ��ü ��� ���� �������� �޼ҵ�
		int x = 0;
		try{
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			
			String sql="select count(*) from cosmetic";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x = rs.getInt(1);
			}
			pstmt.close(); 
			conn.close();
			}
		catch(Exception e){
			System.out.println(e);}
		System.out.println(x);
			return x;			
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
			
			String sql="select * from cosmetic where productname like '%토너%' or productname like '%에멀전%' limit ?,?";
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
