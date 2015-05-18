
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Home Page</title>
</head>
<body>

<p> Welcome  ${it.name} </p>
<form action="/rest/notification" method="post"><input type="submit" value="Get notification"></form>
<a href="/social/SearchForFriend">AddFriend</a> <br>
<a href="/social/Accept">AcceptFriend</a> <br>
<a href="/social/oneToOne">Send Message </a> <br>
<a href="/social/conversation"> send to group </a> <br>
<form action="/rest/ShowMessage" method="post"><input type="submit" value="Show New Msgs"></form>
<form action="/rest/trends" method="post"><input type="submit" value="timeline"></form>
<form action="/rest/mypages" method="post"><input type="submit" value="MY PAGES"></form>
<a href="/social/Signout">Sign out</a> <br>
</body>
</html>