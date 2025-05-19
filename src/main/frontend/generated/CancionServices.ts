import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Cancion_1 from "./com/unl/music/base/models/Cancion.js";
import client_1 from "./connect-client.default.js";
async function create_1(nombre: string | undefined, id_genero: number | undefined, duracion: number | undefined, url: string | undefined, tipo: string | undefined, id_albun: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CancionServices", "create", { nombre, id_genero, duracion, url, tipo, id_albun }, init); }
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CancionServices", "delete", { id }, init); }
async function lisAllCancion_1(init?: EndpointRequestInit_1): Promise<Array<Cancion_1 | undefined> | undefined> { return client_1.call("CancionServices", "lisAllCancion", {}, init); }
async function listCancion_1(init?: EndpointRequestInit_1): Promise<Array<Record<string, unknown> | undefined> | undefined> { return client_1.call("CancionServices", "listCancion", {}, init); }
async function listTipo_1(init?: EndpointRequestInit_1): Promise<Array<string | undefined> | undefined> { return client_1.call("CancionServices", "listTipo", {}, init); }
async function listaAlbumCombo_1(init?: EndpointRequestInit_1): Promise<Array<Record<string, unknown> | undefined> | undefined> { return client_1.call("CancionServices", "listaAlbumCombo", {}, init); }
async function listaAlbumGenero_1(init?: EndpointRequestInit_1): Promise<Array<Record<string, unknown> | undefined> | undefined> { return client_1.call("CancionServices", "listaAlbumGenero", {}, init); }
async function update_1(id: number | undefined, nombre: string | undefined, id_genero: number | undefined, duracion: number | undefined, url: string | undefined, tipo: string | undefined, id_album: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CancionServices", "update", { id, nombre, id_genero, duracion, url, tipo, id_album }, init); }
export { create_1 as create, delete_1 as delete, lisAllCancion_1 as lisAllCancion, listaAlbumCombo_1 as listaAlbumCombo, listaAlbumGenero_1 as listaAlbumGenero, listCancion_1 as listCancion, listTipo_1 as listTipo, update_1 as update };
