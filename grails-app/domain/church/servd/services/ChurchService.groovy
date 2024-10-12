package church.servd.services

class ChurchService {
    String title
    Date startTime
    Date endTime
    Integer capacity
    ChurchServiceType serviceType
    static belongsTo = [serviceType: ChurchServiceType]

    static constraints = {
        title blank: false, maxSize: 200
        startTime nullable: false
        endTime nullable: false
        serviceType nullable: false
        capacity nullable: false, min: 1
    }

}

// TODO add validator end time to be after start time
