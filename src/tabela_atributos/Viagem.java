package tabela_atributos;

public class Viagem {
    public int id_viagem;
    public int local_de_origem;
    public int local_de_destino;
    public int id_motorista;
    public int id_veiculo;
    public int id_evento;
    
    public int getId() {
        return id_viagem;
    }
    
    public void setId(int id) {
        this.id_viagem = id;
    }
    
    
    public int getLocalDeOrigem() {
        return local_de_origem;
    }
    
    public void setLocalDeOrigem(int localDeOrigem) {
        this.local_de_origem = localDeOrigem;
    }
    
    
    public int getLocalDeDestino() {
        return local_de_destino;
    }
    
    public void setLocalDeDestino(int localDeDestino) {
        this.local_de_destino = localDeDestino;
    }
    
    
    public int getId_Motorista() {
        return id_motorista;
    }
    
    public void setId_Motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }
    
    
    public int getId_Veiculo() {
        return id_veiculo;
    }
    
    public void setId_Veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }
    
    
    public int getId_Evento() {
        return id_evento;
    }
    
    public void setId_Evento(int id_evento) {
        this.id_evento = id_evento;
    }
}
