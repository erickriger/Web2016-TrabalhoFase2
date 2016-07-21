package banco;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class CadastroUsuarioController extends HttpServlet {
	private String valor(HttpServletRequest req, String param, String padrao) {
		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}

	private int toInt(HttpServletRequest req, String param, String padrao) {
		return Integer.parseInt(valor(req, param, padrao));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg;
			int id = toInt(req, "id", "0");
			String nome = valor(req, "nome", "");
			String email = valor(req, "email", "");
			String login = valor(req, "login", "");
			String senha = valor(req, "senha", "");

			UsuarioDao.inserir(id, nome, email, login, senha);
			msg = "Inclusão realizada com sucesso.";

			req.setAttribute("msg", msg);
//			req.setAttribute("usuarios", UsuarioDao.listar());

			req.getRequestDispatcher("UsuarioView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}
