package church.servd.services

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChurchServiceServiceSpec extends Specification {

    ChurchServiceService churchServiceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ChurchService(...).save(flush: true, failOnError: true)
        //new ChurchService(...).save(flush: true, failOnError: true)
        //ChurchService churchService = new ChurchService(...).save(flush: true, failOnError: true)
        //new ChurchService(...).save(flush: true, failOnError: true)
        //new ChurchService(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //churchService.id
    }

    void "test get"() {
        setupData()

        expect:
        churchServiceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ChurchService> churchServiceList = churchServiceService.list(max: 2, offset: 2)

        then:
        churchServiceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        churchServiceService.count() == 5
    }

    void "test delete"() {
        Long churchServiceId = setupData()

        expect:
        churchServiceService.count() == 5

        when:
        churchServiceService.delete(churchServiceId)
        sessionFactory.currentSession.flush()

        then:
        churchServiceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ChurchService churchService = new ChurchService()
        churchServiceService.save(churchService)

        then:
        churchService.id != null
    }
}
