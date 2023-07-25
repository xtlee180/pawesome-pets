package com.pawsomepets.controller;

import com.pawsomepets.service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PetControllerTesting {

    @Autowired
    private PetServiceImpl petService;
}
