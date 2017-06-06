package board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDTO;

public class BoardReply2FormCmd implements BoardCmd {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String inputNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> writing = new ArrayList<BoardDTO>();
		
		writing = dao.boardReply2Form(inputNum);
		request.setAttribute("boardReply2Form", writing);

	}

}
