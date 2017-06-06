package board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import board.model.*;
import javax.servlet.http.HttpServletResponse;

public class BoardReply2Cmd implements BoardCmd {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// ´ñ±Û³Ö±â
		String num = request.getParameter("num");
		String userid = request.getParameter("userid");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.boardReply2(num,content,userid);
	}

}
