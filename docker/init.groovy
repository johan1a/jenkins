import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.plugin.JenkinsJobManagement
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import hudson.model.*
import jenkins.model.*
import hudson.plugins.groovy.*
import net.sf.json.JSONObject
import javaposse.jobdsl.dsl.DslFactory
import jenkins.model.*
import hudson.security.*


def credentials = SystemCredentialsProvider.getInstance().getCredentials()
credentials.add(new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL,
                                  "github-user",
                                  "git",
                                  new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource("/var/jenkins_home/.ssh_host/id_rsa"),
                                  null,
                                  "github-user")
)
SystemCredentialsProvider.getInstance().save()


println "Configuring maximum security..."
println ""

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin","admin")
def instance = Jenkins.getInstance()
instance.setSecurityRealm(hudsonRealm)
instance.save()

println "Creating jobs..."
println ""

JobManagement jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))
File jobScript = new File('/usr/share/jenkins/seed.groovy')
new DslScriptLoader(jobManagement).with {

  runScript(jobScript.text)

}
