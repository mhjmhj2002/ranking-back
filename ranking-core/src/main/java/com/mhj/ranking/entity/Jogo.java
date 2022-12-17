/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhj.ranking.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mhj
 */
@Entity
@Table(name = "jogo")
@NamedQueries({
    @NamedQuery(name = "Jogo.findAll", query = "SELECT j FROM Jogo j"),
    @NamedQuery(name = "Jogo.findById", query = "SELECT j FROM Jogo j WHERE j.id = :id"),
    @NamedQuery(name = "Jogo.findByPlacarEquipeUm", query = "SELECT j FROM Jogo j WHERE j.placarEquipeUm = :placarEquipeUm"),
    @NamedQuery(name = "Jogo.findByPlacarEquipeDois", query = "SELECT j FROM Jogo j WHERE j.placarEquipeDois = :placarEquipeDois")})
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "placar_equipe_um")
    private int placarEquipeUm;
    @Basic(optional = false)
    @Column(name = "placar_equipe_dois")
    private int placarEquipeDois;
    @JoinColumn(name = "id_equipe_um", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipe idEquipeUm;
    @JoinColumn(name = "id_equipe_dois", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Equipe idEquipeDois;
    @JoinColumn(name = "id_fase", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fase idFase;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Grupo idGrupo;
    @JoinColumn(name = "id_torneio", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Torneio idTorneio;

    public Jogo() {
    }

    public Jogo(Integer id) {
        this.id = id;
    }

    public Jogo(Integer id, int placarEquipeUm, int placarEquipeDois) {
        this.id = id;
        this.placarEquipeUm = placarEquipeUm;
        this.placarEquipeDois = placarEquipeDois;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlacarEquipeUm() {
        return placarEquipeUm;
    }

    public void setPlacarEquipeUm(int placarEquipeUm) {
        this.placarEquipeUm = placarEquipeUm;
    }

    public int getPlacarEquipeDois() {
        return placarEquipeDois;
    }

    public void setPlacarEquipeDois(int placarEquipeDois) {
        this.placarEquipeDois = placarEquipeDois;
    }

    public Equipe getIdEquipeUm() {
        return idEquipeUm;
    }

    public void setIdEquipeUm(Equipe idEquipeUm) {
        this.idEquipeUm = idEquipeUm;
    }

    public Equipe getIdEquipeDois() {
        return idEquipeDois;
    }

    public void setIdEquipeDois(Equipe idEquipeDois) {
        this.idEquipeDois = idEquipeDois;
    }

    public Fase getIdFase() {
        return idFase;
    }

    public void setIdFase(Fase idFase) {
        this.idFase = idFase;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Torneio getIdTorneio() {
        return idTorneio;
    }

    public void setIdTorneio(Torneio idTorneio) {
        this.idTorneio = idTorneio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogo)) {
            return false;
        }
        Jogo other = (Jogo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhj.ranking.entity.Jogo[ id=" + id + " ]";
    }
    
}
