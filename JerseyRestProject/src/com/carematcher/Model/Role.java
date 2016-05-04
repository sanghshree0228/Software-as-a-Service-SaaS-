package com.carematcher.Model;

public enum Role {
	
	ServiceProvider(1), ServiceSeeker(2);
	
	private final int roleId;
	
	private Role(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public static Role getRole(int roleId) {
		Role returnRole = null;
		for (Role role : Role.values()) {
			if (role.getRoleId() == roleId) {
				returnRole = role;
			}
		}
		return returnRole;
	}
	
}
