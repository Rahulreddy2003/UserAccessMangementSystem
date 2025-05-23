<form action="requestAccess" method="post">
    Software: <select name="software_id"><!-- populate dynamically --></select><br>
    Access Type: <select name="access_type">
        <option>Read</option><option>Write</option><option>Admin</option>
    </select><br>
    Reason: <textarea name="reason"></textarea><br>
    <input type="submit" value="Request Access">
</form>
