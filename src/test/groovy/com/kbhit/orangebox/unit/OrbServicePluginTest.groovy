package com.kbhit.orangebox.unit

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat;


class OrbServicePluginTest {

    @Test
    public void pluginConfiguresCreateDockerfileTask() {
        Project project = ProjectBuilder.builder().build()
        project.buildDir.createNewFile()
        project.apply plugin: 'orb-service'
        project.configure project, {
            orbService {
                name = 'Test Service'
            }
        }

        def createDockerfileTask = project.getTasksByName("createDockerfile", false).first()
        assertThat(createDockerfileTask).isEqualToComparingOnlyGivenFields({
            from = 'java:8u91'
        })
    }

}
