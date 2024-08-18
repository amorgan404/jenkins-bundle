import jenkins.model.*
import hudson.tools.*

// Set Maven home
def instance = Jenkins.getInstance()
def mavenDesc = instance.getDescriptorByType(hudson.tasks.Maven.DescriptorImpl.class)

def installations = []
def mavenHome = new File("/usr/share/maven")

if (mavenHome.exists()) {
    def maven = new hudson.tasks.Maven.MavenInstallation("Maven", mavenHome.getAbsolutePath(), [])
    installations += maven
}

mavenDesc.setInstallations(installations.toArray(new hudson.tasks.Maven.MavenInstallation[0]))
mavenDesc.save()
