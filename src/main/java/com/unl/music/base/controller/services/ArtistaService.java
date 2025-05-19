package com.unl.music.base.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.unl.music.base.controller.dao.dao_models.DaoArtista;
import com.unl.music.base.models.Artista;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.mappedtypes.Pageable;

import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@AnonymousAllowed

    

public class ArtistaService {
    
    private DaoArtista da;
    public ArtistaService() {
        da = new DaoArtista();
    }

    public void createArtista(@NotEmpty String nombre,@NotEmpty String nacionalidad) throws Exception{
        da.getObj().setNacionalidad(nacionalidad);
        da.getObj().setNombre(nombre);
        if(!da.save())
            throw new Exception("No se pudo crear el artista");
    }
    public List<Artista> list(Pageable pageable){
        return Arrays.asList(da.listAll().toArray());
    }

    public List<Artista> listAll(){
        // Corregir la conversión
        Object[] artistas = da.listAll().toArray();
        List<Artista> listaArtistas = new ArrayList<>();
        for (Object obj : artistas) {
            if (obj instanceof Artista) {
                listaArtistas.add((Artista) obj);
            }
        }
        return listaArtistas;
    }
    public List<String> listCountry(){  
        List<String> nacionalidades = new ArrayList<>();
        String [] countryCodes = Locale.getISOCountries();
        for(String countryCode : countryCodes){
            Locale locate = new Locale ("", countryCode);
            nacionalidades.add(locate.getDisplayCountry());
        }
        return nacionalidades;
    }

}
