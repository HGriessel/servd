package church.servd.services

class ChurchServiceType {
    String name
    String description

    static constraints = {
        name blank: false, unique: true
        description nullable: true, maxSize: 500
    }

    String toString() {
        name
    }
}
