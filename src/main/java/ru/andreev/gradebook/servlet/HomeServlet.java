package ru.andreev.gradebook.servlet;

import ru.andreev.gradebook.dao.impl.GroupDao;
import ru.andreev.gradebook.dao.impl.StudentDao;
import ru.andreev.gradebook.entity.Group;
import ru.andreev.gradebook.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    private GroupDao groupDao;
    private StudentDao studentDao;

    @Override
    public void init() {
        groupDao = new GroupDao();
        studentDao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupDao.findAll();
        req.setAttribute("groups",groups);

        req.getRequestDispatcher("groups.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group group = new Group();
        group.setName(req.getParameter("name"));
        groupDao.save(group);

        resp.sendRedirect(req.getRequestURI());
    }
}
