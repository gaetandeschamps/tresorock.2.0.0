package hei.tresorock.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

/**
 * Classe permettant d'afficher la template Connexion.
 * Cette page permet de se connecter à la partie (et donc à toutes les fonctionnalités d'ADMIN du site via un mot de passe et un login.
 * @author gaetandeschamps, clementgeorjon
 */
@WebServlet("/connexionAdmin")
public class ConnexionServlet extends HttpServlet {
    /**
     * méthode doGET qui afficher le templates en allant le chercher dans le bon répertoire.
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

        templateEngine.process("connexion", context, resp.getWriter());

    }

    /**
     * Méthode doPost qui récupère les entrées de l'utilisateur et qui les compares avec les login/mot de passe autorisés.
     * @see hei.tresorock.filters.LoginFilter
     * @param req - permet de récuperer les données entrées dans le formulaire de connexion
     * @param resp - permet d'envoyer toutes les données pour se connecter.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login.equals("admin") && password.equals("admin")){
            req.getSession().setAttribute("adminConnecte","login");
        }else{

        }
        resp.sendRedirect("session/admin");
    }
}
