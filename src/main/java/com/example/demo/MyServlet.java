package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    protected void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        CalcOperations calc = new CalcOperations();

        PrintWriter writer = response.getWriter();
        try {
            String operation = request.getParameter("operation");
            double one = Double.valueOf(request.getParameter("one"));
            double two = Double.valueOf(request.getParameter("two"));

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>");
            writer.println("second servlet");
            writer.println("</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.print("<p1>");

            switch (operation) {
                case "add": {
                    writer.print(one + " + " + two + " = " + calc.add(one, two));
                    break;
                }
                case "sub": {
                    writer.print(one + " - " + two + " = " + calc.sub(one, two));
                    break;
                }
                case "mul": {
                    writer.print(one + " * " + two + " = " + calc.mul(one, two));
                    break;
                }
                case "div": {
                    if (two == 0) {
                        writer.print("div by 0");
                    } else {
                        writer.print(one + " / " + two + " = " + calc.div(one, two));
                        break;
                    }
                }
                default: {

                    throw new Exception();
                }
            }
            writer.println("</p1>");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        } finally {
            writer.println("</body");
            writer.println("</html>");
            writer.close();
        }
    }
}

