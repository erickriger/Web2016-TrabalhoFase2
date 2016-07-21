package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO = Data Access Object.
 */
public class UsuarioDao {

	private static final String URL = "jdbc:derby:banco;create=true";

	public static void inserir(int id, String nome, String email, String login, String senha) throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);		
		// Executar instrução SQL.
		String sql = "insert into usuario (id, nome, email, login, senha) values (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, nome);
		pstmt.setString(3, email);
		pstmt.setString(4, login);
		pstmt.setString(5, senha);
		pstmt.executeUpdate();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
	}

	public static void alterar(int id, String nome, String email, String login, String senha) throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);
		// Executar instrução SQL.
		String sql = "update usuario set nome = ?, login = ?, senha = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.setString(2, email);
		pstmt.setString(3, login);
		pstmt.setString(4, senha);
		pstmt.setInt(5, id);
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
		String sql = "delete from usuario where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
	}

	public static ArrayList<Usuario> listar() throws SQLException {
		// Abrir uma conexão com o banco de dados.
		Connection conn = DriverManager.getConnection(URL);
//		// Cria tabela
//				String createTable = "create table usuario (id int primary key, nome varchar(50), login varchar(30), senha varchar(30))";
//				Statement stmt = conn.createStatement();
//				stmt.executeUpdate(createTable);
//				stmt.close();
		// Executar instrução SQL.
		String sql = "select id, nome, login, senha from usuario";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//Executa a consulta. 
		ResultSet rs = pstmt.executeQuery();
		// Percorrer resultados.
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String login = rs.getString("login");
			String senha = rs.getString("senha");

			Usuario usuario = new Usuario(id, nome, email, login, senha);
			usuarios.add(usuario);
		}
		// Fechar resultado.
		rs.close();
		// Fechar sentença.
		pstmt.close();
		// Fechar conexão.
		conn.close();
		
		return usuarios;
	}
}
