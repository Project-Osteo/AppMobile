package amsi.dei.estg.ipleiria.osteoclinic.modelos;

public class Paciente {
    private long id;
    private long user_id;
    private String nome;
    private String sexo;
    private String nacionalidade;
    private String localidade;
    private int telemovel;
    private double peso;
    private double altura;

    public Paciente (long id, long user_id, String nome, String sexo, String nacionalidade,
                     String localidade, int telemovel, double peso, double altura) {

        this.id = id;
        this.user_id = user_id;
        this.nome = nome;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.localidade = localidade;
        this.telemovel = telemovel;
        this.peso = peso;
        this.altura = altura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
