package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;

public class Feedback {

    private long id;
    private long user_id;
    private long consulta_id;
    private long treino_id;
    private String mensagem;
    private Date datahora;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

