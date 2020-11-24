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


    private Consulta getConsulta(long id){
        for (Consulta consulta: this.lista_consultas){
            if(consulta.getId() == id)
                return consulta;
        }
        return null;
    }


    private void gerarFakeData() {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse("15/06/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user = new Utilizador("mail", "pass", "nome");
        this.lista_consultas.add(new Consulta(data,"peses torcidos", 85, "massacre e uv", "muito negro", "gelo 3x dia"));

    }

    public ArrayList<Consulta> getListaConsultas() { return lista_consultas; }

}
