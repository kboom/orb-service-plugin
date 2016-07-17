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


    def "buildImage task runs successfully"() {
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

        def srcDir = new File(projectDir, 'src/main/java/test')
        srcDir.mkdirs()
        def mainClassFile = new File(srcDir, 'TestApplication.java')
        mainClassFile.createNewFile()
        mainClassFile << """
            package test;

            import org.springframework.context.annotation.Configuration;
            import org.springframework.boot.autoconfigure.SpringBootApplication;

            @SpringBootApplication
            @Configuration
            public class TestApplication {

                public static void main(String[] args) {
                    SpringApplication.run(StorageApplication.class, args);
                }

            }
        """

        when:
        build("buildImage")

        then:
        result.task(":buildImage").outcome == SUCCESS
    }


}
