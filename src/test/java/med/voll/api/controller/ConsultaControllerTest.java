package med.voll.api.controller;

import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.consulta.ReservaDeConsultas;
import med.voll.api.domain.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosReservaConsulta> datosReservaConsultaJson;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> datosDetalleConsultaJson;

    @MockBean
    private ReservaDeConsultas reservaDeConsultas;

    @Test
    @DisplayName("Debería retornar estado HTTP 400 cuando la request no tenga datos")
    @WithMockUser
    void reservar_escenario1() throws Exception{
        var response = mvc.perform(post("/consultas")) // 1. Dispara un POST vacío
                .andReturn().getResponse(); // 2. Atrapa la respuesta

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); // 3. ¿Es un 400?
    }

    @Test
    @DisplayName("Debería devolver HTTP 200 cuando la request reciba un JSON válido")
    @WithMockUser
    void reservar_escenario2() throws Exception {

        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DatosDetalleConsulta(null, 2L, 5L, fecha);

        // Configuración de Mockito para simular el servicio
        when(reservaDeConsultas.reservar(any())).thenReturn(datosDetalle);

        //Act
        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON) // "Oye, te mando un JSON"
                .content(datosReservaConsultaJson.write(
                        new DatosReservaConsulta(2L, 5L, fecha, especialidad)
                ).getJson()) // Convierte el objeto a texto JSON
        ).andReturn().getResponse();

        var jsonEsperado = datosDetalleConsultaJson.write(datosDetalle).getJson();

        // Assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}