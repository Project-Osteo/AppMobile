package amsi.dei.estg.ipleiria.osteoclinic.modelos;

public class Utilizador {
    private long id;
    private String mail;
    private String password;
    private String nome;

    public Utilizador(String mail, String password, String nome) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
