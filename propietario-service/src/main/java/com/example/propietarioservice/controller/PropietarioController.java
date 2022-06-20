package com.example.propietarioservice.controller;

import com.example.propietarioservice.entity.City;
import com.example.propietarioservice.entity.Propietario;
import com.example.propietarioservice.service.PropietarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/propietarios")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public ResponseEntity<List<Propietario>> listAllPropietarios(@RequestParam(name = "cityId", required = false) Long cityId)
    {
        List<Propietario> propietarios = new ArrayList<>();

        if (cityId == null)
        {
            propietarios = propietarioService.ListAllPropietarios();

            if (propietarios.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }

        }
        else
        {
            City city = new City();
            city.setId(cityId);

            propietarios = propietarioService.findByCity(city);
            if(propietarios.isEmpty())
            {
                log.error("Propietarios con City id {} no encontrados", cityId);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Propietario> getPropietario(@PathVariable("id") Long id) {
        log.info("obteniendo el propietario con el id {}", id);
        Propietario propietario = propietarioService.getPropietario(id);
        if(propietario==null)
        {
            log.error("El propietario con el Id {} no fue encontrado", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(propietario);
    }

    @PostMapping
    public ResponseEntity<Propietario> createPropietario(@Valid @RequestBody Propietario propietario, BindingResult result)
    {
        log.info("Creando propietario {}", propietario);
        if (result.hasErrors())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Propietario propietarioDB = propietarioService.createPropietario(propietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(propietarioDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePropietario(@PathVariable("id") long id, @RequestBody Propietario propietario)
    {
        log.info("Actualizando propietario con id {}", id);
        Propietario currentPropietario = propietarioService.getPropietario(id);

        if (currentPropietario == null)
        {
            log.error("No se puede actualizar. Propietario con Id {} no fue encontrado", id);
            return ResponseEntity.notFound().build();
        }
        currentPropietario = propietarioService.updatePropietario(propietario);
        return ResponseEntity.ok(currentPropietario);
    }

    private String formatMessage(BindingResult result)
    {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
