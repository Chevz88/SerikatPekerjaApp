#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Set default gradle home
DEFAULT_GRADLE_HOME=".gradle"

APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`

# Locate the gradle wrapper jar
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Execute gradle
exec java -cp $CLASSPATH org.gradle.wrapper.GradleWrapperMain "$@"
