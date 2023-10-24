def call(Map config = [:]) {
    
    emailext subject: "Build Successful - ${config.jobName}",
    body: "The Jenkins build was successful from Jenkinsfile - ${config.buildNumber}",
    to: "${config.receiver}",
    from: "${config.sender}"
}
