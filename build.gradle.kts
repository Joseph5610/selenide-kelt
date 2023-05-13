plugins {
    java
    kotlin("jvm") version "1.8.20"
    `maven-publish`
}

group = "org.strangeway"
version = "2.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("stdlib"))
    implementation("com.codeborne:selenide:6.14.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
        vendor.set(JvmVendorSpec.AZUL)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("Selenide Kelt")
                description.set("Extension functions to make Selenide tests with Kotlin awesome")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("jreznot")
                        name.set("Yuriy Artamonov")
                        email.set("jreznot@yandex.ru")
                    }
                }
            }
        }
    }
}