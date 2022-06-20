package com.example.plumbingservice.client;

import com.example.plumbingservice.model.Propietario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="propietario-service", fallback = PropietarioHystrixFallbackFactory.class)
public interface PropietarioClient {
    @GetMapping(value = "/propietarios/{id}")
    public ResponseEntity<Propietario> getPropietarios(@PathVariable("id") long id);
}
