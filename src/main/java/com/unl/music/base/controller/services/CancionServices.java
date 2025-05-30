package com.unl.music.base.controller.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.unl.music.base.controller.dao.dao_models.DaoAlbum;
import com.unl.music.base.controller.dao.dao_models.DaoBanda;
import com.unl.music.base.controller.dao.dao_models.DaoCancion;
import com.unl.music.base.controller.dao.dao_models.DaoGenero;
import com.unl.music.base.controller.data_struct.list.LinkedList;
import com.unl.music.base.models.Album;
import com.unl.music.base.models.Banda;
import com.unl.music.base.models.Cancion;
import com.unl.music.base.models.Genero;
import com.unl.music.base.models.RolArtistaEnum;
import com.unl.music.base.models.TipoArchivoEnum;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@AnonymousAllowed
public class CancionServices {
    private DaoCancion db;
    public CancionServices(){
        db = new DaoCancion();
    }

    public void create(@NotEmpty String nombre, Integer id_genero, Integer duracion, 
    @NotEmpty String url, @NotEmpty String tipo, Integer id_albun) throws Exception {
        if(nombre.trim().length() > 0 && url.trim().length() > 0 && 
        tipo.trim().length() > 0 && duracion > 0 && id_genero > 0 && id_albun > 0) {
            db.getObj().setNombre(nombre);
            db.getObj().setDuracion(duracion); // Verify this line
            db.getObj().setId_album(id_albun);
            db.getObj().setId_genero(id_genero);
            db.getObj().setTipo(TipoArchivoEnum.valueOf(tipo));
            db.getObj().setUrl(url);
            if(!db.save())
                throw new  Exception("No se pudo guardar los datos de la banda");
        }
    }

                public void update(Integer id, @NotEmpty String nombre, Integer id_genero, Integer duracion, 
                @NotEmpty String url, @NotEmpty String tipo, Integer id_album) throws Exception {
            if (nombre.trim().length() > 0 && url.trim().length() > 0 && 
            tipo.trim().length() > 0 && duracion > 0 && id_genero > 0 && id_album > 0) {

            // Recupera la canción existente
            Cancion cancion = db.listAll().get(id - 1);
            cancion.setNombre(nombre);
            cancion.setDuracion(duracion);
            cancion.setId_album(id_album);
            cancion.setId_genero(id_genero);
            cancion.setTipo(TipoArchivoEnum.valueOf(tipo));
            cancion.setUrl(url);

            db.setObj(cancion); // Establece el objeto actualizado
            if (!db.update(id - 1)) { // Usa la posición correcta (índice)
            throw new Exception("Error al actualizar la canción");
            }
            }
            }

    public List<Cancion> lisAllCancion(){
        return Arrays.asList(db.listAll().toArray());
        
    }


    public List<HashMap> listaAlbumCombo() {
        List<HashMap> lista = new ArrayList<>();
        DaoAlbum da = new DaoAlbum();
        if(!da.listAll().isEmpty()) {
            Album [] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i)); 
                aux.put("label", arreglo[i].getNombre());   
                lista.add(aux); 
            }
        }
        return lista;
    }

    public List<HashMap> listaAlbumGenero() {
        List<HashMap> lista = new ArrayList<>();
        DaoGenero da = new DaoGenero();
        if(!da.listAll().isEmpty()) {
            Genero [] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i)); 
                aux.put("label", arreglo[i].getNombre()); 
                lista.add(aux);  
            }
        }
        return lista;
    }

    public List<String> listTipo() {
        List<String> lista = new ArrayList<>();
        for(TipoArchivoEnum r: TipoArchivoEnum.values()) {
            lista.add(r.toString());
        }        
        return lista;
    }

    public List<HashMap> listCancion(){
        List<HashMap> lista = new ArrayList<>();
        if(!db.listAll().isEmpty()) {
            Cancion [] arreglo = db.listAll().toArray();           
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("id", arreglo[i].getId().toString());
                aux.put("nombre", arreglo[i].getNombre());
                aux.put("genero", new DaoGenero().listAll().get(arreglo[i].getId_genero() -1).getNombre());
                aux.put("id_genero", arreglo[i].getId_genero().toString());
                aux.put("albun", new DaoAlbum().listAll().get(arreglo[i].getId_album() -1).getNombre());
                aux.put("id_albun", arreglo[i].getId_album().toString());
                aux.put("url", arreglo[i].getUrl());
                aux.put("tipo", arreglo[i].getTipo().toString());
                aux.put("duracion", arreglo[i].getDuracion().toString()); // Fixed duration conversion
                lista.add(aux);
            }
        }
        return lista;
    }

    public void delete(Integer id) throws Exception {
        try {
            if (id == null || id <= 0) {
                throw new Exception("ID de canción inválido o nulo");
            }
    
            // Validamos que exista antes de borrar
            LinkedList<Cancion> lista = db.listAll();
            Integer index = id - 1;
    
            if (index < 0 || index >= lista.getLength()) {
                throw new Exception("Índice fuera de rango: " + index);
            }
    
            if (!db.delete(index)) {
                throw new Exception("No se pudo eliminar la canción con ID: " + id);
            }
    
        } catch (Exception e) {
            throw new Exception("No se pudo eliminar la canción: " + e.getMessage());
        }
    }
}
