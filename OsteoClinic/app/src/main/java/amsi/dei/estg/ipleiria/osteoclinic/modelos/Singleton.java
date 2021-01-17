package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import android.content.Context;
import android.media.session.MediaSession;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.osteoclinic.listeners.ConsultasListener;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.FeedbacksListener;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.LoginListener;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.PacientesListener;
import amsi.dei.estg.ipleiria.osteoclinic.listeners.TreinosListener;
import amsi.dei.estg.ipleiria.osteoclinic.utils.ClinicJsonParser;

public class Singleton {
    private static final int ADICIONAR_FEEDBACK_BD = 1;
    private static final int EDITAR_FEEDBACK_BD = 2;
    private static final int REMOVER_FEEDBACK_BD = 3;

    private ArrayList<Consulta> lista_consultas;
    private ArrayList<Treino> lista_treinos;
    private ArrayList<Feedback> lista_feedback;
    private static Singleton instance = null;

    private ClinicBDHelper clinicBDHelper = null;

    private static RequestQueue volleyQueue = null;

    private ConsultasListener consultasListener;
    private TreinosListener treinosListener;
    private LoginListener loginListener;
    private FeedbacksListener feedbacksListener;
    private PacientesListener pacientesListener;

    //Endereços API
    private static final String host = "10.0.2.2";
    private static final String port = ":3001";
    public static final String mUrlAPIListaConsultas = "http://10.0.2.2:3001/consultas/";
    public static final String mUrlAPIListaTreinos = "http://10.0.2.2:3001/treinos/";
    public static final String mUrlAPIListaFeedback = "http://10.0.2.2:3001/feedbacks/";
    public static final String mUrlAPIRegistarUtilizador = "http://10.0.2.2:3001/utilizadores/registar/";
    public static final String mUrlAPILogin = "http://10.0.2.2:3001/utilizadores/login/";
    public static final String mUrlAPIPacientes = "http://10.0.2.2:3001/pacientes/";

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
        this.lista_feedback = new ArrayList<>();
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

    public Feedback getFeedback(long id){
        for (Feedback feedback: this.lista_feedback){
            if(feedback.getId() == id)
                return feedback;
        }
        return null;
    }


