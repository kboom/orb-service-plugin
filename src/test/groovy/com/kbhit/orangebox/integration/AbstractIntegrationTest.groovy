package com.kbhit.orangebox.integration

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification


abstract class AbstractIntegrationTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()

    File projectDir

    def setup() {
        projectDir = temporaryFolder.root
    }

}
