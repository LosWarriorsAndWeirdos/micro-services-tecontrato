package com.example.propietarioservice.service;

import com.example.propietarioservice.entity.City;
import com.example.propietarioservice.entity.Propietario;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.List;

public interface PropietarioService {
    public List<Propietario> ListAllPropietarios();
    public Propietario getPropietario(long id);
    public Propietario createPropietario(Propietario propietario);
    public Propietario updatePropietario(Propietario propietario);
    public Propietario deletePropietario(long id);
    public List<Propietario> findByCity(City city);
}
