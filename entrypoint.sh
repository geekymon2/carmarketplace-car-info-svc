echo "This is the entrypoint script executing jar file: $ARTIFACT_NAME"
echo "Image version: $VERSION"
java -jar "$ARTIFACT_NAME.jar"