package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.sql.Date;

public class Treino {
    private long id;
    //private long paciente_id;
    private Date data_treino;
    private String descricao;
    private String tipo_treino;
    private String obs;

    public Treino(Date data_treino, String descricao, String tipo_treino, String obs) {
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

    /*public long getPaciente_id() {
        return paciente_id;
    }*/

    /*public void setPaciente_id(long paciente_id) {
        this.paciente_id = paciente_id;
    }*/

    public Date getData_treino() {
        return data_treino;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo_treino() {
        return tipo_treino;
    }

    public String getObs() {
        return obs;
    }
}
