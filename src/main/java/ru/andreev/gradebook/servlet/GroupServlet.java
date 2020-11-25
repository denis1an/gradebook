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
import java.util.Optional;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {

    private GroupDao groupDao;
    private StudentDao studentDao;

    @Override
    public void init() throws ServletException {
        super.init();
        groupDao = new GroupDao();
        studentDao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> optionalGroupId = Optional.of(req.getParameter("id"));
            Optional<Group> optionalGroup = groupDao.findById(Integer.valueOf(optionalGroupId.orElse("-1")));
            if(optionalGroup.isPresent()){
                req.setAttribute("group", optionalGroup.get());
                req.getRequestDispatcher("group.jsp").forward(req,resp);
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(req.getParameter("firstname"));
        student.setLastName(req.getParameter("lastname"));
        Group group = new Group();
        group.setId(Integer.valueOf(req.getParameter("groupId")));

        student.setGroup(group);

        studentDao.save(student);

        resp.sendRedirect(req.getRequestURI() + "?id=" + req.getParameter("groupId"));
    }
}
