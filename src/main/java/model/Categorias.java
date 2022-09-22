package model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "tb_categorias")
@Entity
@Data

public class Categorias {
	@Id
	private int idcategoria;
	private String descripcion;

}
