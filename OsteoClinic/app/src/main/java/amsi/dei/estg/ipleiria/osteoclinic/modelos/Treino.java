package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Treino {

    private long id;
    //private long paciente_id;
    private Date data_treino;
    private String descricao;
    private String tipo_treino;
    private String obs;

    public Treino(long id, Date data_treino, String descricao, String tipo_treino, String obs) {
        this.id = id;
        //this.paciente_id = paciente_id;
        this.data_treino = data_treino;
        this.descricao = descricao;
        this.tipo_treino = tipo_treino;
        this.obs = obs;
    }

    public long getId() {
        return id;
    }

    public Date getDataTreino() { return data_treino; }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoTreino() {
        return tipo_treino;
    }

    public String getObs() {
        return obs;
    }
}
