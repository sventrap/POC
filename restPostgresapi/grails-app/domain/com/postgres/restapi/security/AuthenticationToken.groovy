package com.postgres.restapi.security

class AuthenticationToken {

	String tokenValue
	String username
 
	static mapping = {
		version false
	}
 
    static constraints = {
    }
}
