<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>New Messages</title>
</head>
<body>

<h1>${it.MSGs}</h1>
<form action="/rest/Conversation" method="post">
Message : <input type="text" name="MSG" /> <br>
User Email : <input type="text" name="Email" /> <br>
<input type="submit" value="reply">
</form>
<a href="/social/Signout">Sign out</a> <br>

</body>
</html>