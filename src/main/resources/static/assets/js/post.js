import Prism from 'prismjs';
import {debounce} from "lodash";
import {gsap} from 'gsap';

Prism.manual = true;
Prism.highlightAll()

const contentEl = document.getElementsByClassName("tui-editor-contents")[0];
const scrollMap = {};
let headingList = [...contentEl.childNodes].filter(i => {
    return (i.tagName ?? "").startsWith("H")
});
let headingIndexATagList = headingList.map(heading => {
    const id = heading.id;
    const depth = heading.tagName.substring(1);
    const yOffset = heading.getBoundingClientRect().y;
    scrollMap[yOffset] = id;
    const aTag = document.createElement("a");
    aTag.href = "#" + id;
    aTag.innerText = heading.innerText;
    aTag.classList.add("block", "pl-" + depth * 3, "py-0.5", "hover:bg-gray-100", "text-base", "cursor-pointer", id);
    document.getElementById("heading-index").appendChild(aTag)
    aTag.addEventListener("click", function(e){
        let href = this.getAttribute("href");
        let id = href.split("#")[1];
        document.getElementById(id).scrollIntoView();
        window.scrollBy(0,-200)
    })
    return aTag;
});
const oldOnScroll = debounce(function () {
    let current = "";
    headingList.forEach((heading) => {
        const sectionTop = heading.offsetTop;
        const sectionHeight = heading.clientHeight;
        if (window.scrollY >= sectionTop - sectionHeight / 3 - (116)) {
            current = heading.getAttribute("id");
        }
    });

    headingIndexATagList.forEach((li) => {
        li.classList.remove("bg-gray-300");
        if (li.classList.contains(current)) {
            li.classList.add("bg-gray-300");
        }
    });
}, 100);

document.addEventListener('scroll', oldOnScroll);

let publishedAt = document.getElementById("published-at")
gsap.to("#published-at",{
    scrollTrigger:{
        trigger:"#published-at",
        markers: false,
        start:"bottom 0",
        end:"bottom -200000",
        scrub: 0.5,

        toggleClass:"hidden"
    },

})

gsap.to("#og-image-wrapper", {
    scrollTrigger:{
        scrub:1,
        trigger:"#title-wrapper",
        markers:true,
        start:"bottom 0",
        end:"bottom -200"
    },
    opacity:0
})
