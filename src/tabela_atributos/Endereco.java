package tabela_atributos;

public class Endereco {
    private int id_endereco;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;

    // Getter e Setter para id_endereco
    public int getId_Endereco() {
        return id_endereco;
    }

    public void setId_Endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    // Getter e Setter para rua
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    // Getter e Setter para numero
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Getter e Setter para bairro
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    // Getter e Setter para cidade
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // Getter e Setter para uf
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
