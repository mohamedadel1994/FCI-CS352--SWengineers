<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Send Message</title>
</head>
<body>
  <form action="/rest/Conversation" method="post">
   Message : <input type="text" name="MSG" /> <br>
   User Email : <input type="text" name="Email" /> <br>
   convName : <input type="text" name="convName" /> <br>
  <input type="submit" value="Send Message"><br>
  <a href="/social/Signout/">Sign out</a> <br>
  </form>
</body>
</html>