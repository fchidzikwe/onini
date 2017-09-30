package com.fortune.model;

import javax.persistence.*;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "posting_group")
public class PostingGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "groupname")
  private String group;

  @Column
  private String classification;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }
}
