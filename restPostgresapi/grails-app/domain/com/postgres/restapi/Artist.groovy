package com.postgres.restapi


import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*

@Resource(uri='/api/artists',readOnly = false, formats = ['hal+json','json', 'xml'])
@Secured(['ROLE_ADMIN'])
class Artist {
	UUID id
	String firstName
	String lastName
	Date dateCreated
	Date lastUpdated

	static constraints = {
		id type: 'pg-uuid', generator: 'uuid2'
		firstName blank: false
		lastName blank: false
	}
}