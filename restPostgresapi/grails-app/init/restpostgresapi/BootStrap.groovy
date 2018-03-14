package restpostgresapi

import com.postgres.restapi.security.AppUser
import com.postgres.restapi.security.AppUserRole
import com.postgres.restapi.security.Role

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->
		
		//register JSON marshaller for Date
		grails.converters.JSON.registerObjectMarshaller(Date){
			return it?.format('MM/dd/yyyy')
		}
		//register JSON marshaller for UUID
		grails.converters.JSON.registerObjectMarshaller(UUID){
			return it?.toString()
		}
		
		def adminRole = new Role(authority:'ROLE_ADMIN').save()
		def userRole = new Role(authority:'ROLE_USER').save()

/*		def testUser = new AppUser(username:'test', password:springSecurityService.encodePassword("test")).save()
		def adminUser = new AppUser(username:'admin', password:springSecurityService.encodePassword("admin")).save()
*/
		def testUser = new AppUser(username:'test', password:'test').save()
		def adminUser = new AppUser(username:'admin', password:'admin').save()

		AppUserRole.create testUser, userRole
		AppUserRole.create adminUser, adminRole

		AppUserRole.withSession {
			it.flush()
			it.clear()
		}

		assert AppUser.count() == 2
		assert Role.count() == 2
		assert AppUserRole.count() == 2
		
    }
    def destroy = {
    }
}
