pipeline {
    agent any


    stages {
        stage('Checkout') {
            steps {
                echo '📥 Clonando repositorio...'
                checkout scm
            }
        }

        stage('Compilar') {
            steps {
                echo '⚙️ Compilando proyecto...'
                script {
                    if (isUnix()) {
                        sh './gradlew clean compileJava'
                    } else {
                        bat '.\\gradlew.bat clean compileJava'
                    }
                }
            }
        }

        stage('Ejecutar pruebas Serenity') {
            steps {
                echo '🧪 Ejecutando pruebas automatizadas...'
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    script {
                        if (isUnix()) {
                            sh './gradlew clean test aggregate'
                        } else {
                            bat '.\\gradlew.bat clean test aggregate'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo '📊 Publicando reporte Serenity...'
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'build/serenity',
                reportFiles: 'index.html',
                reportName: 'Reporte Serenity'
            ])
        }
        success {
            echo '✅ Pipeline completado con éxito'
        }
        unstable {
            echo '⚠️ El pipeline terminó con pruebas fallidas. Revisar reporte Serenity.'
        }
        failure {
            echo '❌ El pipeline ha fallado por un error crítico.'
        }
    }
}
