package com.mhj.ranking.model;

import com.mhj.ranking.entity.Torneio;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TemporadaModel {
    private Integer id;
    private String nome;
    private int anoInicio;
    private int anoFim;
    private Torneio idTorneio;
}
