<%-- 
    Document   : logout
    Created on : Jun 25, 2016, 5:15:40 PM
    Author     : Rashmi Tiwari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="bootstrap/js/jquery.js"></script>

        <script >
            <%@include file="bootstrap/js/bootstrap.js" %>
        </script>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
        
    </head>
    <body>
         <%
             System.out.print("logout");
        session.invalidate();
         response.sendRedirect("a.jsp");
                //   RequestDispatcher rd=request.getRequestDispatcher("./a.jsp");//code number display
                //rd.forward(request, response);
      
        %>
        
    </body>
    
</html>
