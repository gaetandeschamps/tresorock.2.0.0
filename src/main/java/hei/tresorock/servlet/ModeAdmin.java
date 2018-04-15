package hei.tresorock.servlet;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hei.tresorock.entities.Client;
import hei.tresorock.entities.Soiree;
import hei.tresorock.managers.ListeSoiree;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Classe permettant d'accéder à la partie ADMIN du site en se connectant.
 * @author gaetandeschamps, clementgeorjon
 */
@WebServlet("/session/admin")
public class ModeAdmin extends HttpServlet {
    /**
     * Méthode doGET qui permet d'afficher le template admin, seulement si la connexion est active.
     * A savoir, un login et un mot de passe corrects et actifs entrés dans le formulaire et connexion et contrôlé par cette classe.
     * @see ConnexionServlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("adminConnecte").equals("login")) {
            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setPrefix("/session/");
            templateResolver.setSuffix(".html");

            WebContext context = new WebContext(req, resp, req.getServletContext());

            List<Soiree> soirees = ListeSoiree.getInstance().listSoiree();
            context.setVariable("soireesList", soirees);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            templateEngine.process("admin", context, resp.getWriter());
        }else {
            resp.sendRedirect("connexionEchec");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //on récupère le(s) paramètre(s) mis dans le form
        LocalDate dateSoiree=null;
        Double recetteCaisse=null;
        Double erreurCaisse=null;
        String themeSoiree=null;
        Boolean actif = true;
        String action = null;

        try{
            action = req.getParameter("action");
        }catch(Exception e){
            log(e.toString());
        }

        //s'il on ajoute soirée alors faire :
        if(action.equals("add")) {
            try {
                String dateSoireeAsString = req.getParameter("dateSoiree");
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dateSoiree = LocalDate.parse(dateSoireeAsString, dateFormat);

                recetteCaisse = Double.parseDouble(req.getParameter("recetteCaisse"));
                erreurCaisse = Double.parseDouble(req.getParameter("erreurCaisse"));
                themeSoiree = req.getParameter("themeSoiree");

            } catch (Exception e) {
                log(e.toString());
            }

            //création d'une nouvelle soirée
            Soiree newSoiree = new Soiree(null, dateSoiree, recetteCaisse, erreurCaisse, themeSoiree, actif);
            try {
                ListeSoiree.getInstance().addSoiree(newSoiree);
            } catch (IllegalArgumentException e) {
                resp.sendRedirect("error");
            }
            //redirection page préc.
            resp.sendRedirect(("/session/admin"));

        }else if(action.equals("changerAct")) {
            ListeSoiree.getInstance().updateActif();
            //redirection page préc.
            resp.sendRedirect(("/session/admin"));
        }
    }
}
