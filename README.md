# CSC2002S_2024
CSC2002S Assignments 2024

Abelian Sandpile Simulation
This repository contains a parallelized Abelian Sandpile simulation using the Fork/Join framework in Java. The simulation includes both serial and parallel implementations.
# Directory Structure
PCP_ParallelAssignment2024/
│
├── src/                         # Source code directory
│   ├── serialAbelianSandpile/   # Package for serial code
│   │   ├── AutomatonSimulation.java
│   │   ├── Grid.java
│   └── parallelAbelianSandpile/ # Package for parallel code
│       ├── ParallelAutomatonSimulation.java
│       ├── ParallelGrid.java
│
├── bin/                         # Compiled class files
│   ├── serialAbelianSandpile/ 
│   └── parallelAbelianSandpile/
│    
├── input/                       # Input files for testing
│   ├── 65_by_65_all_4.csv
│   ├── 517_by_517_centre_534578.csv
│
├── output/                      # Output files (images, etc.)
│
├── Makefile                     # Makefile for compilation and execution
└── README.md                    # This instruction guide

# Prerequisites
Make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- GNU Make

# Compilation and Execution
Follow these steps to compile and run the simulation:
1. Clone the repository:

  ~git clone https://github.com/yourusername/PCP_ParallelAssignment2024.git
  ~cd PCP_ParallelAssignment2024

2. Navigate to the project directory:

   ~cd PCP_ParallelAssignment2024

3. Clean the previous builds:
   This will remove any previously compiled class files.

  ~make clean

4. Compile the project:
   This will compile all the Java source files in both the serial and parallel directories.
   
  ~make

6. Run the serial simulation:
   This will run the serial version of the Abelian Sandpile simulation using the default input and output files.

  ~make run-serial

7. Run the parallel simulation:
   This will run the parallel version of the Abelian Sandpile simulation. Update the Makefile to add a target for running the parallel version if needed.

  ~make run-parallel
  
# Makefile Targets
- 'all': Compiles all Java source files.
- 'clean': Removes all compiled class files.
- 'run-serial': Runs the serial version of the simulation with default arguments.
- 'run-parallel': Runs the parallel version of the simulation with default arguments (if configured).

# Custom Arguments
To run the simulation with custom input and output files, use the 'ARGS' variable:

~make ARGS="input/your_input.csv output/your_output.png" run
~make ARGS="input/your_input.csv output/your_output.png" run-parallel

# Notes
- Ensure the input files are in the input directory.
- The output images will be generated in the output directory.

  
