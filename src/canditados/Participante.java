package canditados;

public class Participante {
	private String nome;
	private String vaga;
	private String idade;
	private String estado;

	public Participante() {

	}

	public Participante(String nome, String vaga, String idade, String estado) {
		super();
		this.nome = nome;
		this.vaga = vaga;
		this.idade = idade;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVaga() {
		return vaga;
	}

	public void setVaga(String vaga) {
		this.vaga = vaga;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Nome=" + getNome() + ", Vaga=" + getVaga() + ", Idade=" + getIdade() + ", Estado=" + getEstado();
	}

}
