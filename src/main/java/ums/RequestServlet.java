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

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int softwareId = Integer.parseInt(req.getParameter("software_id"));
        String accessType = req.getParameter("access_type");
        String reason = req.getParameter("reason");
        String username = (String) req.getSession().getAttribute("username");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");

                ps = conn.prepareStatement("INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')");
                ps.setInt(1, userId);
                ps.setInt(2, softwareId);
                ps.setString(3, accessType);
                ps.setString(4, reason);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().println("Request submitted.");
    }
}
