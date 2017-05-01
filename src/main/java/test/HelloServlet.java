package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangtonghe
 * @date 2017/5/1 17:54
 */
@WebServlet(value = "/test1")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body><h3>"+name);
        writer.write("</h3></body></html>");
        writer.close();
    }
}
