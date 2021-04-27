package UserServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Entity.menu;
import UserService.UserServices;
import UserService.UserServlceImpl;
@WebServlet("/CaiBtnServlet")
public class CaiBtnServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CaiBtnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		int id=Integer.parseInt(request.getParameter("id"));		
		//int typeid=Integer.parseInt(request.getParameter("typeid"));				
		UserServices qs=new UserServlceImpl();
		List<menu>gg=qs.fuid(id);
		HttpSession session =request.getSession();
		   session.setAttribute("list1", gg);
		   response.sendRedirect(request.getParameter("menu"));	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
