package emailuniquifier

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class HomeControllerSpec extends Specification implements ControllerUnitTest<HomeController> {

    def homeController;
    def emailList = ["test.email@gmail.com", "test.email+spam@gmail.com", "testemail@gmail.com"];
    def aDifferentEmail = "notAnEmailNoSirree@somethingelse.com"
    def setup() {
        homeController = new HomeController()
    }

    def cleanup() {
    }

    void "test processEmail"() {
        given:
        def (userName, domain) = homeController.processEmail(emailList[1])

        expect:
        userName == "testemail"
        domain == "gmail.com"
    }

    void "test compareEmails"() {
        given:
        def result = homeController.compareEmails(emailList[0], emailList[1])

        expect:
        result // Don't we all?
    }

    void "test compareEmailFail"() {
        given:
        def match = homeController.compareEmails(emailList[0], aDifferentEmail)

        expect:
        !match
    }
}
