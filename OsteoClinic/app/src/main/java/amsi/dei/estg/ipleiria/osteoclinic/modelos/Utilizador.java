package amsi.dei.estg.ipleiria.osteoclinic.modelos;

public class Utilizador {
    private long id;
    private String mail;
    private String password;
    private String token;

    public Utilizador(String mail, String password, String token) {
        this.mail = mail;
        this.password = password;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
