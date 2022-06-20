package com.example.propietarioservice.service.impl;

import com.example.propietarioservice.entity.City;
import com.example.propietarioservice.entity.Propietario;
import com.example.propietarioservice.repository.PropietarioRepository;
import com.example.propietarioservice.service.PropietarioService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImpl implements PropietarioService {

    @Autowired
    private final PropietarioRepository propietarioRepository;

    @Override
    public List<Propietario> ListAllPropietarios() {
        return propietarioRepository.findAll();
    }

    @Override
    public Propietario getPropietario(long id) {
        return propietarioRepository.findById(id).orElse(null);
    }

    @Override
    public Propietario createPropietario(Propietario propietario)
    {
        Propietario propietarioDB = propietarioRepository.findPropietarioById(propietario.getId());
        if(propietarioDB!=null)
        {
            return propietarioDB;
        }
        propietarioDB = propietarioRepository.save(propietario);
        return propietarioDB;
    }

    @Override
    public Propietario updatePropietario(Propietario propietario) {
        Propietario propietarioDB = getPropietario(propietario.getId());
        if (propietarioDB == null)
        {
            return null;
        }
        propietarioDB.setName(propietario.getName());
        propietarioDB.setLastname(propietario.getLastname());
        propietarioDB.setAddress(propietario.getAddress());
        propietarioDB.setPassword(propietario.getPassword());
        propietarioDB.setEmail(propietario.getEmail());
        propietarioDB.setDni(propietario.getDni());

        return propietarioRepository.save(propietarioDB);
    }

    @Override
    public Propietario deletePropietario(long id) {
        Propietario propietarioDB = getPropietario(id);
        if (propietarioDB == null)
        {
            return null;
        }
        return propietarioRepository.save(propietarioDB);
    }

    @Override
    public List<Propietario> findByCity(City city) {
        return propietarioRepository.findByCity(city);
    }
}
