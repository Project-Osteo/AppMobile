package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Consulta {
    private long id;
    private Date data_consulta;
    private String descricao;
    private long paciente_id;
    private long terapeuta_id;
    private float peso;
    private String tratamento;
    private String obs;
    private String recomendacao;

    public Consulta(Date data_consulta, String descricao, long paciente_id, long terapeuta_id,
                    float peso, String tratamento, String obs, String recomendacao) {
        this.data_consulta = data_consulta;
        this.descricao = descricao;
        this.paciente_id = paciente_id;
        this.terapeuta_id = terapeuta_id;
        this.peso = peso;
        this.tratamento = tratamento;
        this.obs = obs;
        this.recomendacao = recomendacao;
    }

    public long getId() {
        return id;
    }

    public Date getData_consulta() {
        return data_consulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getPaciente_id() {
        return paciente_id;
    }

    public long getTerapeuta_id() {
        return terapeuta_id;
    }

    public float getPeso() {
        return peso;
    }

    public String getTratamento() {
        return tratamento;
    }

    public String getObs() {
        return obs;
    }

    public String getRecomendacao() {
        return recomendacao;
    }
}
