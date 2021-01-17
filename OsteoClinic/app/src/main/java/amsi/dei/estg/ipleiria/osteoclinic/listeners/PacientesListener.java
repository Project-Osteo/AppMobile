package amsi.dei.estg.ipleiria.osteoclinic.listeners;

import amsi.dei.estg.ipleiria.osteoclinic.modelos.Paciente;

public interface PacientesListener {

    void onAddPaciente();
    void onPatchPaciente();
    void onConfirmPaciente(Paciente paciente);
}
