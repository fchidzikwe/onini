package com.fortune.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "timedbill")
public class TimedBill {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "starttime")
  private Date startTime;

  @Column(name = "endtime")
  private Date endTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
}
