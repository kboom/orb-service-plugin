package com.kbhit.orangebox.tasks

import com.bmuschko.gradle.docker.response.ResponseHandler
import com.bmuschko.gradle.docker.tasks.container.DockerInspectContainer
import com.kbhit.orangebox.OrbServiceExtension
import org.gradle.api.Project
import org.gradle.api.Task

class GetContainerIpTaskCreator implements TaskCreator {

    OrbServiceExtension extension

    GetContainerIpTaskCreator(OrbServiceExtension orbServiceExtension) {
        this.extension = orbServiceExtension
    }

    @Override
    Task createTaskFor(Project project) {
        project.task('getContainerIp', type: DockerInspectContainer, group: 'docker') {
            targetContainerId { extension.name }
            setResponseHandler createResponseHandler()
        }
    }

    def createResponseHandler() {
        new ResponseHandler<Void, Object>() {

            @Override
            Void handle(Object container) {
                println container.networkSettings.ipAddress
            }

        }
    }

}