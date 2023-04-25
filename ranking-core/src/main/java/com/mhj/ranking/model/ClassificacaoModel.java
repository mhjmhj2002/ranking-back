package com.mhj.ranking.model;

import com.mhj.ranking.entity.Equipe;
import com.mhj.ranking.entity.Temporada;
import com.mhj.ranking.entity.Torneio;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClassificacaoModel {

    private Long id;
    private Equipe idEquipe;
    private Temporada idTemporada;
    private Torneio idTorneio;

}
