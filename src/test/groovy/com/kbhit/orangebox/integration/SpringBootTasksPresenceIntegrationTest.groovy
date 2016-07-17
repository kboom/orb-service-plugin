package com.kbhit.orangebox.integration

import com.kbhit.orangebox.OrbServicePluginApi
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by Grzegorz Gurgul on 14.07.2016.
 */
class SpringBootTasksPresenceIntegrationTest extends AbstractIntegrationTest {

    Project project

    def setup() {
        project = ProjectBuilder.builder().withProjectDir(projectDir).build()
    }

    def "Does recognize spring boot tasks"() {
        given:
        applyPlugin(project)
        configureOrbService({
            name = 'Test Service'
            version = '1.0.1'
        })

        when:
        project.evaluate()

        then:
        assertThat(project.getTasksByName('bootRepackage', false)).isNotEmpty()
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
