package UserServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utils.PrintUtil;

import Entity.menu;
import UserService.UserServices;
import UserService.UserServlceImpl;

/**
 * Servlet implementation class QxianInsert
 */
@WebServlet("/QxianInsert")
public class QxianInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QxianInsert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserServices qs = new UserServlceImpl();
		String menuname = request.getParameter("menuname");
		String button = request.getParameter("button");
		String faid = request.getParameter("fatherid");
		int fatherid = Integer.valueOf(faid);
		String type2 = request.getParameter("type");
		int type = Integer.valueOf(type2);

		String url = request.getParameter("url");
		menu g = new menu();
		g.setMenuname(menuname);
		g.setButton(button);
		g.setType(type);
		g.setFatherid(fatherid);
		g.setUrl(url);
		int num = -1;
		if (qs.insertQx(g)) {
			num = 1;
		}

		PrintUtil.write(num, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
