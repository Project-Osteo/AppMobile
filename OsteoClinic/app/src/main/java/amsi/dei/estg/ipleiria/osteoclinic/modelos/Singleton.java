package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Singleton {
    private Utilizador user;
    private ArrayList<Consulta> lista_consultas;
    private ArrayList<Treino> lista_treinos;
    private static Singleton instance = null;
    private ClinicBDHelper clinicBDHelper = null;

    private static RequestQueue volleyQueue = null;

    //Endereços API
    public static final String mUrlAPIConsultas = "http://localhost:3001/api/consultas";
    public static final String mUrlAPIRegistar = "http://localhost:3001/api/registar";

    public static synchronized Singleton getInstance(Context context) {
        if(instance == null) {
            instance = new Singleton(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    private Singleton(Context context) {
        this.lista_consultas = new ArrayList<>();
        this.lista_treinos = new ArrayList<>();
        this.clinicBDHelper = new ClinicBDHelper(context);
        //gerarFakeData();
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


    /* ******************** MÉTODOS API ******************** */

    public void registarUtilizador(final Utilizador user, final String token, final  Context context){
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPIRegistar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);
                params.put("id", ""+user.getId());
                params.put("mail", user.getMail());
                params.put("pwd", user.getPassword());
                params.put("nome", user.getNome());
                return params;
            }
        };
    }



    /* ******************** MÉTODOS BD ******************** */
    public ArrayList<Consulta> getListaConsultas() throws ParseException {
        lista_consultas = clinicBDHelper.getAllConsultasBD();
        return lista_consultas;
    }

    public ArrayList<Treino> getListaTreinos() throws ParseException {
        lista_treinos = clinicBDHelper.getAllTreinosBD();
        return lista_treinos;
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
                            1,
                        85,
                        "massacre e uv",
                        "muito negro",
                        "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(2,data1,
                            "peses torcidos",
                            1,
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(3,data1,
                            "peses torcidos",
                            1,
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));
            this.lista_consultas.add(
                    new Consulta(4,data1,
                            "peses torcidos",
                            1,
                            85,
                            "massacre e uv",
                            "muito negro",
                            "gelo 3x dia"));

            this.lista_treinos.add(
                    new Treino(1, data1,
                            "30 min endurance",
                            1,
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(2, data1,
                            "30 min endurance",
                            1,
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(3, data1,
                            "30 min endurance",
                            1,
                            "recuperar forma",
                            "durante 7 dias"));
            this.lista_treinos.add(
                    new Treino(4, data1,
                            "30 min endurance",
                            1,
                            "recuperar forma",
                            "durante 7 dias"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
