package org.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static org.example.connection.Connect.fazerConexao;

public class AlunoService {
    private Statement statement;

    public AlunoService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirAluno(String nome, LocalDate dataNascimento, String endereco) {
        String sql = "INSERT INTO alunos (Nome, DataNascimento, Endereco) VALUES ('" +
                nome + "', '" + dataNascimento + "', '" + endereco + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Aluno '" + nome + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarAlunos() {
        String sql = "SELECT * FROM Alunos";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID") +
                        " | Nome: " + resultSet.getString("Nome") +
                        " | Data de Nascimento: " + resultSet.getString("DataNascimento") +
                        " | Endereço: " + resultSet.getString("Endereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarAluno(int id, String endereco) {
        String sql = "UPDATE Alunos SET Endereco = '" + endereco + "' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Aluno com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Aluno com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarAluno(int id) {
        String sql = "DELETE FROM Alunos WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Aluno com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Aluno com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
