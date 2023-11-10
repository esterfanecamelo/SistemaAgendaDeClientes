package org.teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dao.ClienteDAO;
import org.dao.TelefoneDAO;
import org.model.Cliente;
import org.model.Telefone;

public class Principal {

	public static void main(String[] args) throws SQLException {

			menu();

	}

	private static Cliente cadastrarCliente() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("DIGITE NOME");
		String nomeCliente = scanner.nextLine();
		System.out.println("CPF");
		int cpfCliente = scanner.nextInt();
		System.out.println("EMAIL");
		scanner.nextLine();
		String emailCliente = scanner.nextLine();

		Cliente cliente = new Cliente(nomeCliente, emailCliente);
		cliente.setCpf(cpfCliente);
		cliente = new ClienteDAO().inserir(cliente);
		return cliente;
	}

	private static Cliente consultarPorCpf() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("PESQUISAR POR CPF:");
		String input = scanner.nextLine();
		try {
			Integer cpfCliente = Integer.valueOf(input);
			Cliente cliente = new ClienteDAO().consultar(cpfCliente);
			return cliente;
		} catch (NumberFormatException e) {
			System.out.println("Por favor, digite um número inteiro válido.");
		}
		return null;
	}

	private static void atualizarEmail() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("DIGITE O CPF DO CLIENTE:");
		String input = scanner.nextLine();
		System.out.println("DIGITE O NOVO EMAIL:");
		String novoEmailCliente = scanner.nextLine();
		try {
			Integer cpfCliente = Integer.valueOf(input);
			Cliente cliente = new ClienteDAO().consultar(cpfCliente);
			cliente.setEmail(novoEmailCliente);
			cliente = new ClienteDAO().atualizar(cliente);
			if(cliente != null){
				System.out.println("EMAIL ALTERADO: " + cliente);
			}
		} catch (NumberFormatException e) {
			System.out.println("Por favor, digite um número inteiro válido.");
		}
	}

	private static void deletarCliente() throws SQLException{
		Cliente cliente = consultarPorCpf();
		boolean removerCliente = new ClienteDAO().deletar(cliente);
		if (removerCliente){
			System.out.println("REMOVIDO: " + cliente);
		}
	}
	private static void pesquisarPorNome() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("PESQUISAR POR NOME:");
		String nomeCliente = scanner.nextLine();
		List<Cliente> clientes = new ClienteDAO().pesquisarPorNome(nomeCliente);
		System.out.println(clientes);
	}

	private static void inserirtelefone(Telefone telefone) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("DIGITE O NÚMERO DO TELEFONE:");
		String input = scanner.nextLine();
		try {
			Integer numeroTelefone = Integer.valueOf(input);
			telefone.setNumero(numeroTelefone);
			telefone = new TelefoneDAO().inserir(telefone);
			System.out.println("TELEFONE CADASTRADO: "+telefone);
		} catch (NumberFormatException e) {
			System.out.println("Por favor, digite um número inteiro válido.");
		}
	}


	private static void menu() throws SQLException {
		System.out.println("-------------------------------------------------------------");
		System.out.println("MENU");
		System.out.println("-------------------------------------------------------------");
		System.out.println("1. (INSERIR) CLIENTE");
		System.out.println("2. (CONSULTAR) CLIENTE");
		System.out.println("3. (ATUALIZAR) EMAIL");
		System.out.println("4. (DELETAR) CLIENTE");
		System.out.println("5. (PESQUISAR) POR NOME");
		System.out.println("6. (LISTAR) TODOS OS CLIENTES");
		System.out.println("-------------------------------------------------------------");
		System.out.println("7. (INSERIR) TELEFONE DE CLIENTE");
		System.out.println("8. (CONSULTAR) TELEFONE DE CLIENTE");
		System.out.println("9. (ATUALIZAR) TELEFONE DE CLIENTE");
		System.out.println("10. (REMOVER) TELEFONE DE CLIENTE");
		System.out.println("-------------------------------------------------------------");
		System.out.println("11. (SAIR)");
		System.out.println("-------------------------------------------------------------");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Selecionar: ");
		int opcao = scanner.nextInt();

		Scanner scannerEscolha;
		Cliente cliente;
		List<Cliente> clientes;
		Telefone telefone;
		List<Telefone> telefones;
		int escolha = 0;
		switch(opcao) {
			case 1:
				clear();
				System.out.println("INSERIR CLIENTE");
				cliente = cadastrarCliente();
				System.out.println("CADASTRADO: " + cliente);
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();
				if(escolha == 1){
					menu();
				}
				break;
			case 2:
				clear();
				System.out.println("CONSULTAR CLIENTE POR CPF");
				cliente = consultarPorCpf();
				if (cliente != null){
					System.out.println(cliente);
				}
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();
				if(escolha == 1){
					menu();
				}
				break;
			case 3:
				clear();
				System.out.println("ATUALIZAR EMAIL");
				atualizarEmail();
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();
				if(escolha == 1){
					menu();
				}
				break;
			case 4:
				clear();
				System.out.println("DELETAR CLIENTE");
				deletarCliente();
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();
				if(escolha == 1){
					menu();
				}
				break;
			case 5:
				clear();
				System.out.println("PESQUISAR POR NOME");
				pesquisarPorNome();
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 6:
				clear();
				System.out.println("LISTAR TODOS OS CLIENTES");
				clientes = new ClienteDAO().listarTodos();
				System.out.println(clientes);
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 7:
				clear();
				System.out.println("INSERIR TELEFONE DE CLIENTE");
				cliente = consultarPorCpf();
				telefone = new Telefone(cliente);
				inserirtelefone(telefone);
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 8:
				clear();
				System.out.println("CONSULTAR TELEFONE DE CLIENTE");
				cliente = consultarPorCpf();
				telefones = new TelefoneDAO().buscar(cliente);
				cliente.setTelefones(telefones);
				System.out.println(cliente);
				System.out.println(cliente.getTelefones());
				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 9:
				clear();
				System.out.println("ATUALIZAR TELEFONE DE CLIENTE");
				cliente = consultarPorCpf();
				telefones = new TelefoneDAO().buscar(cliente);
				cliente.setTelefones(telefones);
				System.out.println(cliente);
				System.out.println(cliente.getTelefones());
				System.out.println("DIGITE O ID DO TELEFONE PARA ATUALIZAR:");
				int ID = scanner.nextInt();
				System.out.println("DIGITE O NOVO TELEFONE:");
				scanner.nextLine();
				String input = scanner.nextLine();
				try {
					Integer novoTelefone = Integer.valueOf(input);
					telefone = new TelefoneDAO().consultar(ID);
					telefone.setNumero(novoTelefone);
					telefone = new TelefoneDAO().atualizar(telefone);
					System.out.println("TELEFONE ATUALIZADO: "+telefone);
				} catch (NumberFormatException e) {
					System.out.println("Por favor, digite um número inteiro válido.");
				}

				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 10:
				clear();
				System.out.println("REMOVER TELEFONE DE CLIENTE");
				cliente = consultarPorCpf();
				telefones = new TelefoneDAO().buscar(cliente);
				cliente.setTelefones(telefones);
				System.out.println(cliente);
				System.out.println(cliente.getTelefones());
				try {
					System.out.println("DIGITE O ID DO TELEFONE PARA DELETAR:");
					int id = scanner.nextInt();
					boolean telefoneRemovido = new TelefoneDAO().removerTelefone(id);
					if(telefoneRemovido){
						System.out.println("TELEFONE REMOVIDO");
					}
				} catch (NumberFormatException e) {
					System.out.println("Por favor, digite um número inteiro válido.");
				}

				System.out.println("-------------------------------------------------------------");
				System.out.println("--------DESEJA VOLTAR AO MENU?-------------------------------");
				System.out.println("1. SIM");
				scannerEscolha = new Scanner(System.in);
				escolha = scannerEscolha.nextInt();

				if(escolha == 1){
					menu();
				}
				break;
			case 11:
				clear();
				break;
			default:
				System.out.println("Opção inválida.");
		}

	}

	public static void clear() {
		try {

			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				for (int i = 0; i < 40; ++i) {
					System.out.println();
				}
			} else {
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
