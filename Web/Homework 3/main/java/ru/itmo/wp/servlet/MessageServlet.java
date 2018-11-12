package ru.itmo.wp.servlet;

import com.google.gson.Gson;
import javafx.util.Pair;
import ru.itmo.wp.util.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageServlet extends HttpServlet {

    private List<Message> allMessages = new ArrayList<>();

    private void auth (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String name;
        Object sessionName = session.getAttribute("user");
        String submitName = request.getParameter("user");
        if (sessionName == null && submitName == null) name = "";
        else if (submitName != null) {
            name = submitName;
        } else name = (String) sessionName;
        session.setAttribute("user", name);
        String json = new Gson().toJson(name);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    private void add (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String text = request.getParameter("text");
        String name = (String) session.getAttribute("user");
        allMessages.add(new Message(name, text));
    }

    private void findAll (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json = new Gson().toJson(allMessages);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        switch (request.getRequestURI()) {
            case "/message/auth":
                auth(request, response);
                break;
            case "/message/add":
                add(request, response);
                break;
            case "/message/findAll":
                findAll(request, response);
                break;
        }
    }

}
