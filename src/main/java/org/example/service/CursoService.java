package org.example.service;

import org.example.model.Validacoes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.example.connection.Connect.fazerConexao;
public class CursoService {
    Validacoes validacoes=new Validacoes();
    private Statement statement;

    public CursoService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirCurso(String nomeCurso, int professorResponsavel) {
        if (!validacoes.validarProfessor(professorResponsavel)) {
            System.out.println("ID de professor inválido!");
            return;
        }
        String sql = "INSERT INTO Cursos (NomeCurso, ProfessorResponsavel) VALUES ('" +
                nomeCurso + "', " + professorResponsavel + ")";
        try {
            statement.executeUpdate(sql);
            System.out.println("Curso '" + nomeCurso + "' foi adicionado com sucesso no banco!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarTodosCursos() {
        String sql = "SELECT Cursos.ID, Cursos.NomeCurso, Professores.Nome AS NomeProfessor " +
                "FROM Cursos " +
                "INNER JOIN Professores ON Cursos.ProfessorResponsavel = Professores.ID";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID do Curso: " + resultSet.getInt("ID") +
                        " | Nome do Curso: " + resultSet.getString("NomeCurso") +
                        " | Professor Responsável: " + resultSet.getString("NomeProfessor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterarCurso(int id, String nomeCurso) {
        String sql = "UPDATE Cursos SET NomeCurso = '" + nomeCurso +"' WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Curso com ID " + id + " foi alterado com sucesso.");
            } else {
                System.out.println("Curso com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarCurso(int id) {
        String sql = "DELETE FROM Cursos WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Curso com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Curso com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}