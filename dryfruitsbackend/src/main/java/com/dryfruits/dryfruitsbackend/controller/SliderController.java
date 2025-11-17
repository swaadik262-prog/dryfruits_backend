package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.model.Slider;
import com.dryfruits.dryfruitsbackend.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slider")
public class SliderController {

    @Autowired
    private SliderService sliderService;

    @GetMapping("/getAll")
    public List<Slider> getAllSlider() {
        return sliderService.getAllSlider();
    }

    @PostMapping("/saveSlider")
    public void saveNewSlider(@RequestBody Slider slider) {
        sliderService.saveNewSlider(slider);
    }
}
