def call(body){
    subject = "[Jenkins] Error running ${JOB_BASE_NAME}"
    recipientProviders = [[$class: 'DevelopersRecipientProvider'],
                          [$class: 'RequesterRecipientProvider']]
    
    emailext (mimeType: 'text/html',
              subject: "${subject}",
              body: '''${SCRIPT, template="groovy-html.template"}''',
              recipientProviders: recipientProviders,
              from: "Jenkins <admin@test.com>")
}