pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK_17', type: 'jdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('📥 Clonar repositorio') {
            steps {
                echo '🔄 Clonando código desde GitHub...'
                git branch: 'main', url: 'https://github.com/alexfdez26/CursoJava.git'
            }
        }

        stage('🔧 Compilar proyecto') {
            steps {
                echo '⚙️ Iniciando compilación con Gradle...'
                sh './gradlew clean build -x test'
            }
        }

        stage('🧪 Ejecutar pruebas') {
            steps {
                echo '🧪 Corriendo pruebas unitarias...'
                sh './gradlew test'
            }
            post {
                always {
                    echo '📊 Publicando resultados de pruebas...'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('📦 Empaquetar aplicación') {
            steps {
                echo '📦 Generando archivo JAR...'
                sh './gradlew jar'
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

        stage('🚀 Despliegue simulado') {
            steps {
                echo '🚀 Simulando despliegue de BibliotecaJava...'
                // Ejemplo real:
                // sh 'java -jar build/libs/BibliotecaJava.jar'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline ejecutado con éxito.'
        }
        unstable {
            echo '⚠️ Pipeline finalizó con advertencias.'
        }
        failure {
            echo '❌ Algo falló en el pipeline.'
        }
    }
}
