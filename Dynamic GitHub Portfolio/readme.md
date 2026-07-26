I like the direction, but I would make one important change.

This is your **Tutorials** repository, so the README should explain **what you learned**, not just present the finished code. Since your coding journals are all about understanding *why* things work, I'd organize it like this:

---

# Dynamic Portfolio (Tutorial Version)

## Project Overview

This project follows a YouTube tutorial to build a responsive developer portfolio that dynamically loads GitHub repositories using the GitHub API.

Rather than simply recreating the tutorial, this version serves as a learning project where I document how HTML, CSS, and JavaScript work together to create a dynamic website.

---

## Original Tutorial

**Dynamic Portfolio Tutorial**

*(Add the original tutorial link here.)*

---

## Follow-up Tutorial

### Embedding a Google Doc into a Webpage

[https://youtu.be/YAOqVmEu8CQ?is=S-_64ZS9tP7FyFtZ](https://youtu.be/YAOqVmEu8CQ?is=S-_64ZS9tP7FyFtZ)

### Goal

Display my résumé without uploading a PDF every time I update it.

This will allow my portfolio to always display the most recent version of my résumé directly from Google Docs.

---

# Project Architecture

This project is intentionally separated into three files to reinforce separation of concerns.

---

## 1. HTML — The Structural Skeleton (`index.html`)

The HTML defines the semantic structure of the webpage.

Responsibilities include:

* Navigation bar
* Hero section
* Project section
* Container for dynamically generated GitHub project cards
* Linking external CSS
* Loading JavaScript after the page has rendered

*(Insert your HTML code block here.)*

---

## 2. CSS — The Visual Rulebook (`style.css`)

The CSS controls the appearance of the website.

Responsibilities include:

* Color palette
* Typography
* Responsive layouts
* Grid system
* Project cards
* Navigation styling
* Hover effects

*(Insert your CSS code block here.)*

---

## 3. JavaScript — The Dynamic Data Engine (`app.js`)

The JavaScript communicates with GitHub's REST API.

Responsibilities include:

* Requesting repository data
* Parsing JSON
* Filtering repositories
* Creating project cards dynamically
* Injecting content into the DOM
* Handling API errors

*(Insert your JavaScript code block here.)*

---

# What I Learned

* Separating structure, presentation, and behavior into different files.
* Using the Fetch API to communicate with GitHub.
* Parsing JSON responses.
* Dynamically creating HTML elements using JavaScript.
* Updating the DOM instead of hardcoding project cards.
* Building responsive layouts using CSS Grid.
* Styling with CSS custom properties (`:root` variables).

---

# Future Improvements

* Embed my résumé from Google Docs.
* Create an Aero Glass résumé panel.
* Improve accessibility.
* Add project filtering.
* Expand project information.
* Improve mobile responsiveness.

---

# Repository Purpose

This repository documents my learning process.

My production portfolio will evolve independently, while this project remains a record of the concepts, experiments, and techniques I learned along the way.

---

One more suggestion: **don't include the entire source code in the README.**

GitHub already displays `index.html`, `style.css`, and `app.js`. Instead, use the README to explain **why** each file exists and what you learned from it. That makes the README much easier to read and demonstrates your understanding, while anyone interested can click into the source files themselves. I think that approach aligns well with how you've been using your coding journals: documenting reasoning rather than duplicating the code.
