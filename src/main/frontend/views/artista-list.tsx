


import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, ComboBox, DatePicker, Dialog, Grid, GridColumn, GridItemModel, TextField, VerticalLayout } from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';

import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';

import { useDataProvider } from '@vaadin/hilla-react-crud';
import { ArtistaService } from 'Frontend/generated/endpoints';

import Artista from 'Frontend/generated/com/unl/music/base/models/Artista';
import { useEffect } from 'react';


export const config: ViewConfig = {
  title: 'Artista',
  menu: {
    icon: 'vaadin:clipboard-check',
    order: 2,
    title: 'Artista',
  },
};

type ArtistaEntryFormProps = {
  onArtistaCreated? : () => void;
}

//Guardar artista

function ArtistaEntryForm(props : ArtistaEntryFormProps){
  const nombre = useSignal('');
  const nacionalidad = useSignal('');
  const createArtista = async () => {
      try {
        if (nombre.value.trim().length > 0 && nacionalidad.value.trim().length > 0) {
          await ArtistaService.createArtista(nombre.value, nacionalidad.value);
          if (props.onArtistaCreated) {
            props.onArtistaCreated()
          }
          nombre.value = '';
          nacionalidad.value = '';
          dialogOpened.value = false;
          Notification.show('Artista creado', {duration: 3000, position: 'bottom-end', theme:'sucess'})
        }
        else{
          Notification.show('No se pudo crear, faltan datos', { duration: 3000, position:'top-center', theme:'error'})
        }
      } catch (error) {
        console.log(error);
        handleError(error);
      }
  };
  let pais = useSignal<String[]>([]);
  useEffect(()=> {
    ArtistaService.listCountry().then(data =>
      pais.value = data
    );
  }, []);
  const dialogOpened = useSignal(false);
  return (
    <>
      <Dialog
        modeless
        headerTitle={"Nuevo artista"}
        opened={dialogOpened.value}
        onOpenedChanged={({detail}) =>{
          dialogOpened.value = detail.value;
        }}
    footer = {
      <>
      <Button
        onClick={() =>{
          dialogOpened.value = false;
        }}
      >
        Cancelar
      </Button>
      <Button onClick={createArtista} theme="primary">
          Registrar
      </Button>
      </>
    }
  >
    <VerticalLayout style={{alignItems:'strech', with:'18rem', maxWidth:'100%'}}>
    <TextField label= "Nombre del artista"
      placeholder="Ingrese el nombre del artista"
      aria-label="Nombre del artista"
      value={nombre.value}
      onValueChanged={(evt)=> (nombre.value= evt.detail.value)}
    />
    <ComboBox label="Nacionalidad"
    items={pais.value}
    placeholder='Seleccione un pais'
    aria-label='Seleccione un pais de la lista'
    value={nacionalidad.value}
    onValueChanged={(evt)=> (nacionalidad.value= evt.detail.value)}
    />
    </VerticalLayout>
  </Dialog>
  <Button
    onClick={() => {
    dialogOpened.value = true;
    }}
    >
      Agregar
      </Button>
    </>
  );
}

const dateFormatter = new Intl.DateTimeFormat(undefined, {
  dateStyle: 'medium',
});

//LISTA DE ARTISTAS

export default function ArtistaView() {
  const dataProvider = useDataProvider<Artista>({
    list: () => ArtistaService.listAll(),
  });

function indexLink({item}: {item:Artista}){
    return(
        <span>
        <Button>
            Editar
        </Button>
        </span>
    );
}

function indexIndex({model}: {model:GridItemModel<Artista>}){
  return(
      <span>
        {model.index + 1}
      </span>
  );
}

  return (
    <main className="w-full h-full flex flex-col box-border gap-s p-m">
      <ViewToolbar title="Lista de Artistas">
        <Group>
         <ArtistaEntryForm onArtistaCreated={dataProvider.refresh}/>
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn renderer={indexIndex} header="Nro" />
        <GridColumn path="nombre" header="Nombre del Artista" />
        <GridColumn path="nacionalidad" header="Nacionalidad" />
        <GridColumn header="Acciones" renderer={indexLink} />
      </Grid>
    </main>
  );
}