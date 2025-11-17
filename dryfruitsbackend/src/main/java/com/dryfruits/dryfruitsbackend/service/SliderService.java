package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.model.Slider;
import com.dryfruits.dryfruitsbackend.repository.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SliderService {

    @Autowired
    private SliderRepository sliderRepository;

    public List<Slider> getAllSlider() {
        return sliderRepository.findAll();
    }

    public void saveNewSlider(Slider slider) {
        sliderRepository.save(slider);
    }
}