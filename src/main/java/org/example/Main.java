package org.example;

import org.example.dao.FornecedorDAO;
import org.example.dao.MaterialDAO;
import org.example.dao.NotaEntradaDAO;
import org.example.model.Fornecedor;
import org.example.model.Material;
import org.example.model.NotaEntrada;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
public class Main {

    static Scanner scNum = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
    }

    public static void inicio() {
        boolean sair = false;

        System.out.println("""
                
                
                -------------- Seja Bem-Vindo --------------
                Sistema de Gestão de Almoxarifado Industrial
                
                1 - Cadastrar Fornecedor
                2 - Cadastrar Material
                3 - Registrar Nota de Entrada
                4 - Criar Requisição de Material
                5 - Atender Requisição
                6 - Cancelar Requisição
                0 - Sair
                
                Digite sua Opção:
                """);
        int opcao = scNum.nextInt();
        scStr.nextLine();

        switch (opcao) {
            case 1 -> {
                cadastrarFornecedor();
            }
            case 2 -> {
                cadastrarMaterial();
            }
            case 3 -> {
                registrarNotaEntrada();
            }
            case 4 -> {
                //criarRequisicaoMaterial();
            }
            case 5 -> {
                //atenderRequisicao();
            }
            case 6 -> {
                //cancelarRequisicao();
            }
            case 0 -> {
                System.out.println("Saindo do sistema...");
                sair = true;
            }
            default -> {
                System.err.println("[ERRO] Opção inválida. Tente novamente.");
            }


        }
        if (sair == false) {
            inicio();
        }
    }

    public static void cadastrarFornecedor() {
        System.out.println("CADASTRO DE FORNECEDOR\n");
        System.out.println("Digite o nome do fornecedor:");
        String nome = scStr.nextLine();
        System.out.println("Digite o CNPJ do fornecedor:");
        String cnpj = scStr.nextLine();

        if (!nome.isEmpty() && !cnpj.isEmpty()) {
            var dao = new FornecedorDAO();
            try {
                if (!dao.existeCnpj(cnpj)) {
                    var fornecedor = new Fornecedor(nome, cnpj);
                    dao.cadastrarFornecedor(fornecedor);
                    System.out.println("\n[OK] Fornecedor cadastrado com sucesso!");
                } else {
                    System.err.println("\n[ERRO] CNPJ já cadastrado no sistema.");
                }
            } catch (SQLException e) {
                System.out.println("\n[ERRO] Falha ao verificar CNPJ: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.err.println("\n[ERRO] Todos os campos são obrigatórios. Tente novamente.");
            cadastrarFornecedor();
        }
    }

    public static void cadastrarMaterial() {
        System.out.println("CADASTRO DE MATERIAL\n");

        System.out.println("Digite o nome do Material: ");
        String nome = scStr.nextLine();

        System.out.println("Digite a unidade do Material: ");
        String unidade = scStr.nextLine();

        System.out.println("Digite o estoque do Material: ");
        double estoque = scNum.nextDouble();

        if (!nome.isEmpty()) {
            var dao = new MaterialDAO();
            try {
                if (!dao.existeNomeMaterial(nome)) {
                    if (estoque >= 0) {
                        var material = new Material(nome, unidade, estoque);
                        dao.cadastrarMaterial(material);
                        System.out.println("\n[OK] Material cadastrado com sucesso!");
                    } else {
                        System.err.println("\n[ERRO] Estoque não pode ser negativo.");
                    }
                } else {
                    System.err.println("\n[ERRO] Material já cadastrado no sistema.");
                }
            } catch (SQLException e) {
                System.out.println("\n[ERRO] Falha ao verificar Material: " + e.getMessage());
                e.printStackTrace();

            }
        }
    }

    public static void registrarNotaEntrada() {
        System.out.println("REGISTRAR NOTA DE ENTRADA\n");
        var fornecedorDAO = new FornecedorDAO();
        try {
            List<Fornecedor> fornecedores = fornecedorDAO.listarFornecedores();

            if (fornecedores.isEmpty()) {
                System.err.println("\n[ERRO] Nenhum fornecedor cadastrado. Cadastre um fornecedor antes de registrar uma nota de entrada.");
            } else {
                List<Integer> idsFornecedores = new ArrayList<>();
                for (Fornecedor fornecedor : fornecedores) {
                    System.out.println("\nID: " + fornecedor.getId() + "\n | Nome: " + fornecedor.getNome() + "\n");
                }
                System.out.println("\nInsira o Id do Fornecedor: ");
                int idFornecedor = scNum.nextInt();
                scStr.nextLine();

                if (!idsFornecedores.contains(idFornecedor)){
                    System.err.println("\n[ERRO] Fornecedor com ID " + idFornecedor + " não encontrado.");
                }else{
                    var notaEntrada = new NotaEntrada(idFornecedor);
                    var notaEntradaDAO = new NotaEntradaDAO();
                    notaEntradaDAO.cadastrarNotaEntrada(notaEntrada);
                    System.out.println("[OK] Nota de entrada registrada com sucesso para o fornecedor ID: " + idFornecedor);
                }
            }
        } catch (SQLException e) {
            System.out.println("\n[ERRO] Falha ao registrar nota de entrada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}