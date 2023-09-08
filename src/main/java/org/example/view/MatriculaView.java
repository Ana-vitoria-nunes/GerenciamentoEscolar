package org.example.view;

import org.example.model.InputUser;
import org.example.model.MatriculaModel;
import org.example.service.AlunoService;
import org.example.service.CursoService;
import org.example.service.MatriculaService;
import java.time.LocalDate;
public class MatriculaView {
    MatriculaModel matriculaModel=new MatriculaModel();
    MatriculaService matriculaService=new MatriculaService();
    AlunoService alunoService=new AlunoService();
    CursoService cursoService=new CursoService();
    InputUser inputUser=new InputUser();
    public void MenuMatricula() {
        System.out.println("\nBem-vindo(a) ao menu da Matricula");
        System.out.println("0 - Voltar ao menu principal.");
        System.out.println("1 - Fazer Matricula.");
        System.out.println("2 - Deletar Matricula.");
        System.out.println("3 - Listar Matricula.");
    }
    public void caseMatricula() {
        int option;
        do {
            MenuMatricula();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> addMatricula();
                case 2 -> deleteMatricula();
                case 3 -> matriculaService.consultarTodasMatriculas();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    private void addMatricula() {
        System.out.println("Alunos: ");
        alunoService.listarAlunos();
        System.out.println("\nCursos");
        cursoService.consultarTodosCursos();
        int idAluno=inputUser.readIntFromUser("Qual o id do aluno");
        int idCurso=inputUser.readIntFromUser("Qual o id do curso");
        matriculaModel.setDataMatricula(LocalDate.now());
        matriculaService.inserirMatricula(idAluno,idCurso,matriculaModel.getDataMatricula());
    }
    private void deleteMatricula() {
        matriculaService.consultarTodasMatriculas();
        int id = inputUser.readIntFromUser("Qual o ID da Matricula que deseja deletar: ");
        matriculaService.deletarMatricula(id);
    }
}
