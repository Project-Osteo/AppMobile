package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Consulta {

    private long id;
    private Date data_consulta;
    private String descricao_consulta;
    //private long paciente_id;
    private double peso;
    private String tratamento;
    private String obs_consulta;
    private String recomendacao;

    public Consulta(long id, Date data_consulta, String descricao,
                    double peso, String tratamento, String obs, String recomendacao) {
        this.id = id;
        this.data_consulta = data_consulta;
        this.descricao_consulta = descricao;
        this.peso = peso;
        this.tratamento = tratamento;
        this.obs_consulta = obs;
        this.recomendacao = recomendacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public Date getDataConsulta() { return data_consulta; }

    public String getDescricao_consulta() {
        return descricao_consulta;
    }

    public double getPeso() {
        return peso;
    }

    public String getTratamento() {
        return tratamento;
    }

    public String getObs_consulta() {
        return obs_consulta;
    }

    public String getRecomendacao() {
        return recomendacao;
    }


}
