APP_NAME=cellautomat
BUILD_DIR=build
BIN_DIR=bin
PACK_NAME=com


.PHONY: all clean

all: clean
	@mkdir $(BIN_DIR)
	@echo "Compile com package ..."
	@javac -d $(BIN_DIR) ./$(PACK_NAME)/*
	@mkdir $(BUILD_DIR)
	@echo "Create jar-archive ..."
	@jar -cmf manifest.mf $(APP_NAME).jar -C ./$(BIN_DIR) .
	@mv $(APP_NAME).jar $(BUILD_DIR)/$(APP_NAME).jar
	@echo "\nResult is $(BUILD_DIR)/$(APP_NAME).jar"
	@echo "Compile done"

clean:
	@rm -rf $(BIN_DIR) $(BUILD_DIR)
