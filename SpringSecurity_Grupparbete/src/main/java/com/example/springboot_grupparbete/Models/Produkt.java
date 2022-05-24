package com.example.springboot_grupparbete.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marke;
    private String farg;
    private String storlek;
    private int pris;
    private int antalILager;

    @JsonIgnore
    @ManyToMany(mappedBy = "produkter")
    private Set<Beställning> beställning = new HashSet<>();

    public Produkt(){}

    public Produkt(String marke, String farg, String storlek, int pris, int antalILager) {
        this.marke = marke;
        this.farg = farg;
        this.storlek = storlek;
        this.pris = pris;
        this.antalILager = antalILager;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getFarg() {
        return farg;
    }

    public void setFarg(String farg) {
        this.farg = farg;
    }

    public String getStorlek() {
        return storlek;
    }

    public void setStorlek(String storlek) {
        this.storlek = storlek;
    }

    public int getAntalILager() {
        return antalILager;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setAntalILager(int antalILager) {
        this.antalILager = antalILager;
    }

    public Set<Beställning> getBeställning() {
        return beställning;
    }

    public Long getId() {
        return id;
    }
}
