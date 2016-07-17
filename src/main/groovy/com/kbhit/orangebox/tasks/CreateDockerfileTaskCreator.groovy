package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class CreateDockerfileTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    CreateDockerfileTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('createDockerfile', type: Dockerfile) {
            destFile = project.file("${extension.buildDir}/Dockerfile")
            environmentVariable 'ORANGEBOX_CONFIG_SERVER_URI', extension.configServerUri
            environmentVariable 'ORANGEBOX_SERVICE_LOCATOR_SERVER_URI', extension.serviceLocatorUri
            from 'java:8u91'
            exposePort 8080
            copyFile 'app.jar', '/app/app.jar'
            workingDir '/app'
            defaultCommand 'java', '-jar', 'app.jar'
        }
    }

}
