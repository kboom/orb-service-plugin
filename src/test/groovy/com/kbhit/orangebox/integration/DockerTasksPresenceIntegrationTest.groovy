package com.kbhit.orangebox.integration

import com.kbhit.orangebox.OrbServicePluginApi
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import static org.assertj.core.api.Assertions.assertThat

class DockerTasksPresenceIntegrationTest extends AbstractIntegrationTest {

    Project project

    def setup() {
        project = ProjectBuilder.builder().withProjectDir(projectDir).build()
    }

    def "Does recognize project properties registered under orb-service extension"() {
        given:
        applyPlugin(project)
        configureOrbService({
            name = 'Test Service'
            version = '1.0.1'
        })

        when:
        project.evaluate()

        then:
        assertThat(project.getExtensions().findByName('orbService')).isEqualToComparingOnlyGivenFields({
            name = 'Test Service'
            version = '1.0.1'
        })
    }

    def "Does create createDockerfile task when plugin is applied"() {
        given:
        applyPlugin(project)

        when:
        project.evaluate()

        then:
        project.tasks.findByName('createDockerfile')
    }

    def "Does create buildImage task when plugin is applied"() {
        given:
        applyPlugin(project)

        when:
        project.evaluate()

        then:
        project.tasks.findByName('buildImage')
    }

    def "Does create createContainer task when plugin is applied"() {
        given:
        applyPlugin(project)

        when:
        project.evaluate()

        then:
        project.tasks.findByName('createContainer')
    }

    def "Does create startContainer task when plugin is applied"() {
        given:
        applyPlugin(project)

        when:
        project.evaluate()

        then:
        project.tasks.findByName('startContainer')
    }


    def "Does create stopContainer task when plugin is applied"() {
        given:
        applyPlugin(project)

        when:
        project.evaluate()

        then:
        project.tasks.findByName('stopContainer')
    }

    private Object configureOrbService(closure) {
        project.with {
            orbService closure
        }
    }

    private static void applyPlugin(Project project) {
        project.apply(plugin: OrbServicePluginApi)
    }

}
