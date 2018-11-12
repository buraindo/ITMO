package ru.itmo.wp.servlet;

import ru.itmo.wp.util.Helper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] paths = uri.split("[+]");
        boolean MIMESet = false;
        if (paths.length > 0) {
            for (String u : paths) {
                String path = "C:/Users/Buraindo/Downloads/wp3/src/main/webapp/static/" + u;
                File file = new File(path);
                if (!file.isFile())
                    file = new File(getServletContext().getRealPath("/static" + uri));
                if (file.isFile()) {
                    if (!MIMESet) {
                        MIMESet = true;
                        response.setContentType(Helper.getContentTypeFromName(u));
                    }
                    OutputStream outputStream = response.getOutputStream();
                    Files.copy(file.toPath(), outputStream);
                    outputStream.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }
}
