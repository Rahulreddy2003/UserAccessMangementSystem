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

@WebServlet("/createSoftware")
public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String accessLevel = req.getParameter("access_levels");

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, accessLevel);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().println("Software created successfully.");
    }
}
