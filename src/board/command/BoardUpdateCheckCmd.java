package board.command;

import javax.servlet.http.*;

import board.model.*;

public class BoardUpdateCheckCmd implements BoardCmd{
	
	public boolean password_check;

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");

		
		request.setAttribute("num", inputNum);
		
		BoardDAO dao = new BoardDAO();
		password_check = dao.boardPasswordCheck(inputNum);
		
	}
}
