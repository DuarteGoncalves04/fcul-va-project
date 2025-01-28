# Buoyancy Simulation - An Artificial Life Project

This repository contains our implementation of the **Buoyancy Simulation** using the JAVA [Processing](https://processing.org/) Framework with the aid of [ControlP5](https://www.sojamo.de/libraries/controlP5/) Library. The project models the behavior of objects in air and water, simulating forces such as gravity, buoyancy, and drag.

---

## Table of Contents

1. [Overview](#overview)  
2. [Project Structure](#project-structure)  
3. [Requirements](#requirements)  
4. [Running the Project](#running-the-project) 
5. [Simulation Mechanics](#simulation-mechanics)  
6. [Author and Contact](#author-and-contact)  

---

## Overview
This project is a physics-based artificial life simulation that explores the dynamics of floating and sinking objects under buoyancy forces. It allows users to interact with virtual objects that have different physical properties, such as mass, radius, and density, affecting their movement in the simulated environment.

The key features of the project include:

- **Realistic Physics**: Objects behave according to Newtonian mechanics, reacting to gravity, water resistance, and buoyant forces.
- **Interactive Controls**: A GUI enables real-time modification of object parameters.
- **Agent-Based Simulation**: The system includes various agents representing different types of floating and sinking entities.
- **Processing Framework**: Uses the Processing 4 library for visualization and physics simulation.

---

## Project Structure

```
fcul-va-project/
├─ docs/
|   ├─ VA_ProjectPresentation.ppt // Presentation slides
|   └─ VA_ProjectReport.pdff      // Final report
├─ lib/
|   ├─ controlP5.jar   // GUI Control library
|   └─ core.jar        // Processing framework
├─ src/
│   ├─ Physics/
│   │   ├─ Air.java         // Air environment
│   │   ├─ Body.java        // Represents objects
│   │   ├─ BuoyancyApp.java // Main simulation controller
│   │   ├─ Fluid.java       // Liquid environment
│   │   ├─ Mover.java       // Object movement handler
│   │   ├─ SubPlot.java     // Handles plotting of simulation
│   │   ├─ Wall.java        // Ground collision boundaries
│   │   └─ Water.java       // Water physics and buoyancy logic
|   └─ setup/
│       ├─ IProcessingApp.java  // Interface for Processing applications
│       └─ ProcessingSetup.java // Main setup for the Processing sketch
└─ README.md // This file
```

---

## Requirements

- **Java 8+** (tested up to Java 11 or higher)
- **Processing 4.3.2** or compatible version  
  Download from: [Processing website](https://processing.org/download)
- **ControlP5 2.2.5** or compatible version
  Download from: [ControlP5 website](https://www.sojamo.de/libraries/controlP5/)

---

## Running the Project

### Option 1: Running via Processing IDE
1. Clone the repository:
```bash
git clone https://github.com/YourUsername/fcul-va-project.git
cd fcul-va-project
```

2. Open Processing and Load the Project:
- Open Processing IDE;
- Navigate to File > Open and select BuoyancyApp.pde inside the src/ directory;

3. Install Dependencies:
- Go to Sketch > Import Library > Add Library…
- Search for ControlP5 and install it.

4. Run the Simulation:
- Click the Run button in Processing.

### Option 2: Running via Terminal
1. Clone the repository:
```bash
git clone https://github.com/YourUsername/fcul-va-project.git
cd fcul-va-project
```
2. Compile the Java Files
Ensure you have Java installed and compile all source files:
```bash
javac -cp lib/processing-core.jar:lib/controlP5.jar -d bin $(find src -name "*.java")
```

3. Run the Simulation
After compilation, you can execute the main class:
```bash
java -cp bin:lib/processing-core.jar:lib/controlP5.jar physics.BuoyancyApp
```

---

## Simulation Mechanics
The simulation follows fundamental physics principles to determine the behavior of objects in different environments.

1. **Forces Applied to Objects** 
Each object in the simulation is subjected to multiple forces:

- **Gravity (*Fg*)**: Acts downward on all objects.
- **Buoyancy (*Fb*)**: Acts upwards when objects are in water, proportional to the displaced volume.
- **Drag (*Fd*)**: Resists motion, depending on the medium (air or water).

2. **Object Properties**

Each object in the simulation has:

- **Mass (*m*)**: Determines how much an object resists acceleration.
- **Radius (*r*)**: Affects volume displacement and buoyant force.
- **Density (*ρ*)**: Determines whether the object sinks or floats.

3. **Environments**

- **Air Zone**: Objects fall freely under gravity with minimal air resistance.
- **Water Zone**: Objects experience buoyancy and fluid resistance, leading to floating or sinking behaviors.

---

## Author and Contact

- **Henrique Matos** - [hanrique.matos@gmail.com](mailto:hanrique.matos@gmail.com)
- **Duarte Gonçalves** - [duarte.dapg@gmail.com](mailto:duarte.dapg@gmail.com)

For any questions or suggestions, feel free to open an issue or reach out by email. Pull requests to improve or extend the simulation are also welcome!
