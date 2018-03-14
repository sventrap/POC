import com.postgres.restapi.security.AppUserPasswordEncoderListener
import grails.rest.render.hal.*

// Place your Spring DSL code here
beans = {
    appUserPasswordEncoderListener(AppUserPasswordEncoderListener, ref('hibernateDatastore'))
	halArtistRenderer(HalJsonRenderer, com.postgres.restapi.Artist)
	halArtistCollectionRenderer(HalJsonCollectionRenderer, com.postgres.restapi.Artist)
}
