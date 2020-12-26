package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Treino {

    private long id;
    //private long paciente_id;
    private Date data_treino;
    private String descricao_treino;
    private String tipo_treino;
    private String obs_treino;

    public Treino(long id, Date data_treino, String descricao, String tipo_treino, String obs) {
        this.id = id;
        //this.paciente_id = paciente_id;
        this.data_treino = data_treino;
        this.descricao_treino = descricao;
        this.tipo_treino = tipo_treino;
        this.obs_treino = obs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataTreino() { return data_treino; }

    public String getDescricao_treino() {
        return descricao_treino;
    }

    public void setDescricao_treino(String descricao_treino) {
        this.descricao_treino = descricao_treino;
    }

    public String getTipoTreino() {
        return tipo_treino;
    }

    public void setTipo_treino(String tipo_treino) {
        this.tipo_treino = tipo_treino;
    }

    public String getObs_treino() {
        return obs_treino;
    }

    public void setObs_treino(String obs_treino) {
        this.obs_treino = obs_treino;
    }
}
