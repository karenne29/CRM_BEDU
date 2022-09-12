package com.example.modulo1_sesion1_postwork.controllers;

import com.example.modulo1_sesion1_postwork.Controllers.ClienteController;
import com.example.modulo1_sesion1_postwork.model.ClienteModel;
import com.example.modulo1_sesion1_postwork.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void getCliente() throws Exception {
        given(clienteService.obtenCliente(anyLong())).willReturn(Optional.of(ClienteModel.builder().id(1L).nombre("Nombre").correoContacto("cliente@contacto.com").build()));

        mockMvc.perform(get("/cliente/{clienteId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correoContacto", is("cliente@contacto.com")))
                .andExpect(jsonPath("$.nombre", is("Nombre")))

                /*.andDo(document("cliente/get-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        )));*/

                .andDo(document("cliente/get-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Identificador del cliente"),
                                fieldWithPath("nombre").description("Nombre del cliente"),
                                fieldWithPath("correoContacto").description("Correo de contacto del cliente"),
                                fieldWithPath("numeroEmpleados").description("Número de trabajadores del cliente"),
                                fieldWithPath("direccion").description("Domicilio del cliente")
                        )));
    }

   /* @Test
    void getClientes() throws Exception {

        List<ClienteModel> clientes = Arrays.asList(
                ClienteModel.builder().id(1L).nombre("Nombre 1").direccion("Direccion 1").numeroEmpleados(10).correoContacto("contacto@cliente1.com").build(),
                ClienteModel.builder().id(2L).nombre("Nombre 2").direccion("Direccion 2").numeroEmpleados(10).correoContacto("contacto@cliente2.com").build(),
                ClienteModel.builder().id(3L).nombre("Nombre 3").direccion("Direccion 3").numeroEmpleados(10).correoContacto("contacto@cliente3.com").build()
        );

        given(clienteService.obtenClientes()).willReturn(clientes);

        mockMvc.perform(get("/cliente/getAllClientes")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].correoContacto", is("contacto@cliente1.com")))
                .andExpect(jsonPath("$[2].nombre", is("Nombre 3")))

                .andDo(document("cliente/get-clientes",
                        pathParameters(
                                parameterWithName("id").description("Identificador del cliente"),
                                parameterWithName("nombre").description("Nombre del cliente"),
                                parameterWithName("correoContacto").description("Correo de contacto del cliente"),
                                parameterWithName("numeroEmpleados").description("Número de trabajadores del cliente"),
                                parameterWithName("direccion").description()
                        ),
                        responseFields(
                                fieldWithPath("id").description("Identificador del cliente"),
                                fieldWithPath("nombre").description("Nombre del cliente"),
                                fieldWithPath("correoContacto").description("Correo de contacto del cliente"),
                                fieldWithPath("numeroEmpleados").description("Número de trabajadores del cliente"),
                                fieldWithPath("direccion").description("Domicilio del cliente")
                        )));
    }*/

    @Test
    void creaCliente() throws Exception {
        ClienteModel clienteParametro = ClienteModel.builder().nombre("Nombre").direccion("Direccion").numeroEmpleados(10).correoContacto("contacto@cliente.com").build();
        ClienteModel clienteRespuesta = ClienteModel.builder().id(1L).nombre("Nombre").direccion("Direccion").numeroEmpleados(10).correoContacto("contacto@cliente.com").build();

        given(clienteService.guardaCliente(clienteParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/cliente/addCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isCreated())

                .andDo(document("cliente/crea-cliente",
                        pathParameters(
                                parameterWithName("nombre").description("Nombre del cliente"),
                                parameterWithName("direccion").description("Direccion del cliente"),
                                parameterWithName("numeroEmpleados").description("Numero de empleados del cliente"),
                                parameterWithName("correoContacto").description("Correo de contacto del cliente")
                        )));
    }

    @Test
    void actualizaCliente() throws Exception {

        ClienteModel clienteParametro = ClienteModel.builder().id(1L).nombre("Nombre").direccion("Direccion").numeroEmpleados(10).correoContacto("contacto@cliente.com").build();

        mockMvc.perform(put("/cliente/edit/{clienteId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("cliente/actualiza-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        )));
    }

    @Test
    void eliminaCliente() throws Exception {
        mockMvc.perform(delete("/cliente/delete/{clienteId}")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("cliente/elimina-cliente",
                        pathParameters(
                                parameterWithName("clienteId").description("Identificador del cliente")
                        )));
    }
}
