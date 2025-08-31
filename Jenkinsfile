pipeline {
    agent any

      stages {
        stage('Checkout código') {
            steps {
                echo '📥 Clonando repositorio CursoJava...'
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']], // Cambia a '*/master' si tu rama principal es master
                    userRemoteConfigs: [[
                        url: 'https://github.com/alexfdez26/CursoJava.git'
                    ]]
                ])
            }
        }

        stage('Validar Gradle Wrapper') {
            steps {
                echo '🔍 Verificando gradlew...'
                script {
                    if (isUnix()) {
                        if (!fileExists('gradlew')) {
                            error '❌ No se encontró gradlew. Ejecuta: gradle wrapper'
                        } else {
                            sh 'chmod +x gradlew'
                            echo '✅ gradlew encontrado y permisos ajustados.'
                        }
                    } else {
                        if (!fileExists('gradlew.bat')) {
                            error '❌ No se encontró gradlew.bat. Ejecuta: gradle wrapper'
                        } else {
                            echo '✅ gradlew.bat encontrado.'
                        }
                    }
                }
            }
        }

        stage('Compilar proyecto') {
            steps {
                echo '⚙️ Compilando proyecto...'
                script {
                    if (isUnix()) {
                        sh './gradlew clean build -x test'
                    } else {
                        bat '.\\gradlew.bat clean build -x test'
                    }
                }
            }
        }

        stage('Ejecutar pruebas Serenity') {
            steps {
                echo '🧪 Ejecutando pruebas automatizadas...'
                script {
                    if (isUnix()) {
                        sh './gradlew clean test aggregate'
                    } else {
                        bat '.\\gradlew.bat clean test aggregate'
                    }
                }
            }
        }

        stage('Publicar Reporte Serenity') {
            steps {
                echo '📊 Publicando reporte Serenity...'
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'build/serenity', // Ajusta si tu ruta es distinta
                    reportFiles: 'index.html',
                    reportName: 'Reporte Serenity'
                ])
            }
        }

        stage('Publicar artefactos') {
            steps {
                echo '📦 Archivando artefactos...'
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completado con éxito'
        }
        failure {
            echo '❌ El pipeline ha fallado. Revisar logs y reporte Serenity.'
        }
    }
}
