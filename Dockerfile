FROM adoptopenjdk/openjdk11
LABEL maintainer="geekymon2@gmail.com"
ARG ARTIFACT_NAME
EXPOSE 8080
ADD target/${ARTIFACT_NAME}*.jar ${ARTIFACT_NAME}.jar
COPY entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
ENTRYPOINT ["/bin/bash", "./entrypoint.sh"]