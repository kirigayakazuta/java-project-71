build:
	cd app && ./gradlew installDist

run-dist:
	cd app && ./build/install/app/bin/app

test:
	cd app && ./gradlew test

linter:
	cd app && ./gradlew checkstyleMain

test-report:
	cd app && ./gradlew jacocoTestReport

clean:
	cd app && ./gradlew clean

.PHONY: build