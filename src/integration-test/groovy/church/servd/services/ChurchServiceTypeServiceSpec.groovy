package church.servd.services

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChurchServiceTypeServiceSpec extends Specification {

    ChurchServiceTypeService churchServiceTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ChurchServiceType(...).save(flush: true, failOnError: true)
        //new ChurchServiceType(...).save(flush: true, failOnError: true)
        //ChurchServiceType churchServiceType = new ChurchServiceType(...).save(flush: true, failOnError: true)
        //new ChurchServiceType(...).save(flush: true, failOnError: true)
        //new ChurchServiceType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //churchServiceType.id
    }

    void "test get"() {
        setupData()

        expect:
        churchServiceTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ChurchServiceType> churchServiceTypeList = churchServiceTypeService.list(max: 2, offset: 2)

        then:
        churchServiceTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        churchServiceTypeService.count() == 5
    }

    void "test delete"() {
        Long churchServiceTypeId = setupData()

        expect:
        churchServiceTypeService.count() == 5

        when:
        churchServiceTypeService.delete(churchServiceTypeId)
        sessionFactory.currentSession.flush()

        then:
        churchServiceTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ChurchServiceType churchServiceType = new ChurchServiceType()
        churchServiceTypeService.save(churchServiceType)

        then:
        churchServiceType.id != null
    }
}
