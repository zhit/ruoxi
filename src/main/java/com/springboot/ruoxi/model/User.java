package com.springboot.ruoxi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

  private int userid;
  private String username;
}
