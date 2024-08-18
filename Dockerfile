# Start from the official Jenkins image
FROM jenkins/jenkins:lts

# Switch to the root user to install packages
USER root

# Install Java (if not already included) and Maven
RUN apt-get update && \
    apt-get install -y maven openjdk-17-jdk-headless && \
    apt-get clean && \
    jenkins-plugin-cli --plugins \
    github \
    maven-plugin

# Add initialization script
COPY init-groovy/* /usr/share/jenkins/ref/init.groovy.d/

# Set Java and Maven environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 \
    MAVEN_HOME=/usr/share/maven \
    PATH="$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"

# Switch back to the Jenkins user
USER jenkins

