package mk.ukim.finki.wpaud.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hello", urlPatterns = "/hello")
//bean koj sto e zadolzen da preceka http baranje i so nego da se spravi soodvetno
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name"); //zimam parametar user
        String surname = req.getParameter("surname");

        PrintWriter writer = resp.getWriter(); // na responseot mu barame Writer
        writer.format("<html><head><body><h1>HI %s %s</h1></body></head></html>", name, surname);//html sintaksa
        writer.flush();

    }
}
