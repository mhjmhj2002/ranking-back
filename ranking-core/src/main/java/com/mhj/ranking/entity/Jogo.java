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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Jogo.findById", query = "SELECT j FROM Jogo j WHERE j.id = :id")})
public class Jogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "id_equipe_um", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Equipe idEquipeUm;
    @JoinColumn(name = "id_equipe_dois", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Equipe idEquipeDois;   
    @JoinColumn(name = "id_placar_um", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Placar idPlacarUm;
    @JoinColumn(name = "id_placar_dois", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Placar idPlacarDois;
    @JoinColumn(name = "id_fase", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Fase idFase;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Grupo idGrupo;
    @JoinColumn(name = "id_torneio", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Torneio idTorneio; 
    @JoinColumn(name = "id_temporada", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Temporada idTemporada; 

    public Jogo() {
    }

    public Jogo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Temporada getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(Temporada idTemporada) {
		this.idTemporada = idTemporada;
	}

	public Placar getIdPlacarUm() {
		return idPlacarUm;
	}

	public void setIdPlacarUm(Placar idPlacarUm) {
		this.idPlacarUm = idPlacarUm;
	}

	public Placar getIdPlacarDois() {
		return idPlacarDois;
	}

	public void setIdPlacarDois(Placar idPlacarDois) {
		this.idPlacarDois = idPlacarDois;
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
