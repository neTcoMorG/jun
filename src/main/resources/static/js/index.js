

const loc = document.querySelector(".article-content");
const btn = document.querySelector('.intro-btn');

btn.addEventListener('click', function() {
    loc.scrollIntoView({behavior: 'smooth', block: 'center'});
})

