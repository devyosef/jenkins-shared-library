def renderTemplate(tempName, inputs) {
  def engine = new groovy.text.GStringTemplateEngine()
  def template = engine.createTemplate(tempName).make(inputs)
  return template.toString()
}

def call(Map config = [:]) {
  def tempName = libraryResource 'templates/successEmail.html'
  def inputs = [
    applicationName: env.JOB_NAME,
    buildNumber    : env.BUILD_NUMBER,
    buildUrl       : env.BUILD_URL,
    message        : config.message
  ]
  def emailBody = renderTemplate(tempName,inputs)

  // send to email
  /*emailext (
    subject: "${config.message}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
    body: emailBody,
    receiver: "${config.receiver}",  
    sender: "${config.sender}"
  )*/

   emailext subject: "${config.message}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
    body: emailBody,
    receiver: "${config.receiver}",  
    sender: "${config.sender}"
}
