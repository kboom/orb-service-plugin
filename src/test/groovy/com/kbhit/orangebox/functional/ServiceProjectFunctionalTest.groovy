package com.kbhit.orangebox.functional

import static org.assertj.core.api.Assertions.assertThat
import static org.gradle.testkit.runner.TaskOutcome.*;

class ServiceProjectFunctionalTest extends AbstractFunctionalTest {

    def "createDockerfile task runs successfully"() {
        given:
        buildFile << """
            plugins {
                id 'orb-service'
            }

            orbService {
                name = 'Test service'
                version = '1.0'
            }
        """

        when:
        def result = build("createDockerfile")

        then:
        result.task(":createDockerfile").outcome == SUCCESS
    }


    def "createDockerfile task creates 'Dockerfile' file in 'build/docker' directory"() {
        given:
        buildFile << """
            plugins {
                id 'orb-service'
            }

            orbService {
                name = 'Test service'
                version = '1.0'
            }
        """

        when:
        build("createDockerfile")

        then:
        assertThat(new File(projectDir, 'build/docker/Dockerfile')).exists()
    }

}
