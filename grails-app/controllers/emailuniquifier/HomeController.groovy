package emailuniquifier

import grails.converters.JSON

class HomeController {
    static allowedMethods = [index:'POST']

    def processEmail(email) {
        def s = email.split('@')
        s[0] = s[0].replaceAll("\\.", "")
        if(s[0].indexOf('+') > -1)
            s[0] = s[0].substring(0, s[0].indexOf('+'))
        return s;
    }

    def compareEmails(String email1, String email2) {
        def (user1, suff1) = processEmail(email1)
        def (user2, suff2) = processEmail(email2)
        if(suff1 != suff2) // different domains, not equal
            return false
        if(user1==user2)
            return true
        return false
    }

    def uniqueEmailComparator = {e1, e2 ->
        compareEmails(e1, e2) ? 0 : e1 <=> e2
    } as Comparator

    def index() {
        try {
            if(!request.JSON.emails) {
                throw new Exception()
            }
            render request.JSON.emails.unique(uniqueEmailComparator).size()
        }
        catch(Exception ex) {
            response.status = 400
            render([error: 'Bad Data Received. Please ensure you have a properly formatted \'emails\' collection of valid email addresses.'] as JSON)
        }
    }

}
