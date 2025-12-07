document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('blog-form');
    const generateBtn = document.getElementById('generate-btn');
    const resultContainer = document.getElementById('result-container');
    const blogContent = document.getElementById('blog-content');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        // animate button state
        generateBtn.disabled = true;
        generateBtn.classList.add('loading');

        // hide previous results
        resultContainer.classList.remove('visible');
        blogContent.innerHTML = '';

        const topic = document.getElementById('topic').value;
        const size = document.getElementById('size').value;

        try {
            const response = await fetch(`/creator/create?topic=${encodeURIComponent(topic)}&size=${encodeURIComponent(size)}`);

            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }

            const data = await response.text();

            // Just basic text formatting for now, could be enhanced with a markdown library later
            blogContent.textContent = data;

            // Show result
            resultContainer.classList.add('visible');

            // Smooth scroll to result
            resultContainer.scrollIntoView({ behavior: 'smooth', block: 'start' });

        } catch (error) {
            console.error('Failed to generate blog:', error);
            blogContent.innerHTML = `<p style="color: #ef4444;">Failed to generate blog post. Please try again. (${error.message})</p>`;
            resultContainer.classList.add('visible');
        } finally {
            generateBtn.disabled = false;
            generateBtn.classList.remove('loading');
        }
    });

    // Add subtle intersection observer for fade-in effect on load
    const cards = document.querySelectorAll('.card');
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, { threshold: 0.1 });

    cards.forEach(card => {
        // Initial state for animation
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'opacity 0.6s ease-out, transform 0.6s ease-out';
        observer.observe(card);
    });
});
