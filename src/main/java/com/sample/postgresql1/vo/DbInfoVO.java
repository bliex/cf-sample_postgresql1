package com.sample.postgresql1.vo;

import java.util.List;

public class DbInfoVO {
	private String connectionId;
	
	private List variables;

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public List getVariables() {
		return variables;
	}

	public void setVariables(List variables) {
		this.variables = variables;
	}
}
