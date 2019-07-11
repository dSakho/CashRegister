.PHONY := tools resolve compile up help

.DEFAULT_GOAL := help

export BREW ?= brew
export JAVA ?= java
export MAVEN ?= mvn

export PROJECT := $(PWD)
export BUILD := $(PWD)/target
export TEST_ROOT := $(PWD)/src/test

help: ## Lists useful commands. Other commands may be available, but are not generally useful for most purposes. Read the Makefile for these additional commands.
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

tools: ## Way to inspect which tools you require upon grabnbing this repo
	@which $(BREW) >> /dev/null || (echo "Homebrew is not installed; please install it:\n    brew install 'https://docs.brew.sh/Installation'" && exit 1)
	@which $(JAVA) >> /dev/null || (echo "Java is not installed; please install it:\n    brew tap caskroom/cask\nrew tap caskroom/versions\nbrew cask install java8" && exit 1)
	@which $(MAVEN) >> /dev/null || (echo "Maven is not installed; please install it:\n    brew install maven" && exit 1)


clean: ## Removes the JAR from the target directory
	rm $(BUILD)/cash-register-api-0.0.1.jar

resolve: ## Resolve the applications dependencies
	mvn dependency:resolve

compile: clean resolve ## Builds the source files and creates a JAR
	mvn package
	
up: ## Runs the application on localhost
	java -jar $(BUILD)/cash-register-api-0.0.1.jar

