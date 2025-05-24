package ums;

import ums.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                switch (role) {
                    case "Employee":
                        resp.sendRedirect("requestAccess.jsp");
                        break;
                    case "Manager":
                        resp.sendRedirect("pendingRequests.jsp");
                        break;
                    case "Admin":
                        resp.sendRedirect("createSoftware.jsp");
                        break;
                }
            } else {
                resp.getWriter().println("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
