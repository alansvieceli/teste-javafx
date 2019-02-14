package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable {

	static final long serialVersionUID = -4866126035211761217L;
	
	private Integer id;
	private String nome;
	private String email;
	private Date dt_aniversario;
	private Double salario_base;
	
	private Departamento departamento;
	
	public Vendedor() {
	}

	public Vendedor(Integer id, String nome, String email, Date dt_aniversario, Double salario_base, Departamento departamento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dt_aniversario = dt_aniversario;
		this.salario_base = salario_base;
		this.departamento = departamento;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtAniversario() {
		return dt_aniversario;
	}

	public void setDtAniversario(Date dt_aniversario) {
		this.dt_aniversario = dt_aniversario;
	}

	public Double getSalarioBase() {
		return salario_base;
	}

	public void setSalarioBase(Double salario_base) {
		this.salario_base = salario_base;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", nome=" + nome + ", email=" + email + ", dt_aniversario=" + dt_aniversario + ", salario_base="
				+ salario_base + ", departamento=" + departamento + "]";
	}
}
