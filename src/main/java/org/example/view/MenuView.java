package org.example.view;

import org.example.model.InputUser;
import org.example.service.CursoService;
import org.example.service.EscolaService;
import org.example.service.ProfessorService;
public class MenuView {
    AlunoView alunoView=new AlunoView();
    CursoView cursoView=new CursoView();
    MatriculaView matriculaView=new MatriculaView();
    ProfessorView professorView=new ProfessorView();
    InputUser inputUser=new InputUser();
    ProfessorService professorService=new ProfessorService();
    CursoService cursoService=new CursoService();
    EscolaService escolaService=new EscolaService();
    public void MenuPrincipal() {
        System.out.println("Bem-vindo(a) ao menu Principal\n");
        System.out.println("Digite uma das seguinte opções");
        System.out.println("0 - Sair.");
        System.out.println("1 - Consultar opções para Aluno.");
        System.out.println("2 - Consultar opções para Curso.");
        System.out.println("3 - Consultar opções para Professor.");
        System.out.println("4 - Consultar opções para Matricula.");
        System.out.println("5 - Fazer consultas.");
    }
    public void casePrincipal() {
        int option;
        do {
            MenuPrincipal();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> alunoView.caseAluno();
                case 2 -> cursoView.caseCurso();
                case 3 -> professorView.caseProfessor();
                case 4 -> matriculaView.caseMatricula();
                case 5 -> caseConsultas();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    public void MenuConsulta() {
        System.out.println("\nBem-vindo(a) ao menu da Consulta\n");
        System.out.println("Digite uma das seguinte opções");
        System.out.println("0 - Voltar ao menu principal.");
        System.out.println("1 - Consultar alunos matriculados em um curso específico.");
        System.out.println("2 - Consultar cursos ministrados por um professor.");
        System.out.println("3 - Consultar alunos que não estão matriculados em nenhum curso.");
        System.out.println("4 - Consultar cursos sem alunos matriculados.");
        System.out.println("5 - Consulta de alunos matriculados em mais de um curso.");
    }
    public void caseConsultas() {
        int option;
        do {
            MenuConsulta();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> consultarAlunosPorCurso();
                case 2 -> consultarCursoPorProfessor();
                case 3 -> escolaService.consultarAlunosSemMatricula();
                case 4 -> escolaService.consultarCursosSemAlunos();
                case 5 -> escolaService.consultarAlunosComMaisDeUmCurso();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    private void consultarCursoPorProfessor() {
        professorService.consultarTodosProfessores();
        int id=inputUser.readIntFromUser("Qual o id do professor");
        escolaService.consultarCursosPorProfessor(id);
    }
    private void consultarAlunosPorCurso() {
        cursoService.consultarTodosCursos();
        int id=inputUser.readIntFromUser("Qual o id do curso");
        escolaService.consultarAlunosPorCurso(id);
    }
}
