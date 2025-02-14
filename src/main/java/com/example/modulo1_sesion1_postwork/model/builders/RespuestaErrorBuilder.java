package com.example.modulo1_sesion1_postwork.model.builders;

import com.example.modulo1_sesion1_postwork.model.RespuestaError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class RespuestaErrorBuilder {
    private int estatus;
    private String mensaje;
    private Map<String, String> errores;
    private String ruta;

    public RespuestaErrorBuilder estatus(int estatus) {
        this.estatus = estatus;
        return this;
    }

    public RespuestaErrorBuilder status(HttpStatus estatus) {
        this.estatus = estatus.value();

        if (estatus.isError()) {
            this.errores.put("error", estatus.getReasonPhrase());
        }

        return this;
    }

    public RespuestaErrorBuilder errores(Map<String, String> error) {
        this.errores = errores;
        return this;
    }

    public RespuestaErrorBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }
    public RespuestaErrorBuilder exception(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.estatus = status.value();

        errores = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });

        return this;
    }

    public RespuestaErrorBuilder ruta(String ruta) {
        this.ruta = ruta;
        return this;
    }

    public RespuestaError build() {
        RespuestaError respuesta = new RespuestaError();
        respuesta.setEstatus(estatus);
        respuesta.setMensaje(mensaje);
        respuesta.setErrores(errores);
        respuesta.setRuta(ruta);
        return respuesta;
    }

    public ResponseEntity<RespuestaError> entidad() {
        return ResponseEntity.status(estatus).headers(HttpHeaders.EMPTY).body(build());
    }
}
