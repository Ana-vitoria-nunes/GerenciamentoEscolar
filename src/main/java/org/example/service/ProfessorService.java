package org.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.example.connection.Connect.fazerConexao;

public class ProfessorService {
    private Statement statement;

    public ProfessorService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirProfessor(String nome, String disciplina) {
        String sql = "INSERT INTO Professores (Nome, Disciplina) VALUES ('" +
                nome + "', '" + disciplina + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Professor '" + nome + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarTodosProfessores() {
        String sql = "SELECT * FROM Professores";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID") +
                        " | Nome: " + resultSet.getString("Nome") +
                        " | Disciplina: " + resultSet.getString("Disciplina"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarProfessor(int id, String disciplina) {
        String sql = "UPDATE Professores SET Disciplina = '" + disciplina +
                "' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Professor com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Professor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarProfessor(int id) {
        String sql = "DELETE FROM Professores WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Professor com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Professor com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

