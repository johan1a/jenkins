import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

factory.pipelineJob("syncer - Jenkinsfile") {

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('johan1a/syncer', 'ssh')
                        credentials('github-user')
                        branch('master')
                    }
                    scriptPath("Jenkinsfile")
                }
            }

        }
    }
}

factory.pipelineJob("haskell-go") {

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('johan1a/haskell-go', 'ssh')
                        credentials('github-user')
                        branch('master')
                    }
                    scriptPath("Jenkinsfile")
                }
            }

        }
    }
}
