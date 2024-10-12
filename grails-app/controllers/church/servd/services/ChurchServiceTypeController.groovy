package church.servd.services

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChurchServiceTypeController {

    ChurchServiceTypeService churchServiceTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def churchServiceTypes = churchServiceTypeService.list(params)
        def churchServiceTypeCount = churchServiceTypeService.count()

        request.withFormat {
            json { respond churchServiceTypes,contentType: 'application/json' , status: OK }
            '*' { respond churchServiceTypes, model: [churchServiceTypeCount: churchServiceTypeCount] }
        }
       
        }

    def show(ChurchServiceType churchServiceType) {
        if (churchServiceType == null) {
            notFound()
            return
        }
        respond churchServiceTypeService.get(churchServiceType.id)
    }

    def create() {
        respond new ChurchServiceType(params)
    }

    def save(ChurchServiceType churchServiceType) {
        if (churchServiceType == null) {
            notFound()
            return
        }

        try {
            churchServiceTypeService.save(churchServiceType)
        } catch (ValidationException e) {
            respond churchServiceType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'churchServiceType.label', default: 'ChurchServiceType'), churchServiceType.id])
                redirect churchServiceType
            }
            '*' { respond churchServiceType, [status: CREATED] }
        }
    }
    
    // THIS ACTION IS SOLEY FOR WHEN WE ARE ACCESSING VIA GRAILS FRONTEND(GSP)
    def edit(Long id) {
        respond churchServiceTypeService.get(id)
    }

    def update(ChurchServiceType churchServiceType) {
        if (churchServiceType == null) {
            notFound()
            return
        }

        try {
            churchServiceTypeService.save(churchServiceType)
        } catch (ValidationException e) {
            respond churchServiceType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'churchServiceType.label', default: 'ChurchServiceType'), churchServiceType.id])
                redirect churchServiceType
            }
            '*'{ respond churchServiceType, [status: OK] }
        }
    }

    def delete(ChurchServiceType churchServiceType) {
        if (churchServiceType == null) {
            notFound()
            return
        }

        churchServiceTypeService.delete(churchServiceType.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'churchServiceType.label', default: 'ChurchServiceType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
