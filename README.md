This repository is where I organize and catalog the programming tutorials I've completed, giving me a place to revisit, expand upon, and continue improving them over time.

Learning by building works best for me. Rather than simply following a tutorial once, I use each project as an opportunity to document what I've learned, experiment with new ideas, debug problems, and implement my own improvements beyond the original scope.

Original tutorial authors are credited throughout this repository. My goal isn't to memorize tutorials—it's to understand how they work well enough to modify, improve, and eventually build similar projects on my own.

I had ChatGPT quiz me on the code I answered and was rated on my level of awareness:
I like these answers because they're **your reasoning**, not textbook definitions. That's exactly how I want to do these reviews.

Here's how I'd grade them.

---

### **1. Why use `<ul>`?**

**Your answer:**

> Besides being how he decided to code it also allowed for further formatting actions later.

**Grade: 8.5/10**

You're on the right track.

The main reason is that a navigation menu is **a list of navigation items**. HTML provides `<ul>` specifically for unordered lists, so it is the semantic choice. Then CSS can easily style the entire list with Flexbox, spacing, hover effects, etc.

You recognized the styling benefit, which is an important part of why tutorials do it.

---

### **2. Why both `class="bx bx-menu"` and `id="menu"`?**

**Your answer:**

> Because bx bx I assume is the boxicon... id="menu" to allow #menu later.

**Grade: 10/10**

Exactly.

* `class="bx bx-menu"` tells Boxicons **which icon** to display.
* `id="menu"` gives **your JavaScript and CSS** something unique to select.

One is for the icon library.

One is for your code.

---

### **3. Why use `id="about"`?**

**Your answer:**

> To allow the #about to work when styling and calling it in the css.

**Grade: 9.5/10**

Correct.

There's one more benefit:

```html
<a href="#about">
```

scrolls directly to

```html
<section id="about">
```

So the ID is used by:

* CSS
* JavaScript
* Anchor navigation

You already recognized two of those.

---

### **4. Viewport meta tag**

**Your answer:**

> To auto adjust the screen to fill the screen of the display device.

**Grade: 10/10**

Perfect.

Without it, phones pretend they're much wider than they really are and then shrink the page to fit, which makes responsive layouts behave strangely.

---

### **5. Why put `<script>` at the bottom?**

**Your answer:**

> It calls and loads the javascript... then applies the configurations listed there.

**Grade: 8/10**

Correct, but there's an important reason **why** it goes at the bottom.

Imagine this:

The browser reads:

```html
<script src="app.js"></script>
```

before it has even reached

```html
<i id="menu">
```

JavaScript asks:

```javascript
document.querySelector("#menu")
```

The browser responds:

> "What menu? I haven't read that part of the page yet."

It returns `null`.

By putting the script at the bottom, the browser has already created all the HTML elements before JavaScript starts looking for them.

---

## Overall

I'd honestly give you about a **9.2/10**.

More importantly, your answers show something I've noticed over the past few weeks.

A month ago, you would usually answer:

> "Because that's what the tutorial did."

Now your answers sound like:

> "This exists so CSS can reference it."

> "This ID lets JavaScript find it."

> "The viewport adjusts to the device."

That's a shift from describing *what* the code is to explaining *why* it's there. That's the kind of understanding that carries over when you start building your own portfolio instead of following a tutorial.

