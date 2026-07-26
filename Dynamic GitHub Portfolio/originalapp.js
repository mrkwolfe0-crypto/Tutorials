// Configuration global parameter
const GITHUB_USERNAME = 'mrkwolfe0-crypto';

/**
 * Connects to GitHub API and updates DOM elements with active project objects
 */
async function loadLiveGitHubProjects() {
    // Locate our target hook in index.html
    const grid = document.getElementById('dynamic-project-grid');
    
    try {
        // 📡 Hit the live GitHub endpoint for user repos sorted by push dates
        const response = await fetch(`https://github.com{GITHUB_USERNAME}/repos?sort=pushed&per_page=30`);
        
        // Safety guard: if server errors out, throw error exception to catch block
        if (!response.ok) throw new Error('API communication error occurred');
        
        // Translate the raw byte packet response payload stream into usable JSON arrays
        const repos = await response.json();
        
        // 🧹 Sanitize: filter out forks and the portfolio webpage repo itself
        const validProjects = repos.filter(repo => !repo.fork && repo.name !== `${GITHUB_USERNAME}.github.io`);
        
        if (validProjects.length === 0) {
            grid.innerHTML = '<p class="loading-text">No open source repositories found.</p>';
            return;
        }

        grid.innerHTML = ''; // Wipe out the hardcoded loading text placeholder
        
        // Loop through valid projects array object data to generate DOM cards
        validProjects.forEach(repo => {
            // Create a semantic article shell element node
            const card = document.createElement('article');
            card.className = 'project-card';
            
            // Format programming string styles into human-readable spaced layout
            const humanReadableName = repo.name.replace(/[-_]/g, ' ');
            const primaryLanguage = repo.language || 'Config';
            
            // Build text markup templates and map corresponding data values dynamically
            card.innerHTML = `
                <div>
                    <h3>${humanReadableName}</h3>
                    <p>${repo.description || 'No direct system description documented in repository settings.'}</p>
                </div>
                <div>
                    <div class="tags">
                        <span class="tag">${primaryLanguage}</span>
                    </div>
                    <div class="card-links">
                        <a href="${repo.html_url}" target="_blank" rel="noopener noreferrer">GitHub Source →</a>
                    </div>
                </div>
            `;
            
            // Formally inject completed card element into web DOM container
            grid.appendChild(card);
        });
        
    } catch (error) {
        console.error('API Fail Trace:', error);
        grid.innerHTML = '<p style="color: #ef4444;">Failed to sync with live GitHub codebase profiles.</p>';
    }
}
