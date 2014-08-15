package controllers;

import static play.data.Form.form;

import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.db.jpa.Transactional;
import views.html.login;

public class Login extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Usuario> loginForm = form(Usuario.class).bindFromRequest();

	@Transactional
    public static Result show() {
		if (session().get("user") != null) {
			return redirect(routes.Application.index());
		}
        return ok(login.render(loginForm));
    }
	
	@Transactional
	public static Result logout() {
		session().clear();
		return show();
	}
    
	@Transactional
	public static Result authenticate() {
		
		Usuario u = loginForm.bindFromRequest().get();
		
		String email = u.getEmail();
		String senha = u.getPass();

        if (loginForm.hasErrors() || !validate(email, senha)) {
        	flash("fail", "Email ou Senha Inválidos");
        	return badRequest(login.render(loginForm));
        } else {
        	Usuario user = (Usuario) dao.findByAttributeName(
        			"Usuario", "email", u.getEmail()).get(0);
            session().clear();
            session("user", user.getNome());
            return redirect(
                routes.Application.index()
            );
        }
    }
	
	private static boolean validate(String email, String senha) {
		List<Usuario> u = dao.findByAttributeName("Usuario", "email", email);
		if (u == null || u.isEmpty()) {
			return false;
		}
		if (!u.get(0).getPass().equals(senha)) {
			return false;
		}
		return true;
	}
}