package amsi.dei.estg.ipleiria.osteoclinic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public class TreinosJsonParser {
    public static ArrayList<Treino> parserJsonTreinos(JSONArray resposta){
        ArrayList<Treino> listatreinos = new ArrayList<>();

        try{
            for(int i = 0; i < resposta.length(); i++){
                JSONObject treinoJson = (JSONObject) resposta.get(i);
                long id = treinoJson.getLong("id");
                long terapeuta = treinoJson.getLong("terapeuta_id");
                String data = treinoJson.getString("data_consulta");
                String descricao = treinoJson.getString("descricao");
                String tipo_treino = treinoJson.getString("tipo_treino");
                String obs = treinoJson.getString("obs");

                SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
                Date data1 = formatter.parse(data);

                Treino treino = new Treino(id, terapeuta, data1, descricao, tipo_treino, obs);

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
            long id = treinoJson.getLong("id");
            long terapeuta = treinoJson.getLong("terapeuta_id");
            String data = treinoJson.getString("data_consulta");
            String descricao = treinoJson.getString("descricao");
            String tipo_treino = treinoJson.getString("tipo_treino");
            String obs = treinoJson.getString("obs");

            SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
            Date data1 = formatter.parse(data);

            treino = new Treino(id, terapeuta, data1, descricao, tipo_treino, obs);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return treino;
    }

    public static boolean isConnected(Context contexto){
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo !=null && networkInfo.isConnected();
    }
}
