package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Feedback {

    private long id;
    private long consulta_id;
    //private long treino_id;
    private String mensagem;
    private Date datahora;

    public Feedback(long id, long consulta_id, String mensagem, Date datahora) {
        this.id = id;
        this.consulta_id = consulta_id;
        this.mensagem = mensagem;
        this.datahora = datahora;
    }

    public Feedback(long id, long consulta_id, String mensagem){
        this.id = id;
        this.consulta_id = consulta_id;
        this.mensagem = mensagem;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConsulta_id() {
        return consulta_id;
    }

    /*public long getTreino_id() {
        return treino_id;
    }*/

    public void setConsulta_id(long consulta_id) {
        this.consulta_id = consulta_id;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDatahora() {
        return datahora;
    }
    
}

