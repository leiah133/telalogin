package br.edu.unicesumar.core.DAO;

import br.edu.unicesumar.core.entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.edu.unicesumar.core.conexao.Conexao;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class UsuarioDAO {
    public void cadastroUsuario(Usuario usuario){
        String sql = "insert into usuario(nome, login, senha, email) values (?,?,?,?)";
        PreparedStatement ps;
        boolean cadastrado;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.execute();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void verificarLogin(Usuario usuario) {
        String query = "SELECT id, nome, login, senha, email FROM usuario WHERE login = ? AND senha = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(query)) {
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Acesso autorizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Acesso negado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                ps.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao verificar login.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
    

