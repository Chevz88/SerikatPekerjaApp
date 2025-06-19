@echo off
:: Gradle wrapper for Windows

set DIR=%~dp0
set CLASSPATH=%DIR%gradle\wrapper\gradle-wrapper.jar

java -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
