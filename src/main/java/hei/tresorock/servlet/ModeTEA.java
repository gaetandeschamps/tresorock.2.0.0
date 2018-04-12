package hei.tresorock.servlet;

import hei.tresorock.entities.Client;
import hei.tresorock.entities.Participe;
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
import java.time.format.DateTimeParseException;

/**
 * Classe permettant d'accéder à la partie TEA du site.
 * Ne nécessite aucune session privée au préalable.
 * @author gaetandeschamps, clementgeorjon
 */
@WebServlet("/TEA")
public class ModeTEA extends HttpServlet {
    /**
     * Méthode doGET qui permet d'afficher le template tea, lorsque le bon URL est actif.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        WebContext context = new WebContext(req,resp,req.getServletContext());

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("tea", context, resp.getWriter());

        //accès uniquement si un évènement a été créé côté ADMIN

            //IMPLEMENTER METHODE  TOP

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //on récupère le(s) paramètre(s) mis dans le form
        String nomClient=null;
        String prenomClient=null;
        String ecoleClient=null;
        Boolean cotisantClient=null;
        String statutClient=null;
        Double prixPaye=null;

        // paramètres que l'on va retrouver/définir par la suite (idsoiree = id de la soiree active ; idclient =
        // id du client générée par la BDD)
        Integer idClient=null;
        Integer idSoiree=null;

        try {
            nomClient = req.getParameter("nomClient");
            prenomClient = req.getParameter("prenomClient");
            ecoleClient = req.getParameter("ecoleClient");
            cotisantClient = Boolean.parseBoolean(req.getParameter("cotisantClient"));
            statutClient = req.getParameter("statutClient");
            prixPaye = Double.parseDouble(req.getParameter("prixPaye"));

        }catch(Exception e){
            log(e.toString());
        }

        //création d'un nouveau client
        Client newClient = new Client(null, nomClient, prenomClient, ecoleClient, cotisantClient, statutClient);
        try {
            ListeSoiree.getInstance().addClient(newClient);
        }catch (IllegalArgumentException e){
            resp.sendRedirect("error");
            return;
        }

        //récupération de l'IdClient du client concerné et de l'IdSoiree de la soirée en cours
        try {
            idSoiree = ListeSoiree.getInstance().getSoireeEnCoursId();
            idClient = ListeSoiree.getInstance().getClientId(newClient);
        }catch (IllegalArgumentException e){
            resp.sendRedirect("error");
        }

        Participe newParticipe = new Participe(idSoiree,idClient, prixPaye);
        try {
            //faire cette méthode à la toute fin, après avoir récupéré le IdSoiree et le IdClient correspondants
            ListeSoiree.getInstance().addParticipe(newParticipe);
        }catch (IllegalArgumentException e){
            resp.sendRedirect("error");
            return;
        }
        //redirection page préc.
        resp.sendRedirect(("TEA"));


    }
}
