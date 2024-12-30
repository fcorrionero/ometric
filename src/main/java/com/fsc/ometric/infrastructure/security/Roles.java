package com.fsc.ometric.infrastructure.security;

public enum Roles {
  ADMIN("admin"), USER("user");

  private final String roleName;

  Roles(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleName() {
    return roleName;
  }
}
