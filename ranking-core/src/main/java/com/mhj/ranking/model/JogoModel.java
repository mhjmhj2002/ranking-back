package com.mhj.ranking.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JogoModel {
    private Long id;
    private int placarEquipeUm;
    private int placarEquipeDois;
    private EquipeModel idEquipeUm;
    private EquipeModel idEquipeDois;
    private FaseModel idFase;
    private GrupoModel idGrupo;
    private TorneioModel idTorneio;
}
