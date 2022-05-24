package com.example.springboot_grupparbete.Controllers;


import com.example.springboot_grupparbete.Models.Beställning;
import com.example.springboot_grupparbete.Models.Produkt;
import com.example.springboot_grupparbete.RabbitMQ.CustomMessage_V2;
import com.example.springboot_grupparbete.RabbitMQ.MQConfig_V2;
import com.example.springboot_grupparbete.Repositories.BeställningRepository;
import com.example.springboot_grupparbete.Repositories.ProduktRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path ="/produkt")
public class ProduktController {

    @Autowired
    private RabbitTemplate template;

    private final ProduktRepository produktRepository;
    private final BeställningRepository beställningRepository;


    public ProduktController(ProduktRepository produktRepository, BeställningRepository beställningRepository) {

        this.produktRepository = produktRepository;
        this.beställningRepository = beställningRepository;
    }

    //Använd koden nedan efter att vi har fått till frontend separat
    @GetMapping("")
    public Iterable<Produkt> getAllProdukter() {
        publishMessage();
        return produktRepository.findAll();
    }

    /*
    @GetMapping("")
    public String getAll(Model model){
        Iterable<Produkt> p=produktRepository.findAll();
        model.addAttribute("rubrik", "Alla Våra Produkter");
        model.addAttribute("allProducts", p);
        model.addAttribute("brandTitle", "Märke");
        model.addAttribute("colorTitle", "Färg");
        model.addAttribute("sizeTitle", "Storlek");
        model.addAttribute("priceTitle", "Pris");
        model.addAttribute("stock", "Lager");

        return "products";
    }*/

    @GetMapping("{id}")
    public Optional<Produkt> getProduktById(@PathVariable Long id) {
        return produktRepository.findById(id);
    }

    @RequestMapping("/get")
    public Produkt getById(@RequestParam Long id){
        return produktRepository.findById(id).get();
    }

    @PostMapping("/post")
    public Beställning postBeställning(@RequestBody Beställning b) {
        beställningRepository.save(b);
        return b;
    }


    public void publishMessage() {
        CustomMessage_V2 message = new CustomMessage_V2();
        message.setMessage("Beställning gjord!");
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig_V2.EXCHANGE, MQConfig_V2.ROUTING_KEY, message);
    }
}
