package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.simsim.entities.User;

import database.DbAdapter;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Authenticate");
		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		try {
			User user = (User) in.readObject();
			in.close();
			User fetchedUser = DbAdapter.readUser(user);
			String returnInfo;
			if (fetchedUser != null && fetchedUser.getPassword().equals(user.getPassword()))
					returnInfo = "success";
			else 
					returnInfo = "failure";
			ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
			out.writeObject(returnInfo);
			out.flush();
			out.close();
			System.out.println(returnInfo);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}