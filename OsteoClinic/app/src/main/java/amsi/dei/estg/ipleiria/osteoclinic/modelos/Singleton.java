package amsi.dei.estg.ipleiria.osteoclinic.modelos;

import java.util.ArrayList;

public class Singleton {
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

    private void gerarFakeData() {
    }
}
