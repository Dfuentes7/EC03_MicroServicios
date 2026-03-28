package com.example.Java3.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "computer-sales")
public interface InventoryFeign {

    @GetMapping("/api/computers")
    List<Computer> getComputers();
}
