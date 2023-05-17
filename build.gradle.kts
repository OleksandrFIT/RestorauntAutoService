
plugins {
	java
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.20-Beta"
}

group = "com.work"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.github.sirayan.genericdb-mongo:generic-db:0.0.3")
	implementation("org.apache.tomcat.embed:tomcat-embed-jasper:10.0.16")
	implementation("javax.servlet:jstl:1.2")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets {
	main {
		java.srcDirs("src/main/kotlin", "${buildDir.absolutePath}/generated/source/proto/main")
		resources.srcDir("src/main/resources")
	}
}
tasks.withType<Copy> {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
