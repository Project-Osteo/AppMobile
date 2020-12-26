package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.app.DownloadManager;
import android.content.Context;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.osteoclinic.listeners.ConsultasListener;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.TreinosListener;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ConsultasJsonParser;
import amsi.dei.estg.ipleiria.osteoclinic.utils.TreinosJsonParser;

public class Singleton implements ConsultasListener, TreinosListener {
    private Utilizador user;
    private ArrayList<Consulta> lista_consultas;
    private ArrayList<Treino> lista_treinos;
    private static Singleton instance = null;

    private ClinicBDHelper clinicBDHelper = null;

    private static RequestQueue volleyQueue = null;

    private ConsultasListener consultasListener;
    private TreinosListener treinosListener;

    //Endereços API
    private static final String host = "10.0.2.2";
    private static final String port = ":3001";
    public static final String mUrlAPIConsultas = "http://10.0.2.2:3001/consultas";
    public static final String mUrlAPITreinos = "http://10.0.2.2:3001/treinos";
    public static final String mUrlAPIRegistar = host + port + "/api/registar";

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


    // get lista consultas
    public void getAllConsultasAPI(final Context contexto) {
        if(!ConsultasJsonParser.isConnected(contexto)){
            Toast.makeText(contexto, "Não tem internet!", Toast.LENGTH_SHORT).show();
            if(consultasListener != null)
                try {
                    consultasListener.onRefreshListaConsultas(getListaConsultasBD());
                }catch(ParseException e){
                    e.printStackTrace();
                }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    mUrlAPIConsultas, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            lista_consultas = ConsultasJsonParser.parserJsonConsultas(response);
                            adicionarConsultasBD(lista_consultas);

                            if (consultasListener != null) {
                                consultasListener.onRefreshListaConsultas(lista_consultas);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }

    // get lista treinos
    public void getAllTreinosAPI(final Context contexto) {
        if(!TreinosJsonParser.isConnected(contexto)){
            Toast.makeText(contexto, "Não tem internet", Toast.LENGTH_SHORT).show();
            if(treinosListener != null)
                try {
                    treinosListener.onRefreshListaTreinos(getListaTreinosBD());
                }catch (ParseException e){
                    e.printStackTrace();
                }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    mUrlAPITreinos, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            lista_treinos = TreinosJsonParser.parserJsonTreinos(response);
                            adicionarTreinosBD(lista_treinos);

                            if (treinosListener != null) {
                                treinosListener.onRefreshListaTreinos(lista_treinos);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            volleyQueue.add(request);
        }
    }



    /* ******************** MÉTODOS BD Consultas******************** */

    public ArrayList<Consulta> getListaConsultasBD() throws ParseException {
        lista_consultas = clinicBDHelper.getAllConsultasBD();
        return lista_consultas;
    }

    public void adicionarConsultasBD(ArrayList<Consulta> lista) {
        clinicBDHelper.removeAllConsultasBD();
        for(Consulta c: lista)
            clinicBDHelper.adicionarConsultaBD(c);
    }

    public void adicionarConsultaBD(Consulta consulta){
        clinicBDHelper.adicionarConsultaBD(consulta);
    }


    public boolean editarConsultaBD(Consulta consulta){

        Consulta atual = getConsulta(consulta.getId());

        if(atual != null){
            boolean alterou = clinicBDHelper.editarConsultaBD(atual);
            if(alterou == true){
                atual.setDescricao_consulta(consulta.getDescricao_consulta());
                atual.setPeso(consulta.getPeso());
                atual.setTratamento(consulta.getTratamento());
                atual.setObs_consulta(consulta.getObs_consulta());
                atual.setRecomendacao(consulta.getRecomendacao());
            }
        }
        return false;
    }

    public boolean removerConsultaBD(long id){
        Consulta consulta = getConsulta(id);
        if(consulta != null){
            boolean removeu = clinicBDHelper.removerConsultaBD(id);
            if(removeu == true){
                this.lista_consultas.remove(consulta);
                return true;
            }
        }
        return false;
    }

    /* ******************** MÉTODOS BD treinos******************** */

    public ArrayList<Treino> getListaTreinosBD() throws ParseException {
        lista_treinos = clinicBDHelper.getAllTreinosBD();
        return lista_treinos;
    }

    public void adicionarTreinosBD(ArrayList<Treino> lista) {
        clinicBDHelper.removeAllTreinosBD();
        for(Treino t: lista)
            clinicBDHelper.adicionarTreinoBD(t);
    }

    public void adicionarTreinoBD(Treino treino){
        clinicBDHelper.adicionarTreinoBD(treino);
    }

    public boolean editarTreinoBD(Treino treino){

        Treino atual = getTreino(treino.getId());

        if(atual != null){
            boolean alterou = clinicBDHelper.editarTreinoBD(atual);
            if(alterou == true){
                atual.setDescricao_treino(treino.getDescricao_treino());
                atual.setTipo_treino(treino.getTipoTreino());
                atual.setObs_treino(treino.getObs_treino());
            }
        }
        return false;
    }



    public void setConsultasListener(ConsultasListener consultasListener) {
        this.consultasListener = consultasListener;
    }

    public void setTreinosListener (TreinosListener treinosListener) {
        this.treinosListener = treinosListener;
    }

    @Override
    public void onRefreshListaConsultas(ArrayList<Consulta> listaconsultas) {

    }

    @Override
    public void onRefreshListaTreinos(ArrayList<Treino> listatreinos) {

    }

    public Date stringToDate(String str){
        try {
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.000Z'");
            Date date = sdfSource.parse(str);
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
            str = sdfDestination.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }


//    private void gerarFakeData() {
//
//        user = new Utilizador("mail", "pass", "nome");
//
//        String sDate1 = "11/11/2020";
//        SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
//
//        try {
//            Date data1 = formatter.parse(sDate1);
//            this.lista_consultas.add(
//                    new Consulta(1,data1,
//                            "peses torcidos",
//                            1,
//                            85,
//                            "massacre e uv",
//                            "muito negro",
//                            "gelo 3x dia"));
//            this.lista_consultas.add(
//                    new Consulta(2,data1,
//                            "peses torcidos",
//                            1,
//                            85,
//                            "massacre e uv",
//                            "muito negro",
//                            "gelo 3x dia"));
//            this.lista_consultas.add(
//                    new Consulta(3,data1,
//                            "peses torcidos",
//                            1,
//                            85,
//                            "massacre e uv",
//                            "muito negro",
//                            "gelo 3x dia"));
//            this.lista_consultas.add(
//                    new Consulta(4,data1,
//                            "peses torcidos",
//                            1,
//                            85,
//                            "massacre e uv",
//                            "muito negro",
//                            "gelo 3x dia"));
//
//            this.lista_treinos.add(
//                    new Treino(1, 1, data1,
//                            "30 min endurance",
//                            "recuperar forma",
//                            "durante 7 dias"));
//            this.lista_treinos.add(
//                    new Treino(2, 1, data1,
//                            "30 min endurance",
//                            "recuperar forma",
//                            "durante 7 dias"));
//            this.lista_treinos.add(
//                    new Treino(3, 1, data1,
//                            "30 min endurance",
//                            "recuperar forma",
//                            "durante 7 dias"));
//            this.lista_treinos.add(
//                    new Treino(4, 1, data1,
//                            "30 min endurance",
//                            "recuperar forma",
//                            "durante 7 dias"));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
