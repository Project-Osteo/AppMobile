package amsi.dei.estg.ipleiria.osteoclinic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Feedback;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class ClinicJsonParser {

    /* CONSULTAS JSON PARSER */
    public static ArrayList<Consulta> parserJsonConsultas(JSONArray resposta){
        ArrayList<Consulta> listaconsultas = new ArrayList<>();

        try {
            for(int i = 0; i < resposta.length(); i++){
                JSONObject consultajson = (JSONObject) resposta.get(i);
                long id = consultajson.getLong("id_consulta");
                String data = consultajson.getString("data_consulta");
                String descricao = consultajson.getString("descricao_consulta");
                String tratamento = consultajson.getString("tratamento");
                String obs = consultajson.getString("obs_consulta");
                String rec = consultajson.getString("recomendacao");


                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
                Date data1 = formatter.parse(data.substring(0,10));

                Consulta consulta = new Consulta(id, data1, descricao, tratamento, obs, rec);

                listaconsultas.add(consulta);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listaconsultas;
    }


    public static Consulta parserJsonConsulta(String resposta){
        Consulta consulta = null;

        try {
            JSONObject consultajson = new JSONObject(resposta);
            long id = consultajson.getLong("id_consulta");
            String data = consultajson.getString("data_consulta");
            String descricao = consultajson.getString("descricao_consulta");
            String tratamento = consultajson.getString("tratamento");
            String obs = consultajson.getString("obs_consulta");
            String rec = consultajson.getString("recomendacao");

            SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
            Date data1 = formatter.parse(data.substring(0,10));

            consulta = new Consulta(id, data1, descricao, tratamento, obs, rec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consulta;
    }


    /* TREINOS JSON PARSER */
    public static ArrayList<Treino> parserJsonTreinos(JSONArray resposta){
        ArrayList<Treino> listatreinos = new ArrayList<>();

        try{
            for(int i = 0; i < resposta.length(); i++){
                JSONObject treinoJson = (JSONObject) resposta.get(i);
                long id = treinoJson.getLong("id_treino");
                String data = treinoJson.getString("data_treino");
                String descricao = treinoJson.getString("descricao_treino");
                String tipo = treinoJson.getString("tipo_treino");
                String obs = treinoJson.getString("obs_treino");

                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
                Date data1 = formatter.parse(data.substring(0,10));

                Treino treino = new Treino(id, data1, descricao, tipo, obs);

                listatreinos.add(treino);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listatreinos;
    }

    public static Treino parserJsonTreino(String resposta){
        Treino treino = null;

        try {
            JSONObject treinoJson = new JSONObject(resposta);
            long id = treinoJson.getLong("id_treino");
            String data = treinoJson.getString("data_treino");
            String descricao = treinoJson.getString("descricao_treino");
            String tipo = treinoJson.getString("tipo_treino");
            String obs = treinoJson.getString("obs_treino");

            SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
            Date data1 = formatter.parse(data);

            treino = new Treino(id, data1, descricao, tipo, obs);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return treino;
    }


    /* Feedback JSON Parser */
    public static ArrayList<Feedback> parserJsonFeedbacks(JSONArray resposta){
        ArrayList<Feedback> listafeedback = new ArrayList<>();

        try{
            for(int i = 0; i < resposta.length(); i++){
                JSONObject feedbackJson = (JSONObject) resposta.get(i);
                long id = feedbackJson.getLong("id_feedback");
                long consulta_id = feedbackJson.getLong("consulta_id");
                //long treino_id = feedbackJson.getLong("treino_id");
                String mensagem = feedbackJson.getString("mensagem");
                String datahora = feedbackJson.getString("dataehora");

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date data1 = formatter.parse(datahora);

                Feedback feedback = new Feedback(id, consulta_id, mensagem, data1);

                listafeedback.add(feedback);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listafeedback;
    }

    public static Feedback parserJsonFeedback(String resposta){
        Feedback feedback = null;

        try{
            JSONObject feedbacksjon = new JSONObject(resposta);
            long id = feedbacksjon.getLong("id_feedback");
            long consulta_id = feedbacksjon.getLong("consulta_id");
            //long treino_id = feedbacksjon.getLong("treino_id");
            String mensagem = feedbacksjon.getString("mensagem");
            String datahora = feedbacksjon.getString("dataehora");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date data1 = formatter.parse(datahora);

            feedback = new Feedback(id, consulta_id, mensagem, data1);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return feedback;
    }



    public static boolean isConnected(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static String parserJsonLogin(String resposta){
        String token = null;

        try {
            JSONObject json = new JSONObject(resposta);
            Boolean bool = json.getBoolean("success");
            if(bool){
                token = json.getString("token");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }


}
