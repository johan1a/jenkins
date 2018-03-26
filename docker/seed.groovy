import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this


['groovy-langserver', 'jenkins', 'syncer', 'haskell-go', 'bc-backend', 'bc-frontend'].each { project ->

  factory.pipelineJob("${project}") {
      triggers {
        scm('* * * * *')
      }

      definition {
          cpsScm {
              scm {
                  git {
                      remote {
                          github("johan1a/${project}", 'ssh')
                          credentials('github-user')
                          branch('master')
                      }
                      scriptPath("Jenkinsfile")
                  }
              }

          }
      }
  }

}
