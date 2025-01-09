package com.payvang.APIGateway.Model;

import java.util.Collection;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AuthResponse {
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String>authorities;
	
}