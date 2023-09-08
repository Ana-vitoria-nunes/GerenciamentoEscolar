package org.example.service;

import org.example.model.Validacoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static org.example.connection.Connect.fazerConexao;

public class MatriculaService {
    Validacoes validacoes=new Validacoes();
    private Statement statement;

    public MatriculaService() {
        try {
            statement = fazerConexao().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirMatricula(int idAluno, int idCurso, LocalDate dataMatricula) {
        if (!validacoes.validarAluno(idAluno)) {
            System.out.println("ID de aluno inválido!");
            return;
        }
        if (!validacoes.validarCurso(idCurso)) {
            System.out.println("ID de curso inválido!");
            return;
        }
        if (validacoes.ValidarAlunoMatriculaCurso(idAluno,idCurso)) {
                System.out.println("Esse aluno ja esta matriculado nesse curso");
                return;
        }
        String sql = "INSERT INTO Matriculas (IDAluno, IDCurso, DataMatricula) VALUES (" +
                idAluno + ", " + idCurso + ", '" + dataMatricula + "')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Matrícula do Aluno ID " + idAluno + " no Curso ID " + idCurso + " foi adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void consultarTodasMatriculas() {
        String sql = "SELECT Matriculas.ID, Alunos.Nome AS NomeAluno, Cursos.NomeCurso AS NomeCurso, Matriculas.DataMatricula " +
                "FROM Matriculas " +
                "INNER JOIN Alunos ON Matriculas.IDAluno = Alunos.ID " +
                "INNER JOIN Cursos ON Matriculas.IDCurso = Cursos.ID";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("ID da Matrícula: " + resultSet.getInt("ID") +
                        " | Nome do Aluno: " + resultSet.getString("NomeAluno") +
                        " | Nome do Curso: " + resultSet.getString("NomeCurso") +
                        " | Data de Matrícula: " + resultSet.getString("DataMatricula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarMatricula(int id) {
        String sql = "DELETE FROM Matriculas WHERE ID = " + id;
        try {
            int rowCount = statement.executeUpdate(sql);
            if (rowCount > 0) {
                System.out.println("Matrícula com ID " + id + " foi deletada com sucesso.");
            } else {
                System.out.println("Matrícula com ID " + id + " não encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
