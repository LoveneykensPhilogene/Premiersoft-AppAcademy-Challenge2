package repository;

public class AppChallenge {

	// Programa pricipal
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Respons�vel para executar os methodos
		ParticipanteRepository participanteRepository = new ParticipanteRepository();

		if (participanteRepository != null) {

			participanteRepository.porcentagemDeCandidatos();
			participanteRepository.mostrarIdadeMediaQA();
			participanteRepository.mostrarCandidatoMaisVelhoIOS();
			participanteRepository.mostrarCandidatoMaisNovoAPI_NET();
			participanteRepository.mostrarSomaIdadeCandidatosAPI_NET();
			participanteRepository.mostrarNumeroDeEstadosDistintos();
			participanteRepository.gravarDados();
			participanteRepository.mostrarNomeDoInstrutor();
		} else {
			System.out.println(new Exception("ParticipanteRepository n�o pode ser nullo.").getMessage());

		}

	}

}
