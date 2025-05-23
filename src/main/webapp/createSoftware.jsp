
<form action="createSoftware" method="post">
    Software Name: <input type="text" name="name"><br>
    Description: <textarea name="description"></textarea><br>
    Access Levels: 
    <select name="access_levels">
        <option>Read</option>
        <option>Write</option>
        <option>Admin</option>
    </select><br>
    <input type="submit" value="Create Software">
</form>
