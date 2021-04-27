package UserServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.utils.PrintUtil;

import UserService.UserServices;
import UserService.UserServlceImpl;

/**
 * Servlet implementation class ShanChu
 */
@WebServlet("/ShanChu")
public class ShanChu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShanChu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				request.setCharacterEncoding("UTF-8");
				UserServices qs=new UserServlceImpl();
				String uname=request.getParameter("id");
				int idd=Integer.parseInt(uname);
				PrintUtil.write(qs.deleted(idd), response);
				
	}

}
