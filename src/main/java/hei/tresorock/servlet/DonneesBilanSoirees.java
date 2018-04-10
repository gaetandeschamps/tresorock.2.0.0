package hei.tresorock.servlet;


import com.google.gson.Gson;
import hei.tresorock.entities.BilanSoiree;
import hei.tresorock.managers.ListeSoiree;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session/BilanSoirees")
public class DonneesBilanSoirees extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Gson gsonTool = new Gson();
        int idSoiree = ListeSoiree.getInstance().getSoireeEnCoursId();
        BilanSoiree bilanSoiree = ListeSoiree.getInstance().getBilanSoiree(idSoiree);

        String bilanSoireeJson = gsonTool.toJson(bilanSoiree);

        PrintWriter writerResponse = resp.getWriter();
        writerResponse.append(bilanSoireeJson);
    }

}
