package church.servd.services

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChurchServiceController {

    ChurchServiceService churchServiceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond churchServiceService.list(params), model:[churchServiceCount: churchServiceService.count()]
    }

    def show(Long id) {
        respond churchServiceService.get(id)
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

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        churchServiceService.delete(id)

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
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'churchService.label', default: 'ChurchService'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
