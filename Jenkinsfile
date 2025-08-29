pipeline {
    agent any

    stages {
        stage('Preparar entorno') {
            steps {
                echo '🔧 Preparando entorno de compilación...'
                script {
                    if (isUnix()) {
                        sh 'chmod +x gradlew'
                    } else {
                        bat 'echo Preparando entorno en Windows...'
                    }
                }
            }
        }

        stage('Compilar proyecto') {
            steps {
                echo '⚙️ Compilando proyecto con Gradle...'
                script {
                    if (isUnix()) {
                        sh './gradlew clean build -x test'
                    } else {
                        bat 'gradlew.bat clean build -x test'
                    }
                }
            }
        }

        stage('Ejecutar pruebas') {
            steps {
                echo '🧪 Ejecutando pruebas unitarias...'
                script {
                    if (isUnix()) {
                        sh './gradlew test'
                    } else {
                        bat 'gradlew.bat test'
                    }
                }
            }
        }

        stage('Publicar artefactos') {
            steps {
                echo '📦 Publicando artefactos generados...'
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completado con éxito'
        }
        failure {
            echo '❌ El pipeline ha fallado. Revisar logs.'
        }
    }
}
