package church.servd.services

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.rest.*

class ChurchServiceTypeController extends RestfulController<ChurchServiceType> {
    static responseFormats = ['json', 'xml']
    ChurchServiceTypeController() {
        super(ChurchServiceType)
    }

    ChurchServiceTypeService churchServiceTypeService

    def show(ChurchServiceType  churchServiceType){
        if (churchServiceType == null) {
            notFound()
            return
        }
        respond churchServiceTypeService.get(churchServiceType.id)
 
    }

    protected void notFound() {
        request.withFormat {
            json { render status: NOT_FOUND, contentType: 'application/json', text: '{"error": "ChurchServiceType not found"}' }
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'churchServiceType.label', default: 'ChurchServiceType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
