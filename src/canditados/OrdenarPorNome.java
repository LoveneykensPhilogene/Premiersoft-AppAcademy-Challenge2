package canditados;

import java.util.Comparator;

public class OrdenarPorNome implements Comparator<Participante> {

	// fazer a comparaçao por nome
	@Override
	public int compare(Participante c1, Participante c2) {
		String nome1 = c1.getNome();
		String nome2 = c2.getNome();
		return nome1.compareTo(nome2);

	}

}
