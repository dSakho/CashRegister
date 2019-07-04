export PROJECT := $(PWD)
export BUILD := $(PWD)/target
export TEST_ROOT := $(PWD)/src/test


$(BUILD):
	mkdir -p $(BUILD)/bin

resolve:
	mvn dependency:resolve

install:
	mvn clean compile install

grab-sources:
	mvn eclipse:eclipse -DdownloadSources=true

build: resolve install grab-sources

up:
	java -jar target/cash-register-api-0.1.jar

help: ## Lists useful commands. Other commands may be available, but are not generally useful for most purposes. Read the Makefile for these additional commands.
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.DEFAULT_GOAL := help