/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhj.ranking.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mhj
 */
@Entity
@Table(name = "temporada")
@NamedQueries({
    @NamedQuery(name = "Temporada.findAll", query = "SELECT t FROM Temporada t"),
    @NamedQuery(name = "Temporada.findById", query = "SELECT t FROM Temporada t WHERE t.id = :id"),
    @NamedQuery(name = "Temporada.findByNome", query = "SELECT t FROM Temporada t WHERE t.nome = :nome"),
    @NamedQuery(name = "Temporada.findByAnoInicio", query = "SELECT t FROM Temporada t WHERE t.anoInicio = :anoInicio"),
    @NamedQuery(name = "Temporada.findByAnoFim", query = "SELECT t FROM Temporada t WHERE t.anoFim = :anoFim")})
public class Temporada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "ano_inicio")
    private int anoInicio;
    @Basic(optional = false)
    @Column(name = "ano_fim")
    private int anoFim;
    @JoinColumn(name = "id_torneio", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Torneio idTorneio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTemporada", fetch = FetchType.LAZY)
    private List<Classificacao> classificacaoList;

    public Temporada() {
    }

    public Temporada(Integer id) {
        this.id = id;
    }

    public Temporada(Integer id, String nome, int anoInicio, int anoFim) {
        this.id = id;
        this.nome = nome;
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(int anoFim) {
        this.anoFim = anoFim;
    }

    public Torneio getIdTorneio() {
        return idTorneio;
    }

    public void setIdTorneio(Torneio idTorneio) {
        this.idTorneio = idTorneio;
    }

    public List<Classificacao> getClassificacaoList() {
        return classificacaoList;
    }

    public void setClassificacaoList(List<Classificacao> classificacaoList) {
        this.classificacaoList = classificacaoList;
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
        if (!(object instanceof Temporada)) {
            return false;
        }
        Temporada other = (Temporada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhj.ranking.entity.Temporada[ id=" + id + " ]";
    }
    
}
