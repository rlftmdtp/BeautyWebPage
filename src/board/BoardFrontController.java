package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.command.*;
import cosmetic.command.*;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bbs")
public class BoardFrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmdURI = requestURI.substring(contextPath.length());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		BoardCmd cmd = null;
		String viewPage = null;
		
		if(cmdURI.equals("/boardList.bbs")){
			cmd = new BoardListCmd();
			cmd.execute(request,response);
			viewPage ="boardList.jsp";
		}
		
		if(cmdURI.equals("/boardWriteForm.bbs")){
			viewPage ="boardWrite.jsp";
		}
		

		if(cmdURI.equals("/boardWrite.bbs")){
			cmd = new BoardWriteCmd();
			cmd.execute(request,response);
			viewPage ="boardList.bbs";
		}
		
		if(cmdURI.equals("/boardRead.bbs")){
			cmd = new BoardReadCmd();
			cmd.execute(request, response);
			cmd = new BoardReply2FormCmd();
			cmd.execute(request, response);
			viewPage = "boardRead.jsp";
		}
		
				
				// �� ���� ��й�ȣ Ȯ�� ó��
				if(cmdURI.equals("/boardUpdateCheck.bbs")){
					cmd = new BoardUpdateCheckCmd();
					cmd.execute(request, response);
					
					BoardUpdateCheckCmd checkCmd = (BoardUpdateCheckCmd) cmd;
					if (checkCmd.password_check){
						viewPage = "boardUpdateForm.bbs";				
					} 
				}
				
				// �� ���� ȭ�� ����
				if(cmdURI.equals("/boardUpdateForm.bbs")){
					cmd = new BoardUpdateFormCmd();
					cmd.execute(request, response);
					viewPage = "boardUpdateForm.jsp";
				}
				
				// �� ���� ó��
				if(cmdURI.equals("/boardUpdate.bbs")){
					cmd = new BoardUpdateCmd();
					cmd.execute(request, response);
					viewPage = "boardList.bbs";
				}
		
				
				// �� ���� ��й�ȣ Ȯ�� ó��
				if(cmdURI.equals("/boardDeleteCheck.bbs")){
					cmd = new BoardDeleteCheckCmd();
					cmd.execute(request, response);
					
					BoardDeleteCheckCmd checkCmd = (BoardDeleteCheckCmd) cmd;
					if (checkCmd.password_check && checkCmd.reply_check){
						viewPage = "boardDelete.bbs";				
					} else{
						out.println("<script language=\"javascript\">");
						out.println("alert('����� ������� �� �������Ұ����մϴ�')");
						out.println("history.back();");
						out.println("</script>");
						out.flush();
					}
				}
				
				// �� ���� ó��
				if(cmdURI.equals("/boardDelete.bbs")){
					cmd = new BoardDeleteCmd();
					cmd.execute(request, response);
					viewPage = "boardList.bbs";
				}
				
				// �� �˻� ó��
				if(cmdURI.equals("/boardSearch.bbs")){
					cmd = new BoardSearchCmd();
					cmd.execute(request, response);
					viewPage = "boardSearchList.jsp";
				}
				
				// ��� �ۼ� ȭ�� ����
				if(cmdURI.equals("/boardReplyForm.bbs")){
					cmd = new BoardReplyFormCmd();
					cmd.execute(request, response);
					viewPage = "boardReply.jsp";
				}
				
				// ��� �ۼ� ó��
				if(cmdURI.equals("/boardReply.bbs")){
					cmd = new BoardReplyCmd();
					cmd.execute(request, response);
					viewPage = "boardList.bbs";
				}
				
				if(cmdURI.equals("/boardReply2.bbs")){
					cmd = new BoardReply2Cmd();
					cmd.execute(request, response);
					viewPage = "boardRead.bbs";
				}
				
				if(cmdURI.equals("/cosmetic.bbs")){
					cmd = new CosmeticListCmd();
					cmd.execute(request, response);
					viewPage = "cosmetic.jsp";
				}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}

}
