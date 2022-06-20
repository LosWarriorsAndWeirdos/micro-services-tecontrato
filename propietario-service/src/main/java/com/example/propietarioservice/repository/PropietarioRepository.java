package com.example.propietarioservice.repository;

import com.example.propietarioservice.entity.City;
import com.example.propietarioservice.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropietarioRepository extends JpaRepository<Propietario, Long> {
    public List<Propietario> findByCity(City city);
    public Propietario findPropietarioById(long Id);

}
