package com.fortune.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "audittrial")
public class AuditTrial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long id;

  @OneToOne
  private  User loogedInUser;

  @Column(name = "loginedInTime")
  private Date loggedInTime;

  @Column(name = "loggedOutTime")
  private Date loggedOutTime;

}
