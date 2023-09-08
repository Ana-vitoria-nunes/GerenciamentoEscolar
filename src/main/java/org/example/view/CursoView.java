package org.example.view;

import org.example.model.CursoModel;
import org.example.model.InputUser;
import org.example.service.CursoService;
import org.example.service.ProfessorService;
public class CursoView {
    CursoModel cursoModel=new CursoModel();
    CursoService cursoService=new CursoService();
    ProfessorService professorService=new ProfessorService();
    InputUser inputUser =new InputUser();
    public void MenuCurso() {
        System.out.println("\nBem-vindo(a) ao menu do Curso\n");
        System.out.println("Digite uma das seguinte opções");
        System.out.println("0 - Voltar ao menu principal.");
        System.out.println("1 - Adicinar Curso.");
        System.out.println("2 - Deletar Curso.");
        System.out.println("3 - Alterar informações Curso.");
        System.out.println("4 - Listar Curso.");
    }
    public void caseCurso() {
        int option;
        do {
            MenuCurso();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> addCurso();
                case 2 -> deleteCurso();
                case 3 -> alterarInformacoes();
                case 4 -> cursoService.consultarTodosCursos();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    private void addCurso() {
        professorService.consultarTodosProfessores();
        String nome = inputUser.readStringFromUser("Qual o nome do Curso: ");
        int idProf= inputUser.readIntFromUser("Qual o id do professor desse curso");
        cursoModel.setNomeCurso(nome);
        cursoService.inserirCurso(cursoModel.getNomeCurso(),idProf);
    }
    private void deleteCurso() {
        cursoService.consultarTodosCursos();
        int id = inputUser.readIntFromUser("Qual o ID do Curso que deseja deletar: ");
        cursoService.deletarCurso(id);
    }
    private void alterarInformacoes() {
        cursoService.consultarTodosCursos();
        int id = inputUser.readIntFromUser("Qual o ID do Curso que deseja alterar: ");
        String nome = inputUser.readStringFromUser("Qual o novo nome do curso: ");
        cursoModel.setNomeCurso(nome);
        cursoService.alterarCurso(id,cursoModel.getNomeCurso());
    }

}
