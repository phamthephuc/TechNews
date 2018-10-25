package library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLibrary {
	public static boolean checkLogin(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null )
		{
			try {
				response.sendRedirect(request.getContextPath()+"/admin/login");
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
