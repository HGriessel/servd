package church.servd.services

import grails.gorm.services.Service

@Service(ChurchServiceType)
interface ChurchServiceTypeService {

    ChurchServiceType get(Serializable id)

    List<ChurchServiceType> list(Map args)

    Long count()

    void delete(Serializable id)

    ChurchServiceType save(ChurchServiceType churchServiceType)

}