    /* ******************** MÉTODOS API ******************** */
    public void registarUtilizador(final  Context context, final String email, final String password){
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPIRegistarUtilizador,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginListener.onValidateRegisto(ClinicJsonParser.parserJsonRegisto(response), email);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("mail", email);
                        params.put("pwd", password);
                        return params;
                    }
                };
        volleyQueue.add(request);
    }


    public void loginAPI(final Context context, final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPILogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginListener.onValidateLogin(ClinicJsonParser.parserJsonLogin(response), email);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mail", email);
                params.put("pwd", password);
                return params;
            }
        };
        volleyQueue.add(request);
    }



    // get lista consultas
    public void getAllConsultasAPI(final Context contexto, final long id) {
        if(!ClinicJsonParser.isConnected(contexto)){
            Toast.makeText(contexto, "Não tem internet!", Toast.LENGTH_SHORT).show();
            if(consultasListener != null) {
                try {
                    consultasListener.onRefreshListaConsultas(getListaConsultasBD());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    mUrlAPIPacientes + id + "/consultas", null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            lista_consultas = ClinicJsonParser.parserJsonConsultas(response);
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
    public void getAllTreinosAPI(final Context contexto, final long id) {
        if(!ClinicJsonParser.isConnected(contexto)){
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
                    mUrlAPIPacientes + id + "/treinos", null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            lista_treinos = ClinicJsonParser.parserJsonTreinos(response);
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

    //get paciente by user_id
    public void getPacienteAPI(final Context contexto, long user_id) {
        if(!ClinicJsonParser.isConnected(contexto)){
            Toast.makeText(contexto, "Não tem internet", Toast.LENGTH_SHORT).show();
        }else{
            StringRequest request = new StringRequest(Request.Method.GET,
                    mUrlAPIPacientes + user_id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Paciente paciente = ClinicJsonParser.parserJsonPaciente(response);
                            if(pacientesListener != null){
                                pacientesListener.onConfirmPaciente(paciente);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            volleyQueue.add(request);
        }
    }

    //get lista feedback
    public void getAllFeedbacksAPI(final Context contexto, long consulta_id){
        if(!ClinicJsonParser.isConnected(contexto)){
            Toast.makeText(contexto, "Não tem internet", Toast.LENGTH_SHORT).show();
            if(feedbacksListener != null)
                try{
                    feedbacksListener.onRefreshListaFeedbacks(getListaFeedbackBD());
                }catch (ParseException e){
                    e.printStackTrace();
                }
        }
        else{
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                    mUrlAPIListaFeedback + "consulta/" + consulta_id, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            lista_feedback = ClinicJsonParser.parserJsonFeedbacks(response);
                            adicionarFeedbacksBD(lista_feedback);

                            if (feedbacksListener != null) {
                                feedbacksListener.onRefreshListaFeedbacks(lista_feedback);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            volleyQueue.add(request);
        }
    }

    //adicionar feedback api
    public void adicionarFeedbackAPI(final Context contexto, final Feedback feedback){
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPIListaFeedback + "consulta/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onUpdateListaFeedbackBD(ClinicJsonParser.parserJsonFeedback(response), ADICIONAR_FEEDBACK_BD);

                        if (feedbacksListener != null) {
                            feedbacksListener.onRefreshDetalhes();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("consulta_id", feedback.getConsulta_id()+"");
                parametros.put("mensagem", feedback.getMensagem());
                return parametros;
            }
        };
        volleyQueue.add(request);
    }

    //update feedback api
    public void editarFeedbackAPI(final Context contexto, final Feedback feedback){
        StringRequest request = new StringRequest(Request.Method.PATCH,
                mUrlAPIListaFeedback + feedback.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onUpdateListaFeedbackBD(ClinicJsonParser.parserJsonFeedback(response), EDITAR_FEEDBACK_BD);

                        if (feedbacksListener != null) {
                            feedbacksListener.onRefreshDetalhes();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("consulta_id", feedback.getConsulta_id()+"");
                parametros.put("mensagem", feedback.getMensagem());
                return parametros;
            }
        };
        volleyQueue.add(request);
    }

    //remover feedback api
    public void removerFeedbackAPI(final Feedback feedback, final Context contexto){
        StringRequest request = new StringRequest(Request.Method.DELETE,
                mUrlAPIListaFeedback + feedback.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onUpdateListaFeedbackBD(feedback, REMOVER_FEEDBACK_BD);

                        if (feedbacksListener != null) {
                            feedbacksListener.onRefreshDetalhes();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        volleyQueue.add(request);
    }


    //adicionar dados de paciente
    public void adicionarPacienteAPI(final Paciente paciente, final Context context, final long user_id){
        StringRequest request = new StringRequest(Request.Method.POST,
                mUrlAPIPacientes + user_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nome", paciente.getNome());
                parametros.put("sexo", paciente.getSexo());
                parametros.put("nacionalidade", paciente.getNacionalidade());
                parametros.put("localidade", paciente.getLocalidade());
                parametros.put("telemovel", ""+ paciente.getTelemovel());
                parametros.put("peso", String.format("%4.1f", paciente.getPeso()));
                parametros.put("altura", String.format("%3.2f", paciente.getAltura()));
                return parametros;
            }
        };

        volleyQueue.add(request);
    }

    //editar os dados do paciente
    public void editarPacienteAPI(final Paciente paciente, final Context context, final long id_paciente){
        StringRequest request = new StringRequest(Request.Method.PATCH,
                mUrlAPIPacientes + id_paciente,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nome", paciente.getNome());
                parametros.put("sexo", paciente.getSexo());
                parametros.put("nacionalidade", paciente.getNacionalidade());
                parametros.put("localidade", paciente.getLocalidade());
                parametros.put("telemovel", ""+ paciente.getTelemovel());
                parametros.put("peso", String.format("%4.1f", paciente.getPeso()));
                parametros.put("altura", String.format("%3.2f", paciente.getAltura()));
                return parametros;
            }
        };

        volleyQueue.add(request);
    }


    private void onUpdateListaFeedbackBD(Feedback feedback, int operacao) {
        switch (operacao){
            case ADICIONAR_FEEDBACK_BD:
                adicionarFeedbackBD(feedback);
                break;

            case EDITAR_FEEDBACK_BD:
                editarFeedbackBD(feedback);
                break;

            case REMOVER_FEEDBACK_BD:
                removerFeedbackBD(feedback.getId());
                break;
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

    /* ******************** Métodos BD feedback ************************ */
    public ArrayList<Feedback> getListaFeedbackBD() throws ParseException {
        lista_feedback = clinicBDHelper.getAllFeedbackBD();
        return lista_feedback;
    }

    public void adicionarFeedbacksBD(ArrayList<Feedback> lista){
        clinicBDHelper.removeAllFeedbackBD();
        for(Feedback f: lista)
            clinicBDHelper.adcionarFeedbackBD(f);
    }

    public void adicionarFeedbackBD(Feedback feedback){
        clinicBDHelper.adcionarFeedbackBD(feedback);
    }

    public boolean editarFeedbackBD(Feedback feedback){
        Feedback atual = getFeedback(feedback.getId());

        if(atual != null){
            boolean alterou = clinicBDHelper.editarFeedbackBD(atual);
            if(alterou == true){
                atual.setMensagem(feedback.getMensagem());
            }
        }
        return false;
    }

    public boolean removerFeedbackBD(long id){
        Feedback feedback = getFeedback(id);
        if(feedback != null){
            boolean removeu = clinicBDHelper.removerFeedbackBD(id);
            if(removeu == true){
                this.lista_feedback.remove(feedback);
                return true;
            }
        }
        return false;
    }


    public void setConsultasListener(ConsultasListener consultasListener) {
        this.consultasListener = consultasListener;
    }

    public void setTreinosListener(TreinosListener treinosListener) {
        this.treinosListener = treinosListener;
    }

    public void setFeedbackListener(FeedbacksListener feedbackListener){
        this.feedbacksListener = feedbackListener;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setPacientesListener(PacientesListener pacientesListener) {
        this.pacientesListener = pacientesListener;
    }


//    public Date stringToDate(String str){
//        try {
//            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'.000Z'");
//            Date date = sdfSource.parse(str);
//            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
//            str = sdfDestination.format(date);
//        }catch(ParseException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

}
