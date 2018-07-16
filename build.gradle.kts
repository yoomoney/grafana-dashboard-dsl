import ru.yandex.money.gradle.plugins.library.dependencies.CheckDependenciesPluginExtension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

val skipCheckMutation by extra { true }

apply {
    from("https://jenkins-dev.yamoney.ru/build-scripts/backend-platform/build-common-without-plugins.gradle")
    from("https://jenkins-dev.yamoney.ru/build-scripts/backend-platform/git-stale-branches.gradle")
    plugin("yamoney-library-project-plugin")
    plugin("java")
}

val kotlinVersion by project.extra { "1.2.50" }
// FindBugs отключен, потому что плохо дружит с Kotlin
val findbugsEnabled by project.extra { false }
// Вместо CheckStyle используем KtLint
val checkstyleEnabled by project.extra { false }

apply {
    from("https://jenkins-dev.yamoney.ru/build-scripts/backend-platform/build-kotlin.gradle")
}

val groupIdSuffix by extra { "common" }
val artifactID by extra { "yamoney-grafana-kotlin-dsl" }

dependencies {
    "testCompile"("ru.yandex.money.common:yamoney-test-utils:19.9.1")
    "testCompile"("ru.yandex.money.common:yamoney-kotlin-test-utils:1.2.3")
}

buildscript {
    repositories {
        maven("https://nexus.yamoney.ru/content/repositories/thirdparty/")
        maven("https://nexus.yamoney.ru/content/repositories/central/")
        maven("https://nexus.yamoney.ru/content/repositories/releases/")
        maven("https://nexus.yamoney.ru/content/repositories/public/")

        dependencies {
            classpath("net.researchgate:gradle-release:2.3.0")
            classpath("ru.yandex.money.gradle.plugins:yamoney-library-project-plugin:1.1.0")
            classpath(mapOf("group" to "ru.yandex.money.platform", "name" to "yamoney-libraries-dependencies", "version" to "2.+", "ext" to "zip"))
            classpath("ru.yandex.money.gradle.plugins:yamoney-check-dependencies-plugin:2.+")
        }
    }
}

configure<DependencyManagementExtension> {
    // Запрещаем переопределять версии библиотек в обычной секции Gradle dependencies
    overriddenByDependencies(false)

    // Фиксируем версии библиотек
    imports {
        mavenBom("ru.yandex.money.platform:yamoney-libraries-dependencies:2.+")
    }
}

// Исключаем из проверки согласованности версий библиотек следующие конфигурации:
configure<CheckDependenciesPluginExtension> {
    excludedConfigurations = listOf(
            "apiElements",
            "runtimeElements",
            "implementation",
            "testImplementation",
            "integrationTestImplementation",
            "slowTestImplementation",
            "integrationTestRuntimeOnly",
            "slowTestRuntimeOnly",
            "testRuntimeOnly",
            "runtimeOnly",
            "checkstyle",
            "errorprone",
            "optional",
            "findbugs"
    )
}
