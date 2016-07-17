package com.kbhit.orangebox.config

import org.gradle.api.Project


abstract class ProjectConfigurationBuilder {

    private final Project project;

    public ProjectConfigurationBuilder(Project project) {
        this.project = project;
    }

    public final void build() {
        preConfigure();
        configure(project);
        project.afterEvaluate(this.&postConfigure)
    }

    final preConfigure() {
        createExtensions(project)
    }

    final postConfigure() {
        processExtensions(project)
        configureTasks(project)
    }

    abstract protected configure(Project project)

    abstract protected createExtensions(Project project)

    abstract protected processExtensions(Project project)

    abstract protected configureTasks(Project project)

}
