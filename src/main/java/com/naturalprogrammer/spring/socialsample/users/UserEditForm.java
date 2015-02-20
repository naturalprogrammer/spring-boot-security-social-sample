package com.naturalprogrammer.spring.socialsample.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserEditForm {
	
	@NotNull
	@Size(min=1, max=User.NAME_MAX, message="{nameSizeError}")
	private String name = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
