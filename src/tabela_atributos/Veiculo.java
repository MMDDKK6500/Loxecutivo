package tabela_atributos;

public class Veiculo {
    private String id_placa;
    private String modelo;
    private int ano;
    private String marca;
    private String cor;

    public String getId_Placa() {
        return id_placa;
    }

    public void setId_Placa(String id_placa) {
        this.id_placa = id_placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
