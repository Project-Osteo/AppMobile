package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Consulta {

    private static int ID_INCREMENT = 0;

    private long id;
    private Date data_consulta;
    private String descricao;
    //private long terapeuta_id;
    private float peso;
    private String tratamento;
    private String obs;
    private String recomendacao;

    public Consulta(Date data_consulta, String descricao,
                    float peso, String tratamento, String obs, String recomendacao) {
        this.id = ++ID_INCREMENT;
        this.data_consulta = data_consulta;
        this.descricao = descricao;
//        this.terapeuta_id = terapeuta_id;
        this.peso = peso;
        this.tratamento = tratamento;
        this.obs = obs;
        this.recomendacao = recomendacao;
    }

    public long getId() {
        return id;
    }

    public Date getDataConsulta() {
        return data_consulta;
    }

    public String getDescricao() {
        return descricao;
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
