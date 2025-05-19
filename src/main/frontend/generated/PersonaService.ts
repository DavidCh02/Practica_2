import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type Persona_1 from "./com/unl/music/base/models/Persona.js";
import client_1 from "./connect-client.default.js";
async function lisAllPersona_1(init?: EndpointRequestInit_1): Promise<Array<Persona_1 | undefined> | undefined> { return client_1.call("PersonaService", "lisAllPersona", {}, init); }
export { lisAllPersona_1 as lisAllPersona };
