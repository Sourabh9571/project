package com.mycompany.project;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Questions")
public class question {

	@Id
	@Column(name = "que_id")
    private int id;

	@Column(name = "que_text")
    private String que_text;

        
    @JoinColumn(name = "que_ans")
    @OneToMany(cascade = CascadeType.ALL)
    private List<answer> Opt_list;

    @Column(name = "yes_no")
    private int crr;

    public int getCrr() {
        return crr;
    }

    public void setCrr(int crr) {
        this.crr = crr;
    }
    
    public List<answer> getOpt_list() {
		return Opt_list;
	}


	public void setOpt_list(List<answer> opt_list) {
		Opt_list = opt_list;
	}


	public question() {
    	super();
    }


	public String getQue_text() {
		return que_text;
	}


	public void setQue_text(String que_text) {
		this.que_text = que_text;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
