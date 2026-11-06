# 20MinTillDawn 🎮

This repository contains the source code for a 2D survival roguelike shooter, inspired by the popular game *20 Minutes Till Dawn*. Developed using **Java** and the **LibGDX** framework, this project demonstrates the practical application of Object-Oriented Programming (OOP) principles in game development.

The core gameplay loop challenges players to survive endless waves of enemies, utilizing a dynamic upgrade system to enhance abilities and devise survival strategies.

## 🛠️ Tech Stack & Concepts
*   **Language:** Java (17+)
*   **Framework:** [LibGDX](https://libgdx.com/)
*   **Key Concepts:** Object-Oriented Programming (OOP), Game Loop Architecture, Collision Detection, Entity Management, State Management.

## 🚀 Key Features
*   **Dynamic Gameplay:** Smooth 2D rendering and mechanics powered by LibGDX.
*   **Endless Hordes:** An escalating wave system featuring diverse enemy types with unique AI behaviors.
*   **Progression System:** Players collect experience orbs to level up, unlocking strategic upgrades (e.g., damage multipliers, fire rate enhancements, health boosts, and movement speed increases).
*   **Visual Effects:** Implementation of simple particle systems and fluid sprite animations.
*   **Tracking:** Integrated survival timer and scoring system to monitor player performance.
*   **User Authentication:** Includes a full pre-game flow with Signup, Login, and Guest modes.

## 📸 Showcases

<div align="center">
  <img src="Screenshot 2026-11-06 142016.png" width="45%" alt="Signup Menu">
  <img src="Screenshot 2026-11-06 142119.png" width="45%" alt="Main Menu">
</div>
<br>
<div align="center">
  <img src="Screenshot 2026-11-06 142133.png" width="45%" alt="Pre-Game Hero Selection">
  <img src="Screenshot 2026-11-06 142204.png" width="45%" alt="Early Gameplay">
</div>
<br>
<div align="center">
  <img src="Screenshot 2026-11-06 142306.png" width="45%" alt="Level Up Upgrade Screen">
  <img src="Screenshot 2026-11-06 142327.png" width="45%" alt="Intense Wave Survival">
</div>

## ⚙️ How to Build and Run

### Prerequisites
*   Java Development Kit (JDK) 17 or higher.
*   Gradle (Alternatively, use the provided Gradle wrapper).

### Instructions
1.  Clone this repository:
    ```bash
    git clone [https://github.com/shayan24soley85/20MinTillDawn.git](https://github.com/shayan24soley85/20MinTillDawn.git)
    cd 20MinTillDawn
    ```
2.  **Run the game directly:**
    ```bash
    ./gradlew desktop:run
    ```
3.  **Build a standalone executable JAR:**
    ```bash
    ./gradlew desktop:dist
    ```
    *The generated `.jar` file will be located in the `desktop/build/libs/` directory.*

## 🕹️ Controls
*   **Movement:** `W`, `A`, `S`, `D`
*   **Aiming:** Mouse Cursor
*   **Shooting:** Left Mouse Button (Hold for continuous fire)
*   **Pause/Exit:** `Esc`
