export PROJECT := $(PWD)
export BUILD := $(PWD)/target
export TEST_ROOT := $(PWD)/src/test


$(BUILD):
	mkdir -p $(BUILD)/bin