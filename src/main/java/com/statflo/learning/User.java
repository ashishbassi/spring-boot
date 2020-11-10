package com.statflo.learning;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {
	
	@Min(value = 1, message = "Id cannot be less than 0")
	private Integer id;
	
	@NotBlank(message = "userName cannot be blank")
	private String userName;


}
