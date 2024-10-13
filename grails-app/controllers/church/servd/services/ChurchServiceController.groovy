package church.servd.services

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChurchServiceController {

    static responseFormats = ['json', 'xml']
    ChurchServiceService churchServiceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def churchServices = churchServiceService.list(params)
        def churchServicesCount = churchServiceService.count()

        request.withFormat {
            json { respond churchServices,contentType: 'application/json' , status: OK }
            '*' { respond churchServices, model: [churchServicesCount: churchServicesCount] }
        }
 
        respond churchServiceService.list(params), model:[churchServiceCount: churchServiceService.count()]
    }

    def show(ChurchService churchService) {
        if (churchService == null) {
            notFound()
            return
        }
        respond churchServiceService.get(churchService.id)
    }

    def create() {
        respond new ChurchService(params)
    }

    def save(ChurchService churchService) {
        if (churchService == null) {
            notFound()
            return
        }

        try {
            churchServiceService.save(churchService)
        } catch (ValidationException e) {
            respond churchService.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'churchService.label', default: 'ChurchService'), churchService.id])
                redirect churchService
            }
            '*' { respond churchService, [status: CREATED] }
        }
    }

    // THIS ACTION IS SOLEY FOR WHEN WE ARE ACCESSING VIA GRAILS FRONTEND(GSP)
    def edit(Long id) {
        respond churchServiceService.get(id)
    }

    def update(ChurchService churchService) {
        if (churchService == null) {
            notFound()
            return
        }

        try {
            churchServiceService.save(churchService)
        } catch (ValidationException e) {
            respond churchService.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'churchService.label', default: 'ChurchService'), churchService.id])
                redirect churchService
            }
            '*'{ respond churchService, [status: OK] }
        }
    }

    def delete(ChurchService churchService) {
        if (churchService == null) {
            notFound()
            return
        }

        churchServiceService.delete(churchService.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'churchService.label', default: 'ChurchService'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            json { render status: NOT_FOUND, contentType: 'application/json', text: '{"error": "ChurchService not found"}' }
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'churchService.label', default: 'ChurchService'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
