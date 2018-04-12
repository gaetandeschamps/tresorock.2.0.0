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

/**
 * Classe permettant d'afficher la template Accueil.
 * Cette page est lancée lorsque l'on se connecte au site (c'est la page d'accueil).
 * @author gaetandeschamps, clementgeorjon
 */
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");

        WebContext context = new WebContext(req,resp,req.getServletContext());

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.process("accueil", context, resp.getWriter());


    }
}