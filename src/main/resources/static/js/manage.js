
let sizes = [];

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

    sizes.push(getSize(id));
    auto_scrollDown();
    console.log(sizes);
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

/*
    사이즈 배열과 사진을 전송하는거다 to 서버에

*/
function sendImage() {
    
}

function getSize(itemName) {
    switch(itemName) {
        case "item1":
            return {width: 1, height: 1};
        case "item2":
            return {width: 2, height: 1};
        case "item3":
            return {width: 1, height: 2};
        case "item4":
            return {width: 2, height: 2};
        case "item5":
            return {width: 3, height: 1};
        default:
            console.log("운지");
    }
}