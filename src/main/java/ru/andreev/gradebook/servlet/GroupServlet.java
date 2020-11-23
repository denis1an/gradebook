package ru.andreev.gradebook.servlet;

import ru.andreev.gradebook.dao.impl.GroupDao;
import ru.andreev.gradebook.entity.Group;

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

    @Override
    public void init() throws ServletException {
        super.init();
        groupDao = new GroupDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> optionalGroupId = Optional.ofNullable(req.getParameter("id"));
        if(optionalGroupId.isPresent()){
            Optional<Group> optionalGroup = groupDao.findById(Integer.valueOf(optionalGroupId.get()));
            optionalGroup.ifPresent(group -> req.setAttribute("group", group));
            req.getRequestDispatcher("group.jsp").forward(req,resp);
        } else {
            //todo
        }

    }
}
