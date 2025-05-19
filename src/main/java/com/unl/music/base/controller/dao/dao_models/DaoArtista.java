package com.unl.music.base.controller.dao.dao_models;

import com.unl.music.base.controller.dao.AdapterDao;
import com.unl.music.base.models.Artista;

public class DaoArtista extends AdapterDao<Artista> {
    private Artista obj;

    public DaoArtista(){
        super(Artista.class);

    }

    public Artista getObj() {
        if (obj == null) {
            this.obj = new Artista();
        }
        return this.obj;
    }

    public void setObj(Artista obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {
            //LOG DE ERROR
            e.printStackTrace();
            System.out.println(e);
            return false;
            // TODO: handle exception
        }

    }


public static void main(String[] args) {
        DaoArtista da = new DaoArtista();
        da.getObj().setId(da.listAll().getLength()+1);
        da.getObj().setNacionalidad("Ecuatoriana");
        da.getObj().setNombre("Viviana Cordova");
        if (da.save()) {
            System.out.println("GUARDADO");
        }else{
            System.out.println("HUBO UN ERROR");
        }
    }
}
