package Models.Repository;

import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
// EXISTENCIA SOLAMENTE PARA CHEQUEAR QUE SE GUARDE LAS COSAS
public class RepoColaboradores {

    private List<Colaborador> colaboradorlist;
    private static RepoColaboradores instacia = null;

    public static RepoColaboradores getInstance(){
        if(instacia==null){
            instacia = new RepoColaboradores();
        }
        return instacia;
    }

    public void agregarColaborador(Colaborador colaborador){
        colaboradorlist.add(colaborador);
    }


    private RepoColaboradores() {
        this.colaboradorlist = new ArrayList<>();
    }


}
