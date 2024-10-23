FROM maven:3.9.6-ibm-semeru-17-focal as development

ARG UID=1000

ENV APP_HOME=/home/appuser/app \
    HOME=/home/appuser \
    LANG=C.UTF-8 \
    MAVEN_REPO=/home/appuser/.m2/repository

# Create user in container
RUN groupadd -r -g $UID appuser
RUN useradd -r -m -u $UID -g appuser appuser

USER appuser

WORKDIR $APP_HOME

COPY --chown=appuser:appuser --chmod=755 pom.xml ./

RUN --mount=type=cache,target=${MAVEN_REPO},uid=${UID},gid=${UID} mvn dependency:resolve

RUN --mount=type=cache,target=${MAVEN_REPO},uid=${UID},gid=${UID} mvn dependency:resolve-plugins

COPY --chown=appuser:appuser --chmod=755 . ./

RUN --mount=type=cache,target=${MAVEN_REPO},uid=${UID},gid=${UID} mvn -Dmaven.main.skip \
  -Dmaven.test.skip=true \
  -Dspring-boot.repackage.skip \
  package

CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments=\"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5001\""]
