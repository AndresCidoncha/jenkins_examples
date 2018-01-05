import groovy.json.JsonOutput

def call(message, channel = '#jenkins_notifications', bot_name = 'Jenkins Bot', bot_icon = ':jenkins:') {
    withCredentials([string(credentialsId: 'SlackBotToken', variable: 'SLACK_BOT_TOKEN')]) {
        def slackURL = "${SLACK_BOT_URL}/${SLACK_BOT_TOKEN}" // SLACK_BOT_URL is a global variable defined in jenkins configuration
    }

    def payload = JsonOutput.toJson([text     : message,
                                    channel   : channel,
                                    username  : bot_name,
                                    icon_emoji: bot_icon])

    echo "Sending slack notification to ${channel} channel"

    httpRequest (contentType: 'APPLICATION_JSON',
                 httpMode: 'POST',
                 quiet: true,
                 requestBody: "payload=${payload}",
                 responseHandle: 'NONE',
                 url: slackURL)
}
