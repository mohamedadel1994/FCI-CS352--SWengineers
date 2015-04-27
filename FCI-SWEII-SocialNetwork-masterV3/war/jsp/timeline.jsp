<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>timeline</title>
</head>
<body>

<form action="/rest/timeline" method="post">
feeling :<input type="text" name="feeling" /><br>
content : <input type="text" name="text" /><br>
<input type="radio" name="post" value="Public">Public<br>
<input type="radio" name="post" value="Privacy">Privacy:<input type="text" name="emails" /><br>
<input type="submit" value="post"></form>

<form action="/rest/getpost" method="post">
<input type="radio" name="timeline" value="Public">PublicTimeLine<br>
<input type="radio" name="timeline" value="Profile">ProfileTimeLine<br>
<input type="submit" value="get posts"></form>


<form action="/rest/getUserPost" method="post">
<input type="text" name="user" /><br><input type="submit" value="search for friend"></form>

<form action="/rest/searchHash" method="post">
<input type="text" name="hash" /><br><input type="submit" value="hashtag search"></form>

<a href="/social/createPage"> create page </a> <br>
<a href="/social/searchPage"> search for page </a> <br>

<h1>${it.posts}</h1>
<h1>${it.trends}</h1>
</body>
</html>