/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhj.ranking.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author mhj
 */
@ToString
@Entity
@Table(name = "placar")
@Data
@NoArgsConstructor
@NamedQueries({ @NamedQuery(name = "Placar.findAll", query = "SELECT p FROM Placar p"),
		@NamedQuery(name = "Placar.findById", query = "SELECT p FROM Placar p WHERE p.id = :id"),
		@NamedQuery(name = "Placar.findByNome", query = "SELECT p FROM Placar p WHERE p.nome = :nome") })
public class Placar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Basic(optional = false)
	@Column(name = "nome")
	private Long nome;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Placar)) {
			return false;
		}
		Placar other = (Placar) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
