package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProprietarioDAO {
    private static Connection conn;
    
    public ProprietarioDAO() throws SQLException, ClassNotFoundException {
        conn = MyConnection.getConnection();
    }
    
    //Iniciando métodos do CRUD
    //SELECT (READ)
    public ArrayList<Usuario> listProprietario() throws SQLException {
        //Criar lista vazia
        ArrayList<Usuario> list = new ArrayList<>();
        
        //Query SQL
        String query = "SELECT * FROM usuarios";
        
        //Preparando a query para lançar no banco de dados
        PreparedStatement prep = conn.prepareStatement(query);
        
        //Recebendo o resultado da query na variável result
        //da classe ResultSet
        ResultSet result = prep.executeQuery();
        
        //Enqunto houverem resultados, executa a operação
        while(result.next()) {
            //Objeto vazio de Proprietario
            Usuario proprietario = new Usuario();
            
            //Inserindo atributos neste objeto por meio dos setters
            proprietario.setIdUsuario(result.getInt("id_usuario"));
            proprietario.setNome(result.getString("nome"));
            proprietario.setSobrenome(result.getString("sobrenome"));
            proprietario.setEmail(result.getString("email"));
            proprietario.setCelular(result.getString("celular"));
            proprietario.setSenha(result.getString("senha"));
            proprietario.setGenero(result.getString("genero"));
            
            //Adicionando objeto preenchido na lista
            list.add(proprietario);
        }
        
        //Retornando a lista com todos os registros do BD
        return list;
    }
    
    
    //Insert (CREATE)
    public void insertProprietario(Usuario pro) throws SQLException {
        //Criar a query para inserção
        String query = "INSERT INTO usuarios(nome, sobrenome, email, celular, senha, genero)"
                + "VALUES(?,?,?,?,?,?)";
        
        //Preparando a query para lançar no BD
        PreparedStatement prep = conn.prepareStatement(query);
        
        //Pegar os atributos recebidos do objeto Proprietario
        //e lançar na query
        prep.setString(1, pro.getNome());
        prep.setString(2, pro.getSobrenome());
        prep.setString(3, pro.getEmail());
        prep.setString(4, pro.getCelular());
        prep.setString(5, pro.getSenha());
        prep.setString(6, pro.getGenero());
        
        //Lnaçar a query pronta para o BD
        prep.execute();
        prep.close();
    }
    
    
    //DELETE
    public void deleteProprietario(int i) throws SQLException {
        String query = "DELETE FROM usuarios "
                + "WHERE id_usuario = " + i;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.execute();
        prep.close();
    }
    
    
    //LIST BY ID (Selecionar apenas um registro)
    public Usuario listById(int id) throws SQLException {
        Usuario p = new Usuario();
        
        String sql = "SELECT * FROM usuarios "
                + "WHERE id_usuario = " + id;
        
        PreparedStatement prep = conn.prepareStatement(sql);
        ResultSet result = prep.executeQuery();
        
        if(result.next()) {
            p.setIdUsuario(result.getInt("id_usuario"));
            p.setNome(result.getString("nome"));
            p.setSobrenome(result.getString("sobrenome"));
            p.setEmail(result.getString("email"));
            p.setCelular(result.getString("celular"));
            p.setSenha(result.getString("senha"));
            p.setGenero(result.getString("genero"));
        }
        
        return p;
    }
    
     //LIST BY NAME (Selecionar apenas um registro por meio do nome)
    public Usuario listByName(String nome) throws SQLException {
        Usuario p = new Usuario();
        
        String sql = "SELECT * FROM usuarios "
                + "WHERE id_usuario = ?";
        
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.setString(1, nome);
        
        ResultSet result = prep.executeQuery();
        
        if(result.next()) {
            p.setIdUsuario(result.getInt("id_usuario"));
            p.setNome(result.getString("nome"));
            p.setSobrenome(result.getString("sobrenome"));
            p.setEmail(result.getString("email"));
            p.setCelular(result.getString("celular"));
            p.setSenha(result.getString("senha"));
            p.setGenero(result.getString("genero"));
        }
        
        return p;
    }
    
    
    //UPDATE
    public void updateProprietario(Usuario p) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?,"
                + "cpf = ?, logradouro = ?, numero = ?,"
                + "cep = ?, cidade = ? "
                + "WHERE id_usuario = ?";
        
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.setString(1, p.getNome());
        prep.setString(2, p.getSobrenome());
        prep.setString(3, p.getEmail());
        prep.setString(4, p.getCelular());
        prep.setString(5, p.getGenero());
        prep.setString(6, p.getSenha());
        prep.setInt(7, p.getIdUsuario());
        
        prep.execute();
        prep.close();
    }
}
