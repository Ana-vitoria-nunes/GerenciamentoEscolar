package org.example.view;

import org.example.model.InputUser;
import org.example.model.ProfessorModel;
import org.example.service.ProfessorService;

public class ProfessorView {
    ProfessorModel professorModel=new ProfessorModel();
    ProfessorService professorService=new ProfessorService();
    InputUser inputUser=new InputUser();
    public void MenuProfessor() {
        System.out.println("\nBem-vindo(a) ao menu do Professor");
        System.out.println("0 - Voltar ao menu principal.");
        System.out.println("1 - Adicinar Professor.");
        System.out.println("2 - Alterar informações Professor.");
        System.out.println("3 - Listar Professor.");
    }
    public void caseProfessor() {
        int option;
        do {
            MenuProfessor();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> addProfessor();
                case 2 -> alterarInformacoes();
                case 3 -> professorService.consultarTodosProfessores();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    private void addProfessor() {
        String nome = inputUser.readStringFromUser("Qual o nome do Professor: ");
        String disciplina = inputUser.readStringFromUser("Qual a diciplina desse professor: ");
        professorModel.setNomeProf(nome);
        professorModel.setDisciplina(disciplina);
        professorService.inserirProfessor(professorModel.getNomeProf(),professorModel.getDisciplina());
    }
    private void alterarInformacoes() {
        professorService.consultarTodosProfessores();
        int id = inputUser.readIntFromUser("Qual o ID do Professor que deseja Alterar: ");
        String disciplina = inputUser.readStringFromUser("Qual a novo disciplina: ");
        professorModel.setDisciplina(disciplina);
        professorService.alterarProfessor(id,professorModel.getDisciplina());
    }
}
