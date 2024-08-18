import jenkins.model.*
import hudson.model.*
import hudson.tools.*

def instance = Jenkins.getInstance()
def jdkDesc = instance.getDescriptorByType(hudson.model.JDK.DescriptorImpl.class)

def installations = []
def javaHome = new File("/usr/lib/jvm/java-17-openjdk-amd64") // Replace with your JDK path

if (javaHome.exists()) {
    def jdk = new hudson.model.JDK("JDK 17", javaHome.getAbsolutePath())
    installations += jdk
}

jdkDesc.setInstallations(installations.toArray(new hudson.model.JDK[0]))
jdkDesc.save()
