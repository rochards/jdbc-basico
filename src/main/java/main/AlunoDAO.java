package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> getAll() {

        List<Aluno> aluno = new ArrayList<>();
        // usando o try dessa forma, fica implicito que a conexao vai ser fechada
        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM aluno";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String estado = resultSet.getString("estado");

                aluno.add(new Aluno(id, nome, idade, estado));
            }

        } catch (SQLException throwables) {
            System.out.println("Erro ao buscar alunos!");
            throwables.printStackTrace();
        }

        return aluno;
    }

    public Aluno getById(int id) {

        Aluno aluno = null;

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM aluno WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String estado = resultSet.getString("estado");

                aluno = new Aluno(id, nome, idade, estado);
            }

        } catch (SQLException throwables) {
            System.out.println("Erro ao buscar aluno pelo id " + id);
            throwables.printStackTrace();
        }

        return aluno;
    }

    public void create(Aluno aluno) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setString(3, aluno.getEstado());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Insercao BEM SUCEDIDA!. " + rowsAffected + " linhas foram afetadas.");

        } catch (SQLException throwables) {
            System.out.println("Erro ao cadastrar novo aluno!");
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM aluno WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Exclusao BEM SUCEDIDA!. " + rowsAffected + " linhas foram afetadas.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Aluno aluno) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.setString(3, aluno.getEstado());
            statement.setInt(4, id);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Atualizacao BEM SUCEDIDA!. " + rowsAffected + " linhas foram afetadas.");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
