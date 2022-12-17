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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mhj
 */
@Entity
@Table(name = "torneio")
@NamedQueries({
    @NamedQuery(name = "Torneio.findAll", query = "SELECT t FROM Torneio t"),
    @NamedQuery(name = "Torneio.findById", query = "SELECT t FROM Torneio t WHERE t.id = :id"),
    @NamedQuery(name = "Torneio.findByNome", query = "SELECT t FROM Torneio t WHERE t.nome = :nome")})
public class Torneio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTorneio", fetch = FetchType.LAZY)
    private List<Temporada> temporadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTorneio", fetch = FetchType.LAZY)
    private List<Classificacao> classificacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTorneio", fetch = FetchType.LAZY)
    private List<Jogo> jogoList;

    public Torneio() {
    }

    public Torneio(Integer id) {
        this.id = id;
    }

    public Torneio(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<Temporada> getTemporadaList() {
        return temporadaList;
    }

    public void setTemporadaList(List<Temporada> temporadaList) {
        this.temporadaList = temporadaList;
    }

    public List<Classificacao> getClassificacaoList() {
        return classificacaoList;
    }

    public void setClassificacaoList(List<Classificacao> classificacaoList) {
        this.classificacaoList = classificacaoList;
    }

    public List<Jogo> getJogoList() {
        return jogoList;
    }

    public void setJogoList(List<Jogo> jogoList) {
        this.jogoList = jogoList;
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
        if (!(object instanceof Torneio)) {
            return false;
        }
        Torneio other = (Torneio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhj.ranking.entity.Torneio[ id=" + id + " ]";
    }
    
}
