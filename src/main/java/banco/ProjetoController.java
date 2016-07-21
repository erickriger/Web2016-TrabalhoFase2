package banco;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/projetos")
public class ProjetoController extends HttpServlet {
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
			String op = valor(req, "operacao", "");
			int id = toInt(req, "id", "0");
			String nome = valor(req, "nome", "");
			String descricao = valor(req, "descricao", "");

			if (op.equals("incluir")) {
				ProjetoDao.inserir(id, nome, descricao);
				msg = "Inclusão realizada com sucesso.";
			} else if (op.equals("alterar")) {
				ProjetoDao.alterar(id, nome, descricao);
				msg = "Alteração realizada com sucesso.";
			} else if (op.equals("excluir")) {
				ProjetoDao.excluir(id);
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			req.setAttribute("msg", msg);
			req.setAttribute("projetos", ProjetoDao.listar());
			
			req.getRequestDispatcher("ProjetoView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}
