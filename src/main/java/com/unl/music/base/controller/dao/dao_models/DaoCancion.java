package com.unl.music.base.controller.dao.dao_models;

import java.util.Scanner;

import com.unl.music.base.controller.dao.AdapterDao;
import com.unl.music.base.models.Cancion;
import com.unl.music.base.models.Album;
import com.unl.music.base.controller.data_struct.list.LinkedList;
import com.unl.music.base.controller.dao.dao_models.DaoAlbum;

public class DaoCancion extends AdapterDao<Cancion> {
    private Cancion obj;

    public DaoCancion() {
        super(Cancion.class);
    }

    public Cancion getObj() {
        if (obj == null) {
            this.obj = new Cancion();
        }
        return this.obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }

    // === OPERACIONES CRUD USANDO AdapterDao ===

    public Boolean save() {
        try {
            obj.setId(listAll().getLength() + 1); // Asignar ID automáticamente
            super.persist(obj);
            return true;
        } catch (Exception e) {
            System.out.println("    Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public Boolean update(Integer id) {
        try {
            LinkedList<Cancion> lista = listAll();
            if (id >= 0 && id < lista.getLength()) {
                obj.setId(id); // ✅ Usa el mismo índice como ID
                super.update(obj, id); // Llama al método update correcto
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public LinkedList<Cancion> listAll() {
        try {
            LinkedList<Cancion> lista = super.listAll();
            if (lista == null) {
                return new LinkedList<>();
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // Método para obtener una canción por ID
@Override
public boolean delete(Integer id) {
    try {
        LinkedList<Cancion> lista = listAll();
        if (id >= 0 && id < lista.getLength()) {
            super.delete(id); // Ahora sí funciona
            return true;
        }
        return false;
    } catch (Exception e) {
        System.out.println("Error al eliminar: " + e.getMessage());
        return false;
    }
}

    public Cancion getById(Integer id) {
        try {
            LinkedList<Cancion> canciones = listAll();
            if (id >= 0 && id < canciones.getLength()) {
                return canciones.get(id);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

    