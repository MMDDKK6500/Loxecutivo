package tabela_atributos;

public class Motorista {
    private int id_motorista;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;

    public int getId_Motorista() {
        return id_motorista;
    }
    
    public void setId_Motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRG() {
        return rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
}
