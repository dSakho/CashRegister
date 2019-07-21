.PHONY := tools resolve compile up help

.DEFAULT_GOAL := help


export GIT ?= git
export BREW ?= brew
export JAVA ?= java
export MAVEN ?= mvn

export PROJECT := $(PWD)
export SERVER_CONF := $(PROJECT)/src/main/resources/server/config.yml
export BUILD := $(PWD)/target
export TEST_ROOT := $(PWD)/src/test

export JAR_NAME := cash-register-api-0.0.1.jar

help: ## Lists useful commands. Other commands may be available, but are not generally useful for most purposes. Read the Makefile for these additional commands.
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

tools: ## Way to inspect which tools you require upon grabing this repo
	@which $(GIT) >> /dev/null || (echo "Git is not installed; please install it:\n 'https://www.atlassian.com/git/tutorials/install-git'" && exit 1)
	@which $(BREW) >> /dev/null || (echo "Homebrew is not installed; please install it:\n    brew install 'https://docs.brew.sh/Installation'" && exit 1)
	@which $(JAVA) >> /dev/null || (echo "Java is not installed; please install it:\n    brew tap caskroom/cask\nrew tap caskroom/versions\nbrew cask install java8" && exit 1)
	@which $(MAVEN) >> /dev/null || (echo "Maven is not installed; please install it:\n    brew install maven" && exit 1)

clean: ## Removes the JAR from the target directory
	rm -f $(BUILD)/cash-register-api-0.0.1.jar

resolve: ## Resolve the applications dependencies
	mvn dependency:resolve

compile: clean resolve ## Builds the source files and creates a JAR
	mvn package
	
server: ## Runs the application as an HTTP Server. This command requires a configuration file
	java -jar $(BUILD)/$(JAR_NAME) server $(SERVER_CONF)
	
version: ## Displays the current version of the application
	java -jar $(BUILD)/$(JAR_NAME) --version