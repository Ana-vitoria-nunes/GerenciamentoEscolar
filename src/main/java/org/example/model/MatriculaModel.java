package org.example.model;

import java.time.LocalDate;

public class MatriculaModel {
    int idAluno;
    int idCurso;
    LocalDate dataMatricula;

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
}
