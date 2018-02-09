@Library('nextgen-jenkins-libraries') _
def  utils = new au.gov.nextgen.JenkinsUtils()

def mavenHome
def userInput
def k8sNamespace = 'qa'

node'',{

    stage "Checkout Project",{
        checkout scm
        mavenHome = tool name: '(Default)', type: 'maven'
    }

    stage "Tests Execution", {
        try{
            sh "'${mavenHome}/bin/mvn' test -Dcucumber.options=\"--tags ~@Pending --plugin json:cucumber.json\" "
        }catch(Exception e) {
            currentBuild.result = 'UNSTABLE'
            if (utils.isCD()){
                def subjectText = '[Jenkins] $JOB_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'
                def bodyText = 'Check console output at <a href="$BUILD_URL">$JOB_NAME [$BUILD_NUMBER]</a> to view the results.'
                emailext(
                        mimeType: 'text/html',
                        body: bodyText,
                        to: env.FUNCTIONAL_TESTS_EMAILS,
                        subject: subjectText
                )
            }
        }
    }

    stage "Publish Cucumber Report",{
        step([$class: 'CucumberReportPublisher', fileIncludePattern: 'cucumber.json', sortingMethod: 'NATURAL'])
    }
}