package com.tcs.bankclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	@Getter @Setter
	private boolean success;
	
	@Getter @Setter
	private String result;
	
	

}
