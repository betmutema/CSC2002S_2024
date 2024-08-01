# Makefile for compiling and running both serial and parallel programs

# Directories
SERIAL_SRC_DIR = src/serialAbelianSandpile
PARALLEL_SRC_DIR = src/parallelAbelianSandpile
SERIAL_BIN_DIR = bin/serialAbelianSandpile
PARALLEL_BIN_DIR = bin/parallelAbelianSandpile

# Source files
SERIAL_JAVA_FILES = $(wildcard $(SERIAL_SRC_DIR)/*.java)
PARALLEL_JAVA_FILES = $(wildcard $(PARALLEL_SRC_DIR)/*.java)

# Compiled class files
SERIAL_CLASS_FILES = $(patsubst $(SERIAL_SRC_DIR)/%.java, $(SERIAL_BIN_DIR)/%.class, $(SERIAL_JAVA_FILES))
PARALLEL_CLASS_FILES = $(patsubst $(PARALLEL_SRC_DIR)/%.java, $(PARALLEL_BIN_DIR)/%.class, $(PARALLEL_JAVA_FILES))

# Compilation flags
JAVAC_FLAGS = -d bin -sourcepath src

# Main classes
SERIAL_MAIN_CLASS = serialAbelianSandpile.AutomatonSimulation
PARALLEL_MAIN_CLASS = parallelAbelianSandpile.ParallelAutomatonSimulation

# Default arguments (update these if needed)
ARGS ?= input/65_by_65_all_4.csv output/65_by_65_all_4.png

# Targets
.PHONY: all clean run-serial run-parallel directories

all: directories serial parallel

directories:
	@mkdir -p $(SERIAL_BIN_DIR)
	@mkdir -p $(PARALLEL_BIN_DIR)

serial: $(SERIAL_CLASS_FILES)

parallel: $(PARALLEL_CLASS_FILES)

$(SERIAL_BIN_DIR)/%.class: $(SERIAL_SRC_DIR)/%.java
	javac $(JAVAC_FLAGS) $<

$(PARALLEL_BIN_DIR)/%.class: $(PARALLEL_SRC_DIR)/%.java
	javac $(JAVAC_FLAGS) $<

clean:
	rm -rf bin/*

run-serial: all
	java -classpath bin $(SERIAL_MAIN_CLASS) $(ARGS)

run-parallel: all
	java -classpath bin $(PARALLEL_MAIN_CLASS) $(ARGS)
