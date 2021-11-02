package mk.finki.ukim.wpaud.web.servlet;

import mk.finki.ukim.wpaud.model.Category;
import mk.finki.ukim.wpaud.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//ctrl shift f9 za recompile na tekovna klasa
@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    public final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>Info about our request</h2>");
        writer.format("IP Address:%s </br> Browser: %s", ipAddress, clientAgent);
        writer.println("<h2>Categories</h2>");
        writer.println("<ul>");
        categoryService.listCategories()
                .forEach(element -> writer.format("<li>%s (%s)</li>", element.getName(), element.getDescription()));
        writer.println("</ul>");

        writer.println("<h3>Add New Category</h3>");
        writer.println("<form method='post' action='/servlet/category'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>" +
                "<label for='desc'>Description:</label><input id='desc' type='text' name='description'/>" +
                "<input type='submit' value='Submit'/>" +
                "</form>");
        writer.println("</body>");
        writer.println("<html>");

        writer.println("</body>");
        writer.println("</html>");


        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getParameter("name");
        String catDesc = req.getParameter("description");
        categoryService.create(catName, catDesc);
        resp.sendRedirect("/servlet/category");
    }
}
