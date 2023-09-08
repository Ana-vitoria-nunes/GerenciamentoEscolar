package org.example.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.example.connection.Connect.fazerConexao;

public class EscolaService {
    private Connection connection;
    private Statement statement;

    public EscolaService() {
        try {
            connection = fazerConexao();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarAlunosPorCurso(int id) {
        String sql = "SELECT Alunos.Nome " +
                "FROM Alunos " +
                "INNER JOIN Matriculas ON Alunos.ID = Matriculas.IDAluno " +
                "INNER JOIN Cursos ON Matriculas.IDCurso = Cursos.ID " +
                "WHERE Cursos.ID = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\nAlunos  matriculados nesse curso.");
            while (resultSet.next()) {
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarCursosPorProfessor(int id) {
        String sql = "SELECT Cursos.NomeCurso " +
                "FROM Cursos " +
                "INNER JOIN Professores ON Cursos.ProfessorResponsavel = Professores.ID " +
                "WHERE Professores.ID = '" + id + "'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\nCursos ministrado por esse professor.");
            while (resultSet.next()) {
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosSemMatricula() {
        String sql = "SELECT Alunos.Nome " +
                "FROM Alunos " +
                "LEFT JOIN Matriculas ON Alunos.ID = Matriculas.IDAluno " +
                "WHERE Matriculas.IDCurso IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\nAlunos que não está matriculado em nenhum curso.");
            while (resultSet.next()) {
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarCursosSemAlunos() {
        String sql = "SELECT Cursos.NomeCurso " +
                "FROM Cursos " +
                "LEFT JOIN Matriculas ON Cursos.ID = Matriculas.IDCurso " +
                "WHERE Matriculas.IDAluno IS NULL";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\nCursos sem alunos matriculados .");
            while (resultSet.next()) {
                System.out.println("Nome do Curso: " + resultSet.getString("NomeCurso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarAlunosComMaisDeUmCurso() {
        String sql = "SELECT Alunos.Nome " +
                "FROM Alunos " +
                "INNER JOIN Matriculas ON Alunos.ID = Matriculas.IDAluno " +
                "GROUP BY Alunos.ID, Alunos.Nome " +
                "HAVING COUNT(Matriculas.IDCurso) > 1";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\nAlunos  matriculados em mais de um curso.");
            while (resultSet.next()) {
                System.out.println("Nome do Aluno: " + resultSet.getString("Nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
