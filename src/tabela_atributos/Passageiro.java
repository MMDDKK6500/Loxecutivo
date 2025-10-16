package tabela_atributos;

public class Passageiro {
    public int id_passageiro;
    public String nome;
    public String sobrenome;
    public String numero;
    public String empresa;
    public String rg;
    public String cpf;
    public int id_viagem;
    
    public int getId() {
        return id_passageiro;
    }
    
    public void setId(int id) {
        this.id_passageiro = id;
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
    
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    public String getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
    
    public void setCPF(String CPF) {
        this.cpf = CPF;
    }
    
    
    public int getId_Viagem() {
        return id_viagem;
    }
    
    public void setId_Viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
}
