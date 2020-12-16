package amsi.dei.estg.ipleiria.osteoclinic.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;
import amsi.dei.estg.ipleiria.osteoclinic.modelos.Treino;

public interface TreinosListener {
    void onRefreshListaTreinos(ArrayList<Treino> listatreinos);
}
