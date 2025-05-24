@WebServlet("/approve")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestId = Integer.parseInt(req.getParameter("requestId"));
        String action = req.getParameter("action");

        String newStatus = action.equals("Approve") ? "Approved" : "Rejected";

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE requests SET status = ? WHERE id = ?");
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().println("Request " + newStatus.toLowerCase() + ".");
    }
}
