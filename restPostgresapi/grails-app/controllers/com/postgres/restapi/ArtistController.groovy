package com.postgres.restapi


import grails.rest.*
import grails.converters.*
import grails.plugin.springsecurity.annotation.Secured

class ArtistController extends RestfulController {
    static responseFormats = ['json', 'xml']
    ArtistController() {
        super(Artist)
    }
	
	@Secured('permitAll')
	@Override
	def index(Integer max) {
		super.index(max)
	}

	@Secured('ROLE_USER')
	@Override
	def show() {
		super.show()
	}

	@Secured('ROLE_ADMIN')
	@Override
	def save() {
		super.save()
	}
}
