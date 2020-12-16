package amsi.dei.estg.ipleiria.osteoclinic.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Consulta;

public interface ConsultasListener {
    void onRefreshListaConsultas(ArrayList<Consulta> listaconsultas);
}
