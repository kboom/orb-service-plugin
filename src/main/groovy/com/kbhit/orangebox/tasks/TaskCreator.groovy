package com.kbhit.orangebox.tasks

import org.gradle.api.Project
import org.gradle.api.Task

interface TaskCreator {

    Task createTaskFor(Project project)

}
