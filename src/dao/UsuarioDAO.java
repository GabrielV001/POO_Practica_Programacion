package dao;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO<T> {
    void insertar(T entidad) throws SQLException;
    T obtenerPorId(int id) throws SQLException;
    List<T> obtenerTodos() throws SQLException;
    void actualizar(T entidad) throws SQLException;
    void eliminar(int id) throws SQLException;
}
