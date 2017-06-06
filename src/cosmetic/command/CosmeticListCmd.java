package cosmetic.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import board.command.BoardCmd;
import cosmetic.model.*;

public class CosmeticListCmd implements BoardCmd {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CosmeticDAO dao = new CosmeticDAO();
		ArrayList<CosmeticDTO> list;
		
		int pageCnt = 0;
		String curPage = request.getParameter("curPage");
		
		if(curPage==null) curPage="1";
		
		list = dao.cosmeticList(curPage);
		
		request.setAttribute("cosmeticList", list);
		
		pageCnt = dao.cosmeticPageCnt();
		request.setAttribute("pageCnt", pageCnt);
		
	}

}
