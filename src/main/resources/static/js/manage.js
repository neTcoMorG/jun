
function auto_scrollDown() {
    const preview = document.querySelector(".preview");
    preview.scrollTop = preview.scrollHeight;
}

function dragstart_handler(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
    ev.effectAllowed = "copyMove";
}

function dragover_handler(ev) {
    ev.preventDefault();
}

function drop_handler(ev) {
    ev.preventDefault();

    const id = ev.dataTransfer.getData("text");
    const nodeCopy = document.getElementById("r-" + id).cloneNode(true);
    const formRoot = document.querySelector("#hidden-form");
    const formCopy = document.getElementById("file").cloneNode(true);


    nodeCopy.id = uuidv4();
    nodeCopy.classList.remove("hidden");

    formCopy.id = "file-" + nodeCopy.id;

    ev.target.appendChild(nodeCopy);
    formRoot.appendChild(formCopy);

    auto_scrollDown();
}
   
function dragend_handler(ev) {
     ev.dataTransfer.clearData();
}

function uuidv4() {
    return 'xxxxxxxx'.replace(/[xy]/g, function(c) {
      let r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
}

function clickHandler(e) {
    e.preventDefault();
    document.querySelector("#file-" + e.target.id).click();
}

function loadImage(input) {
    const target = document.getElementById(input.id.split("-")[1]);

    if (input.files && input.files[0]) {
        let render = new FileReader();
        render.onload = (e) => {
           target.innerHTML = "";
            const img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            target.appendChild(img);
            target.style.border = "1px solid black;";
            target.classList.add("img-attach");
        }
        render.readAsDataURL(input.files[0]);
    }
}