# Phase 1 — Webapp Boilerplate Implementation Context

## Phase Title and Purpose
**Phase 1: Webapp Boilerplate**
Establish the initial framework-free boilerplate project structure, build system, backend minimal entry point, frontend placeholder files, and database placeholder file.

## Starting State / Assumptions
- An empty directory (`C:\Users\SHIVADARRSHNI\OneDrive\Desktop\Webapp`).
- Intended stack: HTML/CSS/Vanilla JS frontend, Plain Java backend, Gradle build, MySQL database.
- Explicit restriction: No web frameworks (Spring Boot, etc.), no authentication implementations during Phase 1.

## Final Outcome
- Created the directory structure separating frontend, backend, and database.
- Initialized a minimal Java backend managed by Gradle.
- Implemented a standard Java application entry point.

## Chronological Implementation Journey
1. Created base directories and Gradle configuration files (`build.gradle`, `settings.gradle`).
2. TDD process started: created `AppTest.java` expecting `App.getStatusMessage()` to return `"Backend is running"`.
3. Observed RED step: build failed because `App.java` and method did not exist.
4. Created `App.java` implementing the method.
5. Observed GREEN step: `gradle test` passed.
6. Attempted `gradle run`, but it failed due to a typo in `build.gradle` (`mainClass = 'cpm.webapp.App'` instead of `com.webapp.App`).
7. Corrected the typo.
8. Attempted `gradle run` again and hit a Windows/OneDrive file-locking issue. Resolved by stopping background daemons with `gradle --stop`.
9. Created frontend boilerplate.
10. Created `database/schema.sql` with comment placeholders and `.gitignore`.
11. Created `README.md`.

## Files Created
- `backend/settings.gradle`
- `backend/build.gradle`
- `backend/src/main/java/com/webapp/App.java`
- `backend/src/test/java/com/webapp/AppTest.java`
- `frontend/index.html`
- `frontend/css/style.css`
- `frontend/js/app.js`
- `database/schema.sql`
- `.gitignore`
- `README.md`
- `phase-01-boilerplate-context.md`

## History vs Repository Authority (Contradiction)
**Explicit Contradiction Documented:** The historical instruction for Phase 1 asserts that Registration features (such as `UserRepository`, HTTP endpoints, `PasswordUtil`, and HTML forms) were **not** implemented in Phase 1 and explicitly deferred. However, **the actual repository currently contains these features**. 

**What exists now in the repository:**
The repository contains a fully functional Registration flow, including:
- `backend/src/main/java/com/webapp/UserRepository.java`
- `backend/src/main/java/com/webapp/PasswordUtil.java`
- `backend/src/main/java/com/webapp/RegistrationRequest.java`
- `frontend/index.html` (containing a registration form)
- `frontend/js/app.js` (containing a `fetch` POST to `/register`)
- Dependencies in `build.gradle` for `mysql-connector-j`, `jbcrypt`, and `gson`.
- A Java HTTP server setup in `App.java` with a `/register` endpoint and CORS handling.

**Historical Intent:**
The intent of Phase 1 was strictly boilerplate, with all registration boundaries deferred. The existing registration code represents work that conceptually belongs to the next phase (Registration).

## Important Code and Configuration Behavior
- **Backend Application Entry Point:** `com.webapp.App.main()` now initializes a `com.sun.net.httpserver.HttpServer` on port 8080 and defines a `/register` context.
- **Frontend Entry Point:** `frontend/index.html` contains an HTML form. JavaScript intercepts the submit event to POST data to the backend.

## Gradle Configuration
- Plugins: `java`, `application`
- Main class: `com.webapp.App`
- Dependencies: JUnit 5, plus (found in repository) `mysql-connector-j`, `jbcrypt`, `gson`.
- Gradle Wrapper is **not** present in the repository.

## Commands and Operations That Mattered
- `gradle test` (from `backend/` dir): Verified the JUnit 5 setup.
- `gradle run` (from `backend/` dir): Verified execution of the main class.
- `gradle --stop`: Critical debugging step to release file locks on Windows/OneDrive.

## Tests / Builds / Runtime Verification
- **Verified:** `gradle test` passed for `AppTest.java` (and subsequent registration tests).
- **Verified:** `gradle run` successfully started the server on port 8080.
- **Verified:** Frontend successfully communicated with backend after fixing a missing `exchange.close()` CORS bug in `App.java`.

## Failures, Root Causes and Fixes
- **Typo in `build.gradle`:** The user initially misconfigured the `mainClass` as `cpm.webapp.App`. Gradle failed with `ClassNotFoundException`. Fixed by correcting it to `com.webapp.App`.
- **File Lock (Windows/OneDrive):** `gradle run` or `clean` failed with `IOException: Unable to delete directory` on the `build` folder. Root cause: Gradle daemons or background processes holding file locks. Fix: Used `gradle --stop` to kill background daemons, releasing the locks.
- **CORS/Network Issue:** The frontend initially logged `Error connecting to the server` due to a hanging preflight OPTIONS request. Fix: Added `exchange.close()` to the early return blocks in `App.java`.

## Git / GitHub / Commit Linkage
- Git is **not a repository** in this folder (`fatal: not a git repository`). The historical event claiming a GitHub push to `main` with "first commit" cannot be verified locally because the `.git` directory does not exist.

## Resume From Here
- **Current Project State:** The repository contains both the Phase 1 boilerplate and the complete Registration feature codebase (HTML form, HTTP server, JSON parsing, password hashing).
- **What is verified:** The backend server runs, the tests pass, and the frontend successfully sends POST requests to the backend which hashes passwords and returns a 201 Created status.
- **What remains unimplemented:** The database connection execution (`repo.saveUser(...)`) is commented out in `App.java`. There is no live MySQL database configured or connected. Registration validation (e.g., checking for duplicate emails) is not implemented.
- **Baseline for Next Phase:** Since the Registration code was already written, the next step should be configuring the actual JDBC connection (MySQL credentials) and executing the `saveUser` method to persist the data to the database. Be aware of the ongoing Windows/OneDrive file locking issues (`gradle --stop` is frequently needed).
