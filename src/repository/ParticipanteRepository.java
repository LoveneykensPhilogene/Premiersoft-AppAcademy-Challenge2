package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ICandidatos.InterfaceCandidato;
import canditados.OrdenarPorNome;
import canditados.Participante;

public class ParticipanteRepository implements InterfaceCandidato {

	// Retorna uma lista de todos os participantes dentro do arquivo
	public List<Participante> lerDados() {
		String caminhoDoArquivo = "C:\\AppAcademy_Candidates.csv";
		String separador = ";";
		String nome = "";
		String idade = "";
		String vaga = "";
		String estado = "";
		List<Participante> listas = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(caminhoDoArquivo), "UTF-8"))) {
			String coluna = bufferedReader.readLine();

			while (coluna != null) {

				String campos[] = coluna.split(separador);
				coluna = bufferedReader.readLine();
				nome = campos[0];
				idade = campos[1];
				vaga = campos[2];
				estado = campos[3];
				listas.add(new Participante(nome, idade, vaga, estado));

			}
			bufferedReader.close();
			listas.remove(0);

		} catch (IOException e) {
			System.out.println("Arquivo não encontrado " + e.getMessage() + "\n");
		}
		return listas;

	}

	// Cria um novo arquivo com a lista dos participante
	public void gravarDados() {
		String separador = ";";
		String caminhoDoArquivo = "sorted_AppAcademy_candidates.csv";
		List<Participante> listaDecandidatosOrdenados = ordenarAListaDeCandidatos();

		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(caminhoDoArquivo))) {

			bWriter.write("Nome" + separador + "Idade" + separador + "Vaga" + separador + "Estado");
			bWriter.newLine();

			for (Participante can : listaDecandidatosOrdenados) {

				bWriter.write(can.getNome() + separador + can.getIdade() + separador + can.getVaga() + separador
						+ can.getEstado());
				bWriter.newLine();
			}

			bWriter.close();

			System.out.print(
					"\n lista ordenada em ordem alfabética" + " e foi salva com o nome: " + caminhoDoArquivo + "\n");

		} catch (IOException e) {
			System.out.print("Error" + e.getMessage());

		}

	}

	// Mostra o nome do instrutor da vaga iOS
	public void mostrarNomeDoInstrutor() {
		if (todosOsInstrutores() != null) {
			for (Participante p : todosOsInstrutores()) {
				if (p.getVaga().equals("iOS")) {
					p.getVaga();
				} else {
					System.out.println("\n Instrutor de iOS: " + p.getNome());

				}
			}
		} else {
			System.out.print("Não existe nenhum instrutor");
		}
	}

	// Mostra a Proporcão de candidatos por vagas
	@Override
	public void porcentagemDeCandidatos() {
		if (lerDados() != null) {
			String simbolo = "%";

			long api_net = lerDados().stream().filter(c -> c.getVaga().equals("API .NET")).count();
			String formatApi_net = String.valueOf(api_net);
			long porcentagemApi_net = Math.round(Double.parseDouble(formatApi_net) * 100 / lerDados().size());

			long ios = lerDados().stream().filter(c -> c.getVaga().equals("iOS")).count();
			String formatIos = String.valueOf(ios);
			long porcentagemIos = Math.round(Double.parseDouble(formatIos) * 100 / lerDados().size());

			long qa = lerDados().stream().filter(c -> c.getVaga().equals("QA")).count();
			String formatQa = String.valueOf(qa);
			long porcentagemQa = Math.round(Double.parseDouble(formatQa) * 100 / lerDados().size());
			System.out.println("\n Proporcão de candidatos por vagas: \n" + "API .NET: " + porcentagemApi_net + simbolo
					+ "\n iOS: " + porcentagemIos + simbolo + "\n QA: " + porcentagemQa + simbolo);
		} else {
			System.out.println("Não existe nenhum candidato " + new ArrayList<>());

		}

	}

	// Mostra a Idade media dos candidato de QA
	@Override
	public void mostrarIdadeMediaQA() {
		int somaIdade = 0;
		long mediaIdade = 0;
		if (lerDados() != null) {

			List<Participante> candidatoQa = lerDados().stream().map(canQa -> canQa)
					.filter(c -> c.getVaga().equals("QA")).collect(Collectors.toList());
			List<Integer> idade = candidatoQa.stream().map(can -> Integer.parseInt(can.getIdade().substring(0, 2)))
					.collect(Collectors.toList());
			for (int idadeQa : idade) {
				somaIdade += idadeQa;
			}
			mediaIdade = Math.round(somaIdade / idade.size());
			System.out.println("\n Idade media dos candidato de QA: " + mediaIdade + " " + "anos");
		} else {
			System.out.println("Idade não existe " + new ArrayList<>());
		}

	}

	// Mostra o Candidato(a) mais velho(a) dos candidato de iOS
	@Override
	public void mostrarCandidatoMaisVelhoIOS() {
		List<Participante> candidatoIOs = lerDados().stream().map(canIOs -> canIOs)
				.filter(c -> c.getVaga().equals("iOS")).collect(Collectors.toList());

		Optional<Integer> idade = candidatoIOs.stream().map(can -> Integer.parseInt(can.getIdade().substring(0, 2)))
				.max(Comparator.naturalOrder());
		Participante pIOs = new Participante();
		for (Participante iOs : candidatoIOs) {
			if (Integer.parseInt(iOs.getIdade().substring(0, 2)) >= idade.get()) {

				pIOs = iOs;
			}
		}
		System.out.println("\n Candidato(a) mais velho(a) dos candidato de iOS: " + pIOs.getNome());

	}

	// Mostra Candidato(a) mais novo(a) dos candidato de API .NET
	@Override
	public void mostrarCandidatoMaisNovoAPI_NET() {
		List<Participante> candidatoApi_net = lerDados().stream().map(can -> can)
				.filter(c -> c.getVaga().equals("API .NET")).collect(Collectors.toList());

		Optional<Integer> idade = candidatoApi_net.stream().map(can -> Integer.parseInt(can.getIdade().substring(0, 2)))
				.min(Comparator.naturalOrder());
		Participante pApi_net = new Participante();
		for (Participante api_net : candidatoApi_net) {
			if (Integer.parseInt(api_net.getIdade().substring(0, 2)) <= idade.get()) {

				pApi_net = api_net;
			}
		}
		System.out.println("\n Candidato(a) mais novo(a) dos candidato de API .NET: " + pApi_net.getNome());

	}

	// Mostra A soma da idade de todos candidatos do API .NET
	@Override
	public void mostrarSomaIdadeCandidatosAPI_NET() {
		int somaIdade = 0;

		List<Participante> candidatoQa = lerDados().stream().map(canQa -> canQa)
				.filter(c -> c.getVaga().equals("API .NET")).collect(Collectors.toList());
		List<Integer> idade = candidatoQa.stream().map(can -> Integer.parseInt(can.getIdade().substring(0, 2)))
				.collect(Collectors.toList());
		for (int idadeApi_net : idade) {
			somaIdade += idadeApi_net;
		}

		System.out.println("\n A soma da idade de todos candidatos do API .NET: " + somaIdade);

	}

	// Mostra Número de estados distintos presentes na lista
	@Override
	public void mostrarNumeroDeEstadosDistintos() {
		List<String> estadsoDistintos = lerDados().stream().map(estado -> estado.getEstado()).distinct()
				.collect(Collectors.toList());
		int quandidadeDistinto = estadsoDistintos.size();
		System.out.println("\n Número de estados distintos presentes na lista : " + quandidadeDistinto);

	}

	// Retorna uma lista ordenada em ordem alfabetica
	public List<Participante> ordenarAListaDeCandidatos() {
		List<Participante> listaDecandidatos = lerDados();
		OrdenarPorNome nome = new OrdenarPorNome();
		Collections.sort(listaDecandidatos, nome);
		return listaDecandidatos;

	}

	// Retorna todos os intrutores na lista
	List<Participante> todosOsInstrutores() {

		List<Participante> dados = lerDados();
		List<Participante> instrutores = new ArrayList<>();
		Participante instrutorIos = new Participante();
		Participante instrutorApi_net = new Participante();

		for (Participante instrutor : dados) {
			int idade = Integer.parseInt(instrutor.getIdade().substring(0, 2));
			String lettraIos = instrutor.getNome().substring(0, 1);
			if ((idade % 2 > 0 && lettraIos.equalsIgnoreCase("v")) && (idade > 20 && idade < 30)) {
				instrutorIos = instrutor;
				instrutores.add(instrutorIos);
			} else {
				new Participante();

			}

		}

		for (Participante instrutor : dados) {

			int idadeApi_net = Integer.parseInt(instrutor.getIdade().substring(0, 2));
			String lettraApi_net = instrutor.getNome().substring(instrutor.getNome().length() - 1,
					instrutor.getNome().length());
			if ((idadeApi_net % 2 > 0) && (lettraApi_net.equalsIgnoreCase("k"))) {
				instrutorApi_net = instrutor;
				instrutores.add(instrutorApi_net);
			} else {
				new Participante();
			}
		}

		return instrutores;
	}

}
