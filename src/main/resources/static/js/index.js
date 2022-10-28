

const loc = document.querySelector(".article-content");
const btn = document.querySelector('.intro-btn');

btn.addEventListener('click', function() {
    loc.scrollIntoView({behavior: 'smooth', block: 'center'});
})

function modal_processor() {
    const modal = document.querySelector(".modal");
    const overlay = document.querySelector(".modal-overlay");
    
    modal.classList.remove("hidden");

    overlay.addEventListener('click', () => {
        modal.classList.add("hidden");
    });
}
