package io.github.aplotnikov.junit5_vs_spock.entities

import spock.lang.IgnoreIf
import spock.lang.Requires
import spock.lang.See
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ClientWithTestConditionsSpec extends Specification {

    @Shared
    String clientFirstName = 'Andrii'

    @Shared
    String clientSecondName = 'Plotnikov'

    @Subject
    Client client = new Client(clientFirstName, clientSecondName, ['test@gmail.com', 'test2@gmail.com'])

    @See('spock.util.environment.OperatingSystem')
    @Requires({ os.windows })
    void 'test should be ignored when OS is not Windows'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }

    @See('spock.util.environment.OperatingSystem')
    @Requires({ os.linux })
    void 'test should be ignored when OS is not Linux'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }

    @See('spock.util.environment.OperatingSystem')
    @Requires({ os.macOs })
    void 'test should be ignored when OS is not Mac OS'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }

    @See('spock.util.environment.OperatingSystem')
    @IgnoreIf({ os.windows })
    void 'test should be ignored when OS is Windows'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }

    @See('spock.util.environment.OperatingSystem')
    @IgnoreIf({ os.linux })
    void 'test should be ignored when OS is Linux'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }

    @See('spock.util.environment.OperatingSystem')
    @IgnoreIf({ os.macOs })
    void 'test should be ignored when OS is Mac OS'() {
        expect:
            with(client) {
                firstName == clientFirstName
                secondName == clientSecondName
            }
    }
}
