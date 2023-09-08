package org.example.view;

import org.example.model.AlunoModel;
import org.example.model.InputUser;
import org.example.service.AlunoService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class AlunoView {
    AlunoService alunoService=new AlunoService();
    AlunoModel alunoModel=new AlunoModel();
    InputUser inputUser=new InputUser();
    public void MenuAluno() {
        System.out.println("\nBem-vindo(a) ao menu do Aluno");
        System.out.println("0 - Voltar ao menu principal.");
        System.out.println("1 - Adicinar Aluno.");
        System.out.println("2 - Alterar informações Aluno.");
        System.out.println("3 - Listar Alunos.");
    }
    public void caseAluno() {
        int option;
        do {
            MenuAluno();
            option = inputUser.readIntFromUser("Qual opção você deseja: ");

            switch (option) {
                case 0 -> new MenuView();
                case 1 -> addAluno();
                case 2 -> alterarInformacoes();
                case 3 -> alunoService.listarAlunos();
                default -> System.out.println("Opção inválida, tente novamente!");
            }
        } while (option != 0);
    }
    private void addAluno() {
        String nome = inputUser.readStringFromUser("Qual o nome do aluno: ");
        String dataNascimento = inputUser.readStringFromUser("Qual a data de nascimento do aluno(yyyy-MM-dd): ");
        String endereco = inputUser.readStringFromUser("Qual o endereço do aluno: ");
        alunoModel.setNome(nome);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataNascimento, formatter);
        alunoModel.setDataNascimento(data);
        alunoModel.setEndereco(endereco);
        alunoService.inserirAluno(alunoModel.getNome(),alunoModel.getDataNascimento(),alunoModel.getEndereco());
    }
    private void alterarInformacoes() {
        alunoService.listarAlunos();
        int id = inputUser.readIntFromUser("Qual o ID do Aluno que deseja alterar: ");
        String endereco = inputUser.readStringFromUser("Qual o novo endereço: ");
        alunoModel.setEndereco(endereco);
        alunoService.alterarAluno(id,alunoModel.getEndereco());
    }

}
