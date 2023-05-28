package com.mycompany.project;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answers")
public class answer {

	@Id
	@Column(name = "ans_id")
	private int id;


	@Column(name = "ans_text")
	private String ans;


	public answer() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAns() {
		return ans;
	}


	public void setAns(String ans) {
		this.ans = ans;
	}


}
