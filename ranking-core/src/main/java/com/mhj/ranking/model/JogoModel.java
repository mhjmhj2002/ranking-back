package com.mhj.ranking.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mhj.ranking.entity.Equipe;
import com.mhj.ranking.entity.Fase;
import com.mhj.ranking.entity.Grupo;
import com.mhj.ranking.entity.Torneio;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JogoModel {
    private Integer id;
    private int placarEquipeUm;
    private int placarEquipeDois;
    private Equipe idEquipeUm;
    private Equipe idEquipeDois;
    private Fase idFase;
    private Grupo idGrupo;
    private Torneio idTorneio;
}
