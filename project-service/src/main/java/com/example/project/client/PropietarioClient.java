package com.example.project.client;

import com.example.project.model.Contractor;
import com.example.project.model.Propietario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="propietario-service", fallback = PropietarioHystrixFallbackFactory.class)
public interface PropietarioClient {
    @RequestMapping("/propietarios/{id}")
    public ResponseEntity<Propietario> getPropietario(@PathVariable("id") long id);
}
