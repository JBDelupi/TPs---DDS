package Models.Repository;

import java.util.List;

public interface Dao {

    List buscarTodos();
    Object buscar(int id);
    void agregar(Object unObjeto);
    void modificar(Object unObjeto);
    void eliminar(Object unObjeto);

}
