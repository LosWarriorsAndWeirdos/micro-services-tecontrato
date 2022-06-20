package com.example.project.client;

import com.example.project.model.Budget;
import com.example.project.model.Contractor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="contractor-service")
public interface ContractorClient {
    @RequestMapping("/contractors/{id}")
    public ResponseEntity<Contractor> getContractor(@PathVariable("id") long id);

}
