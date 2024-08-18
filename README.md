# Dockerfile Description

This Dockerfile is based on the official Jenkins LTS image and is customized to include Java and Maven, along with some essential Jenkins plugins.

### Steps included in the Dockerfile:

- **Base Image**: The image starts from the official Jenkins LTS image.
- **oot Access**: Switches to the root user to allow the installation of necessary packages.
- **Package Installation**: Updates the package list and installs Maven and OpenJDK 17 (headless version). After installation, unnecessary files are cleaned up to reduce image size.
- **Jenkins Plugins**: Installs essential Jenkins plugins, including the GitHub and Maven plugins.
- **Initialization Script**: Copies Groovy initialization scripts to Jenkins's init.groovy.d directory, ensuring they are executed on startup.
- **Environment Variables**: Sets environment variables for Java and Maven, making them available globally in the container.
- **Switch Back to Jenkins User**: Finally, switches back to the Jenkins user to ensure that Jenkins runs under the correct user permissions.

This setup allows Jenkins to be fully configured with Java and Maven out of the box, ready to build and manage Java projects.
How to Build the Docker Image

#### To build the Docker image locally, follow these steps:

1. Clone the repository:
```
git clone https://github.com/amorgan404/jenkins-bundle.git
cd jenkins-bundle
```

2. Build the Docker image:
```
docker build -t my-jenkins .
```

3. Run the Docker container:
```
docker run -d -p 8080:8080 -p 50000:50000 \
-v jenkins_home:/var/jenkins_home \
my-jenkins
```

This command runs Jenkins on port 8080, with port 50000 open for Jenkins agents. The jenkins_home volume is used to persist Jenkins data.

#### How to Use the Docker Hub Image

If you prefer to pull the image from Docker Hub instead of building it locally, follow these steps:

Pull the Docker Hub image:

```
docker push morgan404/jenkins-bundle:latest
```

Run the Docker container:

```
docker run -d -p 8080:8080 -p 50000:50000 \
-v jenkins_home:/var/jenkins_home \
morgan404/jenkins-bundle:latest
```

This command will pull the image from Docker Hub and run it with the same configurations as described above.

#### Environment Variables

    JAVA_HOME: /usr/lib/jvm/java-17-openjdk-amd64
    MAVEN_HOME: /usr/share/maven
    PATH: Includes both JAVA_HOME and MAVEN_HOME binaries

#### Persistent Data

Jenkins data is stored in the /var/jenkins_home directory inside the container. It's recommended to use a Docker volume or bind mount to persist this data:

**Docker Volume**: -v jenkins_home:/var/jenkins_home

**Bind Mount**: -v /path/on/host:/var/jenkins_home

Using persistent storage ensures that your Jenkins data, including configurations, plugins, and job information, is retained across container restarts and upgrades.
