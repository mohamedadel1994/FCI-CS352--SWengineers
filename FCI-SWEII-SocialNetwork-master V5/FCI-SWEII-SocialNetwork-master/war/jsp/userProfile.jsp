
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>user </titprofilele>
</head>
<body>

<form action="/rest/profileTimeline" method="post">
<input type = "hidden" name="email" value="${it.email}">
feeling :<input type="text" name="feeling" /><br>
content : <input type="text" name="text" /><br>

<input type="submit" value="post"></form>
  
  </form>
  <h1>${it.posts}</h1>
</body>
</html>