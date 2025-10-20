package tabela_atributos;

public class Evento {
    private int id_evento;
    private String nome;
    private int id_endereco;

    // Getter e Setter para id_evento
    public int getId_Evento() {
        return id_evento;
    }

    public void setId_Evento(int id_evento) {
        this.id_evento = id_evento;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para id_endereco
    public int getId_Endereco() {
        return id_endereco;
    }

    public void setId_Endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }
}
