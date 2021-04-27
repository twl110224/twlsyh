package UserServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utils.PrintUtil;

import Entity.user;
import UserService.UserServices;
import UserService.UserServlceImpl;

/**
 * Servlet implementation class ZengJia
 */
@WebServlet("/ZengJia")
public class ZengJia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZengJia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserServices qs=new UserServlceImpl();
		/*
		 * String idd =request.getParameter("id"); Integer id=Integer.valueOf(idd);
		 */
       String username=request.getParameter("username");
       
       String password=request.getParameter("password");
       String loginname=request.getParameter("loginname");
       String phone=request.getParameter("phone");
       String agee=request.getParameter("age");
       int age=Integer.valueOf(agee);
       user ys=new user();
      // ys.setId(id);
       ys.setUsername(username);
       ys.setPhone(phone);
       ys.setPassword(password);
       ys.setLoginname(loginname);
       ys.setAge(age);
       int num=-1;
       if (qs.insertd(ys)){
    	   num=1;
    	   System.out.println("dd"+num);
	}
      
       PrintUtil.write(num, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
				
	}

}
