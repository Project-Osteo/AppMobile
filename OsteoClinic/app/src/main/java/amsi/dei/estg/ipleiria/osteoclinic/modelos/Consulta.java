package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Consulta {

    private long id;
    private Date data_consulta;
    private String descricao;
    //private long paciente_id;
    private long terapeuta_id;
    private double peso;
    private String tratamento;
    private String obs;
    private String recomendacao;

    public Consulta(long id, Date data_consulta, String descricao, long terapeuta_id,
                    double peso, String tratamento, String obs, String recomendacao) {
        this.id = id;
        this.data_consulta = data_consulta;
        this.descricao = descricao;
        this.terapeuta_id = terapeuta_id;
        this.peso = peso;
        this.tratamento = tratamento;
        this.obs = obs;
        this.recomendacao = recomendacao;
    }

    public Consulta(long id, Date data_consulta, String descricao,
                    double peso, String tratamento, String obs, String recomendacao) {
        this.id = id;
        this.data_consulta = data_consulta;
        this.descricao = descricao;
        this.peso = peso;
        this.tratamento = tratamento;
        this.obs = obs;
        this.recomendacao = recomendacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public Date getDataConsulta() { return data_consulta; }

    public String getDescricao() {
        return descricao;
    }

    public long getTerapeuta_id() { return terapeuta_id; }

    public double getPeso() {
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
