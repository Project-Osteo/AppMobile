package amsi.dei.estg.ipleiria.osteoclinic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
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
                double peso = consultajson.getDouble("peso");
                String tratamento = consultajson.getString("tratamento");
                String obs = consultajson.getString("obs_consulta");
                String rec = consultajson.getString("recomendacao");


                SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
                Date data1 = formatter.parse(data.substring(0,10));

                Consulta consulta = new Consulta(id, data1, descricao, peso, tratamento, obs, rec);

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
            double peso = consultajson.getDouble("peso");
            String tratamento = consultajson.getString("tratamento");
            String obs = consultajson.getString("obs_consulta");
            String rec = consultajson.getString("recomendacao");

            SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
            Date data1 = formatter.parse(data.substring(0,10));

            consulta = new Consulta(id, data1, descricao, peso, tratamento, obs, rec);
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

            SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
            Date data1 = formatter.parse(data);

            treino = new Treino(id, data1, descricao, tipo, obs);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return treino;
    }


    public static boolean isConnected(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
