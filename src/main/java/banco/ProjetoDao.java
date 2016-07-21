package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjetoDao {
	
	private static final String URL = "jdbc:derby:banco;create=true";

	public static void inserir(int id, String nome, String descricao) throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);		
		// Executar instrução SQL.
		String sql = "insert into projeto (id, nome, descricao) values (?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, nome);
		pstmt.setString(3, descricao);
		pstmt.executeUpdate();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
	}

	public static void alterar(int id, String nome, String descricao) throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);
		// Executar instrução SQL.
		String sql = "update projeto set nome = ?, descricao = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.setString(2, descricao);
		pstmt.setInt(3, id);
		pstmt.executeUpdate();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
	}

	public static void excluir(int id) throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);
		// Executar instrução SQL.
		String sql = "delete from projeto where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
	}

	public static ArrayList<Projeto> listar() throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);
		// Executar instrução SQL.
		String sql = "select id, nome, descricao from projeto";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//Executa a consulta. 
		ResultSet rs = pstmt.executeQuery();
		// Percorrer resultados.
		ArrayList<Projeto> projetos = new ArrayList<Projeto>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String descricao = rs.getString("descricao");

			Projeto projeto = new Projeto(id, nome, descricao);
			projetos.add(projeto);
		}
		// Fechar resultado.
		rs.close();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
		
		return projetos;
	}
}
