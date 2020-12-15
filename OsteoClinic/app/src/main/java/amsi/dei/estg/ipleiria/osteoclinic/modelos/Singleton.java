package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Singleton {
    private Utilizador user;
    private ArrayList<Consulta> lista_consultas;
    private ArrayList<Treino> lista_treinos;
    private static Singleton instance = null;

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {
        this.lista_consultas = new ArrayList<>();
        this.lista_treinos = new ArrayList<>();
        gerarFakeData();
    }


    public Consulta getConsulta(long id){
        for (Consulta consulta: this.lista_consultas){
            if(consulta.getId() == id)
                return consulta;
        }
        return null;
    }

    public Treino getTreino(long id){
        for (Treino treino: this.lista_treinos){
            if(treino.getId() == id)
                return treino;
        }
        return null;
    }


    private void gerarFakeData() {

        user = new Utilizador("mail", "pass", "nome");

        String sDate1 = "11/11/2020";
        SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date data1 = formatter.parse(sDate1);
            this.lista_consultas.add(
                    new Consulta(1,data1,
                        "peses torcidos",
                        85,
                        "massacre e uv",
                        "muito negro",
                        "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(2,data1,
                            "peses torcidos",
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(3,data1,
                            "peses torcidos",
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(4,data1,
                            "peses torcidos",
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));

            this.lista_treinos.add(
                    new Treino(data1,
                            "30 min endurance",
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(data1,
                            "30 min endurance",
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(data1,
                            "30 min endurance",
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(data1,
                            "30 min endurance",
                            "recuperar forma",
                            "durante 7 dias"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gerarFakeDataTreinos(){
        user = new Utilizador("mail", "pass", "nome");

        String sDate1 = "12/12/2020";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date data1 = formatter.parse(sDate1);
            this.lista_treinos.add(
                    new Treino((java.sql.Date) data1, "Treino para recuperar de lesão",
                            "Recuperação", "Repetir tudo 3 vezes"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Consulta> getListaConsultas() { return lista_consultas; }

    public ArrayList<Treino> getListaTreinos() {
        return lista_treinos;
    }
}
