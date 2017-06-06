package ch11.logon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogonDBBean {
	private int count;
	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance(){
		return instance;
	}
	
	public void insertMember(LogonData member){ // ȸ������ ���� �ִ� �޼ҵ�
		try{
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			String orgPass = member.getPasswd();
			
			String sql="insert into member values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,member.getName());
			pstmt.setString(2,member.getUserid());
			pstmt.setString(3,member.getPasswd());
			pstmt.setString(4,member.getBirth());
			pstmt.setString(5,member.getSex());
			pstmt.executeUpdate();
			pstmt.close(); 
			conn.close();
		}
		catch(Exception e){
			System.out.println(e);}
			}
	
	public int userCheck(String userid,String passwd){ // �α���â���� ���������� üũ 
		int x = -1;
		try{
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			
			String sql="select passwd from member where userid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()){ // �ش� ���̵� �����ϸ� ���� 
				System.out.println("��ġ�ϴ¾��̵��ֽ��ϴ�");
				String dbpasswd = rs.getString("passwd");
				System.out.println(dbpasswd);
				System.out.println(passwd);
				
				if(dbpasswd.equals(passwd))
				{
					System.out.println("��й�ȣ�����������߽��ϴ�");
					x = 1; // ��������
				}
				else
					x = 0; // ��������
			}else // �ش� ���̵� �ƿ�������
			{
				System.out.println("��ġ�ϴ¾��̵𰡾����ϴ�");
				x = -1; // ���̵� ����
			}
			pstmt.close(); 
			conn.close();
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
			}
		return x;
			}
	
	public String getName(String userid){
		String name="";
		try{
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			String sql = "select name from member where userid =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				System.out.println("���̵���ġ�ϴ� �̸��������ɴϴ�");
				name = rs.getString("name");
			}
			pstmt.close(); 
			conn.close();
			rs.close();
			}
		catch(Exception e){
			e.printStackTrace();
			}
		return name;
		
	}
	public int confirmId(String userid){ // �ߺ� ���̵� üũ �޼ҵ�
		int x = -1;
		System.out.println("�ߺ����̵� üũ�޼ҵ� ����");
		try{
			String jdbc_driver = "com.mysql.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://localhost:3307/jspdb";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			Class.forName(jdbc_driver);
			
			Connection conn=DriverManager.getConnection(jdbc_url,"jspbook","1234");
			String sql = "select userid from member where userid =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				System.out.println("�ߺ����̵�����");
				x = 1; // ���� ���̵�����
			}
			else{
				System.out.println("�ߺ����̵����������ʴ´�");
				x = -1;
			}
			pstmt.close(); 
			conn.close();
			rs.close();
			}
		catch(Exception e){
			e.printStackTrace();
			}
		return x;
	}
}
