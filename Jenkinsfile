pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK_17', type: 'jdk' // Asegúrate de tener este JDK configurado en Jenkins
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('📥 Clonar repositorio') {
            steps {
                git 'https://github.com/tu-usuario/BibliotecaJava.git'
            }
        }

        stage('🔧 Compilar proyecto') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('🧪 Ejecutar pruebas') {
            steps {
                sh './gradlew test'
            }
        }

        stage('📦 Empaquetar aplicación') {
            steps {
                sh './gradlew jar'
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

        stage('🚀 Despliegue simulado') {
            steps {
                echo 'Desplegando la aplicación BibliotecaJava...'
                // Aquí podrías copiar el .jar a un servidor o ejecutar el programa
                // Ejemplo: sh 'java -jar build/libs/BibliotecaJava.jar'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline ejecutado con éxito.'
        }
        failure {
            echo '❌ Algo falló en el pipeline.'
        }
    }
}
