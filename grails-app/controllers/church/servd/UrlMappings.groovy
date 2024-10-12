package church.servd

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/serviceTypes"(resources: 'churchServiceType')
        "/api/services"(resources: 'churchService')


        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
